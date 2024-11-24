import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

public class Modele {
	
	private Niveau niveau; 
	private Joueur joueur;
	protected VueJeu vueJeu;
	
	private int lignes; //longueur
	private int colonnes; //largeur
	protected Object[][] blocs; 
	
	private static int nbAnimauxSauves = 0;
	private static int pointsAccumules = 0;
	
	private static int blocsVoisins = 0;
	protected ArrayList<ArrayList<Integer>> listeC = new ArrayList<ArrayList<Integer>>(); //coordonnees des blocs voisins

	public Modele(int lignes, int colonnes, Niveau niveau, Joueur j) {
		this.lignes = lignes;
		this.colonnes = colonnes;
		blocs = new Object[lignes+2][colonnes+2];
		this.niveau = niveau;
		this.joueur = j;
	}
	
	public Joueur getJoueur() {
		return joueur;
	}
	
	public Niveau getNiveau() {
		return niveau;
	}
	
	public Object[][] getBlocs(){
		return blocs;
	}
	
	public int getLignes() {
		return lignes;
	}
	
	public int getColonnes() {
		return colonnes;
	}
	
	public int getNbAnimauxSauves() {
		return nbAnimauxSauves;
	}
	
	public int getPointsAccumules() {
		return pointsAccumules;
	}
	
	public int getBlocsVoisins() {
		return blocsVoisins;
	}

	
	public void setNbAnimauxSauves(int n) {
		nbAnimauxSauves = n;
	}
	
	public void setPointsAccumules(int n) {
		pointsAccumules = n ;
	}
	
	public void setBlocsVoisins(int n) {
		blocsVoisins = n;
	}

	public void setVueJeu(VueJeu v) {
		vueJeu = v;
	}
	
	//Trouver les voisins autour du bloc cliqué
	public void voisinsAutour(int l, int c) {  
		String s = "";
		if(possedeVoisins(l,c)) {
			if(blocs[l][c] instanceof Bloc) {
				Bloc tmp = (Bloc)blocs[l][c];
				s = tmp.couleur;
				listeC = voisins(l,c,s);
			}
			for (int i = 0; i < listeC.size(); i++) { 
				for (int j = 0; j < listeC.get(i).size(); j++) { 
					voisins(listeC.get(i).get(j), listeC.get(i).get(++j),s);
				}
				
			}
		}
		
		listeC.clear(); //effacer les données de la liste pour ne pas les garder en mémoire
	}
	
	//Voir si le bloc possède au moins un voisin, sinon la recherche de voisins est inutile
	private boolean possedeVoisins(int l, int c) {
		if(blocs[l][c] instanceof Bloc) {
			Bloc aux = (Bloc)blocs[l][c];
			if(blocs[l-1][c] instanceof Bloc) {
				Bloc tmp = (Bloc)blocs[l-1][c];
				if(aux.possedeVoisins(tmp)) {
					return true;
				}
			}
			if(blocs[l][c+1] instanceof Bloc) {
				Bloc tmp = (Bloc)blocs[l][c+1];
				if(aux.possedeVoisins(tmp)) {
					return true;
				}
			}
			if(blocs[l][c-1] instanceof Bloc) {
				Bloc tmp = (Bloc)blocs[l][c-1];
				if(aux.possedeVoisins(tmp)) {
					return true;
				}
			}
			if(blocs[l+1][c] instanceof Bloc) {
				
				Bloc tmp = (Bloc)blocs[l+1][c];
				if(aux.possedeVoisins(tmp)) {
					return true;
				}
			}
		}
		return false;
	}
	
	
	public ArrayList<ArrayList<Integer>> voisins(int l, int c, String s) { 
		if(l>=0 && l<=lignes+1 && c>=0 && c<=colonnes+1 && blocs[l][c] instanceof Bloc) { 
			Bloc aux = (Bloc)blocs[l][c];
			if(blocs[l-1][c] instanceof Bloc) {
				listeC = aux.voisins((Bloc)blocs[l-1][c],s);
			}
			if(blocs[l][c+1] instanceof Bloc) {
				listeC = aux.voisins((Bloc)blocs[l][c+1],s);
			}
			if(blocs[l][c-1] instanceof Bloc) {
				listeC = aux.voisins((Bloc)blocs[l][c-1],s);
			}
			if(blocs[l+1][c] instanceof Bloc) {
				listeC = aux.voisins((Bloc)blocs[l+1][c],s);
			}
		}
		return listeC;
		
	}
	
