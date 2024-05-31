package com.rental;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ToolRentalService {
    private Map<String, Tool> tools = new HashMap<>();

    public ToolRentalService() {
        tools.put("CHNS", new Tool("CHNS", "Chainsaw", "Stihl", 1.49, true, false, true));
        tools.put("LADW", new Tool("LADW", "Ladder", "Werner", 1.99, true, true, false));
        tools.put("JAKD", new Tool("JAKD", "Jackhammer", "DeWalt", 2.99, true, false, false));
        tools.put("JAKR", new Tool("JAKR", "Jackhammer", "Ridgid", 2.99, true, false, false));
    }

    public RentalAgreement checkout(String toolCode, int rentalDays, int discountPercent, Date checkoutDate) throws Exception {
        if (!tools.containsKey(toolCode)) {
            throw new Exception("Tool code not found.");
        }
        if (rentalDays <= 0) {
            throw new Exception("Rental days must be positive.");
        }
        if (discountPercent < 0 || discountPercent > 100) {
            throw new Exception("Discount percent must be between 0 and 100.");
        }

        Tool tool = tools.get(toolCode);
        RentalAgreement agreement = new RentalAgreement(tool, rentalDays, checkoutDate, discountPercent);
        return agreement;
    }

    public Tool getTool(String toolCode) {
        return tools.get(toolCode);
    }
}