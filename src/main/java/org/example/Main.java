package org.example;

import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println(Thread.currentThread().getName());
        Thread t1 = new Thread(new HeavyWorkRunnable());
        t1.start();

        Thread t3 = new MyThread("T3");
        t3.start();

        Runnable myRunabble = new Runnable() {
            @Override
            public void run() {
                System.out.println("Runnable running");
            }
        };
        Thread t4 = new Thread(myRunabble);
        t4.start();

        Runnable runa = ()  -> {
            System.out.println("lambda running");
        };
        Thread t44 = new Thread(runa);
        t44.start();

        Counter counter = new Counter();
        Runnable incrementTask = () -> {
          for (int i = 0 ; i < 10_000; i++) {
              counter.increment();
          }
        };

        Thread thread1 = new Thread(incrementTask);
        Thread thread2 = new Thread(incrementTask);
        Thread thread3 = new Thread(incrementTask);
        Thread thread4 = new Thread(incrementTask);

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

        thread1.join();
        thread2.join();
        thread3.join();
        thread4.join();

        System.out.println(counter.getValue());
        //35_000
        //38_128

        //con synchronized = 40_000

        //variables atomicas
        AtomicInteger atomicCounter = new AtomicInteger(0);
        Runnable atomicIncrementTask = () -> {
            for (int i = 0 ; i < 10_000; i++) {
                atomicCounter.incrementAndGet();
            }
        };
        Thread thread5 = new Thread(atomicIncrementTask);
        Thread thread6 = new Thread(atomicIncrementTask);
        Thread thread7 = new Thread(atomicIncrementTask);
        Thread thread8 = new Thread(atomicIncrementTask);
        thread5.start();
        thread6.start();
        thread7.start();
        thread8.start();
        thread5.join();
        thread6.join();
        thread7.join();
        thread8.join();
        System.out.println("atomic output: " + atomicCounter.get());

    }

}