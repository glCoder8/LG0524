package com.rental;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ToolRentalServiceTest {
    public static void main(String[] args) {
        ToolRentalService service = new ToolRentalService();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

        System.out.println("----------TEST1----------");
        try {
            Date checkoutDate = dateFormat.parse("09/03/2015");
            RentalAgreement agreement = service.checkout("JAKR", 5, 101, checkoutDate);
            agreement.printAgreement();
        } catch (ParseException e) {
            System.out.println("Error parsing date.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println();
        System.out.println("----------TEST2----------");
        try {
            Date checkoutDate = dateFormat.parse("07/02/2020");
            RentalAgreement agreement = service.checkout("LADW", 3, 10, checkoutDate);
            agreement.printAgreement();
        } catch (ParseException e) {
            System.out.println("Error parsing date.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println();
        System.out.println("----------TEST3----------");
        try {
            Date checkoutDate = dateFormat.parse("07/02/2015");
            RentalAgreement agreement = service.checkout("CHNS", 5, 25, checkoutDate);
            agreement.printAgreement();
        } catch (ParseException e) {
            System.out.println("Error parsing date.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println();
        System.out.println("----------TEST4----------");
        try {
            Date checkoutDate = dateFormat.parse("09/03/2015");
            RentalAgreement agreement = service.checkout("JAKD", 6, 0, checkoutDate);
            agreement.printAgreement();
        } catch (ParseException e) {
            System.out.println("Error parsing date.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println();
        System.out.println("----------TEST5----------");
        try {
            Date checkoutDate = dateFormat.parse("07/02/2015");
            RentalAgreement agreement = service.checkout("JAKR", 9, 0, checkoutDate);
            agreement.printAgreement();
        } catch (ParseException e) {
            System.out.println("Error parsing date.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println();
        System.out.println("----------TEST6----------");
        try {
            Date checkoutDate = dateFormat.parse("07/02/2020");
            RentalAgreement agreement = service.checkout("JAKR", 4, 50, checkoutDate);
            agreement.printAgreement();
        } catch (ParseException e) {
            System.out.println("Error parsing date.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}