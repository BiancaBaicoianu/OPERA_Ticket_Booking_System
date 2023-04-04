import models.Opera;
import services.AdminService;
import services.ClientService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Opera opera = Opera.getOpera();

        // clienti cu acces
        List<ClientService> clientList;
        clientList = new ArrayList<>();

        clientList.add(new ClientService("user1", "password1"));
        clientList.add(new ClientService("user2", "password2"));
        clientList.add(new ClientService("user3", "password3"));

        // admini cu acces
        List<AdminService> adminList;
        adminList = new ArrayList<>();

        adminList.add(new AdminService("admin1", "password1"));
        adminList.add(new AdminService("admin2", "password2"));


        // meniu interactiv in functie de rol(admin/client)
        System.out.println("----------------------------------Opera Română-----------------------------------");
        System.out.println("1. Admin");
        System.out.println("2. Client");
        System.out.println("3. Exit");
        System.out.print("Alegeți opțiunea: ");
        int option = scanner.nextInt();
        switch (option) {
            case 1:
                AdminService adminService = AdminService.getAdmin();
                System.out.print("Username: ");
                String username = scanner.next();
                System.out.print("Password: ");
                String password = scanner.next();
                // verific daca adminul exista
                if(adminService.checkAdmin(username, password, adminList)){
                    System.out.println("Bine ai venit, " + username + "!");
                    System.out.println("---------------------------------------------------------------------------------");
                    System.out.println("1. Adaugă spectacol");
                    System.out.println("2. Sterge spectacol");
                    System.out.println("3. Adaugă sala de spectacol");
                    System.out.println("4. Sterge sala de spectacol");
                    System.out.println("5. Vezi spectacolele trecute");
                    System.out.println("6. Vezi spectacolele viitoare");
                    System.out.print("Alege operațiunea pe care vrei sa o realizezi în continuare: ");
                } else {
                    System.out.println("Username sau parola incorecte!");
                    // daca nu exista adminul, ies din program
                    System.exit(0);
                }

                int option2 = scanner.nextInt();
                switch (option2) {
                    case 1:
                        adminService.addSpectacle(scanner);
                        System.exit(0);
                    case 2:
                        adminService.deleteSpectacle(scanner);
                        System.exit(0);
                    case 3:
                        adminService.addHall(scanner);
                        System.exit(0);
                    case 4:
                        adminService.deleteHall(scanner);
                        System.exit(0);
                    case 5:
                        opera.showPastSpectacles();
                        System.exit(0);
                    case 6:
                        opera.showFutureSpectacles();
                        System.exit(0);
                }
            case 2:
                ClientService clientService = ClientService.getClientService();
                System.out.print("Username: ");
                String username1 = scanner.next();
                System.out.print("Password: ");
                String password1 = scanner.next();
                if(clientService.checkClient(username1, password1, clientList)){
                    System.out.println("Bine ai venit, " + username1 + "!");
                    System.out.println("1. Vezi spectacolele trecute");
                    System.out.println("2. Vezi spectacolele viitoare");
                    System.out.println("3. Vezi spectacolele favorite");
                    System.out.println("4. Vezi biletele viitoare");
                    System.out.println("5. Cumpara bilet");
                    System.out.println("6. Adauga la favorite");
                    System.out.println("7. Sterge din favorite");
                    System.out.print("Alege operațiunea pe care vrei sa o realizezi în continuare: ");

        } else {
                    System.out.println("Username sau parola incorecte!");
                }
                int option3 = scanner.nextInt();
                switch (option3) {
                    case 1:
                        opera.showPastSpectacles();
                        System.exit(0);
                    case 2:
                        opera.showFutureSpectacles();
                        System.exit(0);
                    case 3:
                        clientService.showFavorites();
                        System.exit(0);
                    case 4:
                        clientService.showBoughtTickets();
                        System.exit(0);
                    case 5:
                        //introducem id-ul spectacolului la care vrem sa cumparam bilet
                        System.out.print("Introduceti id-ul spectacolului: ");
                        int id = scanner.nextInt();
                        clientService.buyTicket(id, scanner);
                        System.exit(0);
                    case 6:
                        //introducem id-ul spectacolului la care vrem sa il adaugam la favorite
                        System.out.print("Introduceti id-ul spectacolului: ");
                        int id1 = scanner.nextInt();
                        clientService.addToFavorites(id1);
                        System.exit(0);
                    case 7:
                        //introducem id-ul spectacolului la care vrem sa il stergem din favorite
                        System.out.print("Introduceti id-ul spectacolului: ");
                        int id2 = scanner.nextInt();
                        clientService.deleteFromFavorites(id2);
                        System.exit(0);
                }
            case 3:
                System.out.println("La revedere!");
                System.exit(0);
       }

}

}