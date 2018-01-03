package com.fc.easyfactory.suanfa;

/**
 * Created by fc on 2018/1/3.
 *
 * 除法类
 */

public class DivisionCalculation implements Calculate{
    @Override
    public double getResult(double a, double b){

        if(b == 0){
            return 0;
        }
        return a % b;
    }
}
