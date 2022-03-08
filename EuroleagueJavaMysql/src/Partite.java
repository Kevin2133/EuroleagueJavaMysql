public class Partite {
 private int gamecode;

 public Partite(int gamecode){
  this.gamecode = gamecode;
 }

 void printPartita (){
  System.out.println("**********");
  System.out.println("gamecode= " + this.gamecode);
  System.out.println("**********");

 }

 int getGamecode(){
  return this.gamecode;
 }
}
