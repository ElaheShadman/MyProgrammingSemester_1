package bank.accounts;

import bank.exceptions.InvalidAmountException;

public class StudentAccount extends Account implements InterestBearing {

    private static final double INTEREST_RATE = 0.01;
    private String university;

    public StudentAccount(String ownerName, double initialDeposit, String university)
            throws InvalidAmountException {
        super(ownerName, initialDeposit);
        this.university = university;
    }

    @Override
    public String getAccountType() { return "Student"; }

    @Override
    public double getInterestRate() { return INTEREST_RATE; }

    @Override
    public void applyMonthlyInterest() {
        double interest = getBalance() * INTEREST_RATE;
        addInterestTransaction(interest);
        System.out.println("💰 Interest applied: $" + String.format("%.2f", interest)
                + " (1%) → new balance: $" + String.format("%.2f", getBalance()));
    }

    @Override
    public void printSummary() {
        super.printSummary();
        System.out.println("  🎓 University: " + university);
        System.out.println("  ✅ No transaction fees!");
    }
}