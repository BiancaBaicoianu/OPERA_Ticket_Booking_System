import java.util.*;

public class ClientService {
    public static ClientService clientService;
    private int id;
    private String username;
    private String password;
    private Set<Spectacle> favorites;
    private List<Ticket> futureTickets;

    private ClientService(){
        favorites = new TreeSet<>();
        futureTickets = new ArrayList<>();
    }
    public static ClientService getClientService(){
        if(clientService == null)
            clientService = new ClientService();

        return clientService;
    }

    // CUMPĂRĂ BILET
    public void buyTicket(int id, Scanner scanner){
        if(id == 0)
            return;
        Opera opera = Opera.getOpera();

        List<Spectacle> spectacles = opera.getFutureSpectacles();
        if(spectacles == null){
            System.out.println("Nu sunt spectacole disponibile.");
        }
        else{
            Spectacle spectacle = opera.findFutureSpectacle(id);
            if (spectacle == null){
                System.out.println("Id invalid!");
                return;
            }
            System.out.println("Introdu numărul de bilete: ");
            int noTickets = scanner.nextInt();
            if(spectacle.getNoAvailableSeats() == 0){
                System.out.println("Spectacolul ales este sold-out!");
                return;
            }
            if(spectacle.getNoAvailableSeats() < noTickets){
                System.out.println("Sunt disponibile numai "+spectacle.getNoAvailableSeats()+" disponibile.");
                return;
            }
            else{
                System.out.println("Alegeți locurile. (Cele disponibile sunt marcate cu _)");
                spectacle.showAvailableSeats();
                List<String> seats = new ArrayList<>();

                System.out.println("Alegeți locurile( A1,B12, ...):");
                for(int i =0; i < noTickets; i++){
                    System.out.print((i + 1) + ": " );
                    String ans = scanner.next();
                    seats.add(ans);
                }

            }

        }
    }
    // VERIFICA LOC
    private boolean verifySeat(String seat, Spectacle spectacle){
        char c = seat.charAt(0);
        if(seat.length() <= 1)
            return false;
        int number = Integer.parseInt(seat.substring(1)) - 1;
        if(number < 0 || number >= spectacle.getHall().getRows())
            return false;
        if(c - 'A' < 0 || c - 'A' >= spectacle.getHall().getColumns())
            return false;
        return true;
    }
    // ARATA BILETELE CUMPARATE
    public void showBoughtTickets(){
        System.out.println("Biletele cumpărate: ");
        if(futureTickets == null)
            System.out.println("Nu sunt bilete cumpărate pentru evenimente viitoare");
        else if(futureTickets.size() == 0)
            System.out.println("Nu sunt bilete cumpărate pentru evenimente viitoare");
        else{
            for(Ticket t: futureTickets){
                t.print();
                System.out.println();
            }
        }
        System.out.println();
    }
    // ARATA LISTA DE FAVORITE
    public void showFavorites(){
        System.out.println("Spectacolele salvate la favorite: ");
        if(favorites == null)
            System.out.println("Nu sunt spectacole salvate la favorite");
        else if(favorites.size() == 0)
                System.out.println("Nu sunt spectacole salvate la favorite");

        System.out.println();
    }

    // ADAUGA IN LISTA DE FAVORITE
    public void addToFavorites(int id){
        Spectacle spectacle = Opera.getOpera().findFutureSpectacle(id);
        if(spectacle != null) {
            for (Spectacle fav : favorites) {
                if (fav.getSpectacleId() == id) {
                    System.out.println("Spectacolul se află deja în lista de favorite!");
                }
            }
            favorites.add(spectacle);
        }
    }
    //STERGE DIN LISTA DE FAVORITE
    public void deleteFromFavorites(int id){
        if(favorites != null) {
            Spectacle spectacle = Opera.getOpera().findFutureSpectacle(id);
            if (spectacle != null) {
                for (Spectacle fav : favorites) {
                    if (fav.getSpectacleId() == id) {
                        favorites.remove(fav);
                        break;
                    }
                }
            }
        }
    }
}
