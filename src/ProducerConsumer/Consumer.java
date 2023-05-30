package ProducerConsumer;

import java.time.LocalTime;
import java.util.Random;

public class Consumer implements Runnable {
    private final Drop drop;
    private final int numbersToConsume;

    public Consumer(Drop drop, int numbersToConsume) {
        this.drop = drop;
        this.numbersToConsume = numbersToConsume;
    }

    public void run() {
        Random random = new Random();
        for (int i = 0; i < numbersToConsume; i++) {
            int value = drop.take();
            System.out.println("("+ LocalTime.now()+") Consumer sent: " + value);
//            try {
//                Thread.sleep(random.nextInt(1000));
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
        }
    }
}