	//ajuster le plateau après un coup valide
	public void ajuster() {
		for(int i = 1; i<blocs.length-1; i++) {
			for(int j = 1; j<blocs[i].length-1; j++) {
					ajusterColonne(i,j);
				
			}
		}
		
	}
	
	public void ajusterColonne(int x, int y) {
		if(blocs[x][y] instanceof Bloc) {
			Bloc tmp = (Bloc)blocs[x][y];
			if(tmp.getNumero() == -1) {
				for(int i = x-1; i>=0; i--) {
					if(blocs[i][y] instanceof Bloc) {
							
						Bloc aux = (Bloc)blocs[i][y];		
						Bloc aux2 = (Bloc)blocs[i+1][y];

						
						aux2.button.setBackground(aux.button.getBackground());
						aux2.couleur = aux.couleur; aux2.setNumero(aux.getNumero());
						aux2.setEnabled(true);
				
						aux.button.setBackground(Color.white); aux.couleur = "blanc";
						aux.setNumero(-1); aux.setEnabled(false);
					}
					else {
	
						Animal aux = (Animal)blocs[i][y];
						blocs[i+1][y] = aux;
						blocs[i][y] = new Bloc(-1);	
					}
				}
			}
		}
	}
	
	//décaler les colonnes lorsque toute une colonne est vide
	public void decaler() {
		for(int i = 1; i<blocs.length-1; i++) {
			for(int j = 1; j<blocs[i].length-2; j++) {
				if(colonneVide(j)) {
					decalerColonne(j);
				}
			}
		}
	}
	
	//Vérifier si on a bien une colonne vide avant de décaler
	private boolean colonneVide(int y) {
		for(int i = 1; i<blocs.length-1; i++) {
			
				if(blocs[i][y] instanceof Animal) {
					return false;
				}
				else {
					Bloc aux = (Bloc)blocs[i][y];
					if(aux.getNumero() != -1) {
						return false;
					}
				}
			
		} return true;
	}
	
	public void decalerColonne(int y) {
		for(int i = 1; i<blocs.length-1; i++) {
			if(blocs[i][y+1] instanceof Bloc) {
				Bloc aux = (Bloc)blocs[i][y+1];
				Bloc aux2 = (Bloc)blocs[i][y];  
				aux2.button.setBackground(aux.button.getBackground());
				aux2.couleur = aux.couleur; aux2.setNumero(aux.getNumero());
		
				aux.button.setBackground(Color.white); aux.couleur = "blanc";
				aux.setNumero(-1); 

			}
			else {
				Animal tmp = (Animal)blocs[i][y+1];
				blocs[i][y] = tmp;
				blocs[i][y+1] = new Bloc(-1);
			}
		}
	}
	
	boolean tousLesAnimauxPlaces() {
		int rec = 0;
		for(int i = 1; i<blocs.length-1; i++) {
			for(int j = 1; j<blocs[i].length-1; j++) {
				if(blocs[i][j] instanceof Animal) {
					Animal tmp = (Animal) blocs[i][j];
						if(tmp.getEspece() != null) {
							rec++;
						}
				}
				
			}
		} return rec == niveau.getAnimauxASauver();
	}
	
	private boolean uniquementAnimaux(int y) {
		for(int i = 1; i<blocs.length-1; i++) {
				if(colonneVide(y)) {
					return false;
				}
				else if(blocs[i][y] instanceof Bloc) {
					Bloc tmp = (Bloc)blocs[i][y];
					if(tmp.getNumero() != -1) {
						return false;
					}
				}
			
		}
		return true;
	}
	
