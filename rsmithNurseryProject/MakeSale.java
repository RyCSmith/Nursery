package rsmithNurseryProject;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


public class MakeSale {
	Scanner input = new Scanner(System.in);
	private MenuInfo mi = new MenuInfo();
	private ArrayList<Customer> clist;
	private ArrayList<Inventory> ilist;
	
	public MakeSale()
	{
		
	}
	
	
	public Sales makeSale(ArrayList<Customer> c, ArrayList<Inventory> i)
	{
		clist = c;
		ilist = i;
		Sales thisSale = null;
		Day today = new Day();
		boolean needsWork = true;
		String proposedFirst = null;
		String proposedLast = null;
		Customer saleCustomer = null;
		Inventory saleInventory = null;
		while(needsWork)
		{
			System.out.println("Is the customer an existing customer (yes/no)?");
			String existing = input.next();
			if (existing.equalsIgnoreCase("yes"))
			{
				System.out.println("Please enter the customer's first name.");
				proposedFirst = input.next();
				proposedFirst = proposedFirst.toLowerCase().trim();
				System.out.println("Please enter the customer's last name.");
				proposedLast = input.next();
				proposedLast = proposedLast.toLowerCase().trim();
				String proposedName = proposedFirst+proposedLast;
				try{
				int index = mi.findCustomerIndexNoCut(clist, proposedName);
				saleCustomer = clist.get(index);
				needsWork = false;
				}
				catch(ArrayIndexOutOfBoundsException e)
				{
					System.out.println("This customer was not found in the system. " +
							"Please follow the prompts to add this customer.");
					existing = "no";
				}
			}
			if (existing.equalsIgnoreCase("no"))
			{
				String rawName = mi.checkName2(clist);
				char resultID = rawName.charAt(0);
				if(resultID=='0')
				{
					int index = mi.findCustomerIndex(clist, rawName);
					saleCustomer = clist.get(index);
				}
				else if(resultID=='1')
				{
					saleCustomer = mi.createCustomer(rawName);
					clist.add(saleCustomer);
					System.out.println("The customer has been added to the database.");
				}
				needsWork = false;
			}
			if (!existing.equalsIgnoreCase("no") && (!existing.equalsIgnoreCase("yes")))
			{
				System.out.println("You did not respond with a valid answer. Please try again.");
			}	
		}
		String itemName;
		System.out.println("What is the name of the item that "+ saleCustomer.getFirst()+ " "+ saleCustomer.getLast()+
				" would like to purchase?");
		//currently double printing the error message from method mi.findInventoryIndex ONLY after customer has been
		//declared to exist, then did not exist, then was added
		input.nextLine();
		itemName = input.nextLine();
		int index = mi.findInventoryIndex(ilist, itemName);
		saleInventory = ilist.get(index);
		
		System.out.println("How many " + saleInventory.getItemName() + " would he/she like to puchase?");
		int currentStock = 0;
		boolean possible = true;
		int numRequested = 0;
		boolean secondTry = true;
		while(secondTry)
		{
			try{
				numRequested = input.nextInt();
				input.nextLine();
				possible = mi.checkInventoryQuantity(saleInventory, numRequested);
				currentStock = saleInventory.getNumInStock();
				secondTry = false;
			}
			catch(InputMismatchException e)
			{
				System.out.println("You did not enter a valid quantity. Please enter the quantity the customer" +
						" would like to purchase.");
				secondTry = true;
				input.nextLine();
			}
		}
		if(possible)
		{
			thisSale = new Sales(saleCustomer, today, saleInventory, numRequested);
			int updatedStock = currentStock - numRequested;
			saleInventory.setNumInStock(updatedStock);
			System.out.println("The sale has been completed. Here are the invoice details: ");
			System.out.println(thisSale.toString());
		}
		else
		{
			System.out.println("We apologize but there are not enought items in inventory to make this sale at this time." +
					" There are currently " + currentStock + " in stock. Would the customer like to purchase those (yes/no)?");
			String response = input.nextLine();
			boolean tryAgain = true;
			while(tryAgain)
			{
				if (response.equalsIgnoreCase("yes"))
				{
					numRequested = currentStock;
					thisSale = new Sales(saleCustomer, today, saleInventory, numRequested);
					int updatedStock = 0;
					saleInventory.setNumInStock(updatedStock);
					tryAgain = false;
					System.out.println("The sale has been completed. Here are the invoice details: ");
					System.out.println(thisSale.toString());
				}
				else if (response.equalsIgnoreCase("no"))
				{
					System.out.println("We are sorry we could not complete this sale.");
					tryAgain = false;
				}
				else
				{
					System.out.println("You did not enter a valid answer. Would the customer like to buy the items " +
						"currently in stock (yes/no)?");
					response = input.nextLine();
				}
			}
		}
		
	return thisSale;	
	}

	
	public ArrayList<Inventory> getIList() {
		return ilist;
	}

	
	public void setIList(ArrayList<Inventory> ilist) {
		this.ilist = ilist;
	}
	
	public ArrayList<Customer> getCList() {
		return clist;
	}

	
	public void setCList(ArrayList<Customer> clist) {
		this.clist = clist;
	}
}
