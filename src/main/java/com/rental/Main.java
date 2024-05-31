package com.rental;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ToolRentalService service = new ToolRentalService();
        Scanner scanner = new Scanner(System.in);
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

        try {
            System.out.print("Enter tool code: ");
            String toolCode = scanner.nextLine();

            System.out.print("Enter rental days: ");
            int rentalDays = Integer.parseInt(scanner.nextLine());

            System.out.print("Enter discount percent: ");
            int discountPercent = Integer.parseInt(scanner.nextLine());

            System.out.print("Enter checkout date (MM/dd/yyyy): ");
            String checkoutDateString = scanner.nextLine();
            Date checkoutDate = dateFormat.parse(checkoutDateString);

            RentalAgreement agreement = service.checkout(toolCode, rentalDays, discountPercent, checkoutDate);
            agreement.printAgreement();
        } catch (ParseException e) {
            System.out.println("Error parsing date.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}