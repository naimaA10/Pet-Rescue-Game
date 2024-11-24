public class BlockBuster extends Booster{ 
	

	public BlockBuster() { 
		super("BlockBuster");
	}
	
	//Enleve un bloc du plateau
	
	public void fonction(Object[][]blocs, int x, int y) {
		if(blocs[x][y] instanceof Bloc) {
			Bloc tmp = (Bloc)blocs[x][y];
			if(tmp.getNumero() != -1) {
				blocs[x][y] = new Bloc(-1);
			}
		}
	}

	
}
