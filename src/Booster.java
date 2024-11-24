public abstract class Booster {
	
	String nom; 
	
	//Pour n'utiliser qu'une seule fois après avoir cliqué dessus
	boolean clic = false;
	
	public Booster(String n) {
		this.nom=n;
	}
	
	public String toString() {
		return (nom);
	}

	
	
}

