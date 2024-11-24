import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.*;
@SuppressWarnings("serial")
public class Bloc extends JComponent {
	
	private int numero;
	protected String couleur;
	protected JButton button;
	
	//Coordonnees dans le plateau
	private int x = -1;
	private int y = -1;
	
	protected Controleur ctrl;
	
	public Bloc() {
		Random r = new Random();
		numero = r.nextInt(6);
		switch(numero) {
		case 0 : couleur = "rouge";  break;
		case 1 : couleur = "jaune";   break;
		case 2 : couleur = "vert";   break;
		case 3 : couleur = "violet";  break;
		case 4 : couleur = "bleu";   break;
		case 5 : couleur = "noir";   break;
		}
	}
	
	public Bloc(Controleur ctrl) { //permet de modifier sur le plateau
		this.ctrl = ctrl;
		Random r = new Random();
		numero = r.nextInt(6);
		button = new JButton("");
		button.setSize(10, 10);
		button.addActionListener((event) -> ctrl.view.modifier(event, x, y));
		switch(numero) {
		case 0 : couleur = "rouge";  button.setBackground(Color.red); break;
		case 1 : couleur = "jaune";  button.setBackground(Color.yellow); break;
		case 2 : couleur = "vert";  button.setBackground(Color.GREEN); break;
		case 3 : couleur = "violet"; button.setBackground(Color.pink); break;
		case 4 : couleur = "bleu";  button.setBackground(Color.blue); break;
		case 5 : couleur = "noir";  button.setBackground(Color.BLACK); break;
		}
	}
	
	
	public Bloc(int n) {
		button = new JButton("");
		button.setSize(10, 10);
		numero = n;
		if(n == -1) {
			couleur = "blanc";
			button.setBackground(Color.WHITE);
		}
	}
	
	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public void setCoord(int x, int y) {
		this.x = x;
		this.y = y;
	}

	
	public void setBtn() {
		switch(numero) {
		
		case 0 : button.setBackground(Color.red); break;
		case 1 :  button.setBackground(Color.yellow); break;
		case 2 : button.setBackground(Color.GREEN); break;
		case 3 : button.setBackground(Color.pink); break;
		case 4 : button.setBackground(Color.blue); break;
		case 5 : button.setBackground(Color.BLACK); break;
		}
		
	}
	
	public int getNumero() {
		return numero;
	}
	
	public void setNumero(int n) {
		numero = n;
	}
	
	public JButton getBtn() {
		return button;
	}

	
	public String toString() {
		return String.valueOf(numero);
	}
	

	public boolean possedeVoisins(Bloc b1) {
		return (b1.couleur.contentEquals(this.couleur));
	}

	
	public ArrayList<ArrayList<Integer>> voisins(Bloc b1, String s) { 
		ArrayList<Integer>a = new ArrayList<Integer>();
		a.add(x); a.add(y);
		if(!ctrl.view.getModele().listeC.contains(a)) {
			ctrl.view.getModele().listeC.add(a);
		}
		this.button.setBackground(Color.WHITE); 
		this.numero = -1;
		this.couleur = "blanc";
		if(b1.couleur.contentEquals(s)) {
			b1.button.setBackground(Color.WHITE);
			b1.numero = -1; b1.couleur = "blanc";
			ctrl.view.getModele().setBlocsVoisins(ctrl.view.getModele().getBlocsVoisins()+1);;
			ArrayList<Integer> a1 = new ArrayList<Integer>();
			a1.add(b1.x); a1.add(b1.y);
			if(!ctrl.view.getModele().listeC.contains(a1)) {
				ctrl.view.getModele().listeC.add(a1);
			}
			
		}

		return ctrl.view.getModele().listeC;
	}

}
