package ru.learningandroid.calculator;

public interface CalculatorModel {

    public String expressionToPolishNotation(String arithmeticExpression);

    public double calculatePolishNotation(String polishNotation);

    int getOperatorPriority(char operator);
}
