import database.DatabaseConfiguration;
import database.DatabaseManagement;
import models.Admin;
import models.Ballet;
import models.Client;
import models.Spectacle;
import services.impl.AdminService;
import services.impl.ClientService;
import database.DatabaseConfiguration;
import services.impl.Registration;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static models.Admin.admin;
import static models.Client.client;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Registration registration = Registration.getRegistration();

        DatabaseManagement dbManager = DatabaseManagement.getDatabaseManagement(); // cream baza de date

//        dbManager.initDatabase(); // cream tabelele

        // meniu interactiv in functie de rol(admin/client)
        System.out.println("----------------------------------Opera Română-----------------------------------");
        System.out.println("Bine ați venit!");
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("1. Login");
        System.out.println("2. Inregistrare");
        System.out.println("3. Exit");
        System.out.print("Alegeți opțiunea: ");
        int option = scanner.nextInt();
        switch (option) {
            case 1:
                System.out.println("\n-----------Login-----------");
                int res = registration.logIn(scanner);
                if (res == 1) { // login client
                    ClientService clientService = ClientService.getClientService(Client.getClient());
                    //dbManager.initClient();
                    clientService.useMenu(scanner);
                    break;
                }
                else if (res == 2){  // login admin
                    AdminService adminService = AdminService.getAdminService(Admin.getAdmin());
                    adminService.useMenu(scanner);
                    break;
                }
                else{
                    System.out.println("Login esuat!");
                }
                break;
            case 2:
                System.out.println("\n-----------Inregistare-----------");
                System.out.print("1. Client\n2. Admin\nAlegeți tipul de cont:");
                while(true) {
                    char type = scanner.next().charAt(0);
                    if(type == '2') {
                        registration.signUpAdmin(scanner);
                        break;
                    }
                    else if (type == '1') {
                        registration.signUpClient(scanner);
                        break;
                    }
                    else
                        System.out.println("Optiune invalida!");
                }
                break;
            case 3:
                System.out.println("La revedere!");
                break;
            default:
                System.out.println("Optiune invalida!");
                break;
        }
    }
}