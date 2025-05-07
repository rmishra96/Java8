package com.multithreadprogramming.tokenbucketalgorithm;

import java.util.HashSet;
import java.util.Set;

public class DemostrationMainFactoryImpl {
    public static void main(String[] args) throws InterruptedException {
        Set<Thread> allthreads  = new HashSet<Thread>();
        TokenBucketFilter1 tokenBucketFilter1 = TokenBucketFilterFactory.makeTokenBucketFilter(1);

        for(int i= 0; i < 10 ; i++){
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try{
                        tokenBucketFilter1.getToken();
                    }catch (InterruptedException ex){

                    }
                }
            });
            thread.setName("Thread_" + (i + 1));
            allthreads.add(thread);

        }

        for(Thread t: allthreads){
            t.start();
        }

        for(Thread t: allthreads){
            t.join();
        }
    }
}
