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
package org.sosy_lab.cpachecker.cpa.boundary.info;

import org.sosy_lab.cpachecker.cfa.model.CFAEdge;

import java.util.Collection;

public interface BoundedInfo<T> {

  T getBoundedObject();

  CFAEdge getEntry();

  Collection<CFAEdge> getExit();

}
