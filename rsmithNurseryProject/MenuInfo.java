
package rsmithNurseryProject;

import java.text.NumberFormat;
import java.util.*;

public class MenuInfo 
{
	private NumberFormat currency = NumberFormat.getCurrencyInstance();
	Scanner input = new Scanner(System.in);
	
	public void printAll(ArrayList<Customer> cust,	ArrayList<Sales> sales ,	ArrayList<Inventory> inv)
	{
		System.out.println("\n Customers:");
		for(Customer c: cust)
		    System.out.println(c.toString());
		System.out.println("\n Sales:");
		for(Sales s: sales)
		    System.out.println(s.toString());
		System.out.println("\n Inventory:");
		for(Inventory i: inv)
		    System.out.println(i.toString());
	}

	public void printSales(ArrayList<Sales> sales)
	{
		System.out.println("\nSales:");
		double totalSales = 0;
		try{
		for(Sales s: sales)
		    {
			System.out.println(s.toString());
			totalSales = (totalSales + s.netCost());
		    }
		}
		catch (NullPointerException e)
		{
			
		}
		System.out.println("");
		System.out.printf("%s%s", "The total of all sales is ", currency.format(totalSales));
	}

	public ArrayList<Customer> bubbleSortCustFirst(ArrayList<Customer> c)
    {
         for (int out=c.size()-1;out>1;out--)
                for (int in=0;in<out;in++)
               {
                     Customer first = c.get(in);
                     Customer second = c.get(in+1);
                     String name1 = first.getFirst();
                     String name2=second.getFirst();
                      if (name2.compareTo(name1)<0)
                     {
                            c.set(in,second);
                         c.set(in+1,first);
                     }
               }
         return c;
    }
	
	public String checkName(ArrayList<Customer> c)
	{
		boolean taken = true;
		Boolean secondTest = null;
		String finalAnswer = null;
		String proposedFirst = null;
		String proposedLast = null;
		
		while (taken)
		{
			boolean notFound = true;
			System.out.println("Please enter the first name of the customer that you would like to add.");
			proposedFirst = input.next();
			proposedFirst = proposedFirst.toLowerCase().trim();
			System.out.println("Please enter the last name of the customer that you would like to add.");
			proposedLast = input.next();
			proposedLast = proposedLast.toLowerCase().trim();
			String proposedName = proposedFirst+proposedLast;
			int counter =0;
			while (notFound && counter<c.size())
			{
				Customer currentCust = c.get(counter);
				counter++;
				String custFirst = currentCust.getFirst().toLowerCase().trim();
				String custLast = currentCust.getLast().toLowerCase().trim();
				String custName = custFirst+custLast;
				if (proposedName.equals(custName))
				{
					notFound = false;
					System.out.println("That customer already exists. If you would like to review their account " +
							"information enter \"yes\". Enter any key if you would like to reenter the customer name" +
							" that you wish to add.");
					String answer = input.next();
					if(answer.equalsIgnoreCase("yes"))
					{
						taken = false;
						finalAnswer = "0" + proposedFirst + " " +proposedLast;
					}	
				
				}
			}
			if (notFound)
			{
				taken = false;
				secondTest = true;
			}
				
		}
		if (secondTest != null)
			finalAnswer = "1"+ proposedFirst +" "+proposedLast;
		return finalAnswer;
	}
	
