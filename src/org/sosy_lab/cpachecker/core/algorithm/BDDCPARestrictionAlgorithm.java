/*
 * IntPTI: integer error fixing by proper-type inference
 * Copyright (c) 2017.
 *
 * Open-source component:
 *
 * CPAchecker
 * Copyright (C) 2007-2014  Dirk Beyer
 *
 * Guava: Google Core Libraries for Java
 * Copyright (C) 2010-2006  Google
 *
 *
 */
package org.sosy_lab.cpachecker.core.algorithm;

import com.google.common.collect.ImmutableList;

import org.sosy_lab.common.configuration.Configuration;
import org.sosy_lab.common.configuration.FileOption;
import org.sosy_lab.common.configuration.FileOption.Type;
import org.sosy_lab.common.configuration.InvalidConfigurationException;
import org.sosy_lab.common.configuration.Option;
import org.sosy_lab.common.configuration.Options;
import org.sosy_lab.common.io.PathTemplate;
import org.sosy_lab.common.log.LogManager;
import org.sosy_lab.cpachecker.core.counterexample.CounterexampleInfo;
import org.sosy_lab.cpachecker.core.interfaces.AbstractState;
import org.sosy_lab.cpachecker.core.interfaces.ConfigurableProgramAnalysis;
import org.sosy_lab.cpachecker.core.interfaces.Statistics;
import org.sosy_lab.cpachecker.core.interfaces.StatisticsProvider;
import org.sosy_lab.cpachecker.core.interfaces.Targetable;
import org.sosy_lab.cpachecker.core.reachedset.ReachedSet;
import org.sosy_lab.cpachecker.cpa.arg.ARGCPA;
import org.sosy_lab.cpachecker.cpa.arg.ARGUtils;
import org.sosy_lab.cpachecker.cpa.bdd.BDDCPA;
import org.sosy_lab.cpachecker.cpa.bdd.BDDState;
import org.sosy_lab.cpachecker.exceptions.CPAException;
import org.sosy_lab.cpachecker.util.AbstractStates;
import org.sosy_lab.cpachecker.util.CPAs;
import org.sosy_lab.cpachecker.util.predicates.regions.NamedRegionManager;
import org.sosy_lab.cpachecker.util.predicates.regions.Region;

import java.util.Collection;
import java.util.logging.Level;

@Options(prefix = "counterexample")
public class BDDCPARestrictionAlgorithm implements Algorithm, StatisticsProvider {

  @Option(secure = true, description = "The files where the BDDCPARestrictionAlgorithm should write the presence conditions for the counterexamples to.")
  @FileOption(Type.OUTPUT_FILE)
  private PathTemplate presenceConditionFile =
      PathTemplate.ofFormatString("ErrorPath.%d.presenceCondition.txt");

  private final Algorithm algorithm;
  private final LogManager logger;
  private final ConfigurableProgramAnalysis cpa;

  private final NamedRegionManager manager;
  private Region errorSummary;

  public BDDCPARestrictionAlgorithm(
      Algorithm algorithm, ConfigurableProgramAnalysis pCpa,
      Configuration config, LogManager logger) throws InvalidConfigurationException {
    this.algorithm = algorithm;
    this.logger = logger;
    config.inject(this);

    BDDCPA bddCpa = CPAs.retrieveCPA(pCpa, BDDCPA.class);
    if (bddCpa == null) {
      throw new InvalidConfigurationException("BDD CPA needed for BDDCPARestrictionAlgorithm");
    }
    cpa = pCpa;
    logger.log(Level.INFO, "using the BDDCPA Restriction Algorithm");

    manager = bddCpa.getManager();
    errorSummary = manager.makeFalse();
  }

  @Override
  public AlgorithmStatus run(ReachedSet reached) throws CPAException, InterruptedException {
    AlgorithmStatus status = AlgorithmStatus.SOUND_AND_PRECISE;

    while (reached.hasWaitingState()) {
      status = status.update(algorithm.run(reached));
      assert ARGUtils.checkARG(reached);

      final AbstractState lastState = reached.getLastState();
      if (!(lastState instanceof Targetable)
          || !((Targetable) lastState).isTarget()) {
        // no target state
        break;
      }

      // BDD specials
      final BDDState bddErrorState = AbstractStates.extractStateByType(lastState, BDDState.class);
      final Region errorBdd = bddErrorState.getRegion();

      logger.log(Level.INFO, "ErrorBDD:", manager.dumpRegion(errorBdd));
      errorSummary = manager.makeOr(errorBdd, errorSummary);

      if (presenceConditionFile != null && cpa instanceof ARGCPA) {
        CounterexampleInfo counterEx = ((ARGCPA) cpa).getCounterexamples().get(lastState);
        if (counterEx != null) {
          counterEx.addFurtherInformation(manager.dumpRegion(errorBdd), presenceConditionFile);
        }
      }

      logger.log(Level.INFO, "ErrorSummary:", manager.dumpRegion(errorSummary));

      final Region negatedErrorBdd = manager.makeNot(errorBdd);
      for (AbstractState s : ImmutableList.copyOf(reached.getWaitlist())) {
        BDDState bddState = AbstractStates.extractStateByType(s, BDDState.class);
        if (bddState != null) {
          bddState.addConstraintToState(negatedErrorBdd);

          if (bddState.getRegion().isFalse()) {
            reached.removeOnlyFromWaitlist(s);
          }
        }
      }
      // END BDD specials
    }

    return status;
  }

  @Override
  public void collectStatistics(Collection<Statistics> pStatsCollection) {
    if (algorithm instanceof StatisticsProvider) {
      ((StatisticsProvider) algorithm).collectStatistics(pStatsCollection);
    }
  }
}
