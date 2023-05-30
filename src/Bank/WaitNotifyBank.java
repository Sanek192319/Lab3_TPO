package Bank;

public class WaitNotifyBank extends Bank {
    public WaitNotifyBank(int count, int initBalance) {
        super(count, initBalance);
    }

    @Override
    public synchronized void transfer(int from, int to, int amount) {
        while (this.accounts[from] < amount) {
            try {
                wait();
            } catch (InterruptedException exception) {}
        }
        super.transfer(from, to, amount);
        notifyAll();
    }
}
