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
package org.sosy_lab.cpachecker.cpa.shape.values;

/**
 * A general interface for presentation of values
 */
public interface ShapeValue {

  boolean isUnknown();

  /**
   * The returned value can be a symbolic value, a known numerical value or address value.
   */
  Number getValue();

  int getAsInt();

  long getAsLong();

  float getAsFloat();

  double getAsDouble();

}
