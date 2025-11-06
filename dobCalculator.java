import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class dobCalculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter 'DOB' to calculate age from Date of Birth or 'AGE' to calculate DOB:");
        String option = scanner.nextLine();

        System.out.println("Enter date format (e.g., YYYY-MM-DD, DD-MM-YYYY, MM-DD-YYYY):");
        String dateFormat = scanner.nextLine();

        System.out.println("Enter delimiter character (e.g., '-', '/', '.'): ");
        String delimiter = scanner.nextLine();

        // Prepare date format
        String actualDateFormat = dateFormat.replace("dlc", delimiter);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(actualDateFormat);

        if (option.equalsIgnoreCase("DOB")) {
            System.out.println("Enter DOB (e.g., 2000" + delimiter + "02" + delimiter + "28):");
            String dobInput = scanner.nextLine();
            calculateAge(dobInput, formatter);
        } else if (option.equalsIgnoreCase("AGE")) {
            System.out.println("Enter AGE in years (e.g., 22): ");
            int years = scanner.nextInt();
            calculateDOB(years, formatter);
        } else {
            System.out.println("Invalid option. Please enter either 'DOB' or 'AGE'.");
        }

        scanner.close();
    }

    private static void calculateAge(String dobInput, DateTimeFormatter formatter) {
        try {
            LocalDate dob = LocalDate.parse(dobInput, formatter);
            LocalDate today = LocalDate.now();

            long years = ChronoUnit.YEARS.between(dob, today);
            long months = ChronoUnit.MONTHS.between(dob.plusYears(years), today);
            long days = ChronoUnit.DAYS.between(dob.plusYears(years).plusMonths(months), today);

            System.out.println("Age is " + years + " years, " + months + " months, " + days + " days.");
        } catch (Exception e) {
            System.out.println("Invalid date. Please make sure it follows the format and is a real date.");
        }
    }

    private static void calculateDOB(int years, DateTimeFormatter formatter) {
        LocalDate today = LocalDate.now();
        LocalDate dob = today.minusYears(years);

        System.out.println("DOB is " + dob.format(formatter));
    }
}