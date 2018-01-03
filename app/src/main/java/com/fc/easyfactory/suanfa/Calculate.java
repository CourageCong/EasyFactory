package com.fc.easyfactory.suanfa;

/**
 * Created by fc on 2018/1/3.
 * <p>
 * 所有计算操作的父类
 */

public interface Calculate {

    /**
     * @param a
     * @param b 参与计算的两个参数
     * @return 计算返回值
     */
    double getResult(double a, double b);

}
