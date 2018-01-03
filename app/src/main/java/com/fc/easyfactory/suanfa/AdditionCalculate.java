package com.fc.easyfactory.suanfa;

/**
 * Created by fc on 2018/1/3.
 * <p>
 * 加法类
 */

public class AdditionCalculate implements Calculate {

    @Override
    public double getResult(double a, double b) {
        return a + b;
    }

}
