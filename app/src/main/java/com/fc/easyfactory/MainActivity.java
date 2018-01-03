package com.fc.easyfactory;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.fc.easyfactory.suanfa.Calculate;
import com.fc.easyfactory.suanfa.CalculationFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * 根据传入的数字和运算符获取对应运算结果
     *
     * @param a       参与运算的第一个数字
     * @param b       参与运算的第二个数字
     * @param operate 运算符
     */
    public double getResult(double a, double b, String operate) {

        Calculate calculate = CalculationFactory.createCalculate(operate);
        double result = calculate.getResult(a, b);
        return result;

    }
}
