package cn.xpbootcamp.refactor;

public class Rental {

    private Movie movie;
    private int daysRented;

    Rental(Movie movie, int daysRented) {
        this.movie = movie;
        this.daysRented = daysRented;
    }

    Movie getMovie() {
        return movie;
    }

    int getDaysRented() {
        return daysRented;
    }


    public int getRenterPoints() {
        int frequentRenterPoints = 1;
        if ((movie.getPriceCode() == Movie.NEW_RELEASE) && daysRented > 1) {
            frequentRenterPoints++;
        }
        return frequentRenterPoints;
    }

    public StringBuilder generateAmountLine() {
        StringBuilder amountLine = new StringBuilder();
        return amountLine.append("\t")
                .append(movie.getTitle())
                .append("\t")
                .append(getAmount()).append("\n");
    }

    public double getAmount() {
        double thisAmount = 0d;
        switch (getMovie().getPriceCode()) {
            case Movie.HISTORY:
                thisAmount += 2;
                if (getDaysRented() > 2)
                    thisAmount += (getDaysRented() - 2) * 1.5;
                break;
            case Movie.NEW_RELEASE:
                thisAmount += getDaysRented() * 3;
                break;
            case Movie.CAMPUS:
                thisAmount += 1.5;
                if (getDaysRented() > 3)
                    thisAmount += (getDaysRented() - 3) * 1.5;
                break;
        }
        return thisAmount;
    }
}
