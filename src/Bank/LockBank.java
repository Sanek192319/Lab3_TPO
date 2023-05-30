package Bank;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockBank extends Bank {
    private final Lock lock = new ReentrantLock();

    public LockBank(int count, int initBalance) {
        super(count, initBalance);
    }

    @Override
    public void transfer(int from, int to, int amount) {
        lock.lock();
        try {
            super.transfer(from, to, amount);
        } finally {
            lock.unlock();
        }
    }
}
