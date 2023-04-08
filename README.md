# OPERA_Ticket_Booking_System
Proiect PROGRAMARE AVANSATA PE OBIECTE - Anul II, Sem II

## Etapa I 
### 1) Definirea sistemului:  
Să se creeze o lista pe baza temei alese cu cel puțin 10 acțiuni/interogări care se pot face în cadrul 
sistemului și o lista cu cel puțin 8 tipuri de obiecte. 
- Actiuni:
  - Client
    - vede descriere operă
    - vede spectacole trecute
    - vedet spectacole viitoare
    - vede lista de 'Favorite'
    - vede bilete cumparate pentru spectacole viitoare
    - caută spectacol după dată
    - caută spectacol după id
    - cumpara bilete
    - adauga spectacole la 'Favorite'
    - sterge elemente din lista de 'Favorite'
    
  - Admin
    - adauga sali de spectacol
    - sterge sali de spectacol
    - adauga spectacol
    - sterge spectacol
    - verifică disponibilitate sală
    - vizioneaza spectacole trecute
    - vizioneaza spectacole viitoare

  
  
- Obiecte:
  - Hall
  - Opera
  - Spectacle(__clasa abstracta__)
  - Ballet
  - Concert
  - Musical
  - Ticket(__clasa abstracta__)
  - BalletTicket
  - ConcertTicket
  - MusicalTicket
  - Admin
  - Client

- Services:
  - AdminService
  - ClientService


### 2) Implementare 
Sa se implementeze în limbajul Java o aplicație pe baza celor definite la primul punct. 
Aplicația va conține: 
- clase simple cu atribute private / protected și metode de acces 
- cel puțin 2 colecții diferite capabile să gestioneze obiectele definiteanterior (eg: List, Set, Map, 
etc.) dintre care cel puțin una sa fie sortata – se vor folosi array-uri uni- /bidimensionale în cazul în care 
nu se parcurg colectiile pana la data checkpoint-ului. 
- utilizare moștenire pentru crearea de clase adiționale și utilizarea lor încadrul colecțiilor; 
- cel puțin o clasă serviciu care sa expună operațiile sistemului 
- o clasa Main din care sunt făcute apeluri către servicii
