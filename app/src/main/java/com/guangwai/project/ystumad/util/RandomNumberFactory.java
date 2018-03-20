package com.guangwai.project.ystumad.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 随机数生成工具类
 * Created by Ming on 2018/3/15.
 */

public class RandomNumberFactory {

    /**
     * 随机生成一个运算符
     */
    public static int getRandomOperation() {
        Random random = new Random();
        int operation = random.nextInt(4);
        return operation;
    }

    /**
     * 通过运算符来生成对应的model
     *
     * @return
     */
    public static OperationModel getRandomModel(int operation, int max) {
        OperationModel model = null;
        switch (operation) {
            case Constant.OPERATION_ADD:
                model = getAddRandomModel(max);
                break;
            case Constant.OPERATION_REDUCE:
                model = getReduceRandomModel(max);
                break;
            case Constant.OPERATION_MULTIPLY:
                model = getMultiplyRandomModel(max);
                break;
            case Constant.OPERATION_DIVIDE:
                model = getDivideAddRandomModel(max);
                break;

        }
        return model;
    }

    /**
     * 获取加法的model
     *
     * @return
     */
    private static OperationModel getAddRandomModel(int max) {
        Random random = new Random();
        int firstNum = random.nextInt(max + 1); // 0 - 110的随机数
        int secondNum = random.nextInt(max + 1);

        OperationModel model = new OperationModel();
        model.setFirstNum(firstNum);
        model.setSecondNum(secondNum);
        model.setOperation(Constant.OPERATION_ADD);

        return model;
    }

    /**
     * 获取减法的model
     *
     * @return
     */
    private static OperationModel getReduceRandomModel(int max) {
        Random random = new Random();
        int firstNum = random.nextInt(max + 1); // 0 - 110的随机数
        int secondNum = random.nextInt(firstNum + 1);

        OperationModel model = new OperationModel();
        model.setFirstNum(firstNum);
        model.setSecondNum(secondNum);
        model.setOperation(Constant.OPERATION_REDUCE);
        model.setResultNum(firstNum - secondNum);
        return model;
    }

    /**
     * 获取乘法的model
     *
     * @return
     */
    private static OperationModel getMultiplyRandomModel(int max) {
        Random random = new Random();
        int firstNum = random.nextInt(max + 1); // 0 - 110的随机数
        int secondNum;
        if (firstNum > 10) {
            secondNum = random.nextInt(10);
        } else {
            secondNum = random.nextInt(max + 1);
        }
        OperationModel model = new OperationModel();
        model.setFirstNum(firstNum);
        model.setSecondNum(secondNum);
        model.setOperation(Constant.OPERATION_MULTIPLY);
        model.setResultNum(firstNum * secondNum);
        return model;
    }

    /**
     * 获取除法的model
     *
     * @return
     */
    private static OperationModel getDivideAddRandomModel(int max) {
        Random random = new Random();
        int firstNum = random.nextInt(max + 1); // 0 - 110的随机数
        int secondNum;
        if (firstNum == 0) {
            //如果被除数为0的话，除数可以为任意非0数
            secondNum = random.nextInt(max) + 1;
        } else {
            //从所有因子中随机取出一个
            List<Integer> factors = getIntFactors(firstNum);
            int index = random.nextInt(factors.size());
            secondNum = factors.get(index);
        }

        OperationModel model = new OperationModel();
        model.setFirstNum(firstNum);
        model.setSecondNum(secondNum);
        model.setOperation(Constant.OPERATION_DIVIDE);
        model.setResultNum(firstNum / secondNum);
        return model;
    }

    /**
     * 获取一个整数的所有因子
     */
    private static List<Integer> getIntFactors(int num) {
        List<Integer> factorsList = new ArrayList<>();
        for (int i = 1; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                factorsList.add(i);
                factorsList.add(num / i);
            }
        }
        return factorsList;
    }
}
