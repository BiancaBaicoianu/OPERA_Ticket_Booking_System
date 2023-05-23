import database.DatabaseConfiguration;
import database.DatabaseManagement;
import models.Admin;
import models.Ballet;
import models.Client;
import models.Spectacle;
import services.impl.AdminService;
import services.impl.ClientService;
import database.DatabaseConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static models.Admin.admin;
import static models.Client.client;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

//        DatabaseConfiguration databaseConfiguration = new DatabaseConfiguration();
//        databaseConfiguration.connection();

        DatabaseManagement dbManager = DatabaseManagement.getDatabaseManagement(); // cream baza de date

        // clienti cu acces
        List<Client> clientList;
        clientList = new ArrayList<>();

        clientList.add(new Client("user1", "password1"));
        clientList.add(new Client("user2", "password2"));
        clientList.add(new Client("user3", "password3"));

        // admini cu acces
        List<Admin> adminList;
        adminList = new ArrayList<>();

        adminList.add(new Admin("admin1", "password1"));
        adminList.add(new Admin("admin2", "password2"));
        adminList.add(new Admin("admin3", "password3"));


        // meniu interactiv in functie de rol(admin/client)
        System.out.println("----------------------------------Opera Română-----------------------------------");
        System.out.println("1. Admin");
        System.out.println("2. Client");
        System.out.println("3. Exit");
        System.out.print("Alegeți opțiunea: ");
        int option = scanner.nextInt();
        switch (option) {
            case 1:
                AdminService adminS = AdminService.getAdminService(Admin.getAdmin());
                System.out.print("Username: ");
                String username = scanner.next();
                System.out.print("Password: ");
                String password = scanner.next();
                // verific daca adminul exista
                if(admin.checkAdmin(username, password, adminList)){
                    System.out.println("Bine ai venit, " + username + "!");
                    adminS.useMenu(scanner);
                    System.exit(0);
                } else {
                    System.out.println("Username sau parola incorecte!");
                    // daca nu exista adminul, ies din program
                    System.exit(0);
                }

            case 2:
                ClientService clientS = ClientService.getClientService(Client.getClient());
                System.out.print("Username: ");
                String username1 = scanner.next();
                System.out.print("Password: ");
                String password1 = scanner.next();
                // verific daca clientul exista
                if(client.checkClient(username1, password1, clientList)){
                    System.out.println("Bine ai venit, " + username1 + "!");
                    clientS.useMenu(scanner);
                    System.exit(0);
                } else {
                    System.out.println("Username sau parola incorecte!");
                    // daca nu exista clientul, ies din program
                    System.exit(0);
                }
            case 3:
                System.out.println("La revedere!");
                System.exit(0);
       }
    }
}