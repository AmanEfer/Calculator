package Task1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    private static final int ARRAY_MEMBERS_COUNT = 3;
    private static final List<String> MATH_OPERATORS = new ArrayList<>(Arrays.asList("+", "-", "*", "/"));
    private static final List<String> INPUT_ROMAN_NUMERALS = new ArrayList<>(Arrays.asList(
            "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"
    ));

    public static void main(String[] args) throws Exception {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String expression = reader.readLine();
            String result = calc(expression);
            System.out.println(result);
        }

    }

    public static String calc(String input) throws Exception {
        String[] arrayMembers = input.trim().split(" ");

        if (arrayMembers.length != ARRAY_MEMBERS_COUNT || !MATH_OPERATORS.contains(arrayMembers[1])) {
            throw new Exception();
        }

        String calcSystem = getCalcSystem(arrayMembers);
        String result = null;

        if (calcSystem.equals("arabic")) {
            result = getArabicValue(arrayMembers);
        } else if (calcSystem.equals("roman")) {
            result = getRomanValue(arrayMembers);
        }

        return result;
    }

    private static String getCalcSystem(String[] inputArray) throws Exception {
        String firstDigit = inputArray[0];
        String secondDigit = inputArray[2];

        if (INPUT_ROMAN_NUMERALS.contains(firstDigit.toUpperCase()) && INPUT_ROMAN_NUMERALS.contains(secondDigit.toUpperCase())) {
            return "roman";
        } else if (Integer.parseInt(firstDigit) >= -10 && Integer.parseInt(firstDigit) <= 10 &&
                Integer.parseInt(secondDigit) >= -10 && Integer.parseInt(secondDigit) <= 10) {
            return "arabic";
        } else {
            throw new Exception();
        }
    }

    private static String getArabicValue(String[] inputArray) {
        int firstDigit = Integer.parseInt(inputArray[0]);
        int secondDigit = Integer.parseInt(inputArray[2]);
        String mathOperator = inputArray[1];

        int result = mathAction(firstDigit, secondDigit, mathOperator);

        return String.valueOf(result);
    }

    private static String getRomanValue(String[] inputArray) throws Exception {
        int firstDigit = RomanNumber.valueOf(inputArray[0]).getArabicValue();
        int secondDigit = RomanNumber.valueOf(inputArray[2]).getArabicValue();
        String mathOperator = inputArray[1];

        int intResult = mathAction(firstDigit, secondDigit, mathOperator);

        if (intResult <= 0) {
            throw new Exception();
        }

        StringBuilder sb = new StringBuilder();

        for (RomanNumber item : RomanNumber.values()) {
            while (intResult >= item.getArabicValue()) {
                intResult -= item.getArabicValue();
                sb.append(item);
            }
        }
        return sb.toString();
    }

    private static int mathAction(int firstDigit, int secondDigit, String mathOperator) {
        int result = 0;

        switch (mathOperator) {
            case "+":
                result = firstDigit + secondDigit;
                break;
            case "-":
                result = firstDigit - secondDigit;
                break;
            case "*":
                result = firstDigit * secondDigit;
                break;
            case "/":
                result = firstDigit / secondDigit;
                break;
        }
        return result;
    }
}
