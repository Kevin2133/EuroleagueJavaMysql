import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class MySql {
 /** 
  * Variable declaration
  */
  private static String URL = "jdbc:mysql://localhost:3306/euroleague", USER = "root", PASSWORD = "Sandy$001";

// private static String URL = "jdbc:mysql://sql11.freemysqlhosting.net:3306/sql11465103", USER = "sql11465103", PASSWORD = "pLfcXmIgUb";

 private static String[] ex = {"BASKONIA VITORIA-GASTEIZ"};
 
 //INSERT
 private final static String INSERTSQUADRE = "INSERT INTO squadre values (?, ?)";
 private final static String INSERTGIOCATORI = "INSERT INTO giocatori values (?, ?, ?, (SELECT idSquadra FROM squadre WHERE idSquadra = ?))";
 private final static String INSERTPARTITE = "INSERT INTO partite values (?)";
 private final static String INSERTSTATISTICHE = "INSERT INTO statistiche values ((SELECT gamecode FROM partite WHERE gamecode = ?), (SELECT idGiocatore FROM giocatori WHERE idGiocatore = ?), ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
 private final static String INSERTSQUADREPARTITE = "INSERT INTO squadre_partite values ((SELECT idSquadra FROM squadre WHERE idSquadra = ?), (SELECT gamecode FROM partite WHERE gamecode = ?))";

 //SELECT ALL
 private final static String SELALLSTAT = "SELECT * FROM statistiche";
 private final static String SELALLSQUADRE = "SELECT * FROM squadre";
 private final static String SELALLGIOC = "SELECT * FROM giocatori";
 private final static String SELALLSQUADREPARTITE = "SELECT * FROM squadre_partite";
 private final static String SELALLPARTITE = "SELECT * FROM partite";




 //MAX GAMECODE
 private final static String SELECTMAXPARTITA = "SELECT MAX(gamecode) FROM partite";

//BOOLEAN SEARCH
 private final static String SELECTSQUADRA = "SELECT * FROM squadre WHERE idSquadra = ?";
 private final static String SELECTGIOCATORE = "SELECT * FROM giocatori WHERE idGiocatore = ?";
 private final static String SELECTPARTITA = "SELECT * FROM partite WHERE gamecode = ?";
 private final static String SELECTSQUADRAPARTITA = "SELECT * FROM squadre_partite WHERE idSquadra = ? AND gamecode = ?";
 private final static String SELECTSTATISTICA = "SELECT * FROM statistiche WHERE gamecode = ? AND idGiocatore = ?";





 


 /** 
  * Methods
  */
 
 static Connection InitializeDb (){
  try{   
   // Class.forName("com.mysql.jdbc.Driver");
   
   Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
   return conn;
  } catch (Exception ex) {
   System.out.println(ex);
   return null;
  }  
 }

 static void InsertSquadra(Connection conn, Squadre squadra){
  try {
   // Statement stmt = conn.createStatement();
   PreparedStatement pstmt = conn.prepareStatement(INSERTSQUADRE);  

   pstmt.setString(1, squadra.getId());
   pstmt.setString(2, squadra.getSquadra());
   pstmt.execute();
  } catch (SQLException e) {
   e.printStackTrace();
  }
   
 }

 static void InsertGiocatore (Connection conn, Giocatori gioc){
  try {
   // Statement stmt = conn.createStatement();
   PreparedStatement pstmt = conn.prepareStatement(INSERTGIOCATORI);  

   pstmt.setString(1, gioc.getIdGiocatore());
   pstmt.setString(2, gioc.getNome());
   pstmt.setString(3, gioc.getCognome());
   pstmt.setString(4, gioc.getIdSquadra());
   pstmt.execute();
  } catch (SQLException e) {
   e.printStackTrace();
  }
 }

 static void InsertPartite (Connection conn, Partite game){
  try {
   PreparedStatement pstmt = conn.prepareStatement(INSERTPARTITE); 
   pstmt.setInt(1, game.getGamecode());
   pstmt.execute();
  } catch (SQLException e) {
   e.printStackTrace();
  }
 }

 static void InsertSquadrePartite (Connection conn, SquadrePartite sp){
  try {
   // Statement stmt = conn.createStatement();
   PreparedStatement pstmt = conn.prepareStatement(INSERTSQUADREPARTITE);  

   pstmt.setString(1, sp.getIdSquadra());
   pstmt.setInt(2, sp.getGamecode());
   
   pstmt.execute();
  } catch (SQLException e) {
   e.printStackTrace();
  }
 }

 static void InsertStatistiche (Connection conn, Statistiche s){
  try {
   // Statement stmt = conn.createStatement();
   PreparedStatement pstmt = conn.prepareStatement(INSERTSTATISTICHE);  

   pstmt.setInt(1, s.getGamecode());
   pstmt.setString(2, s.getIdGiocatore());
   pstmt.setString(3, s.getMinuti());
   pstmt.setInt(4, s.getTiri2Tentati());
   pstmt.setInt(5, s.getTiri2Segnati());
   pstmt.setInt(6, s.getTiri3Tentati());
   pstmt.setInt(7, s.getTiri3Segnati());
   pstmt.setInt(8, s.getTiriLTentati());
   pstmt.setInt(9, s.getTiriLSegnati());
   pstmt.setInt(10, s.getPunti());
   pstmt.setInt(11, s.getRimbalzi());
   pstmt.setInt(12,s.getAssist());
   pstmt.setInt(13, s.getSteals());
   pstmt.setInt(14, s.getTurnover());
   pstmt.setInt(15, s.getBlocksFv());
   pstmt.setInt(16, s.getBlocksAg());
   pstmt.setInt(17, s.getFoulsCm());
   pstmt.setInt(18, s.getFoulsRv());
   pstmt.setInt(19, s.getPir());   
   
   pstmt.execute();
  } catch (SQLException e) {
   e.printStackTrace();
  }
 }

 static boolean searchSquadra (Connection conn, Squadre squadra){
  PreparedStatement pstmt;
  try {
   pstmt = conn.prepareStatement(SELECTSQUADRA);

   pstmt.setString(1, squadra.getId());
   ResultSet rs = pstmt.executeQuery();
   while(rs.next()){
    String idDb = rs.getString("idSquadra");
    if(idDb.equals(squadra.getId())){
     return true;
    }
   }
   

  } catch (SQLException e) {
   e.printStackTrace();
  }
  return false;
 }

 static boolean searchGioc (Connection conn, Giocatori gioc){
  PreparedStatement pstmt;
  try {
   pstmt = conn.prepareStatement(SELECTGIOCATORE);
   pstmt.setString(1, gioc.getIdGiocatore());
   ResultSet rs = pstmt.executeQuery();

   while(rs.next()){
    String idDb = rs.getString("idGiocatore");
    if(idDb.equals(gioc.getIdGiocatore())){
     return true;
    }
   }
   

  } catch (SQLException e) {
   e.printStackTrace();
  }
  return false;
 }

 static boolean searchPartita (Connection conn, Partite game){
  PreparedStatement pstmt;
  try {
   pstmt = conn.prepareStatement(SELECTPARTITA);
   pstmt.setInt(1, game.getGamecode());
   ResultSet rs = pstmt.executeQuery();

   while(rs.next()){
    int idDb = rs.getInt("gamecode");
    if(idDb == game.getGamecode()){
     return true;
    }
   }
   

  } catch (SQLException e) {
   e.printStackTrace();
  }
  return false;
 }

 static boolean searchSquadraPartita (Connection conn, SquadrePartite sp){
  PreparedStatement pstmt;
  try {
   pstmt = conn.prepareStatement(SELECTSQUADRAPARTITA);
   pstmt.setString(1, sp.getIdSquadra());
   pstmt.setInt(2, sp.getGamecode());
   ResultSet rs = pstmt.executeQuery();

   while(rs.next()){
    String idSDb = rs.getString("idSquadra");
    int gcDb = rs.getInt("gamecode");
    if((gcDb == sp.getGamecode())&&(idSDb.equals(sp.getIdSquadra()))){
     return true;
    }
   }
   

  } catch (SQLException e) {
   e.printStackTrace();
  }
  return false;
 }

 static boolean searchStatistica (Connection conn, Statistiche s){
  PreparedStatement pstmt;
  try {
   pstmt = conn.prepareStatement(SELECTSTATISTICA);
   pstmt.setInt(1, s.getGamecode());
   pstmt.setString(2, s.getIdGiocatore());
   ResultSet rs = pstmt.executeQuery();

   while(rs.next()){
    String idGDb = rs.getString("idGiocatore");
    int gcDb = rs.getInt("gamecode");
    if((idGDb.equals(s.getIdGiocatore()))&&(gcDb == s.getGamecode())){
     return true;
    }
   }
   

  } catch (SQLException e) {
   e.printStackTrace();
  }
  return false;
 }

 static String manageEx (String s){
  for(int i = 0; i < ex.length; i++){
   if(ex[i].equals(s)){
    switch(ex[i]){
     case "BASKONIA VITORIA-GASTEIZ":
      return "BITCI BASKONIA VITORIA-GASTEIZ";     
    }
   }
  }
  return s;
 }

 static int getMaxPartita(Connection conn){
  Statement stmt;
  try {
   stmt = conn.createStatement();
   
   ResultSet rs = stmt.executeQuery(SELECTMAXPARTITA);

   while(rs.next()){    
    int maxGc = rs.getInt("MAX(gamecode)");
    return maxGc;
   }
   

  } catch (SQLException e) {
   e.printStackTrace();
  
    
 
  }

  return -1;

}

 static Statistiche[] getStat(Connection conn){
  try{

  String idGiocatore, minuti;
  int gamecode, tiri2Tentati, tiri2Segnati, tiri3Tentati, tiri3Segnati, tiriLTentati, tiriLSegnati, punti, rimbalzi, assist, steals, turnover, blocksFv, blocksAg, foulsCm, foulsRv, pir;
   PreparedStatement pstmt = conn.prepareStatement(SELALLSTAT);   

   ResultSet rs = pstmt.executeQuery();

   int l = 0;
   int cont = 0;

   while(rs.next()){
    l++;
   }

   Statistiche stats[] = new Statistiche[l];

   rs = pstmt.executeQuery();

   while(rs.next()){
    
     
    
    idGiocatore = rs.getString("idGiocatore");
    minuti = rs.getString("minuti");
    gamecode = rs.getInt("gamecode");
    tiri2Tentati = rs.getInt("tiri2Tentati");
    tiri2Segnati = rs.getInt("tiri2Segnati");
    tiri3Tentati = rs.getInt("tiri3Tentati");
    tiri3Segnati = rs.getInt("tiri3Segnati");
    tiriLTentati = rs.getInt("tiriLTentati");
    tiriLSegnati = rs.getInt("tiriLSegnati");
    punti = rs.getInt("punti");
    rimbalzi = rs.getInt("rimbalzi");
    assist = rs.getInt("assist");
    steals = rs.getInt("steals");
    turnover = rs.getInt("turnover");
    blocksAg = rs.getInt("blocksAg");
    blocksFv = rs.getInt("blocksFv");
    foulsCm = rs.getInt("foulsCm");
    foulsRv = rs.getInt("foulsRv");
    pir = rs.getInt("pir");    
     
     
     Statistiche s = new Statistiche(gamecode, idGiocatore, minuti, tiri2Tentati, tiri2Segnati, tiri3Tentati, tiri3Segnati, tiriLTentati, tiriLSegnati, punti, rimbalzi, assist, steals, turnover, blocksFv, blocksAg, foulsCm, foulsRv, pir);
     
     stats[cont] = s;
     //System.out.println(stats[cont].getGamecode());

     cont++;
   }

   return stats;
  }catch(Exception ex){

  }
  return null;
}
 
 static Squadre[] getSquadre(Connection conn){
  try{

    String idSquadra, squadra;
    PreparedStatement pstmt = conn.prepareStatement(SELALLSQUADRE);   
  
    ResultSet rs = pstmt.executeQuery();
  
    int l = 0;
    int cont = 0;
  
    while(rs.next()){
      l++;
    }
  
    Squadre squadre[] = new Squadre[l];
  
    rs = pstmt.executeQuery();
  
    while(rs.next()){    
      
      idSquadra = rs.getString("idSquadra");
      squadra = rs.getString("squadra");        
        
      Squadre s = new Squadre(idSquadra, squadra);
      squadre[cont] = s;
      //System.out.println(stats[cont].getGamecode());
    
      cont++;
    }
  
    return squadre;
  }catch(Exception ex){
  
  }
  return null;   
 }

 static Giocatori[] getGioc(Connection conn){
  try{

    String idGiocatore, nome, cognome, idSquadra;
    PreparedStatement pstmt = conn.prepareStatement(SELALLGIOC);   
  
    ResultSet rs = pstmt.executeQuery();
  
    int l = 0;
    int cont = 0;
  
    while(rs.next()){
      l++;
    }
  
    Giocatori[] gioc = new Giocatori[l];
    rs = pstmt.executeQuery();
  
    while(rs.next()){    
      
      idSquadra = rs.getString("idSquadra");
      idGiocatore = rs.getString("idGiocatore");     
      nome = rs.getString("nome");
      cognome = rs.getString("cognome");   
        
      Giocatori g = new Giocatori(idGiocatore, nome, cognome, idSquadra);
      gioc[cont] = g;
      //System.out.println(stats[cont].getGamecode());
    
      cont++;
    }
  
    return gioc;
  }catch(Exception ex){
  
  }
  return null;   
 }

 static SquadrePartite[] getSquadrePartite(Connection conn){
  try{

    String idSquadra;
    int gamecode;
    PreparedStatement pstmt = conn.prepareStatement(SELALLSQUADREPARTITE);   
  
    ResultSet rs = pstmt.executeQuery();
  
    int l = 0;
    int cont = 0;
  
    while(rs.next()){
      l++;
    }
  
    SquadrePartite squadrePartite[] = new SquadrePartite[l];
  
    rs = pstmt.executeQuery();
  
    while(rs.next()){    
      
      idSquadra = rs.getString("idSquadra");
      gamecode = rs.getInt("gamecode");        
        
      SquadrePartite s = new SquadrePartite(idSquadra, gamecode);
      squadrePartite[cont] = s;
      //System.out.println(stats[cont].getGamecode());
    
      cont++;
    }
  
    return squadrePartite;
  }catch(Exception ex){
  
  }
  return null;   
 }

 static Partite[] getPartite(Connection conn){
  try{

    int gamecode;
    PreparedStatement pstmt = conn.prepareStatement(SELALLPARTITE);   
  
    ResultSet rs = pstmt.executeQuery();
  
    int l = 0;
    int cont = 0;
  
    while(rs.next()){
      l++;
    }
  
    Partite partite[] = new Partite[l];
  
    rs = pstmt.executeQuery();
  
    while(rs.next()){    
      
      gamecode = rs.getInt("gamecode");
        
      Partite p = new Partite(gamecode);
      partite[cont] = p;
      //System.out.println(stats[cont].getGamecode());
    
      cont++;
    }
  
    return partite;
  }catch(Exception ex){
  
  }
  return null;   
 }
}
