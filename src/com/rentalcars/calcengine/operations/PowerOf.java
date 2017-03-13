package com.rentalcars.calcengine.operations;

import com.rentalcars.calcengine.helpers.MathProcessing;

public class PowerOf implements MathProcessing {
    @Override
    public String getKeyWord() {
        return "power";
    }

    @Override
    public char getSymbol() {
        return '^';
    }

    @Override
    public double doCalculation(double leftVal, double rightVal) {
        return Math.pow(leftVal, rightVal);
    }
}
