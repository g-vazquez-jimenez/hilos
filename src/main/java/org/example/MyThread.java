package org.example;

public class MyThread extends Thread {
    public MyThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        System.out.println("MyThread Start:" + Thread.currentThread().getName());
        System.out.println("Prioridad: " + Thread.currentThread().getPriority());
        try {
            Thread.sleep(1_000);

        }catch(InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println();
    }

    private void doProcessing() throws InterruptedException  {
        Thread.sleep(5_000);
    }
}
