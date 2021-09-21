package ru.nikky.calculator;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Stack;

public class EvaluateExpression {

    private HashMap<String, Integer> prioritiesHashMap;
    private Stack<BigDecimal> numbersStack;
    private Stack<String> operationsStack;
    private String currentExpression;

    private static final char DOT = '.';
    private static final char PLUS = '+';
    private static final char MINUS = '-';
    private static final char MULTIPLICATION = '\u00d7';
    private static final char DIVISION = '/';
    private static final char OPEN_PARENTHESIS = '(';
    private static final char CLOSE_PARENTHESIS = ')';

    public EvaluateExpression(String currentExpression) {
        this.currentExpression = currentExpression;
        initPrioritiesHashMap();
    }

    private void initPrioritiesHashMap() {
        prioritiesHashMap = new HashMap<>();
        prioritiesHashMap.put("+", 1);
        prioritiesHashMap.put("-", 1);
        prioritiesHashMap.put("\u00d7", 2);
        prioritiesHashMap.put("/", 2);
    }

    public BigDecimal evaluate() {
        numbersStack = new Stack<>();
        operationsStack = new Stack<>();
        int currentIndex = 0;
        while (currentIndex < currentExpression.length()) {
            boolean currentTokenIsNumber = ifCurrentTokenIsNumber(currentIndex);
            String currentToken = currentTokenIsNumber ?
                    getCurrentNumber(currentIndex) : getCurrentOperation(currentIndex);
            if (currentTokenIsNumber) {
                numbersStack.add(new BigDecimal(currentToken));
                currentIndex += currentToken.length();
                continue;
            } else {
                if (operationsStack.empty() ||
                        operationsStack.peek().equals(String.valueOf(OPEN_PARENTHESIS))) {
                    operationsStack.add(currentToken);
                    currentIndex += currentToken.length();
                    continue;
                } else if (currentToken.equals(String.valueOf(OPEN_PARENTHESIS))) {
                    operationsStack.add(String.valueOf(OPEN_PARENTHESIS));
                    currentIndex += currentToken.length();
                    continue;
                } else if (currentToken.equals(String.valueOf(CLOSE_PARENTHESIS))) {
                    if (operationsStack.peek().equals(String.valueOf(OPEN_PARENTHESIS))){
                        operationsStack.pop();
                        currentIndex += currentToken.length();
                        continue;
                    } else {
                        BigDecimal newBigDecimal = makeBinaryOperation(numbersStack.pop(), numbersStack.pop(), operationsStack.pop());
                        numbersStack.add(newBigDecimal);
                    }
                } else {
                    if (getOperationPriority(currentToken) >
                            getOperationPriority(operationsStack.peek())) {
                        operationsStack.add(currentToken);
                        currentIndex += currentToken.length();
                        continue;
                    } else {
                        BigDecimal newBigDecimal = makeBinaryOperation(numbersStack.pop(), numbersStack.pop(), operationsStack.pop());
                        numbersStack.add(newBigDecimal);
                    }
                }
            }
        }

        while (!operationsStack.empty()){
            BigDecimal newBigDecimal = makeBinaryOperation(numbersStack.pop(), numbersStack.pop(), operationsStack.pop());
            numbersStack.add(newBigDecimal);
        }

        return numbersStack.pop();
    }

    private BigDecimal makeBinaryOperation(BigDecimal secondNumber, BigDecimal firstNumber, String operation) {
        switch (operation.charAt(0)) {
            case PLUS:
                return firstNumber.add(secondNumber);
            case MINUS:
                return firstNumber.subtract(secondNumber);
            case MULTIPLICATION:
                return firstNumber.multiply(secondNumber);
            case DIVISION:
                return firstNumber.divide(secondNumber);
            default:
                return BigDecimal.valueOf(0);
        }
    }

    private int getOperationPriority(String currentToken) {
        return prioritiesHashMap.get(currentToken);
    }

    private String getCurrentOperation(int currentIndex) {
        switch (currentExpression.charAt(currentIndex)) {
            case PLUS:
                return String.valueOf(PLUS);
            case MINUS:
                return String.valueOf(MINUS);
            case MULTIPLICATION:
                return String.valueOf(MULTIPLICATION);
            case DIVISION:
                return String.valueOf(DIVISION);
            case OPEN_PARENTHESIS:
                return String.valueOf(OPEN_PARENTHESIS);
            case CLOSE_PARENTHESIS:
                return String.valueOf(CLOSE_PARENTHESIS);
            default:
                return "";
        }
    }

    private String getCurrentNumber(int currentIndex) {
        StringBuilder currentNumberSB = new StringBuilder();
        for (int i = currentIndex; i < currentExpression.length(); i++) {
            char currentChar = currentExpression.charAt(i);
            if (Character.isDigit(currentChar) || currentChar == DOT)
                currentNumberSB.append(currentChar);
            else break;
        }
        return currentNumberSB.toString();
    }

    private boolean ifCurrentTokenIsNumber(int currentIndex) {
        char currentChar = currentExpression.charAt(currentIndex);
        return Character.isDigit(currentChar);
    }


}
