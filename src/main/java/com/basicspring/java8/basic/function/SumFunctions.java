package com.basicspring.java8.basic.function;

import java.util.function.BiFunction;
import java.util.function.Function;

public class SumFunctions {
    public static void main(String[] args) {
        BiFunction<Integer, Integer,Integer> sumtoNumber = (a,b) -> a+b;
        Function<Integer,Integer> sumOneNumbers = c -> c;

        int result = sumtoNumber.apply(5,3) + sumOneNumbers.apply(4);
        System.out.println("Result: " + result);

    }
}
