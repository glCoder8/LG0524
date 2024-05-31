package com.rental;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class RentalAgreement {
    private Tool tool;
    private int rentalDays;
    private Date checkoutDate;
    private Date dueDate;
    private int chargeDays;
    private double preDiscountCharge;
    private int discountPercent;
    private double discountAmount;
    private double finalCharge;

    public RentalAgreement(Tool tool, int rentalDays, Date checkoutDate, int discountPercent) {
        this.tool = tool;
        this.rentalDays = rentalDays;
        this.checkoutDate = checkoutDate;
        this.discountPercent = discountPercent;

        calculateDueDate();
        calculateCharges();
    }

    private void calculateDueDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(checkoutDate);
        calendar.add(Calendar.DAY_OF_YEAR, rentalDays);
        dueDate = calendar.getTime();
    }

    private void calculateCharges() {
        chargeDays = calculateChargeDays();
        preDiscountCharge = chargeDays * tool.getDailyCharge();
        discountAmount = preDiscountCharge * discountPercent / 100.0;
        finalCharge = preDiscountCharge - discountAmount;
    }

    private int calculateChargeDays() {
        int chargeDays = 0;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(checkoutDate);

        for (int i = 0; i < rentalDays; i++) {
            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
            boolean isWeekend = (dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY);

            if (tool.isWeekdayCharge() && !isWeekend) {
                chargeDays++;
            }
            if (tool.isWeekendCharge() && isWeekend) {
                chargeDays++;
            }
            if (tool.isHolidayCharge() && isHoliday(calendar.getTime())) {
                chargeDays++;
            }

            calendar.add(Calendar.DAY_OF_YEAR, 1);
        }

        return chargeDays;
    }

    private boolean isHoliday(Date date) {
        // Add logic to check for specific holidays (e.g., Independence Day, Labor Day)
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd");
        String formattedDate = dateFormat.format(date);

        return formattedDate.equals("07/04") || (dateFormat.format(holidayObserved("Labor Day")).equals(formattedDate));
    }

    private Date holidayObserved(String holiday) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(checkoutDate);

        if (holiday.equals("Labor Day")) {
            calendar.set(Calendar.MONTH, Calendar.SEPTEMBER);
            calendar.set(Calendar.DAY_OF_MONTH, 1);

            while (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
                calendar.add(Calendar.DAY_OF_MONTH, 1);
            }
        }

        return calendar.getTime();
    }

    public void printAgreement() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yy");
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.US);

        System.out.println("Tool code: " + tool.getCode());
        System.out.println("Tool type: " + tool.getType());
        System.out.println("Tool brand: " + tool.getBrand());
        System.out.println("Rental days: " + rentalDays);
        System.out.println("Checkout date: " + dateFormat.format(checkoutDate));
        System.out.println("Due date: " + dateFormat.format(dueDate));
        System.out.println("Daily rental charge: " + currencyFormat.format(tool.getDailyCharge()));
        System.out.println("Charge days: " + chargeDays);
        System.out.println("Pre-discount charge: " + currencyFormat.format(preDiscountCharge));
        System.out.println("Discount percent: " + discountPercent + "%");
        System.out.println("Discount amount: " + currencyFormat.format(discountAmount));
        System.out.println("Final charge: " + currencyFormat.format(finalCharge));
    }

    public Tool getTool() {
        return tool;
    }

    public int getRentalDays() {
        return rentalDays;
    }

    public Date getCheckoutDate() {
        return checkoutDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public int getChargeDays() {
        return chargeDays;
    }

    public double getPreDiscountCharge() {
        return preDiscountCharge;
    }

    public int getDiscountPercent() {
        return discountPercent;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public double getFinalCharge() {
        return finalCharge;
    }
}