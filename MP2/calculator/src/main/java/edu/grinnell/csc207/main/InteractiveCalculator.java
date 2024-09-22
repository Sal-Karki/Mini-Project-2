package edu.grinnell.csc207.main;

import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Scanner;

import edu.grinnell.csc207.util.BFCalculator;
import edu.grinnell.csc207.util.BFRegisterSet;
import edu.grinnell.csc207.util.BigFraction;

/**
* Interactice Calculator class.
*/
public class InteractiveCalculator {
  /**
  * Main class.
  * @param args
  */
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    PrintWriter pen = new PrintWriter(System.out, true);

    BFCalculator calculator = new BFCalculator();
    BFRegisterSet registers = new BFRegisterSet();

    while (true) {
      pen.print("> ");
      pen.flush();
      String input = scanner.nextLine().trim();
      if (input.equalsIgnoreCase("QUIT")) {
        break;
      } else if (input.startsWith("STORE ")) {
        char register = input.charAt(6);
        registers.store(register, calculator.get());
        pen.println("STORED");
      } // if
    } // while
    pen.close();
  } // main

  /**
   * Evaluates the expression based on the operator.
   * @param tokens
   * @param calculator
   * @param registers
   * @return BigFraction
   */
  private static BigFraction evaluateExpression(String[] tokens,
      BFCalculator calculator, BFRegisterSet registers) {
    BigFraction currentVal = parseFractionOrRegister(tokens[0], registers);

    for (int i = 1; i < tokens.length; i += 2) {
      String operator = tokens[i];
      BigFraction nextVal = parseFractionOrRegister(tokens[i + 1], registers);
      switch (operator) {
        case "+":
          calculator.add(nextVal);
          break;
        case "-":
          calculator.subtract(nextVal);
          break;
        case "*":
          calculator.multiply(nextVal);
          break;
        case "/":
          calculator.divide(nextVal);
          break;
        default:
          System.err.println("Invalid Operator");
      } // switch
    } // for
    return calculator.get();
  } // evaluateExpression

  /**
   * Separates expression.
   * @param token
   * @param registers
   * @return BigFraction
   */
  private static BigFraction parseFractionOrRegister(String token, BFRegisterSet registers) {
    if (token.length() == 1 && Character.isLetter(token.charAt(0))) {
      return registers.get(token.charAt(0));
    } else {
      String[] parts = token.split("/");
      if (parts.length == 2) {
        return new BigFraction(new BigInteger(parts[0]), new BigInteger(parts[1]));
      } else {
        return new BigFraction(new BigInteger(parts[0]), BigInteger.ONE);
      } // if
    } // if
  } // parseFractionOrRegister
} // InteractiveCalculator
