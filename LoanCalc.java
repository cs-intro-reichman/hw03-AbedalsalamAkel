public class LoanCalc {

    static double epsilon = 0.01; 
    static int iterationCounter; 

    public static void main(String[] args) {
        
        double loan = 100000; 
        double annualRate = 3; 
        int n = 12; 

        
        iterationCounter = 0;
        double bruteForcePayment = bruteForceSolver(loan, annualRate, n);
        System.out.println("Loan sum = " + loan + ", interest rate = " + annualRate + "%, periods = " + n);
        System.out.printf("Periodical payment, using brute force: %.2f\n", bruteForcePayment);
        System.out.println("Number of iterations: " + iterationCounter);

        // Bisection search
        iterationCounter = 0;
        double bisectionPayment = bisectionSolver(loan, annualRate, n);
        System.out.printf("Periodical payment, using bisection search: %.2f\n", bisectionPayment);
        System.out.println("Number of iterations: " + iterationCounter);
    }

    public static double bruteForceSolver(double loan, double annualRate, int n) {
        double payment = loan / n; 
        double balance;

        while (true) {
            balance = endBalance(loan, annualRate, n, payment);
            if (Math.abs(balance) <= epsilon) {
                break;
            }
            payment += epsilon; 
            iterationCounter++;
        }

        return payment;
    }

    public static double bisectionSolver(double loan, double annualRate, int n) {
        double low = 0;
        double high = loan;
        double payment = 0;

        while ((high - low) >= epsilon) {
            payment = (low + high) / 2;
            double balance = endBalance(loan, annualRate, n, payment);

            if (balance > epsilon) {
                low = payment;
            } else if (balance < -epsilon) {
                high = payment;
            } else {
                break; // Found a payment that gets the balance within epsilon of 0
            }
            iterationCounter++;
        }

        return payment;
    }

    private static double endBalance(double loan, double annualRate, int n, double payment) {
        double monthlyRate = annualRate / 12 / 100;
        double balance = loan;

        for (int i = 0; i < n; i++) {
            balance = balance * (1 + monthlyRate) - payment;
        }

        return balance;
    }
}
