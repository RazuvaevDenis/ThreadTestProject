package netcracker;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import org.apache.log4j.Logger;
import org.apache.log4j.Level;

/**
 * Created by Denis on 13.12.2015.
 */
public class Account implements Runnable {

    private Lock lock;
    private int i;
    private Money m;
    private int type;
    private static final Logger log=Logger.getLogger(Account.class.getName());

    public static final int TYPE1=0;
    public static final int TYPE2=1;

    public void IncreaseCount(int tmp) throws InterruptedException {
        Random rnd=new Random();
        lock.lock();
        try{
            m.setCount(m.getCount()+tmp);
            log.log(Level.INFO,"Count increase. Count = "+m.getCount());
            i++;
        }
        finally{
            lock.unlock();
            Thread.sleep(rnd.nextInt(4)*1000);
        }
    }

    public void DecreaseCount(int tmp) throws InterruptedException {
        Random rnd=new Random();
        lock.lock();
        try{
            m.setCount(m.getCount()-tmp);
            log.log(Level.INFO,"Count decrease. Count = "+m.getCount());
            i++;
        }
        finally {
            lock.unlock();
            Thread.sleep(rnd.nextInt(4)*1000);
        }
    }

    public Account(Lock lock, Money m, int type){
        this.lock=lock;
        this.m=m;
        this.i=0;
        this.type=type;
    }

    @Override
    public void run(){
        try {
            while (i < 5) {
                switch (type) {
                    case 0:
                        IncreaseCount(100);
                        break;
                    case 1:
                        DecreaseCount(50);
                        break;
                }
            }
        }
        catch(InterruptedException e){
            log.log(Level.INFO,"Exception. For more details - file 'error.log'");
            log.log(Level.ERROR,"Error - interrupted exception ",e);
        }
    }
}
