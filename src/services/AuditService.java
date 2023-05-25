package services;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class AuditService {
    private static final String FILE_NAME = "audit.csv";

    public static void writeAudit(String actionName) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true));
            writer.write(actionName + "," + LocalDateTime.now() + "\n");
            writer.close();
        } catch (IOException e) {
            System.out.println(e);

        }
    }
}