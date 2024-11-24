public class ColorPop extends Booster{ //Supprimer les blocs de même couleur
	
	public ColorPop() {  
		super("ColorPop");
	}
	
	public void fonction(Object[][]blocs,int x, int y) { //les blocs de même couleur supprimer
		if(blocs[x][y] instanceof Bloc) {
			Bloc aux = (Bloc)blocs[x][y];
			for(int i = 1; i<blocs.length-1; i++) {
				for(int j = 1; j<blocs[i].length-1; j++) {
					if(blocs[i][j] instanceof Bloc) {
						Bloc tmp = (Bloc)blocs[i][j];
						if(tmp.couleur.equalsIgnoreCase(aux.couleur)) {
							blocs[i][j] = new Bloc(-1);
						}
					}
				}
				
			}
		}
	}

}


