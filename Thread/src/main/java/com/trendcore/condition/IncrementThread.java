package com.trendcore.condition;

/**
 * Created by Anurag
 */
public class IncrementThread implements Runnable{

    private final ShareObject shareObject;

    public IncrementThread(ShareObject shareObject) {
        this.shareObject = shareObject;
    }

    @Override
    public void run() {

        boolean flag = true;

        while (flag) {
            shareObject.getLock().lock();
            try {

                int count = shareObject.getCount();
                count++;
                System.out.println("Inside increment Thread :- " + count);

                shareObject.setCount(count);

                shareObject.getCondition().signal();
                shareObject.getCondition().await();
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
