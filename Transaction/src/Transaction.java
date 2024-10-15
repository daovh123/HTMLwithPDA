public class Transaction {
    private String operation;
    private double amount;
    private double balance;

    /**
     * JAVADOC.
     * @param operation A.
     * @param amount B.
     * @param balance C.
     */
    public Transaction(String operation, double amount, double balance) {
        this.operation = operation;
        this.amount = amount;
        this.balance = balance;
    }

    public static final String DEPOSIT = "deposit";
    public static final String WITHDRAW = "withdraw";

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
