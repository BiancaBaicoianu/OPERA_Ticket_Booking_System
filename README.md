# OPERA_Ticket_Booking_System
Proiect PROGRAMARE AVANSATA PE OBIECTE - Anul II, Sem II

##Etapa I 
###1) Definirea sistemului:  
Să se creeze o lista pe baza temei alese cu cel puțin 10 acțiuni/interogări care se pot face în cadrul 
sistemului și o lista cu cel puțin 8 tipuri de obiecte. 
- Actiuni:
  - Client
    - cumpara bilete
    - verifica disponibilitatea locurilor din sala
    - vede bilete cumparate pentru spectacole viitoare
    - adauga spectacole la 'Favorite'
    - vede lista de 'Favorite'
    - sterge elemente din lista de 'Favorite'
    
  - Administrator
    - adauga sali de spectacol
    - sterge sali de spectacol
    - adauga spectacol
    - sterge spectacol
    - vizioneaza spectacole viitoare
    - vizioneaza spectacole trecute

  
  
- Obiecte:
  - Hall
  - Opera
  - Spectalel(__clasa abstracta__)
  - Ballet
  - Concert
  - Musical
  - Ticket(__clasa abstracta__)
  - BalletTicket
  - ConcertTicket
  - MusicalTicket

- Services:
  - AdminService
  - ClientService

##2) Implementare 
Sa se implementeze în limbajul Java o aplicație pe baza celor definite la primul punct. 
Aplicația va conține: 
• clase simple cu atribute private / protected și metode de acces 
• cel puțin 2 colecții diferite capabile să gestioneze obiectele definiteanterior (eg: List, Set, Map, 
etc.) dintre care cel puțin una sa fie sortata – se vor folosi array-uri uni- /bidimensionale în cazul în care 
nu se parcurg colectiile pana la data checkpoint-ului. 
• utilizare moștenire pentru crearea de clase adiționale și utilizarea lor încadrul colecțiilor; 
• cel puțin o clasă serviciu care sa expună operațiile sistemului 
• o clasa Main din care sunt făcute apeluri către servicii
