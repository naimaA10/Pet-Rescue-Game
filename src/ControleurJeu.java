public class ControleurJeu {
	
	protected VueJeu vueJeu;
	protected ModeleJeu modeleJeu;
	
	public ControleurJeu(VueJeu vueJeu) {
		this.vueJeu = vueJeu;

	}
	
	public void setModeleJeu(ModeleJeu m) {
		modeleJeu = m;
	}
	
	public void setVueJeu(VueJeu v) {
		vueJeu = v;
	}
	
	//Affiche le plateau
	public void fonction() {
		for(int i = 0; i<vueJeu.niveaux.length; i++) {
			int counter = i;
			vueJeu.niveaux[i].addActionListener( (event) -> {
				Plateau p = new Plateau();
				Controleur ctrl = new Controleur(p.view);
				ctrl.setModele(modeleJeu.getPlateauTab()[counter]);
				p.view.setCtrl(ctrl);
				p.view.setModele(modeleJeu.getPlateauTab()[counter]);
				p.view.modele.setVueJeu(vueJeu);
				p.view.grille();
				p.view.booster();
				//p.view.robot(); 
			});
		
		}
	}


}
