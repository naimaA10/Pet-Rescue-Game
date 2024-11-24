import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class VueJeu extends JFrame  {
	
	private static final long serialVersionUID = 1L;
	private JButton vie = new JButton(""); 
	private JButton lingot = new JButton(); // affiche le nombre de lingot du joueur
	private JButton piece = new JButton(); // affiche le nombre de piece

	protected JButton[] niveaux;
	protected JLabel[] etoiles;
	protected JPanel level;
	boolean clic = false;
	
	protected ModeleJeu modeleJeu;
	protected ControleurJeu ctrlJeu;
	
	VueJeu() {
		setTitle("Jeu");
		setSize(500, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false); // Quand on lance la fenêtre on peut pas l'agrandir
		setLocationRelativeTo(null); // Affiche la fenêtre au centre de l'écran
		getContentPane().setLayout(null);

		//Actualiser vie,lingots et piece du joueur
		vie.setText("Vie(s) : 5");
		lingot.setText("0 Lingot(s)");
		piece.setText("0 Piece(s)");
		
		// Ajouter les infos sur le jeu du joueur
		JPanel panneau = new JPanel();

		vie.setPreferredSize(new Dimension(100, 30));
		vie.setBackground(Color.pink);

		lingot.setPreferredSize(new Dimension(100, 30));
		lingot.setBackground(Color.yellow);

		piece.setPreferredSize(new Dimension(100, 30));
		piece.setBackground(Color.LIGHT_GRAY);

		panneau.add(vie);
		panneau.add(lingot);
		panneau.add(piece);

		panneau.setBounds(0, 0, 500, 40);
		getContentPane().add(panneau);

		// Ajouter les différents niveau
		level = new JPanel(new GridLayout(5, 1, 10, 20));
		
		niveaux = new JButton[5];
		for(int i = 0; i<niveaux.length; i++) {
			niveaux[i] = new JButton("Niveau "+(i+1));
		}
		
		etoiles = new JLabel[5];
		for(int i = 0; i<etoiles.length; i++) {
			etoiles[i] = new JLabel("Etoile(s) : ");
		}
		
		
		niveaux[0].setBackground(new Color(176, 224, 230));
		niveaux[1].setBackground(new Color(176, 224, 230));
		niveaux[2].setBackground(new Color(176, 224, 230));
		niveaux[3].setBackground(new Color(176, 224, 230));
		niveaux[4].setBackground(new Color(176, 224, 230));

		level.add(niveaux[0]); level.add(etoiles[0]);
		level.add(niveaux[1]); level.add(etoiles[1]);
		level.add(niveaux[2]); level.add(etoiles[2]);
		level.add(niveaux[3]); level.add(etoiles[3]);
		level.add(niveaux[4]);	level.add(etoiles[4]);

		level.setBounds(180, 80, 300, 300);
		getContentPane().add(level);

		setVisible(true);
	}
	
	public void setModeleJeu(ModeleJeu m) {
		modeleJeu = m;
	}
	
	public void setCtrlJeu(ControleurJeu c) {
		ctrlJeu = c;		
	}
	
	public void infoApresNiveau(Niveau n) {
		infoLingots();
		infoPieces();
		infoVies();
		infoEtoiles(n);
		niveauJouable();
	}
	
	public void infoLingots() {
		lingot.setText(modeleJeu.getJoueur().getLingots()+" Lingot(s)");
	}
	
	public void infoPieces() {
		piece.setText(modeleJeu.getJoueur().getArgent()+" Pièce(s)");
	}
	
	public void infoVies() {
		vie.setText("Vies: "+modeleJeu.getJoueur().getVies());
		
	}
	
	public void infoEtoiles(Niveau n) {
		etoiles[n.getIntNiv()-1].setText("Etoiles: "+modeleJeu.getJoueur().getEtoiles(n));
	}
	
	//Seuls les niveaux jouables sont accessibles, par ex on ne peut passer du niv 1 au niv 3 sans avoir réussi au moins une fois le niv 2
	public void niveauJouable() {
		for(int i = 1; i<modeleJeu.getNiveauTab().length; i++) {
			if(modeleJeu.niveauJouable(modeleJeu.getNiveauTab()[i])) {
				niveaux[i].setEnabled(true);
			}
			else {
				niveaux[i].setEnabled(false);
			}
		}
	}
	
	public void niveauTip() {
		for(int i = 0; i<niveaux.length; i++) {
			niveaux[i].setToolTipText("Sauve "+modeleJeu.getNiveauTab()[i].getAnimauxASauver()
		+" animaux, Obtenir "+modeleJeu.getNiveauTab()[i].getPointsAObtenir(0));
		}
	}
	

	
	public static void main(String[] args) {
		new VueJeu();
	}







}