	public Customer createCustomer(String s)
	{
		String rawName = s;
		rawName = rawName.substring(1);
		String[] names = rawName.split(" ");
		String firstName = names[0];
		firstName = firstName.substring(0, 1).toUpperCase() + firstName.substring(1);
		String lastName = names[1];	
		lastName = lastName.substring(0, 1).toUpperCase() + lastName.substring(1);
		boolean needsWork = true;
		String type = null;
		while(needsWork)
		{
			System.out.println("What type of customer is this (wholesale/retail)?");
			type = input.next();
			if ( !type.equalsIgnoreCase("retail") && !type.equalsIgnoreCase("wholesale"))
			{
				System.out.println("You did not enter a valid type. Please try again.");
			}
			else
				needsWork = false;	
		}
		boolean needsWork2 = true;
		int discount = 0;
		while( needsWork2)
		{
			try{
				System.out.println("What is the percentage of discount (if any)?");
				discount = input.nextInt();
				if (discount<= 100)
					needsWork2 = false;
				else
				{
					System.out.println("You did not enter a valid number. Please try again.");
					needsWork2 = true;
					input.nextLine();
				}
			}
			catch (InputMismatchException e)
			{
				System.out.println("You did not enter a valid number. Please try again.");
				needsWork2 = true;
				input.nextLine();
			}
		}
		Customer cc = new Customer(lastName, firstName, type, discount);
		return cc;
	}
	
	public int findCustomerIndex(ArrayList<Customer> list, String s)
	{
		ArrayList<Customer> cust = list;
		String rawName = s;
		rawName = rawName.toLowerCase();
		rawName = rawName.substring(1);
		rawName = rawName.replace(" ", "");
		int index = -1;
		for (int i = 0; i<cust.size(); i++)
		{
			Customer currentCust = cust.get(i);
			String custFirst = currentCust.getFirst().toLowerCase().trim();
			String custLast = currentCust.getLast().toLowerCase().trim();
			String custName = custFirst+custLast;
			boolean check = rawName.equalsIgnoreCase(custName);
			if (check == true)
			{
				index = i;
			}
		}
		return index;
	}
	public int findCustomerIndexNoCut(ArrayList<Customer> list, String s)
	{
		ArrayList<Customer> cust = list;
		String rawName = s;
		rawName = rawName.toLowerCase();
		int index = -1;
		for (int i = 0; i<cust.size(); i++)
		{
			Customer currentCust = cust.get(i);
			String custFirst = currentCust.getFirst().toLowerCase().trim();
			String custLast = currentCust.getLast().toLowerCase().trim();
			String custName = custFirst+custLast;
			boolean check = rawName.equalsIgnoreCase(custName);
			if (check == true)
			{
				index = i;
			}
		}
		return index;
	}
	
