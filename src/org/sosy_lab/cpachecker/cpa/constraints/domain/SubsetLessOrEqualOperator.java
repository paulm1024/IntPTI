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
package org.sosy_lab.cpachecker.cpa.constraints.domain;

import org.sosy_lab.cpachecker.core.interfaces.AbstractDomain;
import org.sosy_lab.cpachecker.core.interfaces.AbstractState;
import org.sosy_lab.cpachecker.cpa.constraints.ConstraintsCPA;
import org.sosy_lab.cpachecker.cpa.constraints.constraint.IdentifierAssignment;
import org.sosy_lab.cpachecker.exceptions.CPAException;

/**
 * Less-or-equal operator for {@link ConstraintsCPA} that defines less-or-equal as
 * <code>c less or equal c' iff c' subset of c</code> with <code>c, c'</code> being
 * ConstraintsStates.
 */
public class SubsetLessOrEqualOperator implements AbstractDomain {

  private static final SubsetLessOrEqualOperator SINGLETON = new SubsetLessOrEqualOperator();

  private SubsetLessOrEqualOperator() {
    // DO NOTHING
  }

  public static SubsetLessOrEqualOperator getInstance() {
    return SINGLETON;
  }

  @Override
  public boolean isLessOrEqual(
      final AbstractState pLesserState,
      final AbstractState pBiggerState
  ) {
    assert pLesserState instanceof ConstraintsState;
    assert pBiggerState instanceof ConstraintsState;

    ConstraintsState lesserState = (ConstraintsState) pLesserState;
    ConstraintsState biggerState = (ConstraintsState) pBiggerState;

    if (biggerState.size() > lesserState.size()) {
      return false;
    }

    IdentifierAssignment lesserStatesDefAssignments = lesserState.getDefiniteAssignment();
    IdentifierAssignment biggerStatesDefAssignments = biggerState.getDefiniteAssignment();

    return lesserState.containsAll(biggerState)
        && lesserStatesDefAssignments.entrySet().containsAll(biggerStatesDefAssignments.entrySet());
  }

  @Override
  public AbstractState join(AbstractState state1, AbstractState state2)
      throws CPAException, InterruptedException {
    throw new UnsupportedOperationException("ConstraintsCPA's domain does not support join");
  }
}
