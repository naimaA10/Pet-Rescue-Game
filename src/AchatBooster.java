
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;

@SuppressWarnings("serial")
public class AchatBooster extends JFrame  {	 
	
	public AchatBooster(Joueur j) {
		setTitle("Achat de Booster");
		setSize(500, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false); // Quand on lance la fenêtre on peut pas l'agrandir
		setLocationRelativeTo(null); // Affiche la fenêtre au centre de l'écran
		getContentPane().setLayout(null);
	
		//Prix Booster
		JPanel prix=new JPanel();
		
		JLabel achat=new JLabel("Boutique des Booster :");		
		JLabel prixbooster = new JLabel("Prix : 1 Booster vaux 1000 pieces, 1 Booster vaux 5 lingots");
		JLabel phrase = new JLabel("Sélectionnez le nombre de Booster vous voulez acheter:");
		phrase.setFont(new Font("Book Antiqua", Font.PLAIN, 16));
		
		achat.setFont(new Font("Book Antiqua", Font.PLAIN, 16));
		prixbooster.setFont(new Font("Book Antiqua", Font.PLAIN, 15));
	
		prix.add(achat);
		prix.add(prixbooster);
		prix.add(phrase);
		
		prix.setBounds(0, 0, 500, 75);
		getContentPane().add(prix);
		
		
		//Choisir le nombre de booster
		JPanel choisir=new JPanel(new GridLayout(3,2, 20,30));
				
		JLabel Blockbuster = new JLabel("BlockBuster");
		Blockbuster.setFont(new Font("Book Antiqua", Font.PLAIN, 15));
		
		JLabel Colorpop = new JLabel("ColorPop");
		Colorpop.setFont(new Font("Book Antiqua", Font.PLAIN, 15));
		
		JLabel Columnblaster = new JLabel("ColumnBlaster");
		Columnblaster.setFont(new Font("Book Antiqua", Font.PLAIN, 15));
		
		
		JComboBox<Integer> nbBlockBuster = new JComboBox<Integer>();
		for (int i=0; i<6; i++) { nbBlockBuster.addItem(i); }
		
		JComboBox<Integer> nbColorpop = new JComboBox<Integer>();
		for (int i=0; i<6; i++) { nbColorpop.addItem(i); }
		
		JComboBox<Integer> nbColumnblaster = new JComboBox<Integer>();
		for (int i=0; i<6; i++) { nbColumnblaster.addItem(i); }
		
		choisir.add(Blockbuster); choisir.add(nbBlockBuster);
		choisir.add(Colorpop); choisir.add(nbColorpop);
		choisir.add(Columnblaster); choisir.add(nbColumnblaster);	
		
		choisir.setBounds(150, 100, 250, 90);
		getContentPane().add(choisir);
		
		//Choix de payement
		JPanel payement=new JPanel();
		
		JLabel proposition = new JLabel("Chochez si vous voulez acheter avec vos lingots, ou avec vos pieces :");
		proposition.setFont(new Font("Book Antiqua", Font.PLAIN, 13));
		
		JCheckBox AvecLingots = new JCheckBox("Lingots ");				
		JCheckBox AvecPieces = new JCheckBox("Pieces");
	
		payement.add(proposition);
		payement.add(AvecLingots);
		payement.add(AvecPieces);
		
		payement.setBounds(50, 230, 400, 60);
		getContentPane().add(payement);
		
		//les JButtons
		JPanel Button=new JPanel();
	
		JButton Acheter = new JButton("Acheter");
		Acheter.setFont(new Font("Book Antiqua", Font.PLAIN, 14));
		
		JButton Retour = new JButton("Retour");
		Retour.setFont(new Font("Book Antiqua", Font.PLAIN, 14));
		
		Button.add(Acheter);
		Button.add(Retour);
		
		Button.setBounds(150, 300, 200, 60);
		getContentPane().add(Button);
		
		setVisible(true);
	}
	
	public static void main(String[]args) {	
		Joueur testeur=new Joueur("me");
		new AchatBooster(testeur);
	}
}
