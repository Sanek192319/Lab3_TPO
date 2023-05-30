package Bank;

public class TransferThread extends Thread {
    private final Bank bank;
    private final int from;
    private final int maxAmount;
    private static final int NUM_ATTEMPTS = 1000;

    public TransferThread(Bank bank, int from, int maxAmount){
        this.bank = bank;
        this.from = from;
        this.maxAmount = maxAmount;
    }

    @Override
    public void run(){
        while (true) {
            for (int idx = 0; idx < NUM_ATTEMPTS; ++idx) {
                int to = (int) (this.bank.size() * Math.random());
                int amount = (int) (this.maxAmount * Math.random());
                this.bank.transfer(this.from, to, amount);
            }
        }
    }
}
