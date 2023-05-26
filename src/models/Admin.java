package models;
import database.DatabaseManagement;
import services.AuditService;
import repos.HallRepository;
import repos.SpectacleRepository;
import services.AuditService;
import repos.AdminRepository;

import java.util.List;
import java.util.Scanner;
public class Admin {
    public static Admin admin;
    private int id;
    private String username;
    private String password;

    public Admin(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public Admin() {
    }

    public String getUsername() {
        return username;
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
    public boolean checkAdmin(String username, String password, List<Admin> admins){
        for(Admin admin : admins){
            if(admin.username.equals(username) && admin.password.equals(password))
                return true;
        }
        return false;
    }

    public static Admin getAdmin(){
        if(admin == null){
            admin = new Admin();
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

        System.out.println("Concert/Balet/Musical? Pentru a ieși, scrie EXIT");
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
        boolean ok = opera.addSpectacle(spectacle);
        if(!ok){
            System.out.println("Spectacolul nu a putut fi adăugat!");
        }
        else{
            SpectacleRepository.insertSpectacle(spectacle); // adauga spectacolul in baza de date
            AuditService.writeAudit("Spectacolul " + spectacleName + " a fost adăugat de către adminul " + this.username);
            System.out.println("Spectacolul a fost adăugat cu succes!");
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

        boolean ok = SpectacleRepository.findSpectacle(id);
        if(!ok){
            System.out.println("Nu există un spectacol cu acest id!");
            return;
        }
        boolean bec = opera.deleteSpectacle(id);
        if(bec){
            SpectacleRepository.deleteSpectacleById(id); // sterge din baza de date
            AuditService.writeAudit("Spectacolul cu id-ul " + id + " a fost șters");
            //System.out.println("Nu există un spectacol cu acest id!");
            return;
        }
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

//        System.out.print("Id sala:");
//        int id = scanner.nextInt();
        System.out.print("Nume sala:");
        String name = scanner.next();
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

        Hall hall = new Hall(name, floor, availability, rows, columns);
        HallRepository.addHall(hall);
        int id = DatabaseManagement.lastIdTable("hall");
        hall.setId(id);
        opera.addHall(hall);
        AuditService.writeAudit("Admin id: " + admin.getId() + " added hall id: " + id);
        System.out.print("Sala a fost adăugată cu succes! ");

    }
    // STERGE SALA
    public void deleteHall(Scanner scanner){
        scanner.nextLine();
        Opera opera = Opera.getOpera();

        System.out.print("Id sala: ");
        int id = scanner.nextInt();

        HallRepository.deleteHall(id);
        AuditService.writeAudit("Admin id: " + admin.getId() + " deleted hall id: " + id);
        opera.deleteHall(id);
    }
    // SCHIMBA PAROLA
    public void changePassword(Scanner scanner){
        scanner.nextLine();
        System.out.print("Introdu parola veche: ");
        String oldPassword = scanner.nextLine();
        if(oldPassword.compareTo(admin.getPassword()) != 0){
            System.out.println("Parola veche nu este corectă!");
            return;
        }
        System.out.print("Introdu parola nouă: ");
        String newPassword = scanner.nextLine();
        System.out.print("Confirmă parola nouă: ");
        String confirmNewPassword = scanner.nextLine();
        if(newPassword.compareTo(confirmNewPassword) != 0){
            System.out.println("Parolele nu coincid!");
            return;
        }
        admin.setPassword(newPassword);
        AdminRepository.UpdatePassword( newPassword);
        AuditService.writeAudit("Admin id: " + admin.getId() + " changed password");
        System.out.println("Parola a fost schimbată cu succes!");
    }
}
