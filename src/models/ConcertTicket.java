package models;

public class ConcertTicket extends Ticket {
    public ConcertTicket(String seat, double price, Spectacle spectacle) {
        super(seat, price, spectacle);
    }

    public void print(){
        System.out.println("Biletul pentru concertul " + spectacle.getNameSpectacle() + " la locul " + seat + " costa " + price + " lei.");
    }
}
