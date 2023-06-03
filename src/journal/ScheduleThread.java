package journal;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ScheduleThread extends Thread {
    public final int WeeksNumber;
    public final Lock lock = new ReentrantLock();
    public Condition TimeToSetRates = lock.newCondition();
    public int CurrentWeek = -1;
    public ScheduleThread(int weeksNumber){
        this.WeeksNumber = weeksNumber;
    }

    @Override
    public void run() {
        for (int week = 0; week < WeeksNumber; week++) {
            lock.lock();
            try {
                CurrentWeek = week;
                TimeToSetRates.signalAll();
            }
            finally {
                lock.unlock();
            }

            try {
                Thread.sleep(100);
            }
            catch (Exception e){
                throw new RuntimeException(e);
            }
        }
    }
}
