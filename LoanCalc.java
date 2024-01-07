public class LoanCalc {
    
    static double epsilon = 0.001; // The computation tolerance (estimation error)
    static int iterationCounter; // Monitors the efficiency of the calculation
    
    public static void main(String[] args) {
        // Gets the loan data
        double loan = Double.parseDouble(args[0]);
        double rate = Double.parseDouble(args[1]);
        int n = Integer.parseInt(args[2]);
        System.out.println("Loan sum = " + loan + ", interest rate = " + rate + "%, periods = " + n);
        
        // Computes the periodical payment using brute force search
        iterationCounter = 0; // Reset counter before starting
        double bruteForcePayment = bruteForceSolver(loan, rate, n, epsilon);
        System.out.print("Periodical payment, using brute force: ");
        System.out.printf("%.2f\n", bruteForcePayment);
        System.out.println("Number of iterations: " + iterationCounter);

        // Computes the periodical payment using bisection search
        iterationCounter = 0; // Reset counter before starting
        double bisectionPayment = bisectionSolver(loan, rate, n, epsilon);
        System.out.print("Periodical payment, using bi-section search: ");
        System.out.printf("%.2f\n", bisectionPayment);
        System.out.println("Number of iterations: " + iterationCounter);
    }
    
    public static double bruteForceSolver(double loan, double rate, int n, double epsilon) {
        double payment = 0; // Starting at 0 ensures we cover all possible values
        iterationCounter = 0;

        while (true) {
            payment += epsilon; // Increment payment by epsilon
            double balance = endBalance(loan, rate, n, payment);
            iterationCounter++;
            if (balance <= 0) {
                break;
            }
        }

        return payment;
    }

    public static double bisectionSolver(double loan, double rate, int n, double epsilon) {
        double low = 0;
        double high = loan;
        double mid = 0;
        iterationCounter = 0;

        while ((high - low) >= epsilon) {
            mid = (low + high) / 2;
            double balance = endBalance(loan, rate, n, mid);
            iterationCounter++;
            if (balance > 0) {
                low = mid;
            } else {
                high = mid;
            }
        }

        return mid;
    }
    
    private static double endBalance(double loan, double rate, int n, double payment) {
        double monthlyRate = rate / 12 / 100;
        double balance = loan;

        for (int i = 0; i < n; i++) {
            balance = (balance - payment) * (1 + monthlyRate);
        }

        return balance;
    }
}
