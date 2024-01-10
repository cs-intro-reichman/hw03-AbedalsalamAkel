
public class Calendar {
    static int day = 1;
    static int month = 1;
    static int year;
    static int daysInMonth = 31; // January has 31 days

    public static void main(String[] args) {
        // Check if a year has been provided
        if (args.length < 1) {
            System.out.println("Please provide a year as an argument.");
            return;
        }

        // Parse the provided year
        year = Integer.parseInt(args[0]);
        int endYear = year + 1; // The year after the provided year

        // Loop through the entire year provided
        while (year < endYear) {
            // Print the current date
            System.out.println(day + "/" + month + "/" + year);

            // Move to the next day
            day++;
            if (day > daysInMonth) {
                day = 1; // Reset to the first day
                month++; // Move to the next month

                if (month > 12) {
                    month = 1; // Reset to January
                    year++; // Increment the year
                }

                // Update the number of days in the new month
                daysInMonth = getDaysInMonth(month, year);
            }
        }
    }

    // Method to determine if a year is a leap year
    private static boolean isLeapYear(int year) {
        // Leap year if divisible by 4 but not by 100, or divisible by 400
        if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
            return true;
        } else {
            return false;
        }
    }

    // Method to get the number of days in a month
    private static int getDaysInMonth(int month, int year) {
        // February has 28 or 29 days
        if (month == 2) {
            if (isLeapYear(year)) {
                return 29;
            } else {
                return 28;
            }
        }
        // April, June, September, November have 30 days
        else if (month == 4 || month == 6 || month == 9 || month == 11) {
            return 30;
        }
        // All other months have 31 days
        else {
            return 31;
        }
    }
}
