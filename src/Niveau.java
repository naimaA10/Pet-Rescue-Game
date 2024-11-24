
public class Niveau {
	
	private static int numero = 0;
	private final int niv;
	private int[] pointsAObtenir;
	private int animauxASauver;
	
	public Niveau(int[]p, int a) {
		numero++;
		this.niv = numero;
		pointsAObtenir = p;
		animauxASauver = a;
	}

	public int getIntNiv() {
		return niv;
	}

	public int[] getPointsAObtenir() {
		return pointsAObtenir;
	}

	public int getPointsAObtenir(int n) {
		return pointsAObtenir[n];
	}

	public void affichePointsAObtenir() {
		for(int i = 0; i<pointsAObtenir.length; i++) {
			System.out.println(pointsAObtenir[i]);
		}
	}
	
	public int getAnimauxASauver() {
		return animauxASauver;
	}


}

