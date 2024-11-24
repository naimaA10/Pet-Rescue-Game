import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.JLabel;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Accueil extends JFrame implements MouseListener  { 
	private static final long serialVersionUID = 1L;
	private JPanel panel = new JPanel();
	private JLabel label=new JLabel();
	private JButton jouer=new JButton("Jouer");
		
	Accueil(){
		setTitle("Accueil");
		setSize(300, 500);
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false); //Quand on lance la fenêtre on peut pas l'agrandir
		setLocationRelativeTo(null); //Affiche la fenêtre au centre de l'écran
		setVisible(true);
		
		//Boutton jouer au centre
		jouer.setFont(new Font("Caladea", Font.PLAIN, 13));
		jouer.setBounds(100,200,100,50);
		jouer.addMouseListener(this);
		
			
		label.setIcon(new ImageIcon("../assets/fond.jpg"));
		label.setBounds(0,0,500,500);
		
		panel.add(label);
		panel.setBounds(0,0,500,500);
		
		this.getContentPane().add(jouer);
		this.getContentPane().add(panel);	
	}
	
	public void paintComponent(Graphics g) {
		BufferedImage image = null;
		try {
			image = ImageIO.read(new File("../assets/fond.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
			
		super.paintComponents(g);
		g.drawImage(image, 0, 0, this);
		((Graphics2D)g).draw(new Rectangle(Math.min(0, 500),Math.min(0, 500), Math.max(0-500,500-0),Math.max(0-500,500-0)));
	}
	
	
	@Override
	public void mouseClicked(MouseEvent e) { //diriger vers la page de Jeu
		if(e.getSource() == jouer) {
			this.dispose();
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
			
			points[3][0] = 600;
			points[3][1] = 700;
			points[3][2] = 800;
			
			points[4][0] = 600;
			points[4][1] = 700;
			points[4][2] = 800;
			
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

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
				
	}

	public static void main(String[]args) {
		new Accueil();
	}

}

