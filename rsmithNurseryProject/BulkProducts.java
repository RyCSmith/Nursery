
package rsmithNurseryProject;


public class BulkProducts extends Inventory {
	
	
	private int weight;
	
	
	public BulkProducts()
	{
		super();
		weight = 0;
	}
	
	
	public BulkProducts(double c, String i, int nIS, double p, int w)
	{
		super(c, i, nIS, p);
		weight = w;
	}

	
	public int getWeight() {
		return weight;
	}

	
	public void setWeight(int weight) {
		this.weight = weight;
	}

}
