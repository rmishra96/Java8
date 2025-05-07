package com.basicspring.java8.basic.function;

import java.util.function.Function;

public class FunctionDemo {
    public static void main(String[] args) {
        Function<Integer,Integer> increament = x -> x +10;
        Function<Integer,Integer> multiply = y  ->  y * 2 ;
        System.out.println("Compose Result: " + increament.compose(multiply).apply(3));
    }
}
