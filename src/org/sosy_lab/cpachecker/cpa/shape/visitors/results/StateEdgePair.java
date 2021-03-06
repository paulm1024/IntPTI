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
package org.sosy_lab.cpachecker.cpa.shape.visitors.results;

import org.sosy_lab.cpachecker.cpa.shape.ShapeState;
import org.sosy_lab.cpachecker.cpa.shape.graphs.edge.SGHasValueEdge;

public final class StateEdgePair {

  private final ShapeState state;
  private final SGHasValueEdge edge;

  public StateEdgePair(ShapeState pState, SGHasValueEdge pEdge) {
    state = pState;
    edge = pEdge;
  }

  public StateEdgePair(ShapeState pState) {
    state = pState;
    edge = null;
  }

  public SGHasValueEdge getEdge() {
    return edge;
  }

  public ShapeState getState() {
    return state;
  }

}
