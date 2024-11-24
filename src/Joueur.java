import java.util.ArrayList;

public class Joueur {
	
	//Identité joueur 
	@SuppressWarnings("unused")
	private String pseudo;
	
	//les infos sur le jeu du joueur 
	private int[]etoilesParNiveau;
	private int nbDeVies = 5;
	private int lingots = 0;
	private int argent = 0;
	private Robot r=new Robot();
	private int nbBlockBuster;
	private int nbColorPop;
	private int nbColumnBlaster;
	private ArrayList<Booster> listeBooster;
	
	
	//Constructeur Joueur
	public Joueur(String pseudo) {
		etoilesParNiveau = new int[5];
		this.pseudo=pseudo;
		nbBlockBuster=5; //Initialement le joueur à 0 booster
		nbColorPop=5;
		nbColumnBlaster=5;
		listeBooster = new ArrayList<Booster>();
	}
		
	//pour le terminal
	public void afficheInfos() {
		System.out.println("Nombre de vies : "+nbDeVies+", Argent : "+argent
				+", Lingots : "+lingots);
	}
	
	public void afficheBooster() {
		System.out.println("BlockBuster(s) : "+nbBlockBuster+", ColorPop : "+nbColorPop
				+", ColumnBlaster(s) : "+nbColumnBlaster);
	}
	
	//Getter
	public int getVies() {
		return nbDeVies;
	}
	
	public int getLingots() {
		return lingots;
	}
	
	public int getArgent() {
		return argent;
	}
	
	public int getEtoiles(Niveau n) {
		return etoilesParNiveau[n.getIntNiv()-1];
	}
	
	public int getLastIntNiveau() {
		for(int i = 0; i<etoilesParNiveau.length; i++) {
			if(etoilesParNiveau[i] == 0) {
				return i-1;
			}
		} return etoilesParNiveau.length;
	}
		

	
	public int getNbBlockBuster() {
		return nbBlockBuster;
	}

	public int getNbColorPop() {
		return nbColorPop;
	}
	
	public int getNbColumnBlaster() {
		return nbColumnBlaster;
	}
	
	public ArrayList<Booster> getListeBooster(){
		return listeBooster;
	}
	
	public Robot getRobot() {
		return r;
	}

	//Setter	
	public void setNbColumnBlaster(int n) {
		this.nbColumnBlaster = n;
	}
	
	public void setNbColorPop(int nbColorPop) {
		this.nbColorPop = nbColorPop;
	}
	
	public void setNbBlockBuster(int nbBlockBuster) {
		this.nbBlockBuster = nbBlockBuster;
	}

	
	public void setEtoiles(Niveau niv, int etoiles) {
		etoilesParNiveau[niv.getIntNiv()-1] = etoiles;
	}

	public void setVies(int n) {
		if (n < 0) nbDeVies=0;
		else {
			nbDeVies = n;	
		}
	}

	public void setLingots(int n) {
		lingots = n;
	}
		
	public void setArgent(int n) {
		argent = n;
	}
	
	public void setListeBooster(ArrayList<Booster> b) {
		listeBooster = b;
	}

	
	public void addListeBooster(Booster b) {
		listeBooster.add(b);
	}
	
	
	public void gainVies() {
		Temps t = new Temps(this);
		t.minuteur();
	}
	
	
	public String toString() { 
		return "Nombre de vies : "+nbDeVies+", Argent : "+argent
				+", Lingots : "+lingots;
	}
	
	//Achat booster (un booster = 1000 piece ou 5 lingots)
	
	//BlocBuster
	public void AchatPieceBlockBuster(int n) {
		if (getArgent() >= n*1000) {
			setArgent(getArgent()-n*1000);
			setNbBlockBuster(nbBlockBuster+n);
		}
		else {
			System.out.println("Vous n'avez pas assez de piece pour acheté "+n+" BlockBuster");
		}	
	}
	
	public void AchatLingotsBlockBuster(int n) {
		if (getLingots() >= n*5) {
			setLingots(getLingots()-n*5);
			setNbBlockBuster(nbBlockBuster+n);
		}
		else {
			System.out.println("Vous n'avez pas assez de lingots pour acheté "+n+" BlockBuster");
		}	
	}
	
	//ColorPop
	public void AchatPieceColorPop(int n) {
		if (getArgent() >= n*1000) {
			setArgent(getArgent()-n*1000);
			setNbColorPop(nbColorPop+n);
		}
		else {
			System.out.println("Vous n'avez pas assez de piece pour acheté "+n+" ColorPop");
		}	
	}
	
	public void AchatLingotsColorPop(int n) {
		if (getLingots() >= n*5) {
			setLingots(getLingots()-n*5);
			setNbColorPop(nbColorPop+n);
		}
		else {
			System.out.println("Vous n'avez pas assez de lingots pour acheté "+n+" ColorPop");
		}	
	}
	
	//ColumnBlaster
	public void AchatPieceColumnBlaster(int n) {
		if (getArgent() >= n*1000) {
			setArgent(getArgent()-n*1000);
			setNbColumnBlaster(nbColumnBlaster+n);
		}
		else {
			System.out.println("Vous n'avez pas assez de piece pour acheté "+n+" ColumnBlaster");
		}	
	}
	
	public void AchatLingotsColumnBlaster(int n) {
		if (getLingots() >= n*5) {
			setLingots(getLingots()-n*5);
			setNbColumnBlaster(nbColumnBlaster+n);
		}
		else {
			System.out.println("Vous n'avez pas assez de lingots pour acheté "+n+" ColumnBlaster");
		}	
	}

	//Classe interne Robot
	public class Robot {

		// Le robot joue un coup aléatoirement
		public void CoupRobot(Modele modele) {
			int coup = 1; // à chaque appele de la méthode on a 1 coup

			while (coup == 1) {
				// Parcourir plateau
				for (int i = 1; i < modele.getBlocs().length - 1; i++) {
					for (int j = 1; j < modele.getBlocs()[1].length - 1; j++) {

						// Si le blocs à des voisins on supprime le blocs est c'est voisin et ajuste le plateau
						modele.voisinsAutour(i, j);
						modele.ajuster();
						modele.sauverAnimaux();
						modele.decaler();
						coup -= 1;
					}
				}
				coup-=1;
			}
		}
				
	}
}



