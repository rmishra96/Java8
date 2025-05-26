package com.multithreadprogramming.threadSafe;


import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class DeferredCallbackExecutor {
    PriorityQueue<Callback> q = new PriorityQueue<Callback>(new Comparator<Callback>() {
        @Override
        public int compare(Callback o1, Callback o2) {
            return (int) (o1.executeAt - o2.executeAt);
        }
    });

    ReentrantLock lock = new ReentrantLock();

    Condition newCallbackArrived = lock.newCondition();

    public static void runTestTenCallbacks() {
        Set<Thread> allThreads = new HashSet<Thread>();
        final DeferredCallbackExecutor deferredCallbackExecutor = new DeferredCallbackExecutor();

        Thread service = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    deferredCallbackExecutor.start();
                }catch (InterruptedException ex){

                }
            }
        });
        service.start();
        for(int i=0 ; i < 10 ; i++){
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    Callback cb = new Callback(1 ,"Hello this is " + Thread.currentThread().getName() );
                    deferredCallbackExecutor.registerCallback(cb);
                }
            });
        }
    }

    private long findSleepDuration(){
        long currentTime = System.currentTimeMillis();
        return q.peek().executeAt - currentTime;
    }

    public void start() throws InterruptedException {
        long sleepFor = 0;
        while (true){
            lock.lock();

            while (q.size() == 0){
                newCallbackArrived.await();
            }

            while (q.size() !=0 ){
                sleepFor = findSleepDuration();

                if(sleepFor <= 0)
                    break;

                newCallbackArrived.await(sleepFor, TimeUnit.MILLISECONDS);
            }
        Callback cb = q.poll();
            System.out.println("Executed at " + System.currentTimeMillis() / 1000 +
                    "required at " + cb.executeAt / 1000
                    +" : message: " + cb.message );
            lock.unlock();
        }


    }

    public void registerCallback(Callback callback){
        lock.lock();
        q.add(callback);
        newCallbackArrived.signal();
        lock.unlock();
    }
}
