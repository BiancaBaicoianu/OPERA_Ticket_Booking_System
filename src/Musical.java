public class Musical extends Spectacle{
    private int noActs;

    public Musical(int spectacleId, Hall hall, double minPrice, String nameSpectacle, String type, int day, int month, int year, String startingHour, String endingHour, int noActs) {
        super(spectacleId, hall, minPrice, nameSpectacle, type, day, month, year, startingHour, endingHour);
        this.noActs = noActs;
    }

    public int getNoActs() {
        return noActs;
    }

    public void setNoActs(int noActs) {
        this.noActs = noActs;
    }

    public double calculatePrice(String seat){
        double total = this.minPrice;
        char c = seat.charAt(0);
        int number = Integer.parseInt(seat.substring(1));
        int row = c - 'A';
        // locurile din primele 3 randuri au un pret cu 20% mai mare,
        // iar cele din primele 3 randuri, centru, mai au un adaos de 30%
        total += 20 * (hall.getRows() - row);
        if(number >= hall.getColumns()*(double)(1/3) && number <= hall.getColumns()*(double)(2/3))
            total *= 1.1;
        return total;
    }
}
