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
package org.sosy_lab.cpachecker.cfa.types.c;

public final class CNumericTypes {

  private CNumericTypes() {
  }

  // type constants                                                        const volatile   basic type    long   short  signed unsign compl  imag   long long
  public final static CSimpleType BOOL =
      new CSimpleType(false, false, CBasicType.BOOL, false, false, false, false, false, false,
          false);
  public final static CSimpleType CHAR =
      new CSimpleType(false, false, CBasicType.CHAR, false, false, false, false, false, false,
          false);
  public static final CSimpleType SIGNED_CHAR =
      new CSimpleType(false, false, CBasicType.CHAR, false, false, true, false, false, false,
          false);
  public static final CSimpleType UNSIGNED_CHAR =
      new CSimpleType(false, false, CBasicType.CHAR, false, false, false, true, false, false,
          false);
  public final static CSimpleType INT =
      new CSimpleType(false, false, CBasicType.INT, false, false, false, false, false, false,
          false);
  public final static CSimpleType SIGNED_INT =
      new CSimpleType(false, false, CBasicType.INT, false, false, true, false, false, false, false);
  public final static CSimpleType UNSIGNED_INT =
      new CSimpleType(false, false, CBasicType.INT, false, false, false, true, false, false, false);
  public final static CSimpleType SHORT_INT =
      new CSimpleType(false, false, CBasicType.INT, false, true, false, false, false, false, false);
  public final static CSimpleType UNSIGNED_SHORT_INT =
      new CSimpleType(false, false, CBasicType.INT, false, true, false, true, false, false, false);
  public final static CSimpleType LONG_INT =
      new CSimpleType(false, false, CBasicType.INT, true, false, false, false, false, false, false);
  public final static CSimpleType UNSIGNED_LONG_INT =
      new CSimpleType(false, false, CBasicType.INT, true, false, false, true, false, false, false);
  public final static CSimpleType LONG_LONG_INT =
      new CSimpleType(false, false, CBasicType.INT, false, false, false, false, false, false, true);
  public final static CSimpleType UNSIGNED_LONG_LONG_INT =
      new CSimpleType(false, false, CBasicType.INT, false, false, false, true, false, false, true);

  public final static CSimpleType FLOAT =
      new CSimpleType(false, false, CBasicType.FLOAT, false, false, false, false, false, false,
          false);
  public final static CSimpleType DOUBLE =
      new CSimpleType(false, false, CBasicType.DOUBLE, false, false, false, false, false, false,
          false);
  public final static CSimpleType LONG_DOUBLE =
      new CSimpleType(false, false, CBasicType.DOUBLE, true, false, false, false, false, false,
          false);
}
