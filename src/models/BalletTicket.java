package models;

public class BalletTicket extends Ticket {
    public BalletTicket(String seat, double price, Spectacle spectacle) {
        super(seat, price, spectacle);
    }
    public void print(){ // folosind colectii
        System.out.println("Biletul pentru baletul " + spectacle.getNameSpectacle() + " la locul " + seat + " costa " + price + " lei.");   }
    }
