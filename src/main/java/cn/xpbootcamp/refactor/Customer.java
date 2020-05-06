package cn.xpbootcamp.refactor;

import java.util.Enumeration;
import java.util.Vector;

public class Customer {

    private String name;
    private Vector<Rental> rentals = new Vector<>();

    Customer(String name) {
        this.name = name;
    }

    void addRental(Rental rental) {
        rentals.addElement(rental);
    }

    public String getName() {
        return name;
    }

    String statement() {
        StringBuilder result = new StringBuilder("Rental Record for " + getName() + "：\n");
        StringBuilder amountLines = getAmountLines();
        result.append(amountLines);
        int frequentRenterPoints = getFrequentRenterPoints();
        result.append("You earned ").append(frequentRenterPoints).append(" frequent renter points");
        return result.toString();
    }

    private StringBuilder getAmountLines() {
        StringBuilder amountLines = new StringBuilder();
        double totalAmount = 0d;
        Enumeration<Rental> rentals = this.rentals.elements();
        while (rentals.hasMoreElements()) {
            Rental each = rentals.nextElement();
            amountLines.append(each.generateAmountLine());
            totalAmount += each.getAmount();
        }
        amountLines.append("Amount owed is ").append(totalAmount).append("\n");
        return amountLines;
    }

    private int getFrequentRenterPoints() {
        int frequentRenterPoints = 0;
        Enumeration<Rental> rentals = this.rentals.elements();
        while (rentals.hasMoreElements()) {
            Rental each = rentals.nextElement();
            frequentRenterPoints += each.getRenterPoints();
        }
        return frequentRenterPoints;
    }

}
