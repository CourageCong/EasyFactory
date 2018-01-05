# 设计模式之路（一）——简单工厂模式

> 前言：在刚刚接触并学会一些编程知识的时候，我觉得编程也就这样，并非难事，但是有一天，老师的一个朋友说的一句话惊醒了梦中的我。他说：“现在回头想想几年前写的代码，简直不堪入目。”难道是当年的代码格式不规范吗？我想也不至于不堪入目吧，于是我继续询问，得到的答案是**设计模式**。

---
在接触设计模式之前，我也不太相信设计模式的力量，学完真的会认为之前的代码不堪入目吗？学完之后，我想说，是的。

## 简单工厂模式


> 有一系列相似的操作的时候，可以抽象出一个接口，每一个具体的操作都去单独创建一个类并实现该接口。再创建一个工厂类，根据外部传入的参数实例化具体的操作对象返回给外部。

举一个简单的计算器的例子，我们需要客户端输入两个数字和一个运算符号，然后返回运算结果。最简单的做法是写加减乘除四个方法，然后根据传进来的符号进行switch/case判断，调用对应的方法，返回相应的结果值。直观的想法肯定是这么做，而且实话是这样做要比使用简单工厂模式快的多，使用设计模式的话工作量至少要加倍，甚至还要多，当初我也是对此嗤之以鼻。那么麻烦，我为什么要用，根本没有意义吗。

但是当你做完时，你的项目经理要求你再加四十种运算方式，而且其中不乏很多复杂的算法，并且要求几个同事同时来完成。之前的方法此时完全行不通了，因为一起修改会导致之前算法的稳定性无法得到保证，大家一起改同一个类，想想就不能让人放心，而且之后每一次的维护、修改、新增都会使你担心会不会误改了之前的算法，每一次都需要小心翼翼。但是，如果使用了简单工厂模式，你将告别这一切。

---
#### **第一步：抽象类的创建**

首先该设计模式针对的是相似方法的封装，有我们需要根据业务来提取公共部分，抽成接口，让所有的具体操作实现该接口，从而达到统一各操作方法的方法名及返回类型，这里我们需要对各种算法进行抽象。

``` java
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
```
#### **第二步：具体类的创建（加减乘除类的创建）**
定义好接口后，我们只需要为每一个具体的计算方法定义一个类，并且实现该接口即可。在重写的方法中去写具体的算法逻辑，这样，不同的算法类之间没有交叉的部分，对于后续的修改和扩展都能维持很好的稳定性。下面我们就以加减乘除四个类来举例：

- 加法类
```java
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
```
- 减法类
```java
/**
 * Created by fc on 2018/1/3.
 * <p>
 * 减法类
 */

public class SubtractionCalculation implements Calculate {
    
    @Override
    public double getResult(double a, double b) {
        return a - b;
    }
    
}
```
 - 乘法类
```java
 /**
 * Created by fc on 2018/1/3.
 * <p>
 * 乘法类
 */

public class MultiplicationCalculation implements Calculate {

    @Override
    public double getResult(double a, double b) {
        return a * b;
    }

}
```
- 除法类
```java
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
```

四个运算方法对应四个运算类，原本只需要一个类就能解决，所以在这里先提一下，适度使用设计模式，如果业务确定只有加减乘除这四种简单的运算的话实在不必这样来做。

#### **第三步：工厂类的创建**
运算类准备好了，但是怎么用呢，switch/case啊，没错，但是并不是在最外层使用的地方用，而是在工厂类中使用。通过工厂类的封装，可以使外部暴露的接口使用起来非常的容易，而且更容易管理。

```java
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
```
#### **第四步：使用**
此时所有的准备工作都已经准备好了，接下来就是如何使用了，之前的步骤恐怕会使你觉得这个设计模式好费力，但是有付出就一定会有回报的，让我们看看他用起来是如何的轻松。
```java
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
```

只需要三行代码，我们就完成了。这样如果有很多地方都需要调用此类运算，我们可以轻松的维护，同时在新增运算方法的时候，我们并不需要像之前那样，在一堆代码中进行修改，只需要新建一个类，实现对应的接口，完成自己的运算逻辑，最后加在 **工厂类** CalculationFactory 中即可大功告成。不用再担心是否会破坏其他的代码，修改运算逻辑的时候也是如此，从此不再害怕需求的扩展。多人同时开发只需要定义好接口即可，开发结束后统一创建工厂类即可，易扩展，已维护，易复用的简单工厂模式到这里就介绍完了，从此告别一团乱麻。（笑脸）

csdn传送门： [设计模式之路（一）——简单工厂模式](http://blog.csdn.net/dacongge/article/details/78958584)






