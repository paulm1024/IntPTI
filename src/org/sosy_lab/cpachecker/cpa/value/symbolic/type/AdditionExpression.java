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
package org.sosy_lab.cpachecker.cpa.value.symbolic.type;

import org.sosy_lab.cpachecker.cfa.types.Type;
import org.sosy_lab.cpachecker.util.states.MemoryLocation;

/**
 * {@link BinarySymbolicExpression} representing addition.
 */
public class AdditionExpression extends BinarySymbolicExpression {

  private static final long serialVersionUID = 7356078098388713345L;

  protected AdditionExpression(
      SymbolicExpression pOperand1, SymbolicExpression pOperand2,
      Type pExpressionType, Type pCalculationType) {
    super(pOperand1, pOperand2, pExpressionType, pCalculationType);
  }

  protected AdditionExpression(
      final SymbolicExpression pOperand1,
      final SymbolicExpression pOperand2,
      final Type pExpressionType,
      final Type pCalculationType,
      final MemoryLocation pRepresentedLocation
  ) {
    super(pOperand1, pOperand2, pExpressionType, pCalculationType, pRepresentedLocation);
  }

  @Override
  public AdditionExpression copyForLocation(final MemoryLocation pRepresentedLocation) {
    return new AdditionExpression(getOperand1(), getOperand2(), getType(), getCalculationType(),
        pRepresentedLocation);
  }

  @Override
  public <VisitorReturnT> VisitorReturnT accept(SymbolicValueVisitor<VisitorReturnT> pVisitor) {
    return pVisitor.visit(this);
  }

  @Override
  public String getOperationString() {
    return "+";
  }
}