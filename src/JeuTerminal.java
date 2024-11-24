import java.util.InputMismatchException;
import java.util.Scanner;

public class JeuTerminal {
	
	private Scanner sc; 
	private Joueur joueur;
	protected ModeleJeu modeleJeu;
	
	
	public JeuTerminal() {
		sc = new Scanner(System.in);
	}
	
	public Joueur getJoueur() {
		return joueur;
	}
	
	public void setModeleJeu(ModeleJeu m) {
		modeleJeu = m;
	}
	
	public String demanderStr(String s) {
		try {
			System.out.println(s);
			return sc.nextLine();
		}
		catch(InputMismatchException e) {
			return "Vous n'avez pas rentrez une lettre";
		}
	}
	
	public int demanderInt(String s) {
		try {
			System.out.println(s);
			return sc.nextInt();
		}
		catch(InputMismatchException e) {
			System.out.println("Vous n'avez pas rentré de chiffres");
			return -1;
		}
	}
	
	public boolean demandeJouer() {
		String s = demanderStr("Voulez-vous jouer : (o/n)");
		if(s.equalsIgnoreCase("o")) {
			return true;
		}
		return false;
	}
	
	public String demanderPseudo() {
		String s = demanderStr("Entre un pseudo : ");
		joueur = new Joueur(s);
		return s;
	}
	
	public int demanderNiveau() {
		int n = demanderInt("Quel niveau voulez-vous jouer ?");
		while(!modeleJeu.niveauJouable(n)) {
			n = demanderInt("Quel niveau voulez-vous jouer ?");
		}
		return n;
	}
	
	public int[] demanderCoup() {
		int[] coord = new int[2];
		int x = demanderInt("Donner un numéro de ligne : ");
		int y = demanderInt("Donner un numéro de colonne : ");
		coord[0] = x;
		coord[1] = y;
		return coord;
	}
	
	public boolean demanderBlockBuster() {
		if(joueur.getNbBlockBuster() > 0) {
			demanderStr("Utiliser un BlockBuster ? (o/n)");
			if(sc.nextLine().equalsIgnoreCase("o")) {
				return true;
			}
		}return false;
	}
	
	public boolean demanderColorPop() {
		if(joueur.getNbBlockBuster() > 0) {
			String s = demanderStr("Utiliser un ColorPop ? (o/n)");
			if(s.equalsIgnoreCase("o")) {
				return true;
			}
		}return false;
	}
	
	public boolean demanderColumnBaster() {
		if(joueur.getNbBlockBuster() > 0) {
			String s = demanderStr("Utiliser un ColumnBlaster ? (o/n)");
			if(s.equalsIgnoreCase("o")) {
				return true;
			}
		}return false;
	}
	
	public void utiliserBlockBuster(int n) {
		if(demanderBlockBuster()) {
			BlockBuster b = new BlockBuster();
			
			int[]c = demanderCoup();
			
			while(!coupValide(n, c[0], c[1])) {
				c = demanderCoup();
			}
			b.fonction(modeleJeu.getPlateauTab()[n-1].getBlocs(), c[0], c[1]);
			joueur.setNbBlockBuster(joueur.getNbBlockBuster()-1);
			
		}
	}
	
	public void utiliserColorPop(int n) {
		if(demanderColorPop()) {
			ColorPop b = new ColorPop();
			int[]c = demanderCoup();
			while(!coupValide(n, c[0], c[1])) {
				c = demanderCoup();
			}
			b.fonction(modeleJeu.getPlateauTab()[n-1].getBlocs(), c[0], c[1]);
			joueur.setNbColorPop(joueur.getNbColorPop()-1);
		}
	}
	
	public void utiliserColumnBlaster(int n) {
		if(demanderColumnBaster()) {
			ColumnBlaster b = new ColumnBlaster();
			int[]c = demanderCoup();
			while(!coupValide(n, c[0], c[1])) {
				c = demanderCoup();
				affichagePlateau(n);
			}
			b.fonction(modeleJeu.getPlateauTab()[n-1].getBlocs(), c[1]);
			joueur.setNbColumnBlaster(joueur.getNbColumnBlaster()-1);
		}
	}
	
	public void utiliserBooster(int n) {

		utiliserBlockBuster(n);
		utiliserColorPop(n);
		utiliserColumnBlaster(n);
		modeleJeu.getPlateauTab()[n-1].ajusterTer();
		modeleJeu.getPlateauTab()[n-1].sauverAnimauxTer();
		modeleJeu.getPlateauTab()[n-1].decalerTer();
	}
	
