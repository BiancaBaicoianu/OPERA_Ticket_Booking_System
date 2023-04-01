public class Ballet extends Spectacle{
    private boolean logeSeat; //daca este loc la loja, va avea suprataxa

    public Ballet(int spectacleId, Hall hall, double minPrice, String nameSpectacle, String type, int day, int month, int year, String startingHour, String endingHour, boolean logeSeat) {
        super(spectacleId, hall, minPrice, nameSpectacle, type, day, month, year, startingHour, endingHour);

        this.logeSeat = logeSeat;
    }

    public boolean isLogeSeat() {
        return logeSeat;
    }

    public void setLogeSeat(boolean logeSeat) {
        this.logeSeat = logeSeat;
    }

    public double calculatePrice(String seat){
        double total = this.minPrice;
        if(getStartingHour().substring(2).compareTo("18") >= 0) // daca spectacolul incepe dupa ora 18 => adaos 15%
            total *= 1.15;
        if(isLogeSeat()){
            total *= 1.3; // adaos 30% daca este log in loja
        }
        return total;
    }
}
