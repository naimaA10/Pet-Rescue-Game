import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Jeu extends JFrame {

	protected VueJeu v;
	
	Jeu(Joueur joueur) {
		v = new VueJeu();
		v.setVisible(true);
	}

	public static void main(String[] args) {
		Joueur joueur = new Joueur("test");
		
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
		mod[0] = new Modele(6,6,niv[0],joueur);
		mod[1] = new Modele(6,6,niv[1],joueur);
		mod[2] = new Modele(6,6,niv[2],joueur);
		mod[3] = new Modele(6,6,niv[3],joueur);
		mod[4] = new Modele(6,6,niv[4],joueur);
		
		
		//ModeleJeu
		ModeleJeu m = new ModeleJeu(joueur,niv,mod);
		
		//Jeu
		Jeu jeu = new Jeu(joueur);
		
		//Vue du Jeu
		jeu.v.setModeleJeu(m);
		ControleurJeu c = new ControleurJeu(jeu.v);
		c.setModeleJeu(m);
		jeu.v.setCtrlJeu(c);
		c.fonction();
		jeu.v.niveauJouable();
		jeu.v.niveauTip();
	}
}


