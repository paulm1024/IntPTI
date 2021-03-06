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
package org.sosy_lab.cpachecker.core.interfaces;

import org.sosy_lab.cpachecker.exceptions.CPAException;


public interface ForcedCoveringStopOperator extends StopOperator {

  /**
   * Check whether one state may be strengthened such that it is then covered by
   * reachedState.
   */
  boolean isForcedCoveringPossible(
      AbstractState state,
      AbstractState reachedState,
      Precision precision) throws CPAException, InterruptedException;

}
