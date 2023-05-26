package models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public abstract class Spectacle implements Comparable<Spectacle>{
    protected int spectacleId;
    protected Hall hall;
    protected double minPrice;
    protected String nameSpectacle;
    protected String type;
    protected String description;
    protected List<List<Character>> availableSeats;
    protected int noAvailableSeats;
    protected int day;
    protected int month;
    protected int year;
    protected String startingHour;
    protected String endingHour;

    public Spectacle() {
    }
    public Spectacle(int spectacleId, Hall hall, double minPrice, String nameSpectacle, String type, int day, int month, int year, String startingHour, String endingHour){
        this.spectacleId = spectacleId;
        this.hall = hall;
        this.minPrice = minPrice;
        this.nameSpectacle = nameSpectacle;
        this.type = type;
        this.availableSeats = new ArrayList<>();    // vom avea o lista cu 'x'/'_', unde 'x' marcheaza locurile ocupate
        for(int i = 0; i < hall.getRows(); i++){
            List<Character> aux = new ArrayList<>();
            for(int j = 0; j < hall.getColumns(); j++){
                aux.add('_');
            }
            availableSeats.add(aux);
        }

        this.day = day;
        this.month = month;
        this.year = year;
        this.startingHour = startingHour;
        this.endingHour = endingHour;
        this.noAvailableSeats = hall.getSeatsNo();
    }

    public void showAvailableSeats(){
        System.out.print(" ");
        for(int i = 0; i < hall.getColumns();i++){
            System.out.print((char)('A'+ i) + " "); // fiecare rand va incepe cu cate o litera din alfabet
        }
        System.out.println();
        for(int i = 0; i < hall.getRows(); i++){
            System.out.print((i+1) + " ");
            for(int j = 0; j < hall.getColumns(); j++){
                System.out.print(availableSeats.get(i).get(j) + " ");
            }
            System.out.println();
        }
    }

    public void occupySeats(String seat){
        char c = seat.charAt(0);
        int no = Integer.parseInt(seat.substring(1)) - 1;
        List<Character> l = availableSeats.get(no);
        l.set(c-'A','x');
        availableSeats.set(no, l);
    }

    public abstract double calculatePrice(String seat);

    public String getNameSpectacle() {
        return nameSpectacle;
    }

    public void setNameSpectacle(String nameSpectacle) {
        this.nameSpectacle = nameSpectacle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int compareTo(Spectacle s) {
        return this.getNameSpectacle().compareTo(s.getNameSpectacle());
    }

    public int getNoAvailableSeats() {
        return noAvailableSeats;
    }

    public void setNoAvailableSeats(int noAvailableSeats) {
        this.noAvailableSeats = noAvailableSeats;
    }

    public Hall getHall() {
        return hall;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
    }

    public double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(double minPrice) {
        this.minPrice = minPrice;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getStartingHour() {
        return startingHour;
    }

    public void setStartingHour(String startingHour) {
        this.startingHour = startingHour;
    }

    public String getEndingHour() {
        return endingHour;
    }

    public void setEndingHour(String endingHour) {
        this.endingHour = endingHour;
    }

    public int getSpectacleId() {
        return spectacleId;
    }

    public void setSpectacleId(int spectacleId) {
        this.spectacleId = spectacleId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public List<List<Character>> getAvailableSeats() {
        return availableSeats;
    }

    @Override
    public String toString() {
        return "models.Spectacle: " +
                nameSpectacle + " - " +
                day + "/" +
                month + "/" +
                year + " " +
                startingHour + "-" +
                endingHour;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Spectacle spectacle = (Spectacle) o;
        return spectacleId == spectacle.spectacleId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(spectacleId);
    }
}
