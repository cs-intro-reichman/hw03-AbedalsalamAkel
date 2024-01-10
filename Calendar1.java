public class Calendar1 {

    static int dayOfMonth = 1;
    static int month = 1;
    static int year = 1900;
    static int dayOfWeek = 1; 
    static int nDaysInMonth = 31; 
    static int sundayCount = 0;

    public static void main(String[] args) {
        while (year < 2000) {
            if (dayOfWeek == 0 && dayOfMonth == 1) {
                sundayCount++;
            }
            advance();
        }

        System.out.println("Number of Sundays on the first of the month during the 20th century: " + sundayCount);
    }

    public static void advance() {
        dayOfWeek = (dayOfWeek + 1) % 7;
        dayOfMonth++;
        
        if (dayOfMonth > nDaysInMonth) {
            dayOfMonth = 1;
            month++;
            if (month > 12) {
                month = 1;
                year++;
            }
            nDaysInMonth = nDaysInMonth(month, year);
        }
    }

    public static boolean isLeapYear(int year) {
        return (year % 4 == 0) && (year % 100 != 0 || year % 400 == 0);
    }

    public static int nDaysInMonth(int month, int year) {
        if (month == 2) {
            return isLeapYear(year) ? 29 : 28;
        } else if (month == 4 || month == 6 || month == 9 || month == 11) {
            return 30;
        } else {
            return 31;
        }
    }
}
