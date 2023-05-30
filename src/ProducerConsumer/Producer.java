package ProducerConsumer;

import java.time.LocalTime;
import java.util.Random;

public class Producer implements Runnable {
    private final Drop drop;
    private final int numbersToProduce;

    public Producer(Drop drop, int numbersToProduce) {
        this.drop = drop;
        this.numbersToProduce = numbersToProduce;
    }

    public void run() {
        Random random = new Random();
        for (int i = 0; i < numbersToProduce; i++) {
            int value = random.nextInt(1000);
            System.out.println("("+ LocalTime.now()+" Producer sent: " + value);
            drop.put(value);
//            try {
//                Thread.sleep(random.nextInt(1000));
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
        }
    }
}