	public ArrayList<Customer> deleteCustomer(ArrayList<Customer> c)
	{
		boolean looking = true;

		String proposedFirst = null;
		String proposedLast = null;
		
		while (looking)
		{
			boolean notFound = true;
			System.out.println("Please enter the first name of the customer that you would like to delete.");
			proposedFirst = input.next();
			proposedFirst = proposedFirst.toLowerCase().trim();
			System.out.println("Please enter the last name of the customer that you would like to delete.");
			proposedLast = input.next();
			proposedLast = proposedLast.toLowerCase().trim();
			String proposedName = proposedFirst+proposedLast;
			int counter = 0;
			while (notFound && counter<c.size())
			{
				Customer currentCust = c.get(counter);
				String custFirst = currentCust.getFirst().toLowerCase().trim();
				String custLast = currentCust.getLast().toLowerCase().trim();
				String custName = custFirst+custLast;
				if (proposedName.equals(custName))
				{
					notFound = false;
					looking = false;
					c.remove(counter);
					System.out.println("The customer has been deleted." );
					continue;				
				}
				counter++;
			}
			if (notFound)
			{
				looking = true;
				System.out.println("The customer was not found. Please try again.");
			}

		}
		return c;	
	}
	public String checkName2(ArrayList<Customer> c)
	{
		boolean taken = true;
		Boolean secondTest = null;
		String finalAnswer = null;
		String proposedFirst = null;
		String proposedLast = null;
		
		while (taken)
		{
			boolean notFound = true;
			System.out.println("Please enter the first name of the customer that you would like to add.");
			proposedFirst = input.next();
			proposedFirst = proposedFirst.toLowerCase().trim();
			System.out.println("Please enter the last name of the customer that you would like to add.");
			proposedLast = input.next();
			proposedLast = proposedLast.toLowerCase().trim();
			String proposedName = proposedFirst+proposedLast;
			int counter =0;
			while (notFound && counter<c.size())
			{
				Customer currentCust = c.get(counter);
				counter++;
				String custFirst = currentCust.getFirst().toLowerCase().trim();
				String custLast = currentCust.getLast().toLowerCase().trim();
				String custName = custFirst+custLast;
				if (proposedName.equals(custName))
				{
					notFound = false;
					System.out.println("That customer already exists. Would you like to make a sale to this existing customer?");
					String answer = input.next();
					if(answer.equalsIgnoreCase("yes"))
					{
						taken = false;
						finalAnswer = "0" + proposedFirst +" "+proposedLast;
					}	
				//when you type random keys here it acts as if you said "no". It needs a "no" option specified and
					//something to correct incorrect responses
				}
			}
			if (notFound)
			{
				taken = false;
				secondTest = true;
			}
				
		}
		if (secondTest != null)
			finalAnswer = "1"+ proposedFirst +" "+proposedLast;
		return finalAnswer;
	}
	public int findInventoryIndex(ArrayList<Inventory> list, String s)
	{
		boolean needsWork = true;
		boolean found = false;
		int index = -1;
		String rawName = s;
		while(needsWork)
		{
			rawName = rawName.toLowerCase();
			rawName = rawName.replace(" ", "");
			for (int i = 0; i<list.size(); i++)
			{
				Inventory currentInv = list.get(i);
				String itemName = currentInv.getItemName().toLowerCase().trim();
				itemName = itemName.replace(" ", "");
				if (rawName.equals(itemName))
				{
					index = i;
					found = true;
					needsWork = false;
				}
			}
			if (found == false)
			{
				System.out.println("The item was not found in inventory. Please try again.");
				System.out.println("Enter the name of the inventory item that the customer would like to purchase.");
				rawName = input.nextLine();
			}
		}
			
		return index;
	}
	public int specialFindInventoryIndex(ArrayList<Inventory> list, String s)
	{
		boolean needsWork = true;
		boolean found = false;
		int index = -1;
		String rawName = s;
		while(needsWork)
		{
			rawName = rawName.toLowerCase();
			rawName = rawName.replace(" ", "").trim();
			for (int i = 0; i<list.size(); i++)
			{
				Inventory currentInv = list.get(i);
				String itemName = currentInv.getItemName().toLowerCase().replace(" ", "").trim();
				if (rawName.equals(itemName))
				{
					index = i;
					found = true;
					needsWork = false;
				}
			}
			if (found == false)
			{
				System.out.println("The item was not found in inventory. Please try again.");
				System.out.println("Re-enter the name of the inventory item that you'd like to edit");
				rawName = input.nextLine();
			}
		}
			
		return index;
	}
	public boolean checkInventoryQuantity(Inventory item, int numRequested)
	{
		boolean yeaOrNay = false;
		Inventory thisItem = item;
		int numR = numRequested;
		int currentStock = thisItem.getNumInStock();
		if (currentStock>=numR)
			yeaOrNay = true;
		else
			yeaOrNay=false;
		return yeaOrNay;
				
	}
	
	public void addInventoryItem(ArrayList<Inventory> inv)
	{
		Inventory newInventory = addInventorySubMenu();
		inv.add(newInventory);
		System.out.println("The item has been added to the inventory.\n"+ "Here are the current item details:\n"+
				newInventory+"\n");
	}
	
