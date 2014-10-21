
package rsmithNurseryProject;


public class Perennials extends Plants {
	
	
	private String flowerColor;
	
	
	public Perennials()
	{
		super();
	}
	
	public Perennials(double c, String i, int nIS, double p, String color)
	{
		super(c, i, nIS, p);
		flowerColor = color;
	}

	
	public String getFlowerColor() {
		return flowerColor;
	}

	
	public void setFlowerColor(String flowerColor) {
		this.flowerColor = flowerColor;
	}

}
