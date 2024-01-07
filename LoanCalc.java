public class LoanCalc {

    static double epsilon = 0.01; 
    static int iterationCounter; 

    public static void main(String[] args) {
        
        double loan = 100000; 
        double annualRate = 3; 
        int n = 12; 

        System.out.println("Loan sum = " + loan + ", interest rate = " + annualRate + "%, periods = " + n);

        
        iterationCounter = 0; 
        double bruteForcePayment = bruteForceSolver(loan, annualRate, n, epsilon);
        System.out.print("Periodical payment, using brute force: ");
        System.out.printf("%.2f\n", bruteForcePayment);
        System.out.println("Number of iterations: " + iterationCounter);

        
        iterationCounter = 0; 
        double bisectionPayment = bisectionSolver(loan, annualRate, n, epsilon);
        System.out.print("Periodical payment, using bisection search: ");
        System.out.printf("%.2f\n", bisectionPayment);
        System.out.println("Number of iterations: " + iterationCounter);
    }

    public static double bruteForceSolver(double loan, double annualRate, int n, double epsilon) {
        double payment = 0;
        double balance;
        iterationCounter = 0;

        
        while (true) {
            payment += epsilon;
            balance = endBalance(loan, annualRate, n, payment);
            iterationCounter++;

            if (Math.abs(balance) <= epsilon) {
                break;
            }
        }

        return payment;
    }

    public static double bisectionSolver(double loan, double annualRate, int n, double epsilon) {
        double low = 0;
        double high = loan;
        double payment = (low + high) / 2;
        iterationCounter = 0;

        while ((high - low) >= epsilon) {
            payment = (low + high) / 2;
            double balance = endBalance(loan, annualRate, n, payment);
            iterationCounter++;

            if (balance > 0) {
                low = payment;
            } else {
                high = payment;
            }
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

