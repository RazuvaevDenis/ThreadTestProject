package netcracker;

import org.apache.log4j.Logger;
import org.apache.log4j.Level;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Denis on 12.12.2015.
 */
public class Test {

    private static final int N = 3;
    private static final Logger log=Logger.getLogger(Test.class.getName());
    public static void CountArraySumm(Counter[] counters) throws InterruptedException {
        Thread[] threads=new Thread[counters.length];
        for(int i=0; i<counters.length; i++){
            threads[i]=new Thread(counters[i]);
            threads[i].start();
        }
        for(int j=0;j<counters.length;j++){
            threads[j].join();
        }
    }

    public static int CountSumm(Counter[] counters){
        int summ=0;
        for(int i=0;i<counters.length;i++){
            summ+=counters[i].getSumm();
        }
        return summ;
    }

    public static void AccountWork(Account[] accounts){
        Thread[] threads=new Thread[accounts.length];
        for(int i=0;i<accounts.length;i++) {
            threads[i] = new Thread(accounts[i]);
            threads[i].start();
        }
    }

    public static void main(String[] args) {
        int[] tmp1={1,3,5,6,8};
        int[] tmp2={4,5,4,1,3};
        int[] tmp3={1,4,6,6,3};
        Counter[] counters=new Counter[N];
        counters[0]=new Counter(tmp1,1);
        counters[1]=new Counter(tmp2,2);
        counters[2]=new Counter(tmp3,3);
        Lock lock=new ReentrantLock();
        Money m=new Money(500,"Euro");
        Account[] accounts=new Account[2];
        accounts[0]=new Account(lock,m,Account.TYPE1);
        accounts[1]=new Account(lock,m,Account.TYPE2);
        int summ=0;
        try {
            CountArraySumm(counters);
            summ=CountSumm(counters);
            log.log(Level.INFO,"Summa = " + summ);
            log.log(Level.INFO,"------------------------");
            log.log(Level.INFO,"Count start = " + m.getCount());
            AccountWork(accounts);
        } catch (InterruptedException e) {
            log.log(Level.INFO,"Exception. For more details - file 'error.log'");
            log.log(Level.ERROR,"Error - interrupted exception ",e);
        }
    }
}