	protected Inventory addInventorySubMenu()
	{
		Scanner input2 = new Scanner(System.in);
		Inventory newItem = null;
		String flowerColor = null;
		boolean evergreen = false;
		int weight = 0;
		String invType;
		String name;
		int stock = 0;
		double cost = 0;
		double salesPrice = 0;
		int remember = 0;
		boolean thisLoopX = true;
		while(thisLoopX)
		{
			System.out.printf("%s\n%s\n%s\n%s\n","What type of inventory item would you like to add?", 
					"   1: Perennials ", "   2: Trees ", "   3: Bulk Products ");
			invType = input2.nextLine();
			invType = invType.trim();
			if(invType.equalsIgnoreCase("1"))
			{
				thisLoopX = false;
				remember = 1;
				boolean thisLoop1 = true;
				while(thisLoop1)
				{
					System.out.println("What is the color of the flowers?");
					flowerColor = input2.nextLine();
					boolean check1 = true;
					for (int i = 0; i<flowerColor.length(); i++)
					{
						Character current = flowerColor.charAt(i);
						boolean check = Character.isDigit(current);
						if (check==true)
						{
							System.out.println("The color must contain only letters.");
							check1 = false;
						}
					}
					if (check1 == true)
						thisLoop1 = false;
				}	
			}
			else if(invType.equalsIgnoreCase("2"))
			{
				thisLoopX = false;
				remember = 2;
				boolean thisLoop2 = true;
				while(thisLoop2)
				{
					System.out.println("Is this tree an evergreen?");
					String yayNay = input2.nextLine();
					if(yayNay.equalsIgnoreCase("yes"))
					{
						evergreen = true;
						thisLoop2 = false;
					}
					else if (yayNay.equalsIgnoreCase("no"))
					{
						evergreen = false;
						thisLoop2 = false;
					}
					else
						System.out.println("You must answer \"yes\" or \"no\".");
				}
			}
			else if(invType.equalsIgnoreCase("3"))
			{
				thisLoopX = false;
				remember = 3;
				boolean thisLoop3 = true;
				while(thisLoop3)
				{
					System.out.println("What is the weight of the product" +
							" rounded to the nearest pound?");
					try{
						weight = input2.nextInt();
						thisLoop3 = false;
					}
					catch (InputMismatchException e)
					{
						System.out.println("The weight must be a whole number.");
						input2.nextLine();
					}
				}
			}
			else
				System.out.println("You did not enter a valid type. Please try again.");
			input2.nextLine();
		}
		
		System.out.println("What is the name of the item that you would " +
				"like to add?");
		name = input2.nextLine();
		name = name.substring(0, 1).toUpperCase() + name.substring(1);
		
		
		boolean currentLoop = true;
		while(currentLoop)
		{
			System.out.println("How many " + name + " do you want to add" +
					" to stock?");
			try{
				stock = input2.nextInt();
				currentLoop = false;
			}
			catch (InputMismatchException e){
				input.nextLine();
				System.out.println("This can only be a whole number. Please reenter.");
				input2.nextLine();
			}
		}
		
		boolean currentLoop2 = true;
		while(currentLoop2)
		{
			System.out.println("How much is the cost of this item per unit?");
			try{
				cost = input2.nextDouble();
				currentLoop2 = false;
			}
			catch (InputMismatchException e){
				System.out.println("This must be a number. Decimals are allowed. Please reenter.");
				input2.nextLine();
			}
		}
		
		boolean currentLoop3 = true;
		while(currentLoop3)
		{
			System.out.println("How much is the salesPrice of this item per unit?");
			try{
				salesPrice = input2.nextDouble();
				currentLoop3 = false;
			}
			catch (InputMismatchException e){
				System.out.println("This must be a number. Decimals are allowed. Please reenter.");	
				input2.nextLine();
			}
		}
		
		if (remember==1)
			newItem = new Perennials(cost, name, stock, salesPrice, flowerColor);
		else if (remember==2)
			newItem = new Trees(cost, name, stock, salesPrice, evergreen);
		else if (remember==3)
			newItem = new BulkProducts(cost, name, stock, salesPrice, weight);
		
		return newItem;
	}
	
