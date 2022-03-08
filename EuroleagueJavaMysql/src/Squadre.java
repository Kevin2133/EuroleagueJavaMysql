public class Squadre {
 private String idSquadra, squadra;

 public Squadre(String idSquadra, String squadra){
  this.idSquadra = idSquadra;
  this.squadra = squadra;
 }

 void printSquadra () {
  System.out.println("**********");
  System.out.println("Squadra= " + this.squadra);
  System.out.println("**********");

 }

 public String getId(){
  return this.idSquadra;
 }

 public String getSquadra(){
  return this.squadra;
 }
}
