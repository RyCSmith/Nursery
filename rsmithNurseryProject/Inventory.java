
package rsmithNurseryProject;

import java.io.Serializable;
import java.text.NumberFormat;

public abstract class Inventory  implements Comparable<Inventory>, Serializable{
	
	
	protected double cost;
	protected int invNum;
	protected String itemName;
	protected int numInStock;
	protected double salesPrice;
	
	protected static int nextNum = 1000;
	
	private NumberFormat currency = NumberFormat.getCurrencyInstance();
	
	public Inventory(double c, String i, int nIS, double p)
	{
		cost = c;
		itemName = i;
		numInStock = nIS;
		salesPrice = p;
		
		invNum = nextNum;
		nextNum++;
	}
	
	
	public Inventory()
	{
		invNum = nextNum;
		nextNum++;
	}
	
	public String toString()
	{
		double totalValue = cost * numInStock;
		
		return "There are " + numInStock + " of " + itemName + " (Item Number:  " + invNum 
			+ ") in stock with a cost of " + currency.format(cost) + " each.  The total value in inventory is "
			+ currency.format(totalValue); 
	}
	
	public String toStringCust() {
		return  itemName + " (Item Number:  " + invNum + ") which cost " +
				currency.format(cost) + " each and sell for "+currency.format(salesPrice) ;
				 
	}
	public String toStringOptionTwo()
	{
		double totalValue = cost * numInStock;
		return itemName + " (Item Number:  " + invNum + "): There are " +numInStock +" in stock with a cost of " + currency.format(cost) + " each.  The total value in inventory is "
				+ currency.format(totalValue); 
	}
	public String toStringOptionThree()
	{
		return itemName + " (Item Number:  " + invNum + ")";
	}
	public int compareTo(Inventory inv){
		return (this.getItemName()).compareTo(inv.getItemName()); 
	}
	

	public double getCost() {
		return cost;
	}

	
	public void setCost(double cost) {
		this.cost = cost;
	}

	
	public int getInvNum() {
		return invNum;
	}

	
	public void setInvNum(int invNum) {
		this.invNum = invNum;
	}

	
	public String getItemName() {
		return itemName;
	}

	
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	
	public int getNumInStock() {
		return numInStock;
	}

	
	public void setNumInStock(int numInStock) {
		this.numInStock = numInStock;
	}

	
	public double getSalesPrice() {
		return salesPrice;
	}

	
	public void setSalesPrice(double salesPrice) {
		this.salesPrice = salesPrice;
	}

}
