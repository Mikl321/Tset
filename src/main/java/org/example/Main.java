package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите два операнда(римские или арабские) и один оператор(+,-,*,/): ");

        String expression = scanner.nextLine();

        try {
            System.out.println(calc(expression));
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public static String calc(String expression) throws Exception {
        int value1, value2;
        String operator, result;

        boolean isRoman;

        String[] operands = expression.split("[+\\-*/]");

        if (operands.length != 2) {
            throw new Exception("Должно быть 2 операнда и один математический знак(+,-,/,*)");
        }

        // если оба числа римские
        if (Roman.isRoman(operands[0]) && Roman.isRoman(operands[1])) {
            value1 = Roman.convertToArabian(operands[0]);
            value2 = Roman.convertToArabian(operands[1]);

            isRoman = true;
            // если оба числа арабские
        } else if (!Roman.isRoman(operands[0]) && !Roman.isRoman(operands[1])) {
            value1 = Integer.parseInt(operands[0]);
            value2 = Integer.parseInt(operands[1]);

            isRoman = false;
        } else {
            // остальные случаи
            throw new Exception("Числа должны быть в одном формате");
        }

        operator = detectOperation(expression);

        if (value1 > 10 || value2 > 10) {
            throw new Exception("Числа должны быть от 1 до 10");
        }

        int arabian = calculate(value1, value2, operator);

        if (isRoman) {
            // если римское число получилось меньше либо равно нулю, генерируем ошибку
            if (arabian <= 0) {
                throw new Exception("Римское число должно быть больше нуля");
            }
            // конвертируем результат оперции из арабского в римское
            result = Roman.convertToRoman(arabian);
        } else {
            // конвертируем арабское число в стринг
            result = String.valueOf(arabian);
        }
        return result;
    }

    static String detectOperation(String expression) {
        if (expression.contains("+")) return "+";
        else if (expression.contains("-")) return "-";
        else if (expression.contains("*")) return "*";
        else if (expression.contains("/")) return "/";
        else return null;
    }

    static int calculate(int value1, int value2, String operator) {
        int result = 0;

        switch (operator) {
            case "+":
                result = value1 + value2;
                break;
            case "-":
                result = value1 - value2;
                break;
            case "*":
                result = value1 * value2;
                break;
            case "/":
                result = value1 / value2;
                break;
        }
        return result;
    }
}