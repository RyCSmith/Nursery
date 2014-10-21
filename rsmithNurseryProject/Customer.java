
package rsmithNurseryProject;

import java.io.Serializable;

public class Customer  implements Comparable<Customer>, Serializable{
	
	
	private int custNum;	
	private int discountPercent;	
	private String first;	
	private String last;	
	private String type;
	
	public static int nextNum = 100;
	
	
	public Customer()
	{
		custNum = nextNum;
		nextNum++;
	}
	
	
	public Customer(String l, String f, String t, int percent)
	{
		last = l;
		first = f;
		type = t;
		discountPercent = percent;
		custNum = nextNum;
		nextNum++;
	}
	
	
	public String toString()
	{
		double discountDecimal = discountPercent / 100.00;
		
		return first + " " + last + " (#" + custNum + "/" + type + ") Discount:"  + discountDecimal + "."; 
	}
	
	public int compareTo(Customer cust){
		return (this.getLast()).compareTo(cust.getLast()); 
	}
	
	public int getCustNum() {
		return custNum;
	}

	
	public void setCustNum(int custNum) {
		this.custNum = custNum;
	}

	
	public int getDiscountPercent() {
		return discountPercent;
	}

	
	public void setDiscountPercent(int discountPercent) {
		this.discountPercent = discountPercent;
	}

	
	public String getFirst() {
		return first;
	}

	
	public void setFirst(String first) {
		this.first = first;
	}

	
	public String getLast() {
		return last;
	}

	
	public void setLast(String last) {
		this.last = last;
	}

	
	public String getType() {
		return type;
	}

	
	public void setType(String type) {
		this.type = type;
	}
	
}
