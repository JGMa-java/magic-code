package com.jgma.xcode.multiThread;

/**
 * @Author: admin
 */
public class ThreadBankDemo {

    public static void main(String[] args) {
        Bank bank = new Bank();

        bank.getMoney(1500);
        Thread person1 = new Thread(bank);
        person1.setName("==person1==");
        person1.start();
        try {
            // 有顺序执行，1.2.3 yield
            person1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        bank.getMoney(2000);
        Thread person2 = new Thread(bank);
        person2.setName("==person2==");
        person2.start();
        try {
            person2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        bank.getMoney(3000);
        Thread person3 = new Thread(bank);
        person3.setName("==person3==");
        person3.start();
        try {
            person3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


}

class Bank implements Runnable {

    int totalMoney = 10000;
    int money;// 要取出的钱

    public void getMoney(int money) {
        this.money = money;
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        System.out.println(name + "取出：" + money);
        int remainMoney = totalMoney - money;
        totalMoney = remainMoney;
        System.out.println("银行剩余:" + remainMoney);
    }
}