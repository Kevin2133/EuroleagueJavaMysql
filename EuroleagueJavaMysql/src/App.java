import java.sql.Connection;
import java.util.Scanner;

import org.json.JSONException;
import org.json.JSONObject;

public class App {   
      
    static void riempiDb(){
        String data;
        int k, j;
        Squadre s;
        Statistiche stat;
        Giocatori g;
        Partite p;
        SquadrePartite sp;
         
        Connection conn = MySql.InitializeDb();
        if(conn == null){
            System.out.println("Something went wrong, try again.");
        }else{

            int maxGc = MySql.getMaxPartita(conn);
            if(maxGc < 1){
                maxGc = 1;
                System.out.println("Database will be filled from scratch");
            }else{
                System.out.println("Database will be filled from current game");
            }
        
            for(int i = maxGc; i < 9*16 + 1; i++){
                data = ApiData.getData(i);
                JSONObject obj;
                try {
                    obj = new JSONObject(data);


                    //Inserimento squadre
                        for(k = 0; k < 2; k++){
                        s = ApiData.createSquadra(conn, obj, k); 
                        if(s != null){
                            if(!MySql.searchSquadra(conn, s)){
                                MySql.InsertSquadra(conn, s);
                            }
                        }else{
                            //System.out.println("Impossibile trovare i dati necessari per squadre");
                        }
                        }

                    

                    //Inserimento giocatori

                        for(j = 0; j < 2; j++){
                            for(k = 0; k < 12; k++){
                                g = ApiData.createGiocatore(obj, j, k);
                                if(g != null){
                                    if(!MySql.searchGioc(conn, g)){
                                        MySql.InsertGiocatore(conn, g);
                                    }
                                }else{
                                    //System.out.println("Impossibile trovare i dati necessari per giocatori gamecode= " + i); 
                                }
                            }
                        }
                    

                    
                    //Inserimenti Partite
                        p = ApiData.createPartita(i);

                        if(p != null){
                            if(!MySql.searchPartita(conn, p)){
                                MySql.InsertPartite(conn, p);
                            }
                        }else{
                            //System.out.println("Impossibile trovare i dati necessari per partite gamecode= " + i);  
                        }

                        

                        //Inserimento Squadre-partite

                        for(j = 0; j < 2; j++){
                            sp = ApiData.createSquadraPartita(obj, j, i);
                            if(sp != null){
                                if(!MySql.searchSquadraPartita(conn, sp)){
                                    MySql.InsertSquadrePartite(conn, sp);
                                }
                            }else{
                                //System.out.println("Impossibile trovare i dati necessari per squadre_partite gamecode= " + i); 
                            }
                        }
                        
                        //Inserimento Statistiche
                        for(j = 0; j < 2; j++){
                            for(k = 0; k < 12; k++){
                                stat = ApiData.createStat(obj, j, k, i);

                                if(stat != null){
                                    if(!MySql.searchStatistica(conn, stat)){
                                        MySql.InsertStatistiche(conn, stat);
                                    }
                                }else{
                                    //System.out.println("Dati non trovati per statistiche gamecode= "+i);
                                }
                            }
                        }

                } catch (JSONException e) {
                    //e.printStackTrace();
                } 
            }
            System.out.println("Database pronto...");         
        }       
        
              
        
    }


    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int scelta;
        Giocatori squadra[] = new Giocatori[20];



        
        riempiDb();

        Connection conn = MySql.InitializeDb();
        int maxGc = MySql.getMaxPartita(conn);
        int round = maxGc / 9;
        Statistiche stats[] = new Statistiche[round];
        


        do{
            System.out.println("0. esci");
            System.out.println("1. ................");
            System.out.println("2. ................");
            System.out.println("3. ................");
            System.out.println("4. tutte le statistiche");
            

            scelta = sc.nextInt();
            sc.nextLine();

            switch(scelta){
                case 0:
                    break;
                case 1:
                            
                    break;
                case 2:
                    
                    break;
                case 3:
                    
                    break;                                
                case 4:
                    Statistiche statistiche[] = MySql.getStat(conn);
                    for(int i = 0; i < statistiche.length; i++){
                        statistiche[i].printStat();
                    }
                    break;
                default:
                    System.out.println("Scelta non prevista");
                    break;
            }

        }while(scelta != 0);
        
    }
}
