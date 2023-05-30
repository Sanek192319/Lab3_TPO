package ProducerConsumer;

import ProducerConsumer.Consumer;
import ProducerConsumer.Drop;
import ProducerConsumer.Producer;

public class ProducerConsumerExample {
    public static void main(String[] args) {
        int bufferSize = 1000;
        Drop drop = new Drop(bufferSize);
        (new Thread(new Producer(drop,bufferSize))).start();
        (new Thread(new Consumer(drop,bufferSize))).start();
    }
}