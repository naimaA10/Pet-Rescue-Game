public class ColumnBlaster extends Booster { //(fusée) nettoyer une rangée de blocs

	
	public ColumnBlaster() { 
		super("ColumnBlaster");
	}

	public void fonction(Object[][]blocs,int colonne) { //prends en argument position la colonne à éliminer 
		for(int i = 1; i<blocs.length-1; i++) {
			if(blocs[i][colonne] instanceof Bloc) {
				blocs[i][colonne] = new Bloc(-1);
			}
		} 

	}

}

