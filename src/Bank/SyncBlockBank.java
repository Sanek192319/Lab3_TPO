package Bank;

public class SyncBlockBank extends Bank {

    public SyncBlockBank(int count, int initBalance) {
        super(count, initBalance);
    }

    @Override
    public void transfer(int from, int to, int amount) {
        synchronized (this) {
            super.transfer(from, to, amount);
        }
    }
}