	public void sauverAnimaux() {
		for(int i = 1; i<blocs.length-1; i++) {
			for(int j = 1; j<blocs[i].length-1; j++) {
				sauverAnimauxColonne(j);
			}
		}
		
	}
	
	public void sauverAnimauxColonne(int y) {
		if(uniquementAnimaux(y)) {
			while(!colonneVide(y)) {
				if(blocs[blocs.length-2][y] instanceof Animal) { //2nde vérification
					nbAnimauxSauves ++;
					blocs[blocs.length-2][y] = new Bloc(-1);
					for(int i = 1; i<blocs.length-1 ;i++) {
						ajusterColonne(i,y);
					}
				}
			}
		} 
	}

	public static void pointsApresCoup() {
		if(blocsVoisins >= 1) {
			pointsAccumules += (blocsVoisins+1)*((blocsVoisins+1) * 10);
			blocsVoisins = 0;
		}
	}
	
	public void etoilesFin() {
		if(pointsAccumules >= niveau.getPointsAObtenir(0) 
				&& pointsAccumules < niveau.getPointsAObtenir(1)) {
			joueur.setEtoiles(niveau,1);
		}
		else if(pointsAccumules >= niveau.getPointsAObtenir(1) 
				&& pointsAccumules < niveau.getPointsAObtenir(2)) {
			
				joueur.setEtoiles(niveau, 2);
			
			
		}
		else if(pointsAccumules >= niveau.getPointsAObtenir(2)) {
			
				joueur.setEtoiles(niveau, 3);
				
		}
		
	}
	
	public void piecesFin() {
		joueur.setArgent(joueur.getArgent()+1000);
	}
	
	public void lingotsFin() {
		joueur.setLingots(joueur.getLingots()+5);
	}
	
	public void boostersFin() {
		joueur.setNbBlockBuster(joueur.getNbBlockBuster()+2);
		joueur.setNbColorPop(joueur.getNbColorPop()+2);
		joueur.setNbColumnBlaster(joueur.getNbColumnBlaster()+2);
	}

	
	public ArrayList<Booster> boosters() {
		joueur.getListeBooster().clear();
		ArrayList<Booster> b = new ArrayList<Booster>();
		for(int i = 0; i<joueur.getNbBlockBuster(); i++) {
			b.add(new BlockBuster());
		}
		for(int i = 0; i<joueur.getNbColorPop(); i++) {
			b.add(new ColorPop());
		}
		for(int i = 0; i<joueur.getNbBlockBuster(); i++) {
			b.add(new ColumnBlaster());
		}
		joueur.setListeBooster(b);
		return b;
	}
	
	
	public boolean victoire() {
		if(nbAnimauxSauves == niveau.getAnimauxASauver() &&
				pointsAccumules >= niveau.getPointsAObtenir(0)) {
			return true;
		}
		return false;
	}
	
	
	public boolean coupPossible() {
		for(int i = 1; i<blocs.length-1; i++) {
			for(int j = 1; j<blocs[i].length-1; j++) {
				if(blocs[i][j] instanceof Bloc) {
					Bloc aux = (Bloc)blocs[i][j];
					if(aux.getNumero() != -1 && possedeVoisins(i,j)) {
						return true;
					}
				}
			}
		}
		if(joueur.getNbBlockBuster() > 0 || joueur.getNbColorPop() > 0
				|| joueur.getNbColumnBlaster() > 0) {
			return true;
		}
		joueur.setVies(joueur.getVies()-1);
		reinitialisationDonnees();
		return false;
	}

	public void reinitialisationDonnees() {
		nbAnimauxSauves = 0;
		pointsAccumules = 0;
	}
	
	
	//Pour le terminal
	public void initialisation() {
		placerAnimaux();
		for(int i = 0; i<blocs.length; i++) {
			for(int j = 0; j<blocs[i].length; j++) {
				if(i == 0 || i == blocs.length-1 || j == 0 || j == blocs[i].length-1) {
					blocs[i][j] = new Bloc(-1);
				}
				else {
					if(!(blocs[i][j] instanceof Animal)) {
						blocs[i][j] = new Bloc();
					}
				}
			}
		}

	}
	
