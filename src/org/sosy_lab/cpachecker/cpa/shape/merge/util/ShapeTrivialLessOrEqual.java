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
package org.sosy_lab.cpachecker.cpa.shape.merge.util;

import org.sosy_lab.cpachecker.cpa.shape.ShapeState;

public final class ShapeTrivialLessOrEqual implements ShapeLessOrEqual {

  ShapeTrivialLessOrEqual() {
  }

  @Override
  public boolean isLessOrEqual(
      ShapeState successor, ShapeState reached) {
    return reached.equals(successor);
  }
}
