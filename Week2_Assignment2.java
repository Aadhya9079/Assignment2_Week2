/***
 * Problem Statement :
   Task 1 - Write a Java program that takes a string as input and calculates the number of unique combinations where a
            palindrome is formed.
   Task 2 - Write a Java program to print the nth number in the Fibonacci series without using loops.
   Task 3 - Write a Java program where the user inputs a string in snake_case. If the string is not in snake_case,
            first convert it to snake_case, and then to camelCase.
   Task 4 - Write a Java program that takes a string as input and finds the number of consonants in the string.
   Task 5 - Write a Java program that takes an integer in binary format and converts it to its decimal representation.
 * Owner Name : Aadhya Goyal
 * Date of Creation : 11-09-24 - 13-09-24
 */

import java.math.BigInteger;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Week2_Assignment2 {

    public static final int Maximum_String_Length = 100;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println(Constant2.AvailableOperations);
            System.out.print(Constant2.ExecutionMessage);
            String operation = scanner.nextLine();

            switch (operation) {
                case "1":
                    System.out.print("Enter a String:");
                    String originalString = scanner.nextLine();
                    if (originalString.isEmpty()) {
                        System.out.println("Invalid input. The string cannot be empty.");
                    }
                    else if (originalString.length() > Maximum_String_Length) {
                        System.out.print("Invalid String.");
                    }
                    else {
                        String[] uniquePalindromes = new String[15];
                        int[] palindromeCount = {0};
                        findPalindromes(originalString, 0, 1, uniquePalindromes, palindromeCount);
                        System.out.println("Number of Palindromes: " + palindromeCount[0]);
                    }
                    break;

                case "2":
                    System.out.print(Constant2.InputMessage);
                    try {
                        BigInteger input = scanner.nextBigInteger();
                        scanner.nextLine();
                        if (input.signum() < 0) {
                            throw new NumberFormatException("Negative numbers are not allowed.");
                        }
                        System.out.println("Fibonacci number at position " + input + " is: " + fibonacci(input));
                    }
                    catch (InputMismatchException | NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a non-negative integer.");
                        scanner.nextLine();
                    }
                    break;

                case "3":
                    System.out.print("Enter a String :");
                    String inputString = scanner.nextLine();
                    if (inputString.length() > Maximum_String_Length) {
                        System.out.println("Invalid input. The string is too large.");
                    } else if (inputString.isEmpty()) {
                        System.out.println("Invalid input. The string cannot be empty.");
                    } else {
                        String snakeCaseString = SnakeCase(inputString);
                        System.out.println("Snake_case is: " + snakeCaseString);
                        String camelCaseString = CamelCase(snakeCaseString);
                        System.out.println("CamelCase is: " + camelCaseString);
                    }
                    break;

                case "4":
                    System.out.println("Enter a string:");
                    String Inputstring = scanner.nextLine();
                    if (Inputstring.length() > Maximum_String_Length) {
                        System.out.println("Invalid input. The string is too large.");
                    }
                    else if (Inputstring.isEmpty()) {
                        System.out.println("Invalid input. The string cannot be empty.");
                    } else {
                        int consonantCount = CountWords(Inputstring, 0);
                        System.out.println("Number of consonants: " + consonantCount);
                    }
                    break;

                case "5":
                    System.out.print(Constant2.Message_5);
                    String inputDigit = scanner.nextLine();
                    if (isBinary(inputDigit)) {
                        int decimal = binaryToDecimal(inputDigit);
                        System.out.println("Decimal representation: " + decimal);
                    } else {
                        System.out.println(Constant2.Invalid_5);
                    }
                    break;

                default:
                    System.out.println(Constant2.InvalidMessage);
                    break;
            }

            System.out.print(Constant2.YesOrNoMessage);
            String cont = scanner.nextLine();
            if (!cont.equals("yes")) {
                break;
            }
        }
    }

    //Method Count Palindrome
    public static boolean isPalindrome(String stringPalindrome, int start, int end) {
        if (start >= end) {
            return true;
        }
        if (stringPalindrome.charAt(start) != stringPalindrome.charAt(end)) {
            return false;
        }
        return isPalindrome(stringPalindrome, start + 1, end - 1);
    }

    public static String formSubstring(String stringSubstring, int start, int end) {
        if (start > end)
            return "";

        return stringSubstring.charAt(start) + formSubstring(stringSubstring, start + 1, end);
    }

    public static boolean AlreadyPresent(String[] uniquePalindromes, String palindrome, int size) {
        if (size == 0)
            return false;
        if (uniquePalindromes[size - 1].equals(palindrome))
            return true;

        return AlreadyPresent(uniquePalindromes, palindrome, size - 1);
    }

    public static void findPalindromes(String findPalindrome, int start, int end, String[] uniquePalindromes, int[] palindromeCount) {
        int n = findPalindrome.length();
        if (start >= n)
            return;
        if (end > n) {
            findPalindromes(findPalindrome, start + 1, start + 1, uniquePalindromes, palindromeCount);
            return;
        }

        String substring = formSubstring(findPalindrome, start, end - 1);
        if (!substring.isEmpty() && isPalindrome(findPalindrome, start, end - 1)) {
            if (!AlreadyPresent(uniquePalindromes, substring, palindromeCount[0])) {
                uniquePalindromes[palindromeCount[0]] = substring;
                palindromeCount[0]++;
            }
        }
        findPalindromes(findPalindrome, start, end + 1, uniquePalindromes, palindromeCount);
    }

    //Method Nth fibonacci
    public static BigInteger fibonacci(BigInteger input) {
        if (input.equals(BigInteger.ZERO)) {
            return BigInteger.ZERO;
        } else if (input.equals(BigInteger.ONE)) {
            return BigInteger.ONE;
        } else {
            return fibonacci(input.subtract(BigInteger.ONE)).add(fibonacci(input.subtract(BigInteger.TWO)));
        }
    }

    //Method SnakeCase
    private static String SnakeCase(String SnakeCase) {
        return toSnakeCase(SnakeCase, 0, new StringBuilder()).toString();
    }

    private static StringBuilder toSnakeCase(String string, int index, StringBuilder result) {
        if (index == string.length()) {
            return result;
        }
        char currentChar = string.charAt(index);

        if (Character.isUpperCase(currentChar)) {
            if (result.length() > 0) {
                result.append('_');
            }
            result.append(Character.toLowerCase(currentChar));
        } else if (currentChar == ' ' || currentChar == '-') {
            result.append('_');
        } else {
            result.append(currentChar);
        }
        return toSnakeCase(string, index + 1, result);
    }

    // Method snake_case to camelCase
    private static String CamelCase(String snakeCaseStr) {
        return toCamelCase(snakeCaseStr, 0, new StringBuilder()).toString();
    }

    private static StringBuilder toCamelCase(String str, int index, StringBuilder result) {
        if (index == str.length()) {
            return result;
        }

        char currentChar = str.charAt(index);
        if (currentChar == '_') {
            if (index + 1 < str.length()) {
                result.append(Character.toUpperCase(str.charAt(index + 1)));
                return toCamelCase(str, index + 2, result);
            }
        } else {
            if (index == 0) {
                result.append(Character.toLowerCase(currentChar));
            } else {
                result.append(currentChar);
            }
        }
        return toCamelCase(str, index + 1, result);
    }

    //Method Count Consonants
    public static int CountWords(String str, int index) {
        if (index == str.length()) {
            return 0;
        }
        char currentChar = str.charAt(index);
        boolean isConsonant = isConsonant(currentChar);
        return (isConsonant ? 1 : 0) + CountWords(str, index + 1);
    }

    private static boolean isConsonant(char ch) {
        ch = Character.toLowerCase(ch);
        return (ch >= 'a' && ch <= 'z') && !(ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u');
    }

    //Method Binary To Decimal
    public static int binaryToDecimal(String binaryString) {
        return binaryToDecimal(binaryString, 0);
    }

    private static int binaryToDecimal(String binaryString, int exponent) {
        if (binaryString.isEmpty()) {
            return 0;
        } else {
            char digit = binaryString.charAt(binaryString.length() - 1);
            int decimalDigit = (digit == '0') ? 0 : 1;
            int decimalValue = decimalDigit * power(2, exponent);
            return decimalValue + binaryToDecimal(binaryString.substring(0, binaryString.length() - 1), exponent + 1);
        }
    }

    private static int power(int base, int exponent) {
        if (exponent == 0) {
            return 1;
        } else {
            return base * power(base, exponent - 1);
        }
    }

    public static boolean isBinary(String str) {
        for (char c : str.toCharArray()) {
            if (c != '0' && c != '1') {
                return false;
            }
        }
        return true;
    }
}