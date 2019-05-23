package com.damon.test.common.thread;

import javax.swing.*;
import java.util.function.Predicate;

public abstract class ThreadLocalTest {

    public static void main(String[] args) {
//        safeDeposit();
//        notSafeDeposit();

    }

    public static void test() {
        Runnable runnable = () -> System.out.println("hello world");

        JButton button = new JButton();
        button.addActionListener(event -> System.out.println(event.getActionCommand()));
    }

    abstract boolean check(Predicate<Integer> predicate);

    abstract boolean check(IntPred intPred);

    private boolean checkResult() {
        return true;//check(x -> x > 5);
    }

    /**
     * 线程安全的存款
     */
    private static void safeDeposit() {
        SafeBank bank = new SafeBank();
        Thread thread1 = new Thread(() -> bank.deposit(200), "张成瑶");
        Thread thread2 = new Thread(() -> bank.deposit(200), "马云");
        Thread thread3 = new Thread(() -> bank.deposit(500), "马化腾");
        thread1.start();
        thread2.start();
        thread3.start();
    }

    /**
     * 非线程安全的存款
     */
    private static void notSafeDeposit() {
        NotSafeBank bank = new NotSafeBank();
        Thread thread1 = new Thread(() -> bank.deposit(200), "张成瑶");
        Thread thread2 = new Thread(() -> bank.deposit(200), "马云");
        Thread thread3 = new Thread(() -> bank.deposit(500), "马化腾");
        thread1.start();
        thread2.start();
        thread3.start();
    }




}

class SafeBank {

    private ThreadLocal<Integer> balance = ThreadLocal.withInitial(() -> 1000);

    public void deposit(int money) {
        String threadName = Thread.currentThread().getName();
        System.out.println(threadName + " -> 当前账户余额为：" + this.balance.get());
        this.balance.set(this.balance.get() + money);
        System.out.println(threadName + " -> 存入 " + money + " 后，当前账户余额为：" + this.balance.get());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class NotSafeBank {

    private int balance = 100;

    public void deposit(int money) {
        String threadName = Thread.currentThread().getName();
        System.out.println(threadName + " -> 当前账户余额为：" + this.balance);
        this.balance += money;
        System.out.println(threadName + " -> 存入 " + money + " 后，当前账户余额为：" + this.balance);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

interface IntPred {
    boolean test(Integer v);
}
