package services;

import models.*;

import java.util.List;
import java.util.Scanner;
public class AdminService {
    public static AdminService admin;
    private int id;
    private String username;
    private String password;

    public AdminService(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public AdminService() {
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    // verifica daca adminul cu user si parola exista in lista mea de admini
    public boolean checkAdmin(String username, String password, List<AdminService> admins){
        for(AdminService admin : admins){
            if(admin.username.equals(username) && admin.password.equals(password))
                return true;
        }
        return false;
    }

    public static AdminService getAdmin(){
        if(admin == null){
            admin = new AdminService();
        }
        return admin;
    }
    // ADAUGA SPECTACOL
    public void addSpectacle(Scanner scanner){
        Opera opera = Opera.getOpera();
        scanner.nextLine();

        System.out.print("Nume spectacol: ");
        String spectacleName = scanner.nextLine();
        System.out.print("Preț minim: ");
        double minPrice = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Tip spectacol: ");
        String type = scanner.next();
        System.out.print("Ora de început: ");
        String startingHour = scanner.next();
        System.out.print("Ora de încheiere: ");
        String endingHour = scanner.next();
        System.out.print("Zi: ");
        int day = scanner.nextInt();
        System.out.print("Luna: ");
        int month = scanner.nextInt();
        System.out.print("An: ");
        int year = scanner.nextInt();

        System.out.print("Săli disponibile: ");
        Hall[] hallsAvailable = opera.hallsAvailable(day, month, year);
        for(int i = 0; i < hallsAvailable.length; i++){
            System.out.println((i+1) + ". " + hallsAvailable[i].getName());
        }
        System.out.print("Introdu numărul săli pe care o alegi: ");
        int noHall = scanner.nextInt() - 1;

        if(noHall >= hallsAvailable.length || noHall < 0){
            System.out.println("Sală invalidă!");
        }

        System.out.println("Concert/Balet/models.Musical? Pentru a ieși, scrie EXIT");
        Spectacle spectacle;
        while(true){
            String typeSpectacle = scanner.next();
            if(typeSpectacle.toLowerCase().compareTo("concert")==0){
                boolean withSeat;
                System.out.println("Este cu loc pe scaun sau nu?(da/nu)");
                while(true){
                    String ans = scanner.next();
                    if(ans.equalsIgnoreCase("da")){
                        withSeat = true;
                        break;
                    }
                    else if(ans.equalsIgnoreCase("nu")){
                        withSeat = false;
                        break;
                    }
                    else{
                        System.out.println("Opțiune invalidă");
                    }
                }
                Opera.noSpectacles++;
                spectacle = new Concert(Opera.noSpectacles, hallsAvailable[noHall], minPrice, spectacleName, type, day, month, year, startingHour, endingHour, withSeat);
                break;
            }
            else if (typeSpectacle.toLowerCase().compareTo("balet")==0){
                boolean logeSeat;
                System.out.println("Exista locuri in loja?(da/nu)");
                while(true){
                    String ans2 = scanner.next();
                    if(ans2.equalsIgnoreCase("da")){
                        logeSeat = true;
                        break;
                    }
                    else if(ans2.equalsIgnoreCase("nu")){
                        logeSeat = false;
                        break;
                    }
                    else{
                        System.out.println("Opțiune invalidă!");
                    }
                }
                Opera.noSpectacles++;
                spectacle = new Ballet(Opera.noSpectacles, hallsAvailable[noHall],minPrice, spectacleName, type, day, month, year, startingHour, endingHour, logeSeat );
                break;
            }
            else if (typeSpectacle.toLowerCase().compareTo("musical") == 0){
                int noActs;
                System.out.println("Introdu numărul de acte: ");
                while(true) {
                    int ans3 = scanner.nextInt();
                    noActs = ans3;
                    break;
                }

                Opera.noSpectacles++;
                spectacle = new Musical(Opera.noSpectacles, hallsAvailable[noHall], minPrice, spectacleName, type, day, month, year, startingHour, endingHour,noActs);
                break;
            }
            else if(typeSpectacle.toLowerCase().compareTo("exit") == 0){
                return;
            }
            else{
                System.out.println("Opțiune invalidă");
            }
        }

    }
    // STERGE UN SPECTACOL
    public void deleteSpectacle(Scanner scanner){
        Opera opera = Opera.getOpera();

        System.out.println("Poti șterge numai spectacole viitoare:");
        opera.showFutureSpectacles();

        if(opera.getFutureSpectacles() != null){
            if(opera.getFutureSpectacles().size() == 0){
                return;
            }
        }
        else if(opera.getFutureSpectacles() == null){
            return;
        }

        System.out.print("Introdu id-ul pe care doriți să-l ștergeți:");
        int id = scanner.nextInt();

        opera.deleteSpectacle(id);

    }
    // VIZIONEAZA SPECTACOLE VIITOARE
    public void seeFutureSpectacles() {
        Opera opera = Opera.getOpera();
        opera.showFutureSpectacles();
        System.out.println();
    }
    // VIZIONEAZA SPECTACOLE TRECUTE
    public void seePastSpectacles() {
        Opera opera = Opera.getOpera();
        opera.showPastSpectacles();
        System.out.println();
    }
    // ADAUGA SALA
    public void addHall(Scanner scanner){
        Opera opera = Opera.getOpera();
        scanner.nextLine();

        System.out.print("Id sala:");
        int id = scanner.nextInt();
        System.out.print("Etaj:");
        int floor = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Sala este disponibila?(da/nu)");
        String ans = scanner.next();
        while(ans.compareTo("nu") != 0 && ans.compareTo("da") != 0){
            System.out.println("Opțiune invalidă.");
            ans = scanner.next();
        }
        boolean availability = ans.equalsIgnoreCase("yes");
        System.out.print("Număr de rânduri:");
        int rows = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Număr de coloane: ");
        int columns = scanner.nextInt();
        scanner.nextLine();

        Hall hall = new Hall(id, floor, availability, rows, columns);
        opera.addHall(hall);
        System.out.print("Sala a fost adăugată cu succes! ");

    }
    // STERGE SALA
    public void deleteHall(Scanner scanner){
        scanner.nextLine();
        Opera opera = Opera.getOpera();

        System.out.print("Id sala: ");
        int id = scanner.nextInt();

        opera.deleteHall(id);
    }

}
