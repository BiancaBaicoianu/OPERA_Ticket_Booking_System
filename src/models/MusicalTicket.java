package models;

public class MusicalTicket extends Ticket {
    public MusicalTicket(String seat, double price, Spectacle spectacle) {
        super(seat, price, spectacle);
    }
    public void print(){
        System.out.println("Biletul pentru musical-ul " + spectacle.getNameSpectacle() + " la locul " + seat + " costa " + price + " lei.");
    }
}
