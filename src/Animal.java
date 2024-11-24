import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Animal {
	
	private String espece;
	protected JLabel label = new JLabel();
	
	public Animal() {
		Random r = new Random();
		int nb = r.nextInt(3); 
		label.setSize(10, 10);
		switch(nb) {
		case 0 : espece = "Chat"; 
		try {
			label = new JLabel(new ImageIcon("../assets/Animaux/cat.gif"));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		break;
	
		case 1 : espece = "Chien"; 
		try {
			label = new JLabel(new ImageIcon("../assets/Animaux/dog.gif"));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		break;
	
		case 2 : espece = "Lapin"; 
		try {
			label = new JLabel(new ImageIcon("../assets/Animaux/rabbit.gif"));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		break;
		}
		
	}

	
	public String getEspece() {
		return espece;
	}
	
	//affichage de l'animal dans le terminal
	
	public String toString() {
		return espece.substring(0, 1);
	}


}

