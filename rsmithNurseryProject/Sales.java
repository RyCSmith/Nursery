
package rsmithNurseryProject;

import java.io.Serializable;
import java.text.NumberFormat;


public class Sales implements Serializable{
	
	private Customer cust;
	private Day dateSold;
	private Inventory inv;
	private int numBought;
	private int salesNum;
	NumberFormat currency = NumberFormat.getCurrencyInstance();
	public static int nextNum = 1000;
	
	
	public Sales()
	{
		salesNum = nextNum;
		nextNum++;
		
	}
	
	public Sales(Customer c, Day date, Inventory in, int num)
	{
		cust = c;
		dateSold = date;
		inv = in;
		numBought = num;
		salesNum = nextNum;
		nextNum++;
	}
	
	
	public String toString()
	{
		NumberFormat nf = NumberFormat.getCurrencyInstance();
		String s = "Sales#:" + salesNum +"  " + inv.toStringCust() + "  " + cust.toString() + " bought " + 
		numBought + " of these on " +dateSold + ". Net Cost: " + nf.format(netCost()) ;
		return s;
	}
	
	public String toStringOptionTwo()
	{
		NumberFormat nf = NumberFormat.getCurrencyInstance();
		String s = dateSold+ " : " +"Sales#:" + salesNum +"  " + inv.toStringCust() + "  " + cust.toString() + " bought " + 
		numBought +". Net Cost: " + nf.format(netCost()) ;
		return s;
	}
	public double netCost() {
		double initialPrice = numBought * inv.getSalesPrice();
		double netCost;
		String custType = cust.getType();
		if (custType.equalsIgnoreCase("wholesale"))
		{
			double discount = initialPrice * cust.getDiscountPercent();
			netCost = initialPrice - discount/100;
		}
		else
			{
			if(initialPrice>=200)
				netCost = initialPrice * .95;
			else
				netCost = initialPrice;
			}
		return netCost;
	}
	
	public Customer getCust() {
		return cust;
	}

	
	public void setCust(Customer cust) {
		this.cust = cust;
	}

	
	public Day getDateSold() {
		return dateSold;
	}

	
	public void setDateSold(Day dateSold) {
		this.dateSold = dateSold;
	}

	public Inventory getInv() {
		return inv;
	}

	
	public void setInv(Inventory inv) {
		this.inv = inv;
	}

	
	public int getNumBought() {
		return numBought;
	}

	
	public void setNumBought(int numBought) {
		this.numBought = numBought;
	}

}
