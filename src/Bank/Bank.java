package Bank;

import java.util.stream.IntStream;

public class Bank {
    protected final int[] accounts;
    protected long num_transactions;
    public static final int LOG_EVERY = 10000;

    public Bank(int count, int initBalance){
        this.accounts = new int[count];
        for (int idx = 0; idx < this.accounts.length; ++idx)
            this.accounts[idx] = initBalance;
        this.num_transactions = 0;
    }

    public void transfer(int from, int to, int amount) {
        this.accounts[from] -= amount;
        this.accounts[to] += amount;

        this.num_transactions++;
        if (this.num_transactions % LOG_EVERY == 0)
            this.log();
    }

    public void log() {
        int balance = IntStream.of(this.accounts).sum();
        System.out.println("Transactions: " + this.num_transactions + " Balance: " + balance);
    }

    public int size() {
        return this.accounts.length;
    }
}
