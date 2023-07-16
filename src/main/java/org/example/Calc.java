package org.example;

import java.util.Scanner;

public class Calc {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите два числа (римские или арабские):");
        String expression = scanner.nextLine();
        System.out.println(parse(expression));

    }

    public static String parse(String expression) throws Exception {
        int num1;
        int num2;
        String oper;
        String result;
        boolean isRoman;
        String[] operands = expression.split("[+\\-*/]");
        if (operands.length != 2) throw new Exception("Должно быть 2 операнда");
        oper = detectOperation(expression);
        if (oper == null) throw new Exception("Неподдерживаемая математическая операция");
        // если оба числа римские
        if (Roman.isRoman(operands[0]) && Roman.isRoman(operands[1])) {
            //конвертируем оба числа
            num1 = Roman.convertToArabian(operands[0]);
            num2 = Roman.convertToArabian(operands[1]);
            isRoman = true;
        }
        if (!Roman.isRoman(operands[0]) && !Roman.isRoman(operands[1])) {
            //конвертируем оба числа
            num1 = Roman.convertToArabian(operands[0]);
            num2 = Roman.convertToArabian(operands[1]);
            isRoman = false;
        } else {
            throw new Exception("Числа должны быть в одном формате");
        }
        if (num1 > 10 || num2 > 10) {
            throw new Exception("Числа должны быть от 1 до 10");
        }

        int arabian = calc(num1, num1, oper);

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

    static int calc(int a, int b, String oper) {
        if (oper.equals("+")) return a + b;
        else if (oper.equals("-")) return a - b;
        else if (oper.equals("*")) return a * b;
        else return a / b;

    }

    class Roman {
        static String[] romanArray = new String[]{"0", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV", "XV",
                "XVI", "XVII", "XVIII", "XIX", "XX", "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII",
                "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL", "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLVIX", "L", "LI",
                "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX", "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX", "LXXI",
                "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX", "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII",
                "LXXXVIII", "LXXXIX", "XC", "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"};

        public static boolean isRoman(String val) {
            for (int i = 0; i < romanArray.length; i++) {
                if (val.equals(romanArray[i])) {
                    return true;
                }

            }
            return false;
        }

        public static int convertToArabian(String roman) {
            for (int i = 0; i < romanArray.length; i++) {
                if (roman.equals(romanArray[i])) {
                    return i;
                }


            }
            return -1; }

        public static String convertToRoman(int arabian) {
            return romanArray[arabian];
        }

    }
}