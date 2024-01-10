public class Calendar0 {

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: java Calendar0 <year>");
            return;
        }

        int year = Integer.parseInt(args[0]);
        isLeapYearTest(year);
        nDaysInMonthTest(year);
    }

    private static void isLeapYearTest(int year) {
        boolean leapYear = isLeapYear(year);
        if (leapYear) {
            System.out.println(year + " is a leap year");
        } else {
            System.out.println(year + " is a common year");
        }
    }

    private static void nDaysInMonthTest(int year) {
        for (int month = 1; month <= 12; month++) {
            int days = nDaysInMonth(month, year);
            System.out.println("Month " + month + " has " + days + " days");
        }
    }

    public static boolean isLeapYear(int year) {
        return (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));
    }

    public static int nDaysInMonth(int month, int year) {
        switch (month) {
            case 2:
                return isLeapYear(year) ? 29 : 28;
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
            default:
                return 31;
        }
    }
}
