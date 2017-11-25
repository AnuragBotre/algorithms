package com.trendcore.condition;

/**
 * Created by Anurag
 */
public class DecrementThread implements Runnable {

    private final ShareObject shareObject;

    public DecrementThread(ShareObject shareObject) {
        this.shareObject = shareObject;
    }

    @Override
    public void run() {
        boolean flag = true;

        while (flag) {
            shareObject.getLock().lock();
            try {
                int count = shareObject.getCount();
                count--;
                System.out.println("Inside decrement Thread :- " + count);

                shareObject.setCount(count);

                shareObject.getCondition().await();
                shareObject.getCondition().signal();
                /*shareObject.getLock().newCondition().signal();
                shareObject.getLock().newCondition().await();*/

            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                shareObject.getLock().unlock();
            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
