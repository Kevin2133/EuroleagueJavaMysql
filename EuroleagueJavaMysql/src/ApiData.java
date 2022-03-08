import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ApiData {
 
 public static String getData(int gamecode){
  URLConnection connection;
  String url = "https://live.euroleague.net/api/Boxscore?gamecode=%s&seasoncode=E2021";
  try {
   connection = new URL(String.format(url, gamecode)).openConnection();
   BufferedReader in = new BufferedReader(new InputStreamReader(
   connection.getInputStream()));
   String inputLine = in.readLine();
   if(inputLine!= null){
    return inputLine;
   }
   in.close();
  } catch (IOException e) {   
   e.printStackTrace();
   return null;
  }
  return null;
 }

 public static Squadre createSquadra(Connection conn, JSONObject obj, int k){
  try {
   JSONArray teams = obj.getJSONArray("ByQuarter");
   
   String teamName = teams.getJSONObject(k).getString("Team");
   teamName = MySql.manageEx(teamName);
   Squadre s = new Squadre(teamName, teamName);
   return s;
   
  } catch (JSONException e) {
   //e.printStackTrace();
  }

  return null;

 }

 public static Statistiche createStat(JSONObject obj, int j, int k, int gamecode){
  String idGioc, minuti;
  int tiri2Tentati, tiri2Segnati, tiri3Tentati, tiri3Segnati, tiriLTentati, tiriLSegnati, punti, rimbalzi, assist, steals, turnover, blocksFv, blocksAg, foulsCm, foulsRv, pir; 
  
  try{
   JSONArray stats = obj.getJSONArray("Stats");

   JSONArray t = stats.getJSONObject(j).getJSONArray("PlayersStats");
   
   do{
    idGioc = t.getJSONObject(k).getString("Player_ID");
    minuti = t.getJSONObject(k).getString("Minutes");
    tiri2Tentati = t.getJSONObject(k).getInt("FieldGoalsAttempted2");
    tiri2Segnati = t.getJSONObject(k).getInt("FieldGoalsMade2");
    tiri3Tentati = t.getJSONObject(k).getInt("FieldGoalsAttempted3");
    
    tiri3Segnati = t.getJSONObject(k).getInt("FieldGoalsMade3");
    
    tiriLTentati = t.getJSONObject(k).getInt("FreeThrowsAttempted");
    tiriLSegnati = t.getJSONObject(k).getInt("FreeThrowsMade");
    punti = t.getJSONObject(k).getInt("Points");
    rimbalzi = t.getJSONObject(k).getInt("TotalRebounds");
    assist = t.getJSONObject(k).getInt("Assistances");
    steals = t.getJSONObject(k).getInt("Steals");
    turnover = t.getJSONObject(k).getInt("Turnovers");
    blocksAg = t.getJSONObject(k).getInt("BlocksAgainst");
    blocksFv = t.getJSONObject(k).getInt("BlocksFavour");
    foulsCm = t.getJSONObject(k).getInt("FoulsCommited");
    foulsRv = t.getJSONObject(k).getInt("FoulsReceived");
    pir = t.getJSONObject(k).getInt("Valuation");

   }while((idGioc == "")||(idGioc == null));
   
   Statistiche stat = new Statistiche(gamecode, idGioc, minuti, tiri2Tentati, tiri2Segnati, tiri3Tentati, tiri3Segnati, tiriLTentati, tiriLSegnati, punti, rimbalzi, assist, steals, turnover, blocksFv, blocksAg, foulsCm, foulsRv, pir);

  // if(stat.getTiri3Segnati() != 0){
  //   System.out.println("Tiri Segnati= " + stat.getTiri3Segnati());
  // }
  //  System.out.println("Tiri Tentati= " + stat.getTiri2Tentati());

   return stat;

  }catch(Exception ex){

  }

  return null;
  
 }

 public static Giocatori createGiocatore(JSONObject obj, int j, int k){
   Giocatori gioc;
   String idGiocatore, nome, cognome, idSquadra;
   int ind;
   try{
   JSONArray t = obj.getJSONArray("Stats").getJSONObject(j).getJSONArray("PlayersStats");

   idGiocatore = t.getJSONObject(k).getString("Player_ID");
   nome = t.getJSONObject(k).getString("Player");

   ind = nome.indexOf(", ");
   cognome = nome.substring(0, ind);
   nome = nome.substring(ind + ", ".length());

   idSquadra = MySql.manageEx(obj.getJSONArray("Stats").getJSONObject(j).getString("Team"));

   gioc = new Giocatori(idGiocatore, nome, cognome, idSquadra);
   return gioc;
  }catch(Exception ex){

  }
  return null;
 }

 public static Partite createPartita(int gamecode){
  Partite game = new Partite(gamecode);
  return game;
 }

 public static SquadrePartite createSquadraPartita(JSONObject obj, int j, int i){
  String squadra;
  
  try{
   JSONArray teams = obj.getJSONArray("ByQuarter");
   squadra = MySql.manageEx(teams.getJSONObject(j).getString("Team"));
   SquadrePartite sp = new SquadrePartite(squadra, i); 
   return sp;
  }catch(Exception ex){

  }
  return null;
 
 }
}
