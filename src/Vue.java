import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.WindowConstants;


@SuppressWarnings("serial")
public class Vue extends JFrame {
		
	protected JLabel animauxSauves = new JLabel("", JLabel.CENTER);
	protected JLabel pointsGagnes = new JLabel("000000", JLabel.CENTER);
	protected JProgressBar barre =new JProgressBar();
	
	protected JButton quit = new JButton("X");
	protected JButton block = new JButton("BlockBuster :");
	protected JButton color = new JButton("ColorPop :");
	protected JButton column = new JButton("ColumnBlaster :");
	protected JButton jeuduRobot = new JButton("Robot"); //ne fonctionne pas
	
	protected JPanel info = new JPanel();
	protected JPanel grille = new JPanel();
	protected JPanel panneauBoutons = new JPanel();
	protected JPanel total = new JPanel();
	
	
	Modele modele; 
	Controleur ctrl;
	
	
	public Vue() {
		setTitle("Pet Rescue Saga");
		setSize(500, 500);
		setResizable(false); //Quand on lance la fenêtre on peut pas l'agrandir
		setLocationRelativeTo(null); //Affiche la fenêtre au centre de l'écran
		
	    add(info, BorderLayout.NORTH);
	    add(grille);
	    setVisible(true);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    //animaux sauvés
	    animauxSauves.setText("Animaux sauvés : ");
	    
	    //points gagnés
	    pointsGagnes.setText("000000");
	    
	    //barre de progression
	    barre = new JProgressBar(0,50);
	    
	    //info
	    info.setLayout(new BorderLayout());
	    info.add(animauxSauves, BorderLayout.WEST);
	    info.add(pointsGagnes, BorderLayout.CENTER);
	    info.add(barre, BorderLayout.EAST);
	    
	    //boosters
	    block.setToolTipText("Cliquer sur un bloc pour l'éliminer");
	    color.setToolTipText("Cliquer sur un bloc pour éliminer les blocs de même couleur");
	    column.setToolTipText("Cliquer sur un bloc pour éliminer sa colonne");
	    
	    // Robot
	 	jeuduRobot.setToolTipText("Clic ici et le robot joue un coup aléatoire !");
	    
	    //panneauBoutons
	    panneauBoutons.add(quit, BorderLayout.WEST);
	    panneauBoutons.add(block);
	    panneauBoutons.add(color);
	    panneauBoutons.add(column);
	    panneauBoutons.add(jeuduRobot);
	    quit.addActionListener((event) -> messageFin(event));
	    
	    
	    //total
	    total.setLayout(new BorderLayout());
	    total.add(info, BorderLayout.NORTH);
	    total.add(grille, BorderLayout.CENTER);
	    total.add(panneauBoutons, BorderLayout.SOUTH); //devrait s'afficher en dernier mais s'affiche au milieu 
	    
	    
	    add(total);
	}
	
	public void setModele(Modele m) {
		modele = m;
	}
	
	public Modele getModele() {
		return modele;
	}
	
	public void setCtrl(Controleur c) {
		ctrl = c;
	}
	
	public Controleur getCtrl() {
		return ctrl;
	}
	
	public void initialisation() {
		modele.blocs = new Object[modele.getLignes()+2][modele.getColonnes()+2];
		placerAnimaux();
		for(int i = 0; i<modele.blocs.length; i++) {
			for(int j = 0; j<modele.blocs[i].length; j++) { 
				if(i == 0 || i == modele.blocs.length-1 || j == 0 || j == modele.blocs[i].length-1) {
					modele.blocs[i][j] = new Bloc(-1);
					Bloc aux = (Bloc)modele.blocs[i][j];
					aux.setCoord(i, j);
				}
				else {
					if(!(modele.blocs[i][j] instanceof Animal)) {
						modele.blocs[i][j] = new Bloc(ctrl); 
						Bloc aux = (Bloc)modele.blocs[i][j];
						aux.setCoord(i, j);
						aux.setBtn();
						
					}
				}
				
			}
		}
		
		
	}
	
	public void placerAnimaux() {
		for(int i = 0; i<modele.getNiveau().getAnimauxASauver(); i++) {
			while(!modele.tousLesAnimauxPlaces()) {
			
				Random r = new Random();
				int c = r.nextInt(modele.getColonnes())+1; //chiffre entre 1 et colonnes
				
				modele.blocs[1][c] = new Animal(); 
				//les animaux ne seront que sur la premiere ligne
			}
		}
	}
	
	public void grille() {
		initialisation();
		
		grille.setLayout(new GridLayout(modele.getLignes(), modele.getColonnes()));
		
	    for(int i = 1; i<modele.blocs.length-1; i++) { 
	    	
			for(int j = 1; j<modele.blocs[i].length-1; j++) {
				
				if(modele.blocs[i][j] instanceof Bloc) {
					
					Bloc aux = (Bloc)modele.blocs[i][j];
					
					if(aux.getNumero() != -1) {
						grille.add(aux.getBtn());
					}
				}
				else {
					
					Animal animal = (Animal)(modele.blocs[i][j]);
					
					grille.add(animal.label);
				}
				
			}
			System.out.println();
		}
	    total.add(grille);
	    setContentPane(total);
	}
	

	
	public void informationApresCoup() {
		infoBoosters();
		infoAnimaux();
		infoPoints();
		infoEtoiles();
	}
	
	//afficher le nombre d'animaux sauvés au fur et à mesure qu'on exécute un coup 
	public void infoAnimaux() {
		animauxSauves.setText("Animaux sauvés "+modele.getNbAnimauxSauves()
				+"/"+modele.getNiveau().getAnimauxASauver());
		animauxSauves.setVisible(true);		
	}
	
