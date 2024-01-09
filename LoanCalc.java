public class LoanCalc {

    static double epsilon = 0.01; 
    static int iterationCounter;

    public static void main(String[] args) {
        
        if(args.length < 3) {
            System.out.println("Usage: java LoanCalc <loan sum> <annual interest rate> <number of periods>");
            return;
        }
        
        double loan = Double.parseDouble(args[0]);
        double annualRate = Double.parseDouble(args[1]);
        int n = Integer.parseInt(args[2]);

        iterationCounter = 0;
        double bruteForcePayment = bruteForceSolver(loan, annualRate, n);
        System.out.println("Loan sum = " + loan + ", interest rate = " + annualRate + "%, periods = " + n);
        System.out.printf("Periodical payment, using brute force: %.2f\n", bruteForcePayment);
        System.out.println("Number of iterations: " + iterationCounter);

        iterationCounter = 0;
        double bisectionPayment = bisectionSolver(loan, annualRate, n);
        System.out.printf("Periodical payment, using bisection search: %.2f\n", bisectionPayment);
        System.out.println("Number of iterations: " + iterationCounter);
    }

    public static double bruteForceSolver(double loan, double annualRate, int n) {
        double payment = 0;
        double balance;

        // Start with an estimate for payment
        payment = loan / n;

        do {
            payment += epsilon;
            balance = endBalance(loan, annualRate, n, payment);
            iterationCounter++;
        } while (balance > epsilon);  // Changed condition to balance > epsilon for convergence

        return payment;
    }

    public static double bisectionSolver(double loan, double annualRate, int n) {
        double low = loan / n / 2;
        double high = loan;
        double payment = 0;

        do {
            payment = (low + high) / 2;
            double balance = endBalance(loan, annualRate, n, payment);
            iterationCounter++;

            if (balance > epsilon) {
                low = payment;
            } else if (balance < -epsilon) {
                high = payment;
            }
        } while (high - low > epsilon);

        return payment;
    }

    private static double endBalance(double loan, double annualRate, int n, double payment) {
        double monthlyRate = annualRate / 12 / 100;
        double balance = loan;

        // Applying the annuity formula to calculate balance
        for (int i = 0; i < n; i++) {
            balance -= payment;
            balance = balance * (1 + monthlyRate);
        }

        return balance;
    }
}
