package models;

import java.util.*;

public class Opera {
    public static final Opera opera = new Opera();
    private final String name = "Opera Română din București";
    private final String address = "Splaiul independentei, nr 6, București";
    private final Map<String,String> program = Map.of("Luni", "12:00-20:00",
            "Marți", "10:00-23:00",
            "Miercuri","10:00-23:00",
            "Joi", "10:00-23:00",
            "Vineri", "10:00-23:00",
            "Sâmbătă", "10:00-23:00",
            "Duminică", "închis");
    private List<Hall> halls;
    private List<Spectacle> futureSpectacles;
    private List<Spectacle> pastSpectacles;

    public static int noSpectacles = 0;

    private Opera(){
        halls = new ArrayList<>();
        futureSpectacles = new ArrayList<>();
        pastSpectacles = new ArrayList<>();
    }

    public static Opera getOpera(){
        return opera;
    }

    public List<Spectacle> getPastSpectacles() {
        return pastSpectacles;
    }

    public void setPastSpectacles(List<Spectacle> pastSpectacles) {
        this.pastSpectacles = pastSpectacles;
    }

    public List<Spectacle> getFutureSpectacles() {
        return futureSpectacles;
    }

    public void setFutureSpectacles(List<Spectacle> futureSpectacles) {
        this.futureSpectacles = futureSpectacles;
    }

    public List<Hall> getHalls() {
        return halls;
    }

    public void setHalls(List<Hall> halls) {
        this.halls = halls;
    }

    public String getName() {
        return name;
    }

    // ARATA INFORMATII DESPRE OPERA
    public void showInfos(){
        System.out.println("Bine ați venit la " + name +
                "\nAdresă: " + address +
                "\nProgram" + "\n----------------");
        for(Map.Entry<String, String> m : program.entrySet()){
            System.out.print(m.getKey() + ": " + m.getValue() + " ");
        }
        System.out.println();
    }
    // ADAUGARE SPECTACOL
    public boolean addSpectacle(Spectacle spectacle){
        if(spectacle != null){
            this.futureSpectacles.add(spectacle);
            return true;
        }
        return false;
    }
    // STERGERE SPECTACOL dupa ID
    public boolean deleteSpectacle(int id){
        boolean found = false;
        if(futureSpectacles.size() != 0){
            for(int i = 0; i < futureSpectacles.size(); i++){
                if(futureSpectacles.get(i).getSpectacleId() == id){
                    found = true;
                    futureSpectacles.remove(i);
                    break;
                }
            }
        }
        return found;
    }
    //GASESTE SPECTACOL VIITOR DUPA ID
    public Spectacle findFutureSpectacle(int id){
        for(Spectacle s:futureSpectacles){
            if(s.getSpectacleId() == id){
                return s;
            }
        }
        return null;
    }
    //ARATA SPECTACOLELE VIITOARE
    public void showFutureSpectacles(){
        if(futureSpectacles != null) {
            if (futureSpectacles.size() == 0) {
                System.out.println("Nu sunt spectacole\n");
            } else {
                for (Spectacle s : futureSpectacles) {
                    System.out.println(s.getSpectacleId() + ": " + s);
                }
            }
        }
        else{
            System.out.println("Nu sunt spectacole!");
        }
    }
    // ARATA SPECTACOLELE TRECUTE
    public void showPastSpectacles(){
        if(pastSpectacles != null) {
            if (pastSpectacles.size() == 0) {
                System.out.println("Nu sunt spectacole\n");
            } else {
                for (Spectacle s : pastSpectacles) {
                    System.out.println(s.getSpectacleId() + ": " + s);
                }
            }
        }
        else{
            System.out.println("Nu sunt spectacole!");
        }
    }
    //ADAUGARE HALL
    public void addHall(Hall hall){

        halls.add(hall);
    }

    //STERGERE HALL dupa ID
    public void deleteHall(int id){
        int index = -1;
        for(int i = 0; i < halls.size(); i++){
            if(halls.get(i).getId() == id){
                index = i;
            }
        }
        if(index != -1){
            halls.remove(index);
        }
    }
    // VERIFICĂ DACĂ SALA ESTE DISPONIBILĂ
    public Hall[] hallsAvailable(int day, int month, int year){
        Set<Hall> hallsSet = new HashSet<>();
        for(Hall hall: halls){
            if(hall.getAvailability())
                hallsSet.add(hall);
        }
        for(Spectacle s: futureSpectacles){
            if(s.getDay() == day && s.getMonth() == month && s.getYear() == year)
                hallsSet.remove(s.getHall());
        }
        Hall[] result = new Hall[hallsSet.size()];
        hallsSet.toArray(result);

        return result;
    }
}
