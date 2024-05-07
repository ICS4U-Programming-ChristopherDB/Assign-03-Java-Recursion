package com.example;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

/**
 * Change me.
 *
 * @author Christopher Di Bert
 * @version 1.0
 * @since 2024-05-05
 */

// RecursionAssignment class
public final class RecursionAssignment {

  /** Private constructor to prevent instantiation. */
  private RecursionAssignment() {
    throw new UnsupportedOperationException("Cannot instantiate");
  }

  /**
   * This is the main method.
   *
   * @param args Unused
   */
  public static void main(final String[] args) {
    // Creates file for the palindrome input text file.
    File palReadFile =
        new File(
            "/home/vscode/ICS4U/Assign/Assign-03/Assign-03-Java-Recursion/com/example/PalindromeIO/input.txt");
    try {
      // Creates writer for the palindrome output text file.
      FileWriter palFileWriter =
          new FileWriter(
              "/home/vscode/ICS4U/Assign/Assign-03/Assign-03-Java-Recursion/com/example/PalindromeIO/output.txt");
      BufferedWriter palWriter = new BufferedWriter(palFileWriter);
      // Scanner to read from the palindrome input text file.
      Scanner sc = new Scanner(palReadFile);
      while (sc.hasNextLine()) {
        try {
          // Cast each line to an int and pass it to the palindrome function.
          int inputInt = Integer.parseInt(sc.nextLine());
          int palResult = DepthForPalindrome(inputInt);
          palWriter.write(Integer.toString(palResult));
          // Error message written to output file if given invalid input.
        } catch (Exception e) {
          palWriter.write("Must enter a single integer!");
          // Creates a new line in the palindrome output text file.
        } finally {
          palWriter.newLine();
        }
      }
      // Closes readers and writers for palindrome I/O.
      palWriter.close();
      sc.close();
    // Error message printed if an invalid write path was given.
  } catch (Exception e) {
    System.out.println("Invalid write path for palindrome input!");
  }
    // Creates file for the wrap input text file.
    File wrapReadFile =
        new File(
            "/home/vscode/ICS4U/Assign/Assign-03/Assign-03-Java-Recursion/com/example/WrapIO/input.txt");
    try {
      // Creates writer for the wrap output text file.
      FileWriter wrapFileWriter =
          new FileWriter(
              "/home/vscode/ICS4U/Assign/Assign-03/Assign-03-Java-Recursion/com/example/WrapIO/output.txt");
      BufferedWriter wrapWriter = new BufferedWriter(wrapFileWriter);
      // Scanner to read from the wrap input text file.
      Scanner sc = new Scanner(wrapReadFile);
      while (sc.hasNextLine()) {
        try {
          // Gets the target character.
          char inputChar = sc.nextLine().charAt(0);
          // Gets the length of the wrapped string.
          int inputLength = Integer.parseInt(sc.nextLine());
          // Writes the wrapped string to wrap output text file.
          wrapWriter.write(AlphabetWrap(inputChar, inputLength));
        } catch (Exception e) {
          // Exception thrown if input is invalid.
          wrapWriter.write("Must enter a single character and integer on separate line");
          // Writes a new line in the wrap output text file.
        } finally {
          wrapWriter.newLine();
        }
      }
      // Closes wrap writer and reader.
      wrapWriter.close();
      sc.close();
      // Error message thrown if an invalid write path was given.
    } catch (Exception e) {
      System.out.println("Invalid write path for wrap input!");
    }

  }

  /**
   * Recursive method that reverses its input string.
   *
   * @param inputString passed.
   * @return inputString.
   */
  private static String stringReverser(final String inputString) {
    // Returns the reversed string once its length is zero.
    if (inputString.length() == 0) {
      return inputString;
      /* Otherwise, return the input string passed into the
       * string reverser again but with its index shifted over one
       * added to the first character of the input string.
       */
    } else {
      return stringReverser(inputString.substring(1)) + inputString.charAt(0);
    }
  }

  /**
   * Gets the palindrome depth of a number.
   *
   * @param someNumber
   * @return palindrome depth of number
   */
  private static int DepthForPalindrome(final int someNumber) {
    // Gets the input number and casts to string.
    String numString = Integer.toString(someNumber);
    // Makes a copy of the number string and reverses it.
    String reversedNumString = stringReverser(numString);
    // If the number is equal to the number reversed, return 0.
    if (numString.equals(reversedNumString)) {
      return 0;
    } else {
      // Returns the input number reversed and added to the original.
      return 1 + DepthForPalindrome(someNumber + Integer.parseInt(reversedNumString));
    }
  }

  /**
   * Wraps characters.
   *
   * @param someChar
   * @param someNum
   * @return String of wrapped characters.
   */
  private static String AlphabetWrap(char someChar, int someNum) {
    // Alphabet used to get index of someChar and to retrieve characters.
    final String alphabet = "abcdefghijklmnopqrstuvwxyz";
    // Gets the index of someChar by searching for it in the alphabet string.
    int charIndex = alphabet.indexOf(someChar);
    if (someNum == 1) {
      return Character.toString(someChar);
    } else if (charIndex == 25) {
      return alphabet.charAt(charIndex) + AlphabetWrap(alphabet.charAt(0), someNum - 1);
    } else {
      return alphabet.charAt(charIndex) + AlphabetWrap(alphabet.charAt(charIndex + 1), someNum - 1);
    }
  }
}
