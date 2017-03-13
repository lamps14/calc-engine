package com.rentalcars.myapp;

import com.rentalcars.calcengine.operations.Adder;
import com.rentalcars.calcengine.helpers.CalculateBase;
import com.rentalcars.calcengine.helpers.CalculateHelper;
import com.rentalcars.calcengine.operations.Divider;
import com.rentalcars.calcengine.helpers.DynamicHelper;
import com.rentalcars.calcengine.exceptions.InvalidStatementException;
import com.rentalcars.calcengine.helpers.MathEquation;
import com.rentalcars.calcengine.helpers.MathProcessing;
import com.rentalcars.calcengine.operations.Multiplier;
import com.rentalcars.calcengine.operations.PowerOf;
import com.rentalcars.calcengine.operations.Subtracter;

public class Main {

    public static void main(String[] args) throws InvalidStatementException {
        if(args.length > 0) {
            useCommandLine(args[0], args[1], args[2]);
        } else {
            useMathEquation();
            useCalculatorBase();
            useCalculateHelper();
            useDynamicHelper();
        }
    }

    private static void useMathEquation() {

        MathEquation[] equations = new MathEquation[4];
        equations[0] = new MathEquation('d', 100.0d, 50.0d);
        equations[1] = new MathEquation('a', 25.0d, 92.0d);
        equations[2] = new MathEquation('s', 225.0d, 17.0d);
        equations[3] = new MathEquation('m', 11.0d, 3.0d);


        for (MathEquation equation : equations) {
            equation.execute();
        }

        System.out.println();
        System.out.println("Using Overloads");
        System.out.println();

        double leftDouble = 9.0d;
        double rightDouble = 4.0d;

        int leftInt = 9;
        int rightInt = 4;

        MathEquation equationOverload = new MathEquation('d');

        equationOverload.execute(leftDouble, rightDouble);
        System.out.print("result = ");
        System.out.println(equationOverload.getResult());

        equationOverload.execute(leftInt, rightInt);
        System.out.print("result = ");
        System.out.println(equationOverload.getResult());

        equationOverload.execute((double)leftInt, rightInt);
        System.out.print("result = ");
        System.out.println(equationOverload.getResult());
    }

    private static void useCalculatorBase() {

        System.out.println();
        System.out.println("Using Inheritance");
        System.out.println();

        CalculateBase[] calculators = {
                new Divider(100.0d, 50.0d),
                new Adder(25.0d, 92.0d),
                new Subtracter(225.0d, 17.0d),
                new Multiplier(11.0d, 3.0d),
        };

        for(CalculateBase calculator: calculators) {
            calculator.calculate();
            System.out.print("result = ");
            System.out.println(calculator.getResult());
        }
    }

    private static void useCalculateHelper() {

        System.out.println();
        System.out.println("Using Calculate Helper");
        System.out.println();

        String[] statements = {
                "add 1.0", // Error: incorrect number of values
                "add xx 25.0", // Error: non-numeric data
                "addX 0.0 0.0", // Error invalid command
                "divide 100.0 50.0", //100.0 / 50.0 = 2.0
                "add 25.0 92.0", // 25.0 + 92.0 = 117.0
                "substract 225.0 17.0", // 225.0 - 17.0 = 108.0
                "multiply 11.0 3.0" // 11.0 * 3.0 = 33.0
        };

        CalculateHelper helper = new CalculateHelper();

        for (String statement:statements) {
            try {
                helper.process(statement);
                System.out.println(helper);
            } catch(InvalidStatementException e) {
                System.out.println(e.getMessage());
                if(e.getCause() != null) {
                    System.out.println(" Original exception: " +e.getCause().getMessage());
                }
            }
        }
    }

    private static void useDynamicHelper() {
        System.out.println();
        System.out.println("Using Dynamic Helper");
        System.out.println();

        String[] statements = {
                "power 5.0 2.0", // 5.0 ^ 2.0 = 25.0
                "divide 100.0 50.0", //100.0 / 50.0 = 2.0
                "add 25.0 92.0", // 25.0 +92.0 = 117.0
                "subtract 225.0 17.0", // 225.0 - 17.0 = 108.0
                "multiply 11.0 3.0" // 11.0 * 3.0 = 33.0
        };
        DynamicHelper helper = new DynamicHelper(new MathProcessing[] {
                new PowerOf(),
                new Divider(),
                new Adder(),
                new Subtracter(),
                new Multiplier()
        });
        for(String statement:statements) {
            String output = helper.process(statement);
            System.out.println(output);
        }
    }


    private static void useCommandLine(String operator, String leftVal, String rightVal) {
        System.out.println();
        System.out.println("Using Command Line:");
        System.out.println();

        String[] statements = {
                operator + " " + leftVal + " " + rightVal
        };
        MathProcessing[] dynamicOperator = new MathProcessing[1];

        switch (operator) {
            case "add":
                dynamicOperator[0] = new Adder();
                break;

            case "subtract":
                dynamicOperator[0] = new Subtracter();
                break;

            case "divide":
                dynamicOperator[0] = new Divider();
                break;

            case "multiply":
                dynamicOperator[0] = new Multiplier();
                break;

            case "power":
                dynamicOperator[0] = new PowerOf();
                break;
        }

        DynamicHelper helper =  new DynamicHelper(dynamicOperator);

        for(String statement:statements) {
            String output = helper.process(statement);
            System.out.println(output);

        }
    }
}


