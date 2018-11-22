package modelo;

public class Electricity extends Trap{

	private int numGemm;
	
	public Electricity(int damage, int x, int y, int numGemm) {
		super(damage, x, y);
		this.numGemm = numGemm;
		
	}
	
	public int getNumGemm() {
		return numGemm;
	}
	
	public void setNumGemm(int numGemm) {
		this.numGemm = numGemm;
	}

}
