package models;

import java.util.Objects;

public class Hall {
    private int id;
    private String name;
    private int floor;
    private int seatsNo;
    private boolean availability = true;
    private int rows;
    private int columns;

    public Hall() {
    }
    public Hall(int id, String name, int floor, boolean available, int rows, int columns) {
        this.id = id;
        this.name = name;
        this.floor = floor;
        this.availability = available;
        this.rows = rows;
        this.columns = columns;
        this.seatsNo = rows * columns;
    }
    public Hall( String name, int floor, boolean available, int rows, int columns) {
        this.name = name;
        this.floor = floor;
        this.availability = available;
        this.rows = rows;
        this.columns = columns;
        this.seatsNo = rows * columns;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public int getSeatsNo() {
        return seatsNo;
    }

    public void setSeatsNo(int seatsNo) {
        this.seatsNo = seatsNo;
    }

    public boolean getAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public String toString(){
        return "models.Hall " + name + "\n Floor " + floor;
    }

    public void showInfos(){
        System.out.println("Id: " + id +
                "\nHall: " + name +
                "\nAvailability: " + availability +
                "\n Seats number: " + seatsNo +
                "\n Floor: " + floor + "\n");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hall hall = (Hall) o;
        return id == hall.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, floor, seatsNo, availability, rows, columns);
    }
}
