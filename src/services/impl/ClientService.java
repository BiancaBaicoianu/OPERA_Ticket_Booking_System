package services.impl;

import models.Client;
import models.Opera;
import models.Spectacle;
import repos.ClientRepository;
import services.IService;
import java.util.List;
import java.util.Scanner;

import static models.Opera.opera;

public class ClientService implements IService {
    Client client;
    public static ClientService clientService;

    private ClientService(Client c) {
        client = c;
    }
    public static ClientService getClientService(Client c) {
        if (clientService == null) {
            clientService = new ClientService(c);
        }
        return clientService;
    }

    public void showMenu() {
        System.out.println("\n----------- Meniu -----------");
        System.out.println("0. Vezi descriere opera");
        System.out.println("1. Vezi spectacolele trecute");
        System.out.println("2. Vezi spectacolele viitoare");
        System.out.println("3. Vezi spectacolele favorite");
        System.out.println("4. Vezi biletele viitoare");
        System.out.println("5. Caută spectacol dupa dată");
        System.out.println("6. Caută spectacol după id");
        System.out.println("7. Cumpără bilet");
        System.out.println("8. Adăugă la favorite");
        System.out.println("9. Șterge din favorite");
        System.out.println("10. Sterge cont");
        System.out.println("11. Logout");
        System.out.print("Alege operațiunea pe care vrei să o realizezi în continuare: ");


    }

    public void useMenu(Scanner scanner){
        this.showMenu();
        int option = scanner.nextInt();
        if (verifyOption(option)) {
            if (option == 0) {
                System.out.println("\n----------- Descriere opera -----------");
                opera.showInfos();
                System.exit(0);
            }
            else if (option == 1) {
                System.out.println("\n----------- Spectacole trecute -----------");
                opera.showPastSpectacles();
                System.exit(0);
            }
            else if (option == 2){
                System.out.println("\n----------- Spectacole viitoare -----------");
                opera.showFutureSpectacles();
                System.exit(0);
            }
            else if (option == 3) {
                System.out.println("\n----------- Vezi lista de favorite -----------");
                client.showFavorites();
                System.exit(0);
            }
            else if (option == 4){
                System.out.println("\n----------- Vezi biletele cumpărate -----------");
                client.showBoughtTickets();
                System.exit(0);
            }
            else if (option == 5) {
                System.out.println("\n----------- Cauta spectacol dupa data -----------");
                System.out.print("Zi: ");
                int day = scanner.nextInt();
                System.out.print("Luna: ");
                int month = scanner.nextInt();
                System.out.print("An: ");
                int year = scanner.nextInt();
                searchByDate(day, month, year);
                System.exit(0);
            }
            else if (option == 6) {
                System.out.println("\n----------- Cauta spectacol dupa id -----------");
                System.out.print("Id spectacol: ");
                int id = scanner.nextInt();
                opera.findFutureSpectacle(id);
                System.exit(0);
            }
            else if (option == 7) {
                System.out.println("\n----------- Cumpără bilet -----------");
                System.out.print("Id spectacol: ");
                int id = scanner.nextInt();
                System.out.println();
                client.buyTicket(id, scanner);
                System.exit(0);
            }
            else if (option == 8){
                System.out.println("\n----------- Adaugă la favorite -----------");
                System.out.print("Id spectacol:");
                int id = scanner.nextInt();
                client.addToFavorites(id);
                System.out.println("Spectacol adăugat cu succes!");
                System.exit(0);
            }
            else if (option == 9){
                System.out.println("\n----------- Șterge de la favorite ----------");
                System.out.print("Id spectacol:");
                int id = scanner.nextInt();
                client.deleteFromFavorites(id);
                System.out.println("Spectacol eliminat cu succes!");
                System.exit(0);
            }
            else if (option == 10){
                System.out.println("\n----------- Șterge cont ----------");
                ClientRepository.DeleteClient(Client.getClient().getUsername());
                System.out.println("Logout cu succes!");
                Registration.getRegistration().logOut(client.getId(),client.getUsername());
            }
            else if (option == 11){
                System.out.println("\n----------- Logout ----------");
                Registration.getRegistration().logOut(client.getId(),client.getUsername());
            }
        }
        else{
            System.out.println("Opțiune invalidă!");
            System.exit(0);
        }
    }


    private void searchByDate(int day, int month, int year) {
        Opera opera = Opera.getOpera();
        List<Spectacle> spectacles = opera.getFutureSpectacles();
        if(spectacles == null || spectacles.size() == 0)
            System.out.println("Nu s-au găsit spectacole!");
        else {
            boolean ok = false;
            for (int i = 0; i < spectacles.size(); i++)
                if ((spectacles.get(i).getDay() == day) && (spectacles.get(i).getMonth() == month) && (spectacles.get(i).getYear() == year)) {
                    System.out.println((i + 1) + ". " + spectacles.get(i));
                    ok = true;
                }
            if (ok == false)
                System.out.println("Nu s-au găsit spectacole!");
        }
        System.out.println();
    }

    public boolean verifyOption(int option){

        return option <= 11 && option >= 0;
    }
}