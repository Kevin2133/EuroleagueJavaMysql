public class Statistiche {
 String idGiocatore, minuti;
 int gamecode, tiri2Tentati, tiri2Segnati, tiri3Tentati, tiri3Segnati, tiriLTentati, tiriLSegnati, punti, rimbalzi, assist, steals, turnover, blocksFv, blocksAg, foulsCm, foulsRv, pir;

 public Statistiche(int gamecode, String idGiocatore, String minuti, int tiri2Tentati, int tiri2Segnati, int tiri3Tentati, int tiri3Segnati, int tiriLTentati, int tiriLSegnati, int punti, int rimbalzi, int assist, int steals, int turnover, int blocksFv, int blocksAg, int foulsCm, int foulsRv, int pir){
  this.gamecode = gamecode;
  this.idGiocatore = idGiocatore;
  this.minuti = minuti;
  this.punti = punti;
  this.tiri2Segnati = tiri2Segnati;
  this.tiri2Tentati = tiri2Tentati;
  this.tiri3Tentati = tiri3Tentati;
  this.tiri3Segnati = tiri3Segnati;
  this.tiriLSegnati = tiriLSegnati;
  this.tiriLTentati = tiriLTentati;
  this.rimbalzi = rimbalzi;
  this.assist = assist;
  this.steals = steals;
  this.turnover = turnover;
  this.blocksAg = blocksAg;
  this.blocksFv = blocksFv;
  this.foulsCm = foulsCm;
  this.foulsRv = foulsRv;
  this.pir = pir;
 }

 void printStat (){
  System.out.println("************");
  System.out.println("Gamecode = " + this.gamecode);
  System.out.println("idGiocatore = " + this.idGiocatore);
  System.out.println("minuti = " + this.minuti);
  System.out.println("tiri2Segnati = " + this.tiri2Segnati);
  System.out.println("tiri2Tentati = " + this.tiri2Tentati);
  System.out.println("tiri3Segnati = " + this.tiri3Segnati);
  System.out.println("Tiri3Tentati = " + this.tiri3Tentati);
  System.out.println("tiriLSegnati = " + this.tiriLSegnati);
  System.out.println("tiriLTentati = " + this.tiriLTentati);
  System.out.println("punti = " + this.punti);
  System.out.println("Rimbalzi = " + this.rimbalzi);
  System.out.println("assist = " + this.assist);
  System.out.println("Steals = " + this.steals);
  System.out.println("turnover = " + this.turnover);
  System.out.println("blocksFv = " + this.blocksFv);
  System.out.println("blocksAg = " + this.blocksAg);
  System.out.println("foulsCm = " + this.foulsCm);
  System.out.println("foulsRv = " + this.foulsRv);
  System.out.println("Pir = " + this.pir);
  System.out.println("************");  
 }

 String getIdGiocatore(){
  return this.idGiocatore;
 }

 int getGamecode(){
  return this.gamecode;
 }

 String getMinuti(){
  return this.minuti;
 }

 int getPunti(){
  return this.punti;
 }

 int getTiri2Tentati(){
  return this.tiri2Tentati;
 }

 int getTiri2Segnati(){
  return this.tiri2Segnati;
 }

 int getTiri3Tentati(){
  return this.tiri3Tentati;
 }

 int getTiri3Segnati(){
  return this.tiri3Segnati;
 }

 int getTiriLTentati(){
  return this.tiriLTentati;
 }

 int getTiriLSegnati(){
  return this.tiriLSegnati;
 }

 int getRimbalzi(){
  return this.rimbalzi;
 }

 int getAssist(){
  return this.assist;
 }

 int getSteals(){
  return this.steals;
 }

 int getTurnover(){
  return this.turnover;
 }

 int getBlocksAg(){
  return this.blocksAg;
 }

 int getBlocksFv(){
  return this.blocksFv;
 }

 int getFoulsCm(){
  return this.foulsCm;
 }

 int getFoulsRv(){
  return this.foulsRv;
 }

 int getPir(){
  return this.pir;
 }

}
