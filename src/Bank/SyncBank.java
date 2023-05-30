package Bank;

public class SyncBank extends Bank {
    public SyncBank(int count, int initBalance) {
        super(count, initBalance);
    }

    @Override
    public synchronized void transfer(int from, int to, int amount) {
        super.transfer(from, to, amount);
    }
}
