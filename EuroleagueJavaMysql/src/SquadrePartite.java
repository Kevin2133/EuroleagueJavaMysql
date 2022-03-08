public class SquadrePartite {
 private String idSquadra;
 private int gamecode;

 public SquadrePartite(String idSquadra, int gamecode){
  this.idSquadra = idSquadra;
  this.gamecode = gamecode;
 }

 void printSquadrePartite (){
  System.out.println("**********");
  System.out.println("gamecode= " + this.gamecode);
  System.out.println("squadra= " + this.idSquadra);
  System.out.println("**********");

 }

 int getGamecode(){
  return this.gamecode;
 }

 String getIdSquadra(){
  return this.idSquadra;
 }

}
