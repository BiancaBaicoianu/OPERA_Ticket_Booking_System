package services.impl;

import models.Admin;
import services.IService;
import java.util.Scanner;

import static models.Opera.opera;

public class AdminService implements IService {
    Admin admin;
    public static AdminService adminService;

    private AdminService(Admin admin) {
        this.admin = admin;
    }
    public static AdminService getAdminService(Admin admin) {
        if (adminService == null) {
            adminService = new AdminService(admin);
        }
        return adminService;
    }
    public void showMenu() {
        System.out.println("\n----------- Meniu -----------");
        System.out.println("1. Adaugă spectacol ");
        System.out.println("2. Șterge spectacol ");
        System.out.println("3. Verifică dacă sală de spectacol este disponibilă");
        System.out.println("4. Adaugă sală de spectacol");
        System.out.println("5. Șterge sală de spectacol");
        System.out.println("6. Vezi spectacolele trecute");
        System.out.println("7. Vezi spectacolele viitoare");
        System.out.println("8. Schimba parola ");
        System.out.print("Alege operațiunea pe care vrei să o realizezi în continuare: ");
    }

    @Override
    public void useMenu(Scanner scanner){
        showMenu();
        int option = scanner.nextInt();
        if (verifyOption(option)) {
            if (option == 1){
                System.out.println("\n----------- Adaugă spectacol -----------");
                admin.addSpectacle(scanner);
                //System.out.println("Spectacolul a fost adăugat cu succes!");
            }
            else if (option == 2) {
                System.out.println("\n----------- Șterge spectacol -----------");
                admin.deleteSpectacle(scanner);
                //System.out.println("Spectacolul a fost șters cu succes!");
            }
            else if (option == 3) {
                System.out.println("\n----------- Verifică dacă sala de spectacol este disponibilă -----------");
                System.out.println("Zi:");
                int day = scanner.nextInt();
                System.out.println("Luna:");
                int month = scanner.nextInt();
                System.out.println("An:");
                int year = scanner.nextInt();
                opera.hallsAvailable(day, month, year);
            }
            else if (option == 4) {
                System.out.println("\n----------- Adaugă sală de spectacol-----------");
                admin.addHall(scanner);
                //System.out.println("Sala de spectacol a fost adăugată cu succes!");
            }
            else if (option == 5){
                System.out.println("\n----------- Șterge sală de spectacol -----------");
                admin.deleteHall(scanner);
                //System.out.println("Sala de spectacol a fost ștearsă cu succes!");
            }
            else if (option == 6){
                System.out.println("\n----------- Vezi spectacole trecute -----------");
                admin.seePastSpectacles();
            }
            else if (option == 7){
                System.out.println("\n----------- Vezi spectacole viitoare -----------");
                admin.seeFutureSpectacles();
            }
            else if (option == 8){
                System.out.println("\n----------- Schimba parola -----------");
                admin.changePassword(scanner);
            }
        }
        else
            System.out.println("Opțiune invalidă!");
    }


    public boolean verifyOption(int option){

        return option <= 8 && option >= 1;
    }
}
