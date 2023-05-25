package services.impl;

import models.Admin;
import models.Client;
import repos.ClientRepository;
import repos.AdminRepository;
import services.AuditService;
import services.IRegistration;
import database.DatabaseManagement;

import java.io.IOException;
import java.util.Scanner;

public class Registration implements IRegistration {
    public static final Registration registration = new Registration();

    public static Registration getRegistration() {
        return registration;
    }

    @Override
    public int logIn(Scanner scanner){
        System.out.print("Username:");
        String username = scanner.next();
        System.out.print("Password:");
        String password = scanner.next();

        int clientId = ClientRepository.checkClient(username, password);
        if (clientId != -1) {
            Client client = Client.getClient();
            client.setId(clientId);
            client.setUsername(username);
            client.setPassword(password);
            AuditService.writeAudit("Login client id: " + clientId);
            return 1;
        }

        int adminId = AdminRepository.checkAdmin(username, password);
        if (adminId != -1) {
            Admin admin = Admin.getAdmin();
            admin.setId(adminId);
            admin.setUsername(username);
            admin.setPassword(password);
            AuditService.writeAudit("Login admin id: " + adminId);
            return 2;
        }
        return 0;
    }

    public void logOut(int userId, String username) {
        AuditService.writeAudit("User id: " + userId + " logged out");
        System.out.println("La revedere, " + username + "!");
    }

    public void signUpClient(Scanner scanner) {
        System.out.print("Username: ");
        String username = scanner.next();
        while (AdminRepository.checkUsername(username))
        {
            System.out.println("Username-ul exista deja! Alege altceva:");
            username = scanner.next();
        }
        System.out.print("Parola: ");
        String password = scanner.next();
        ClientRepository.ADDClient(username, password);
        int id = DatabaseManagement.lastIdTable("client");
        AuditService.writeAudit("Sign up client id: " + id);
        System.out.println("Inregistrarea s-a efectuat cu succes!");
    }

    public void signUpAdmin(Scanner scanner) {
        System.out.print("Username: ");
        String username = scanner.next();
        while (AdminRepository.checkUsername(username))
        {
            System.out.println("Username-ul exista deja! Alege altceva:");
            username = scanner.next();
        }
        System.out.print("Parola: ");
        String password = scanner.next();

        AdminRepository.AddAdmin(username, password);
        int id = DatabaseManagement.lastIdTable("admin");
        AuditService.writeAudit("Sign up admin id: " + id);
        System.out.println("Inregistrarea s-a efectuat cu succes!");
    }
}
