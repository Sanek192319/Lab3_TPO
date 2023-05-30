package Bank;

public class BankMain {
    public static final int NUM_ACCOUNTS = 10;
    public static final int INIT_BALANCE = 10000;

    public static void main(String[] args) {
        var bank = new SyncBlockBank(NUM_ACCOUNTS, INIT_BALANCE);

        for (int idx = 0; idx < NUM_ACCOUNTS; ++idx){
            var thread = new TransferThread(bank, idx, INIT_BALANCE);

            thread.setPriority(Thread.NORM_PRIORITY + idx % 2);
            thread.start();
        }
    }
}
