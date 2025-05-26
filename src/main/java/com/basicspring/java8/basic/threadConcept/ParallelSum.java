package com.basicspring.java8.basic.threadConcept;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

public class ParallelSum {
    public static void main(String[] args) {
        int[] array1 = {1,2,4,5,6};
        int[] array2 = {3,5,6,7,8};

        CompletableFuture<Integer> sumFuture1 = CompletableFuture.supplyAsync(() -> sumArray(array1));
        CompletableFuture<Integer> sumFuture2 = CompletableFuture.supplyAsync(() -> sumArray(array2));

        int totalSum = sumFuture1.join() + sumFuture2.join() ;

        System.out.println("Total Sum: " + totalSum);

    }

    private static Integer sumArray(int[] array1) {
        return (Arrays.stream(array1).sum());
    }
}
