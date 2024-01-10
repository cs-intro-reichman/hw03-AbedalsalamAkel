public class LoanCalc {
	
	static double epsilon = 0.001; 
	static int iterationCounter;    
	
    
	public static void main(String[] args) {		
		double loan = Double.parseDouble(args[0]);
		double rate = Double.parseDouble(args[1]);
		int n = Integer.parseInt(args[2]);
		System.out.println("Loan sum = " + loan + ", interest rate = " + rate + "%, periods = " + n);
		System.out.print("Periodical payment, using brute force: ");
		System.out.printf("%.2f", bruteForceSolver(loan, rate, n, epsilon));
		System.out.println();
		System.out.println("number of iterations: " + iterationCounter);
		System.out.print("Periodical payment, using bi-section search: ");
		System.out.printf("%.2f", bisectionSolver(loan, rate, n, epsilon));
		System.out.println();
		System.out.println("number of iterations: " + iterationCounter);
	}
	
    public static double bruteForceSolver(double loan, double rate, int n, double epsilon) {
		iterationCounter = 0;
    	double payment = 0;
		while (Math.abs(endBalance(loan, rate, n, payment)) >= epsilon) {
			payment += epsilon;
			iterationCounter++;
		}
    	return payment;
    }
    
   
    public static double bisectionSolver(double loan, double rate, int n, double epsilon) {
		iterationCounter = 0;
		
    	double L = 0, H = loan, payment = (L + H) / 2.0;
		double endBalance = endBalance(loan, rate, n, payment);
	
		while (Math.abs(endBalance) >= epsilon) {
			if (endBalance > 0) {
				L = payment;
			} else if (endBalance < 0) {
				H = payment;
			}
			payment = (L + H) / 2;
			iterationCounter++;
			endBalance = endBalance(loan, rate, n, payment);
		}
    	return payment;
    }
	
	private static double endBalance(double loan, double rate, int n, double payment) {
		
		for (int i = 0; i < n; i++) {
			loan -= payment;
			loan *= (1 + (rate / 100));
		}
    	return loan;
	}
}