	public void editInventory(ArrayList<Inventory> inv)
	{
		Inventory currentItem = null;
		double newCost = 0;
		double newSalesPrice = 0;
		String choice1 = null;
		String choice2 = null;
		System.out.println("What is the name of the inventory item that you" +
				" would like to edit?");
		choice1 = input.nextLine();
		int index = specialFindInventoryIndex(inv, choice1);
		currentItem = inv.get(index);
		System.out.println("Currently, "+ currentItem.getItemName() +
				" costs "+ currentItem.getCost() + " per item and sells for " +
				currentItem.getSalesPrice() + " per item.");
		boolean currentLoop = true;
		while(currentLoop)
		{
			System.out.println("Would you like to edit the cost of this item (yes/no)?");
			choice2 = input.nextLine();
			if(choice2.equalsIgnoreCase("yes"))
			{
				currentLoop = false;
				boolean littleLoop = true;
				while(littleLoop)
				{
					System.out.println("Please enter the new cost of "+ currentItem.getItemName());
					try{
						newCost = input.nextDouble();
						currentItem.setCost(newCost);
						littleLoop = false;
					}
					catch (InputMismatchException e)
					{
						System.out.println("You did not enter a valid price.");
						input.nextLine();
					}
					input.nextLine();
				}
			}
			else if (choice2.equalsIgnoreCase("no"))
			{
				currentLoop = false;
			}
			else
				System.out.println("That was not a valid answer.");
		}
		boolean currentLoop5 = true;
		while(currentLoop5)
		{
			System.out.println("Would you like to edit the sales price of this item (yes/no)?");
			choice2 = input.nextLine();
			if(choice2.equalsIgnoreCase("yes"))
			{
				currentLoop5 = false;
				boolean littleLoop1 = true;
				while(littleLoop1)
				{
					System.out.println("Please enter the new sales price of "+ currentItem.getItemName());
					try{
						newSalesPrice = input.nextDouble();
						currentItem.setSalesPrice(newSalesPrice);
						littleLoop1 = false;
					}
					catch (InputMismatchException e)
					{
						System.out.println("You did not enter a valid price.");
						input.nextLine();
					}
				}
			}
			else if (choice2.equalsIgnoreCase("no"))
			{
				currentLoop5 = false;
			}
			else
				System.out.println("That was not a valid answer.");
		}
		
		System.out.println("The chosen inventory item has been edited. \n" +
				"Here is the current information:");
		System.out.println(currentItem);
		input.nextLine();
	}
	public void searchPartialNames(ArrayList<Inventory> inv)
	{
		String needle = null;
		String haystack = null;
		int index = 0;
		System.out.println("Enter the name of the item you would like to view. (Partial names ok)");
		needle = input.nextLine();
		needle = needle.toLowerCase().trim();
		for (Inventory list : inv)
		{
			haystack = list.getItemName();
			haystack = haystack.toLowerCase().trim();
			index = haystack.indexOf(needle);
			if (index != -1)
				System.out.println(list);
		}
	}
	
	public void sortSalesByDate(ArrayList<Sales> als)
	{
		Collections.sort(als, new DayComparator());
		for (Sales s : als)
			System.out.println(s.toStringOptionTwo());
	}
	public void showConciseInventory(ArrayList<Inventory> inv)
	{
		for (Inventory i : inv)
			System.out.println(i.toStringOptionThree());
	}
	public ArrayList<Sales> salesForIndividualItemNum(ArrayList<Sales> als)
	{
		int itemChoice = 0;
		System.out.println("Enter the item number of the inventory item you would like to view.");
		boolean again = true;
		while(again)
		{
			try{
				itemChoice = input.nextInt();
				again = false;
			}
			catch (InputMismatchException e)
			{
				System.out.println("You must enter a whole number. Please try again.");
				input.nextLine();
			}
		}
		ArrayList<Sales> customList = new ArrayList<Sales>();
		for(Sales s : als)
		{
			int itemNum = s.getInv().getInvNum();
			if (itemNum == itemChoice)
				customList.add(s);
		}
		boolean test = customList.isEmpty();
		if(test)
			System.out.println("There have not been any sales for that item.");
		return customList;
		
	}
	
}