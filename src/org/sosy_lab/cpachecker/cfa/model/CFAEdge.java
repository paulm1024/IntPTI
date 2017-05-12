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
package org.sosy_lab.cpachecker.cfa.model;


import com.google.common.base.Optional;

import org.sosy_lab.cpachecker.cfa.ast.AAstNode;
import org.sosy_lab.cpachecker.cfa.ast.FileLocation;

public interface CFAEdge {

  public CFAEdgeType getEdgeType();

  public CFANode getPredecessor();

  public CFANode getSuccessor();

  public Optional<? extends AAstNode> getRawAST();

  public int getLineNumber();

  public FileLocation getFileLocation();

  /**
   * Returns the part of the original input file from which this edge was
   * produced. This should usually be a single statement / declaration etc.
   * (what normal programmers write on one line).
   *
   * The result does not reflect any post-processing and simplification which
   * was done after parsing and thus may show different C code than the result
   * of {@link #getCode()}.
   */
  public String getRawStatement();

  /**
   * Returns a string representation of the code attached to this edge.
   * If there is no such representation, the method returns the empty string.
   */
  public String getCode();

  /**
   * Returns a representation of this edge which is meant to be shown to the
   * user. This description should only contain the code content of the edge
   * (i.e., no meta-information like line number and predecessor/successor) and
   * thus usually be similar to the output of {@link #getCode()}.
   */
  public String getDescription();

  /**
   * Returns a full representation of this edge (including as many information
   * as possible) which is meant to be shown to the user.
   */
  @Override
  public String toString();
}
