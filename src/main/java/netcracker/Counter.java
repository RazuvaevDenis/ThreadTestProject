package netcracker;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * Created by Denis on 12.12.2015.
 * Пример для распараллеливания несвязанных вычислений
 */
public class Counter implements Runnable {

    private int[] tmp;
    private int summ;
    private int num;
    private static final Logger log=Logger.getLogger(Counter.class.getName());
    public Counter(int[] tmp,int num){
        this.tmp=tmp;
        summ=0;
        this.num=num;
    }

    public int getSumm(){
        return summ;
    }

    public void run() {
        for(int i=0; i<tmp.length;i++){
            summ+=tmp[i];
        }
        log.log(Level.INFO,"Thread " + num + " count summ");
    }
}
