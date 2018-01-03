package com.fc.easyfactory.suanfa;

/**
 * Created by fc on 2018/1/3.
 * <p>
 * 工厂类，用于对运算类的统一调度
 */

public class CalculationFactory {

    /**
     * @param operate 运算符，也可以通过枚举类型或者static final等定义自己的传入参数
     *                由此来判断需要实例化什么对象
     */
    public static Calculate createCalculate(String operate) {

        Calculate calculate = null;

        switch (operate) {
            case "+":
                calculate = new AdditionCalculate();
                break;
            case "-":
                calculate = new SubtractionCalculation();
                break;
            case "*":
                calculate = new MultiplicationCalculation();
                break;
            case "%":
                calculate = new DivisionCalculation();
                break;

        }
        return calculate;
    }

}
