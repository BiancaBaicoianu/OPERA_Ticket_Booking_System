import java.util.Scanner;
public class Admin {
    public static Admin admin;
    private int id;
    private String username;
    private String password;

    private Admin(){}

    public static Admin getAdmin(){
        if(admin == null){
            admin = new Admin();
        }
        return admin;
    }


}
