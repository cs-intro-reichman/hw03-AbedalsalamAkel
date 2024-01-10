public class Calendar1 {

    static int currentDay = 1;   
    static int currentMonth = 1;
    static int currentYear = 1900;
    static int dayOfWeekIndex = 2; // 1.1.1900 was a Monday (2nd day of the week)
    static int daysInCurrentMonth = 31; 
    static int sundaysOnFirstCount = 0;
    
    public static void main(String[] args) {
        while (currentYear < 2000) {
            printDate(currentDay, currentMonth, currentYear, dayOfWeekIndex);
            if (dayOfWeekIndex == 1 && currentDay == 1) {
                sundaysOnFirstCount++;
            }
            advanceDay();
        }
        System.out.println("During the 20th century, " + sundaysOnFirstCount + " Sundays fell on the first day of the month");
    }
    
    private static void printDate(int day, int month, int year, int dayOfWeek) {
        System.out.print(day + "/" + month + "/" + year);
        if (dayOfWeek == 1) { // 1 represents Sunday
            System.out.print(" Sunday");
        }
        System.out.println();
    }
    
    private static void advanceDay() {
        dayOfWeekIndex = (dayOfWeekIndex % 7) + 1;
        currentDay++;
        if (currentDay > daysInCurrentMonth) {
            advanceMonth();
        }
        daysInCurrentMonth = calculateDaysInMonth(currentMonth, currentYear);
    }
    
    private static void advanceMonth() {
        currentMonth++;
        currentDay = 1;
        if (currentMonth > 12) {
            currentMonth = 1;
            currentYear++;
        }
    }
    
    private static boolean isLeapYear(int year) {
        return year % 4 == 0 && (year % 100 != 0 || year % 400 == 0);
    }
    
    private static int calculateDaysInMonth(int month, int year) {
        if (month == 2) {
            return isLeapYear(year) ? 29 : 28;
        } else if (month == 4 || month == 6 || month == 9 || month == 11) {
            return 30;
        }
        return 31;
    }
}
