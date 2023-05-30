package ProducerConsumer;

import java.time.LocalTime;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Drop {
    private final int[] buffer;
    private int bufferElements = 0;

    private final Lock lock = new ReentrantLock();
    private final Condition notEmpty = lock.newCondition();
    private final Condition notFull = lock.newCondition();

    public Drop(int bufferSize) {
        buffer = new int[bufferSize];
    }

    public int take() {
        lock.lock();
        try {
            while (bufferElements < 1) {
                notEmpty.await();
            }
            bufferElements--;
            notFull.signalAll();
            return buffer[bufferElements];
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

    public void put(int element) {
        lock.lock();
        try {
            while (bufferElements >= buffer.length) {
                notFull.await();
            }
            buffer[bufferElements] = element;
            bufferElements++;
            notEmpty.signalAll();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }
}