	public boolean initialisationCorrecte() { //il faut assez de cases afin de placer les animaux
		int aux = niveau.getAnimauxASauver();
		int rec = 0;
		for(int i = 1; i<blocs.length-1; i++) {
			for(int j = 1; j<blocs[i].length-1; j++) {
				if(blocs[i][j] instanceof Bloc) {
					Bloc tmp = (Bloc) blocs[i][j];
					if(tmp.getNumero() == 5) {
						rec++;
					}
				}
			}
		}
		return rec >= aux;
	}

	
	public void placerAnimaux() {
		
		for(int i = 0; i<niveau.getAnimauxASauver(); i++) {
			while(!tousLesAnimauxPlaces()) {
			
				Random r = new Random();
				int c = r.nextInt(colonnes)+1; //chiffre entre 1 et colonnes
				
				blocs[1][c] = new Animal(); 
				//les animaux ne seront que sur la premiere ligne
			}
		}			

		
	}
	
	private boolean possedeVoisinsTer(int l, int c) {
		int rec = 0;
		if(blocs[l][c] instanceof Bloc) {
			Bloc tmp = (Bloc)blocs[l][c];
			String s = tmp.couleur;
			if(blocs[l-1][c] instanceof Bloc) {
				Bloc aux = (Bloc)blocs[l-1][c];
				if(aux.couleur.contentEquals(s)) {
					rec++;
					
				}
			}
			if(blocs[l][c-1] instanceof Bloc) {
				Bloc aux = (Bloc)blocs[l][c-1];
				if(aux.couleur.contentEquals(s)) {
					rec++;
				}
			}
			if(blocs[l][c+1] instanceof Bloc) {
				Bloc aux = (Bloc)blocs[l][c+1];
				if(aux.couleur.contentEquals(s)) {
					rec++;
				}
			}
			if(blocs[l+1][c] instanceof Bloc) {
				Bloc aux = (Bloc)blocs[l+1][c];
				if(aux.couleur.contentEquals(s)) {
					rec++;
				}
			}
			
		}
		return rec > 0;
	}
	
	
	private ArrayList<ArrayList<Integer>> voisinsTer(int l, int c, String s) { 

			if(blocs[l][c] instanceof Bloc) {
				blocs[l][c] = new Bloc(-1);
				if(blocs[l-1][c] instanceof Bloc) {
					Bloc aux = (Bloc)blocs[l-1][c];
					if(aux.couleur.contentEquals(s)) {
						blocsVoisins++;
						ArrayList<Integer> a1 = new ArrayList<Integer>();
						a1.add(l-1); a1.add(c);
						listeC.add(a1);
						
					}
				}
				if(blocs[l][c-1] instanceof Bloc) {
					Bloc aux = (Bloc)blocs[l][c-1];
					if(aux.couleur.contentEquals(s)) {
						blocsVoisins++;
						ArrayList<Integer> a2 = new ArrayList<Integer>();
						a2.add(l); a2.add(c-1);
						listeC.add(a2);
						
					}
				}
				if(blocs[l][c+1] instanceof Bloc) {
					Bloc aux = (Bloc)blocs[l][c+1];
					if(aux.couleur.contentEquals(s)) {
						blocsVoisins++;
						ArrayList<Integer> a3 = new ArrayList<Integer>();
						a3.add(l); a3.add(c+1);
						listeC.add(a3);
						
					}
				}
				if(blocs[l+1][c] instanceof Bloc) {
					Bloc aux = (Bloc)blocs[l+1][c];
					if(aux.couleur.contentEquals(s)) {
						blocsVoisins++;
						ArrayList<Integer> a4 = new ArrayList<Integer>();
						a4.add(l+1); a4.add(c);
						listeC.add(a4);
						
					}
				}
				
			}
		return listeC;
	}
	
