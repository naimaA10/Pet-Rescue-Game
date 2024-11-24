import java.awt.event.ActionEvent;

public class Controleur {
	
	protected Modele modele;
	protected Vue view;
	
	public Controleur(Vue view) {
		this.view = view;
	}
	
	public void setModele(Modele m) {
		modele = m;
	}
		
	
	@SuppressWarnings("static-access")
	public void fonctionBloc(ActionEvent e, int l, int c) {
		boolean booster = false;
		for(Booster b : modele.getJoueur().getListeBooster()) {
			if(b.clic) {
				booster = true;
				if(b instanceof BlockBuster) {
					BlockBuster tmp = (BlockBuster)b;
					tmp.fonction(modele.getBlocs(), l, c);
					modele.getJoueur().setNbBlockBuster(modele.getJoueur().getNbBlockBuster()-1);
					break;
				}
				if(b instanceof ColorPop) {
					ColorPop tmp = (ColorPop)b;
					tmp.fonction(modele.getBlocs(), l, c);
					modele.getJoueur().setNbColorPop(modele.getJoueur().getNbColorPop()-1);
					break;
				}
				if(b instanceof ColumnBlaster) {
					ColumnBlaster tmp = (ColumnBlaster)b;
					tmp.fonction(modele.getBlocs(), c);
					modele.getJoueur().setNbColumnBlaster(modele.getJoueur().getNbColumnBlaster()-1);
					break;
				}
			}
						
		}

		if(booster) {
			modele.ajuster();
			modele.sauverAnimaux();
			modele.decaler();
			modele.pointsApresCoup();
			view.informationApresCoup();
			view.grilleApresCoup();
			view.messageFin(e);
			view.booster();  
		}
		else {
			modele.voisinsAutour(l,c);
			modele.ajuster();
			modele.sauverAnimaux();
			modele.decaler();
			modele.pointsApresCoup();
			view.informationApresCoup();
			view.grilleApresCoup();
			view.messageFin(e);
		}
		if(modele.victoire()) {
			modele.etoilesFin();
			modele.piecesFin();
			modele.lingotsFin();
			modele.vueJeu.infoApresNiveau(modele.getNiveau()); 
			modele.reinitialisationDonnees();
		}
		
		if(!modele.coupPossible()) {
			Temps t = new Temps(modele.getJoueur());
			t.minuteur();
			modele.vueJeu.infoApresNiveau(modele.getNiveau());
		}

	}
	
}

