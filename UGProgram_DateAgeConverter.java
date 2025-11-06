import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

public class UGProgram_DateAgeConverter {

    public static void main(String[] args) {
        if (args.length != 4) {
            printUsageAndExit("Incorrect number of arguments.");
        }

        String first = args[0].trim();
        String refDateStr = args[1].trim();
        String dateFormatToken = args[2].trim();
        String dlcArg = args[3].trim();

        if (dlcArg.length() != 1 || "-/.".indexOf(dlcArg.charAt(0)) < 0) {
            printUsageAndExit("Delimiter (4th arg) must be one character and one of '-', '/', '.'");
        }
        char dlc = dlcArg.charAt(0);

        String pattern = buildPatternFromToken(dateFormatToken, dlc);
        if (pattern == null) {
            printUsageAndExit("Invalid date format token (3rd arg). Use DD<dlc>MM<dlc>YYYY or YYYY<dlc>MM<dlc>DD or MM<dlc>DD<dlc>YYYY");
        }

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern(pattern, Locale.ENGLISH);

        LocalDate refDate = null;
        try {
            refDate = LocalDate.parse(refDateStr, fmt);
        } catch (DateTimeParseException e) {
            System.err.println("Error: Reference date '" + refDateStr + "' does not match format '" + dateFormatToken + "' with delimiter '" + dlc + "'.");
            System.err.println("Reason: " + e.getMessage());
            System.exit(2);
        }

        if (first.toUpperCase().startsWith("DOB=")) {
            String dobStr = first.substring(4);
            LocalDate dob = null;
            try {
                dob = LocalDate.parse(dobStr, fmt);
            } catch (DateTimeParseException e) {
                System.err.println("Error: DOB '" + dobStr + "' does not match format '" + dateFormatToken + "' with delimiter '" + dlc + "'.");
                System.err.println("Reason: " + e.getMessage());
                System.exit(3);
            }

            if (dob.isAfter(refDate)) {
                System.err.println("Refuse to process: DOB " + dobStr + " is after reference date " + refDateStr + ".");
                System.exit(4);
            }

            Period age = Period.between(dob, refDate);
            System.out.println("Age is " + age.getYears() + " years, " + age.getMonths() + " months, " + age.getDays() + " days");
            System.exit(0);

        } else if (first.toUpperCase().startsWith("AGE=")) {
            String ageStr = first.substring(4);
            String[] tokens = ageStr.split(java.util.regex.Pattern.quote(String.valueOf(dlc)));
            if (tokens.length != 3) {
                System.err.println("Error: AGE must be provided as Years" + dlc + "Months" + dlc + "Days (e.g. AGE=19" + dlc + "10" + dlc + "27)");
                System.exit(5);
            }
            int years=0, months=0, days=0;
            try {
                years = Integer.parseInt(tokens[0]);
                months = Integer.parseInt(tokens[1]);
                days = Integer.parseInt(tokens[2]);
            } catch (NumberFormatException e) {
                System.err.println("Error: AGE tokens must be numeric. Found: '" + ageStr + "'.");
                System.exit(6);
            }

            if (years < 0 || months < 0 || days < 0) {
                System.err.println("Error: AGE components must be non-negative.");
                System.exit(7);
            }

            Period agePeriod = Period.of(years, months, days);
            LocalDate dob = null;
            try {
                dob = refDate.minus(agePeriod);
            } catch (DateTimeException e) {
                System.err.println("Error computing DOB from AGE and reference date: " + e.getMessage());
                System.exit(8);
            }

            String dobFormatted = dob.format(fmt);
            System.out.println("DOB is " + dobFormatted);
            System.exit(0);

        } else {
            printUsageAndExit("First argument must begin with either 'DOB=' or 'AGE='");
        }
    }

    private static String buildPatternFromToken(String token, char dlc) {
        String lower = token.toUpperCase();
        String dlcStr = String.valueOf(dlc);
        String[] parts = lower.split(java.util.regex.Pattern.quote(dlcStr));
        if (parts.length != 3) return null;
        StringBuilder pattern = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            String p = parts[i];
            if (p.equals("DD")) pattern.append("dd");
            else if (p.equals("MM")) pattern.append("MM");
            else if (p.equals("YYYY")) pattern.append("yyyy");
            else return null;
            if (i < 2) pattern.append(dlc);
        }
        return pattern.toString();
    }

    private static void printUsageAndExit(String message) {
        System.err.println("Error: " + message);
        System.err.println("Usage: java UGProgram_DateAgeConverter \"DOB=dd<dlc>MM<dlc>YYYY\"|\"AGE=YY<dlc>MM<dlc>DD\" <referenceDate> <DateFormatToken> <dlc>");
        System.err.println("Example: java UGProgram_DateAgeConverter \"DOB=28-02-2001\" \"27-02-2024\" \"DD-MM-YYYY\" \"-\"");
        System.err.println("Example (AGE): java UGProgram_DateAgeConverter \"AGE=19-10-27\" \"27-10-2024\" \"DD-MM-YYYY\" \"-\"");
        System.err.println("Note: delimiter (4th arg) must be one of '-', '/', '.' and the 3rd arg must use the same delimiter token positions.");
        System.exit(1);
    }
}


