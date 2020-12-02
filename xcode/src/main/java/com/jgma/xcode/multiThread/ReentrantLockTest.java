package com.jgma.xcode.multiThread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: admin
 */
public class ReentrantLockTest {

    private static final ReentrantLock lock = new ReentrantLock();

    /**
     * newCachedThreadPool，可以进行缓存的线程池，意味着它的线程数是最大的，无限的。
     * 但是核心线程数为 0，这没关系。这里要**考虑线程的摧毁**，因为不能够无限的创建新的线程，所以**在一定时间内要摧毁空闲**的线程。
     */
    private static ExecutorService executorService = new ThreadPoolExecutor(0,10,
                                      60L,TimeUnit.SECONDS,
                                      new SynchronousQueue<Runnable>());

    private static Integer count = 0;

    public static void main(String[] args) {

        for (int i = 0; i < 5; i++) {
            executorService.submit(new Thread(() -> test()));
        }

        // 如果不需要用到线程池可以关闭
        executorService.shutdown();
    }

    /**
     * 在使用阻塞等待获取锁的方式中，必须在try代码块之外，并且在加锁方法与try代码块之间没有任何可能抛出异常的方法调用，
     *      避免加锁成功后，在finally中无法解锁。
     * 说明一：如果在lock方法与try代码块之间的方法调用抛出异常，那么无法解锁，造成其它线程无法成功获取锁。
     * 说明二：如果lock方法在try代码块之内，可能由于其它方法抛出异常，导致在finally代码块中，unlock对未加锁的对象解锁，
     *        它会调用AQS的tryRelease方法（取决于具体实现类），抛出IllegalMonitorStateException异常。
     * 说明三：在Lock对象的lock方法实现中可能抛出unchecked异常，产生的后果与说明二相同。
     */
    public static void test(){
        final ReentrantLock takeLock = lock;
        takeLock.lock();
        try {
            count ++;
            System.out.println(Thread.currentThread().getName()+"获取了锁"+count);
//            TimeUnit.SECONDS.sleep(2);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName()+"释放了锁!!!!!");
            takeLock.unlock();
        }

    }



















}
