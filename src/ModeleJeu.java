
public class ModeleJeu {
	
	private Joueur joueur;
	private Niveau[] niv;
	private Modele[] plateau;

	ModeleJeu(Joueur j, Niveau[]niv, Modele[]p){
		joueur = j;
		this.niv = niv;
		plateau = p;
	}
	
	public Joueur getJoueur() {
		return joueur;
	}
	
	public Niveau[] getNiveauTab() {
		return niv;
	}
	
	public Modele[] getPlateauTab() {
		return plateau;
	}
	
	public boolean niveauJouable(Niveau n) {
		for(int i = 1; i<niv.length; i++) {
			if(niv[i].equals(n)) {
				if(joueur.getEtoiles(niv[i-1]) > 0) {
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean niveauJouable(int n) {
		if(n == 1) {
			return true;
		}
		for(int i = 1; i<niv.length; i++) {
			if(niv[i].getIntNiv() == n) {
				if(joueur.getEtoiles(niv[i-1]) > 0) {
					return true;
				}
			}
		}
		return false;
	}
	
	//pour le terminal
	public void afficheNiveauxJouable() {
		System.out.println("Niveau "+niv[0].getIntNiv()+" Etoile(s) : "+joueur.getEtoiles(niv[0])
		+ ", Sauve : "+niv[0].getAnimauxASauver()+" animaux, Obtenir : "+niv[0].getPointsAObtenir(0)+" points");
		for(int i = 1; i<niv.length; i++) {
			if(niveauJouable(niv[i])) {
				System.out.println("Niveau "+niv[i].getIntNiv()+" Etoile(s) : "+joueur.getEtoiles(niv[i])
				+ ", Sauve : "+niv[0].getAnimauxASauver()+" animaux, Obtenir : "+niv[i].getPointsAObtenir(0)+" points");
			}
		}
	}
}

