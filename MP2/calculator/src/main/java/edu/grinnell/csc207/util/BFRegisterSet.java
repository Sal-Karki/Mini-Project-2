package edu.grinnell.csc207.util;

import java.math.BigInteger;

/**
 * BFRegister Class.
 */
public class BFRegisterSet {
  /**
   * array of registers.
   */
  private BigFraction[] registers;

  /**
   * Creates a new set of fractions.
   */
  public BFRegisterSet() {
    registers = new BigFraction[26];
    for (int i = 0; i < 26; i++) {
      registers[i] = new BigFraction(BigInteger.ZERO, BigInteger.ONE);
    } // for
  } // BFRegisterSet

  /**
   * Stores factions within the register.
   * @param register
   * @param value
   */
  public void store(char register, BigFraction value) {
    int index = register - 'a';
    if (index >= 0 && index < 26) {
      registers[index] = value;
    } else {
      System.err.println("ERROR: Invalid register. Use letters a to z.");
    } // if
  } // store

  /**
   * returns the register.
   * @param register
   * @return register
   */
  public BigFraction get(char register) {
    int index = register - 'a';
    if (index >= 0 && index < 26) {
      return registers[index];
    } else {
      System.err.println("ERROR: Invalid register. Use letters a to z.");
      return new BigFraction(BigInteger.ZERO, BigInteger.ONE); // Default return value if invalid
    } // if
  } // get
} // BFRegisterSet
