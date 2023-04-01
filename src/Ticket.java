// clasa abstracta
public abstract class Ticket {
    protected String seat;
    protected double price;
    protected Spectacle spectacle;

    public Ticket(){

    }
    public Ticket(String seat, double price, Spectacle spectacle){
        this.seat = seat;
        this.price = price;
        this.spectacle = spectacle;
    }

    public String getSeat(){
        return seat;
    }

    public void setSeat(String seat){
        this.seat = seat;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Spectacle getSpectacle() {
        return spectacle;
    }

    public void setSpectacle(Spectacle spectacle) {
        this.spectacle = spectacle;
    }

    public abstract void print();
}
