public class Giocatori {
 String idGiocatore;
 String nome, cognome, idSquadra; 

 public Giocatori(String idGiocatore, String nome, String cognome, String idSquadra){
  this.idGiocatore = idGiocatore;
  this.nome = nome;
  this.cognome = cognome;
  this.idSquadra = idSquadra;
 }

 void printGiocatore (){
  System.out.println("**********");
  System.out.println("idGiocatore= "+ this.idGiocatore);
  System.out.println("nome= "+this.nome);
  System.out.println("cognome= "+this.cognome);
  System.out.println("idSquadra = "+this.idSquadra);
  System.out.println("**********");  
 }

 public String getIdGiocatore() {
  return this.idGiocatore;
 }

 public String getNome() {
  return this.nome;
 }

 public String getCognome() {
  return this.cognome;
 }

 public String getIdSquadra() {
  return this.idSquadra;
 }

}
