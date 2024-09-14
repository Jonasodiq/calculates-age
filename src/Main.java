
// 5 sep 2024 Jonas N Övning-4: Convert Data Types

import java.util.Scanner;
import java.time.Period;
import java.time.LocalDate;
import java.text.NumberFormat;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String again;
        do {
            int birthYear = 0;
            int birthMonth = 0;
            int birthDay = 0;
            boolean validInput = false;

            while (!validInput) {
                try {
                    System.out.println("Skriv in ditt födelseår (1900):");
                    birthYear = sc.nextInt();
                    System.out.println("Skriv in din födelsemånad (1-12):");
                    birthMonth = sc.nextInt();
                    System.out.println("Skriv in din födelsedag (1-31):");
                    birthDay = sc.nextInt();

                    // Create a LocalDate object based on the input
                    LocalDate birthDate = LocalDate.of(birthYear, birthMonth, birthDay);
                    LocalDate today = LocalDate.now(); // Hämta dagens datum

                    // Check if the date of birth is in the future
                    if (birthDate.isAfter(today)) {
                        System.out.println("Födelsedatumet kan inte vara i framtiden. Försök igen.");
                    } else {
                        validInput = true;
                    }
                } catch (Exception e) {
                    System.out.println("Ogiltig inmatning. Försök igen.");
                    sc.next(); // Clear incorrect input
                }
            }

            // Get today's date and calculate age based on year, month and day
            LocalDate today = LocalDate.now();
            LocalDate birthDate = LocalDate.of(birthYear, birthMonth, birthDay);
            Period agePeriod = Period.between(birthDate, today);

            int years = agePeriod.getYears();
            int months = agePeriod.getMonths();
            int days = agePeriod.getDays();

            // Calculate total number of days including leap years
            int leapYears = countLeapYears(birthYear, today.getYear());
            // Approximation for month to days
            int totalDays = (365 * years) + leapYears + days + (months * 30);
            double totalHours = 24 * totalDays;
            double totalMinutes = 60 * totalHours;
            double totalSeconds = 60 * totalMinutes;

            long roundedDays = roundToInt(totalDays);
            long roundedHours = roundToInt(totalHours);
            long roundedMinutes = roundToInt(totalMinutes);
            long roundedSeconds = roundToInt(totalSeconds);

            NumberFormat numberFormat = NumberFormat.getInstance();

            // Print the result
            System.out.println("Hittills har du levt:\n"
                    + years + " år, "
                    + months + " månader, "
                    + days + " dagar.\nTotalt:\n"
                    + numberFormat.format(roundedDays) + " dagar,\n"
                    + numberFormat.format(roundedHours) + " timmar,\n"
                    + numberFormat.format(roundedMinutes) + " minuter,\n"
                    + numberFormat.format(roundedSeconds) + " sekunder.\n"
            );

            System.out.println("Vill du räkna igen? (y/n)");
            again = sc.next();

        } while (again.equalsIgnoreCase("y"));
        sc.close();
    }

    // Method for counting the number of leap years between two years
    static int countLeapYears(int startYear, int endYear) {
        int leapYears = 0;
        for (int year = startYear; year <= endYear; year++) {
            if (isLeapYear(year)) {
                leapYears++;
            }
        }
        return leapYears;
    }

    // Method to check if a year is a leap year
    static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    // Function to round a number to the nearest integer
    public static long roundToInt(double number) {
        return Math.round(number);
    }
}