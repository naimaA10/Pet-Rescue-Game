public class Temps{
	
	private Joueur joueur;
	
	public Temps(Joueur j) {
		this.joueur=j;
	}
	
	public Joueur getJoueur() {
		return joueur;
	}
		
	public void minuteur() {
		long timer=System.currentTimeMillis(); 
		//int delay = 5*60*1000; //5 minutes en ms 	
		int delay = 10*1000;
		while (System.currentTimeMillis() - timer <= delay && joueur.getVies() < 5){  
			 //tant que le temps ecoulé < 5 min on fait rien 
				joueur.setVies(joueur.getVies()+1);

		}
			
	}
	
	public static void main(String[] args) {	
	}
}

