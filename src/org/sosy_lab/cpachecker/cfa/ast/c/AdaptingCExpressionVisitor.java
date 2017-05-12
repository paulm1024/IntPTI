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
package org.sosy_lab.cpachecker.cfa.ast.c;

import com.google.errorprone.annotations.ForOverride;

/**
 * Class similar to {@link ForwardingCExpressionVisitor} that allows to have
 * a different return type than the delegate visitor.
 *
 * @param <R> The return type of the methods of this class.
 * @param <D> The return type of the methods of the delegate visitor.
 * @param <X> The (common) exception type.
 */
public abstract class AdaptingCExpressionVisitor<R, D, X extends Exception>
    implements CExpressionVisitor<R, X> {

  protected final CExpressionVisitor<D, X> delegate;

  protected AdaptingCExpressionVisitor(CExpressionVisitor<D, X> pDelegate) {
    delegate = pDelegate;
  }

  @ForOverride
  protected abstract R convert(D value, CExpression exp) throws X;

  @Override
  public R visit(CArraySubscriptExpression e) throws X {
    return convert(delegate.visit(e), e);
  }

  @Override
  public R visit(CBinaryExpression e) throws X {
    return convert(delegate.visit(e), e);
  }

  @Override
  public R visit(CCastExpression e) throws X {
    return convert(delegate.visit(e), e);
  }

  @Override
  public R visit(CComplexCastExpression e) throws X {
    return convert(delegate.visit(e), e);
  }

  @Override
  public R visit(CFieldReference e) throws X {
    return convert(delegate.visit(e), e);
  }

  @Override
  public R visit(CIdExpression e) throws X {
    return convert(delegate.visit(e), e);
  }

  @Override
  public R visit(CCharLiteralExpression e) throws X {
    return convert(delegate.visit(e), e);
  }

  @Override
  public R visit(CFloatLiteralExpression e) throws X {
    return convert(delegate.visit(e), e);
  }

  @Override
  public R visit(CIntegerLiteralExpression e) throws X {
    return convert(delegate.visit(e), e);
  }

  @Override
  public R visit(CImaginaryLiteralExpression e) throws X {
    return convert(delegate.visit(e), e);
  }

  @Override
  public R visit(CStringLiteralExpression e) throws X {
    return convert(delegate.visit(e), e);
  }

  @Override
  public R visit(CTypeIdExpression e) throws X {
    return convert(delegate.visit(e), e);
  }

  @Override
  public R visit(CUnaryExpression e) throws X {
    return convert(delegate.visit(e), e);
  }

  @Override
  public R visit(CPointerExpression e) throws X {
    return convert(delegate.visit(e), e);
  }

  @Override
  public R visit(CAddressOfLabelExpression e) throws X {
    return convert(delegate.visit(e), e);
  }
}
