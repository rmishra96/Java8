package com.multithreadprogramming.tokenbucketalgorithm;

import java.util.HashSet;
import java.util.Set;

public class MultithreadedTokenBucketFilter{
    private long possibleTokens = 0;
    private final int MAX_TOKENS ;
    private final int ONE_SECOND =1000;

    public  MultithreadedTokenBucketFilter(int maxTokens){

        MAX_TOKENS = maxTokens;

        Thread dt = new Thread( () -> {
            daemonThread();
        });
        dt.setDaemon(true);
        dt.start();
    }

    private void daemonThread(){
        while(true){
            synchronized (this){
                if(possibleTokens < MAX_TOKENS){
                    possibleTokens++;
                }
                this.notify();
            }

            try{
                Thread.sleep(ONE_SECOND);
            }catch (InterruptedException ie){

            }
        }
    }

    void getToken() throws InterruptedException {
        synchronized (this){
            while(possibleTokens == 0){
                this.wait();
            }
            possibleTokens--;
        }
        System.out.println("Granting " + Thread.currentThread().getName() + " token at "
                    + System.currentTimeMillis() / 1000);
    }

    public static void main(String[] args) throws InterruptedException {
        Set<Thread> allthreads = new HashSet<Thread>();
        final MultithreadedTokenBucketFilter tokenBucketFilter = new MultithreadedTokenBucketFilter(1);

        for(int i=0 ; i< 10;i++){
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        tokenBucketFilter.getToken();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            });

            thread.setName("Thread_" + (i + 1));
            allthreads.add(thread);
        }

        for(Thread t :allthreads){
            t.start();
        }

        for(Thread t : allthreads){
            t.join();
        }
    }

    public void initialize() {
    }
}
