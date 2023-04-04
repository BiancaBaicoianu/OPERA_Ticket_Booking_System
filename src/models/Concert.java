package models;

public class Concert extends Spectacle {
    private boolean withSeat; // exista posibilitatea de a sta in picioare

    public Concert(int spectacleId, Hall hall, double minPrice, String nameSpectacle, String type, int day, int month, int year, String startingHour, String endingHour, boolean withSeat) {
        super(spectacleId, hall, minPrice, nameSpectacle, type, day, month, year, startingHour, endingHour);
        this.withSeat = withSeat;
    }

    public boolean isWithSeat() {
        return withSeat;
    }

    public void setWithSeat(boolean withSeat) {
        this.withSeat = withSeat;
    }


    public double calculatePrice(String seat){
        if(!withSeat){ // stabilirea biletelor in functie de locul din sala
            double total = this.minPrice;
            char c = seat.charAt(0);
            int row = c - 'A';
            total += 15 * (hall.getRows() - row);

            return total;
        }
        else{   // pretul min la care se adauga TVA de 9%
            return this.minPrice * 1.09;
        }
    }

}
