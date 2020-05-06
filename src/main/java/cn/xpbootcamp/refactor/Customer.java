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
            double amount = getSingleAmount(each);
            amountLines.append("\t")
                  .append(each.getMovie().getTitle())
                  .append("\t")
                  .append(amount).append("\n");
            totalAmount += amount;
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

    private double getSingleAmount(Rental each) {
        double thisAmount = 0d;
        switch (each.getMovie().getPriceCode()) {
            case Movie.HISTORY:
                thisAmount += 2;
                if (each.getDaysRented() > 2)
                    thisAmount += (each.getDaysRented() - 2) * 1.5;
                break;
            case Movie.NEW_RELEASE:
                thisAmount += each.getDaysRented() * 3;
                break;
            case Movie.CAMPUS:
                thisAmount += 1.5;
                if (each.getDaysRented() > 3)
                    thisAmount += (each.getDaysRented() - 3) * 1.5;
                break;
        }
        return thisAmount;
    }

}
