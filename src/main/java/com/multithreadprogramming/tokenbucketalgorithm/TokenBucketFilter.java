package com.multithreadprogramming.tokenbucketalgorithm;

import java.util.HashSet;
import java.util.Set;

public class TokenBucketFilter {

    private int MAX_TOKENS;

    private long lastRequestTime = System.currentTimeMillis();

    long possibleTokens = 0;

    public TokenBucketFilter(int maxTokens) {
        MAX_TOKENS = maxTokens;
    }

    public TokenBucketFilter() {

    }


    synchronized void getToken() throws InterruptedException{
        possibleTokens += (System.currentTimeMillis() - lastRequestTime) / 1000;

        if(possibleTokens > MAX_TOKENS){
            possibleTokens = MAX_TOKENS;
        }

        if(possibleTokens == 0){
            Thread.sleep(1000);
        }else {
            possibleTokens-- ;
        }
        lastRequestTime = System.currentTimeMillis();

        System.out.println("Granting " + Thread.currentThread().getName() + " token at" +
                (System.currentTimeMillis() / 1000 ));
    }

    public  static void runTestMaxTokenIs1() throws InterruptedException{
        Set<Thread> allThreads = new HashSet<Thread>();
        final TokenBucketFilter tokenBucketFilter = new TokenBucketFilter(1);

        for(int i= 0; i < 10; i++){
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try{
                        tokenBucketFilter.getToken();
                    }catch (InterruptedException ex){
                        System.out.println("We have a problem");
                    }
                }
            });
            thread.setName("Thread_" + (i+1));
            allThreads.add(thread);
        }
        for(Thread t : allThreads){
            t.start();
        }

        for(Thread t: allThreads){
            t.join();
        }
    }



    public static void main(String[] args) throws InterruptedException {
        TokenBucketFilter.runTestMaxTokenIs1();
    }
}
