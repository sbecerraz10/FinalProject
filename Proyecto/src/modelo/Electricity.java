package modelo;

public class Electricity extends Trap{

	private int numGemm;
	
	public Electricity(int damage, int x, int y, int numGemm) {
		super(damage, x, y);
		this.numGemm = numGemm;
		
	}
	
	/**
     * Retorna el atributo numGemm 
     * @return numGemm el atributo numGemm 
     */
	public int getNumGemm() {
		return numGemm;
	}
	
	/**
     * Modifica el atributo numGemm 
     * @param numGemm nuevo valor deseado para numGemm
     */
	public void setNumGemm(int numGemm) {
		this.numGemm = numGemm;
	}

}