	public boolean coupValide(int n, int x, int y) {
		if(modeleJeu.getPlateauTab()[n-1].getBlocs()[x][y] != null &&
				modeleJeu.getPlateauTab()[n-1].getBlocs()[x][y] instanceof Bloc) {
			Bloc aux = (Bloc)modeleJeu.getPlateauTab()[n-1].getBlocs()[x][y];
			if(aux.getNumero() != -1) {
				return true;
			}
		}
		return false;
	}
	

	
	public void affichagePlateau(int n) { 

		for(int i = 1; i<modeleJeu.getPlateauTab()[n-1].getBlocs().length-1; i++) {
			System.out.print("   "+i+"   ");
		}
		System.out.println();
		for(int i = 1; i<modeleJeu.getPlateauTab()[n-1].getBlocs().length-1; i++) { 
			for(int j = 1; j<modeleJeu.getPlateauTab()[n-1].getBlocs()[i].length-1; j++) {
				if(modeleJeu.getPlateauTab()[n-1].getBlocs()[i][j] instanceof Bloc) {
					Bloc aux = (Bloc)modeleJeu.getPlateauTab()[n-1].getBlocs()[i][j];
					if(aux.getNumero() == -1) {
						System.out.print("| "+modeleJeu.getPlateauTab()[n-1].getBlocs()[i][j]+"  |");
					}
					else {
						System.out.print("|  "+modeleJeu.getPlateauTab()[n-1].getBlocs()[i][j]+"  |");
					}
				}
				else {
					System.out.print("|  "+modeleJeu.getPlateauTab()[n-1].getBlocs()[i][j]+"  |");
				}

			}
			System.out.print("  "+i);
			System.out.println();
		}
	}
	
	
	public void jouerSurTerminal() {
			
			joueur.afficheInfos();
		
			//Affichage des niveaux accessibles
			modeleJeu.afficheNiveauxJouable();
			
			//Quel niveau jouer ?
			int n = demanderNiveau();
			
			//Initialiser le plateau du niveau en question
			modeleJeu.getPlateauTab()[n-1].initialisation();
			while(!modeleJeu.getPlateauTab()[n-1].initialisationCorrecte()) {
				modeleJeu.getPlateauTab()[n-1].initialisation();
			}
			
			//aficher le plateau
			affichagePlateau(n);
			
			//BlockBuster
			joueur.afficheBooster();
			utiliserBooster(n);
			
			//Blocs
			affichagePlateau(n);
			
			int[]coord = demanderCoup();
			while(!coupValide(n, coord[0], coord[1])) {
				System.out.println("COUP INVALIDE");
				affichagePlateau(n);
				coord = demanderCoup();
			}
			
			//Les voisins du blocs
			modeleJeu.getPlateauTab()[n-1].voisinsAutourTer(coord[0], coord[1]);
			modeleJeu.getPlateauTab()[n-1].ajusterTer();
			modeleJeu.getPlateauTab()[n-1].sauverAnimauxTer();
			modeleJeu.getPlateauTab()[n-1].decalerTer();
			affichagePlateau(n);
			modeleJeu.getPlateauTab()[n-1].afficherPoints();
			while(!modeleJeu.getPlateauTab()[n-1].victoire() || !modeleJeu.getPlateauTab()[n-1].coupPossible()) {
				joueur.afficheBooster();
				utiliserBooster(n);
				affichagePlateau(n);
				
				int[]c = demanderCoup();
				while(!coupValide(n, c[0], c[1])) {
					System.out.println("COUP INVALIDE");
					affichagePlateau(n);
					c = demanderCoup();
				}
				
				//Les voisins du blocs
				modeleJeu.getPlateauTab()[n-1].voisinsAutourTer(c[0], c[1]);
				modeleJeu.getPlateauTab()[n-1].ajusterTer();
				modeleJeu.getPlateauTab()[n-1].sauverAnimauxTer();
				modeleJeu.getPlateauTab()[n-1].decalerTer();
				affichagePlateau(n);
				modeleJeu.getPlateauTab()[n-1].afficherPoints();
				modeleJeu.getPlateauTab()[n-1].afficherAnimauxSauves();
			}
			if(modeleJeu.getPlateauTab()[n-1].victoire()) {
				System.out.println("Victoire !");
				modeleJeu.getPlateauTab()[n-1].etoilesFin();
				modeleJeu.getPlateauTab()[n-1].piecesFin();
				modeleJeu.getPlateauTab()[n-1].lingotsFin();
				modeleJeu.getPlateauTab()[n-1].boostersFin();
			}
			else if(!modeleJeu.getPlateauTab()[n-1].coupPossible()) {
				System.out.println("Plus de coups possible. Défaite");
				Temps t = new Temps(joueur);
				t.minuteur();
			}

	}
	
	public static void main(String[]args) {
		JeuTerminal j = new JeuTerminal();
		j.demanderPseudo();

			//Points
			int[][]points = new int[5][3];
			points[0][0] = 100;
			points[0][1] = 200;
			points[0][2] = 300;
			
			points[1][0] = 200;
			points[1][1] = 300;
			points[1][2] = 400;
			
			points[2][0] = 300;
			points[2][1] = 400;
			points[2][2] = 500;
			
			points[3][0] = 400;
			points[3][1] = 500;
			points[3][2] = 600;
			
			points[4][0] = 500;
			points[4][1] = 600;
			points[4][2] = 700;
			
			//Niveaux
			Niveau[]niv = new Niveau[5];
			niv[0] = new Niveau(points[0],2);
			niv[1] = new Niveau(points[1],2);
			niv[2] = new Niveau(points[2],3);
			niv[3] = new Niveau(points[3],3);
			niv[4] = new Niveau(points[4],4);
			

			
			//Modeles
			Modele[]mod = new Modele[5];
			mod[0] = new Modele(6,6,niv[0],j.joueur);
			mod[1] = new Modele(6,6,niv[1],j.joueur);
			mod[2] = new Modele(6,6,niv[2],j.joueur);
			mod[3] = new Modele(6,6,niv[3],j.joueur);
			mod[4] = new Modele(6,6,niv[4],j.joueur);
			
			//ModeleJeu
			ModeleJeu m = new ModeleJeu(j.joueur,niv,mod);

			j.setModeleJeu(m);
			boolean jouer = j.demandeJouer();
			while(jouer) {
				j.jouerSurTerminal();
				j.demandeJouer();
				String s = j.sc.nextLine();
				if(s.equalsIgnoreCase("o")) {
					jouer = true;
				}
				else {
					jouer = false;
				}
			}
		

	}

}

