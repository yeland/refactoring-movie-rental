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
}