	//afficher les points au fur et à mesure qu'on exécute un coup
	public void infoPoints() {
		pointsGagnes.setText(String.format("%06d",modele.getPointsAccumules()));
	}
	
	//afficher etoiles en fonction des points obtenus
	public void infoEtoiles() {
		barre.setValue(modele.getPointsAccumules());
		if(modele.getPointsAccumules() >= modele.getNiveau().getPointsAObtenir(0) 
				&& modele.getPointsAccumules() < modele.getNiveau().getPointsAObtenir(1)) {
			barre.setString("1 étoile");
			barre.setStringPainted(true);
		}
		else if(modele.getPointsAccumules() >= modele.getNiveau().getPointsAObtenir(1) 
				&& modele.getPointsAccumules() < modele.getNiveau().getPointsAObtenir(2)) {
			barre.setString("2 étoiles");
			barre.setStringPainted(true);
		}
		else if(modele.getPointsAccumules() >= modele.getNiveau().getPointsAObtenir(2)) {
			barre.setString("3 étoiles");
			barre.setStringPainted(true);
		}
		barre.setVisible(true);
		
	}
	
	public void grilleApresCoup() {
		grille.removeAll();
		
		for(int i = 1; i<modele.blocs.length-1; i++) { 
			for(int j = 1; j<modele.blocs[i].length-1; j++) {
				if(modele.blocs[i][j] instanceof Bloc) {
					Bloc aux = (Bloc)modele.blocs[i][j];
					
						grille.add(aux.getBtn());
					
				}
				else {
					Animal animal = (Animal)(modele.blocs[i][j]);
					grille.add(animal.label);
				}
				
			}
			System.out.println();
		}
		
		
		grille.revalidate(); 
		grille.repaint();

	}
	
	public void modifier(ActionEvent e,int l, int c) {
		ctrl.setModele(modele);
		ctrl.fonctionBloc(e, l, c);
	}
	
	public void infoBoosters() {
		block.setText("BlockBuster: "+modele.getJoueur().getNbBlockBuster());
		color.setText("ColorPop: "+modele.getJoueur().getNbColorPop());
		column.setText("ColumnBlaster: "+modele.getJoueur().getNbColumnBlaster());
	}
	
	public void booster() {
		modele.boosters();
		block.addActionListener((event) -> {
			for(Booster b : modele.getJoueur().getListeBooster()) {
				if(b instanceof BlockBuster) {
					b.clic = true;
					break;
				}
			}
		});
		color.addActionListener((event) -> {
			for(Booster b :modele.getJoueur().getListeBooster()) {
				if(b instanceof ColorPop) {
					b.clic = true;
					break;
				}
			}
		});
		column.addActionListener((event) -> {
			for(Booster b : modele.getJoueur().getListeBooster()) {
				if(b instanceof ColumnBlaster) {
					b.clic = true;
					break;
				}
			}
		});
	}
	
	//Le robot joue un coup aléatoire
	public void robot() {
		jeuduRobot.addActionListener((event) -> {
			modele.getJoueur().getRobot().CoupRobot(modele);
		});
	}
	
	public void messageFin(ActionEvent e) {
		//message
		JFrame message = new JFrame();
		message.setSize(300,200);
		message.setResizable(false); //Quand on lance la fenêtre on peut pas l'agrandir
		message.setLocationRelativeTo(null); //Affiche la fenêtre au centre de l'écran
		message.setVisible(false);
		message.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		
		//panel
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		
		//éléments dans le panel
		JLabel label = new JLabel("", JLabel.CENTER);
		JButton exit = new JButton("Quitter");
		JButton oui = new JButton("Oui");
		JButton non = new JButton("Non");
		
		exit.setSize(80,70);
		oui.setSize(20,70);
		non.setSize(80,70);
		
	    if(e.getSource() == quit) {
	    	message.setVisible(true);
	    	label.setText("Voulez-vous quitter ?");
	    	oui.addActionListener((event) -> {
	    		modele.getJoueur().setVies(modele.getJoueur().getVies()-1);  
	    		modele.vueJeu.infoApresNiveau(modele.getNiveau());
	    		message.setVisible(false);
	    		this.setVisible(false);
	    		message.dispose(); this.dispose();
	    	});
	    	non.addActionListener((event) -> { 
	    		message.setVisible(false);
	    		message.dispose();
	    	});
	    	JPanel auxiliaire = new JPanel();
			auxiliaire.setLayout(new BorderLayout());
	    	auxiliaire.add(oui, BorderLayout.WEST);
	    	auxiliaire.add(non,BorderLayout.EAST);
	    	
	    	panel.add(label, BorderLayout.NORTH);
	    	panel.add(auxiliaire, BorderLayout.SOUTH);

	    }
	    
		if(!modele.coupPossible()) {
			message.setVisible(true);
			label.setText("Vous avez perdu");
			panel.add(label, BorderLayout.CENTER);
			panel.add(exit, BorderLayout.SOUTH);
			
		} 
		if(modele.victoire()) {
			message.setVisible(true);
			label.setText("Vous avez gagné !");
			panel.add(label, BorderLayout.CENTER);
			panel.add(exit, BorderLayout.SOUTH);
		}
		
		exit.addActionListener((event) -> {
			modele.vueJeu.infoApresNiveau(modele.getNiveau());
			message.setVisible(false);
    		this.setVisible(false);
			message.dispose(); 
			this.dispose();
		});//ouvrir la fenetre niveau avec informations mises à jour
		message.add(panel);
	}
	
	

}