	//Fonctionne
	public void voisinsAutourTer(int l, int c) {
		String s = "";
		if(possedeVoisinsTer(l,c)) {
			if(blocs[l][c] instanceof Bloc) {
				Bloc tmp = (Bloc)blocs[l][c];
				s = tmp.couleur;
				listeC = voisinsTer(l,c,s);
			}
			for (int i = 0; i < listeC.size(); i++) { 
	            for (int j = 0; j < listeC.get(i).size(); j++) { 
	            	voisinsTer(listeC.get(i).get(j), listeC.get(i).get(++j),s);
	            	
	            }
	            
			}
		}
		listeC.clear();
		if(blocsVoisins > 0) {
			pointsAccumules += (blocsVoisins+1)*((blocsVoisins+1) * 10);
			blocsVoisins = 0;
		}

			
	}

	public void ajusterTer() {
		for(int i = 1; i<blocs.length-1; i++) {
			for(int j = 1; j<blocs[i].length-1; j++) {
				ajusterColonneTer(i,j);
			}
		}
		
	}

	public void ajusterColonneTer(int x, int y) {
		if(blocs[x][y] instanceof Bloc) {
			Bloc tmp = (Bloc)blocs[x][y];
			if(tmp.getNumero() == -1) {
				for(int i = x-1; i>=0; i--) {
					if(blocs[i][y] instanceof Bloc) {
						Bloc aux = (Bloc)blocs[i][y];
						
							blocs[i+1][y] = aux;
							
							blocs[i][y] = new Bloc(-1);
							
						
					}
					else {
						Animal aux = (Animal)blocs[i][y];
						blocs[i+1][y] = aux;
						blocs[i][y] = new Bloc(-1);							
					}
				}
			}
		}
		
		
	}
	
 
	private boolean colonneVideTer(int y) {
		for(int i = 0; i<blocs.length; i++) {
			
				if(blocs[i][y] instanceof Animal) {
					return false;
				}
				else {
					Bloc aux = (Bloc)blocs[i][y];
					if(aux.getNumero() != -1) {
						return false;
					}
				}
			
		} return true;
	}
 
	public void decalerTer() {
		for(int i = 1; i<blocs.length-1; i++) {
			for(int j = 1; j<blocs[i].length-2; j++) {
				if(colonneVideTer(j)) {
					decalerColonneTer(j);
				}
			}
		}
	}
	
	public void decalerColonneTer(int y) {
		for(int i = 1; i<blocs.length-1; i++) {
			if(blocs[i][y+1] instanceof Bloc) {
				Bloc tmp = (Bloc)blocs[i][y+1];
				blocs[i][y] = tmp;
				blocs[i][y+1] = new Bloc(-1);
			}
			else {
				Animal tmp = (Animal)blocs[i][y+1];
				blocs[i][y] = tmp;
				blocs[i][y+1] = new Bloc(-1);
			}
		}
	}
	
	private boolean uniquementAnimauxTer(int y) {
		for(int i = 1; i<blocs.length-1; i++) {
				if(colonneVideTer(y)) {
					return false;
				}
				else if(blocs[i][y] instanceof Bloc) {
					Bloc tmp = (Bloc)blocs[i][y];
					if(tmp.getNumero() != -1) {
						return false;
					}
				}
			
		}
		return true;
	}
	
	public void sauverAnimauxTer() {
		for(int i = 1; i<blocs.length-1; i++) {
			for(int j = 1; j<blocs[i].length-1; j++) {
				sauverAnimauxColonneTer(j);
			}
		}
	}
	
	//Fonctionne
	public void sauverAnimauxColonneTer(int y) {
		if(uniquementAnimauxTer(y)) {
			while(!colonneVideTer(y)) {
				if(blocs[blocs.length-2][y] instanceof Animal) { //2nde vérification
					nbAnimauxSauves ++;
					blocs[blocs.length-2][y] = new Bloc(-1);
					for(int i = 1; i<blocs.length-1 ;i++) {
						ajusterColonneTer(i,y);
					}
				}
			}
		} 
	}
	
	public void afficherPoints() {
		System.out.println(pointsAccumules+" points");
	}
	
	public void afficherAnimauxSauves() {
		System.out.println("Animaux sauves: "+nbAnimauxSauves+"/"+niveau.getAnimauxASauver());
	}

}

