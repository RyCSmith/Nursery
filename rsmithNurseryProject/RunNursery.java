
package rsmithNurseryProject;

import java.util.*;


public class RunNursery {
	static Scanner input = new Scanner(System.in);
	
	
	public static void main(String[] args) {
		

		// Setup the array lists for customer, sales, and inventory
		ArrayList<Customer> cust = new ArrayList<Customer>();
		ArrayList<Sales> sales = new ArrayList<Sales>();
		ArrayList<Inventory> inv = new ArrayList<Inventory>();
		MenuInfo mi = new MenuInfo();
		InputOutput ipop = new InputOutput();
		MakeSale msale;
		Sales newSale;
		
		cust = ipop.readFromCustList();
		inv = ipop.readFromInvList();
		sales = ipop.readFromSalesList();
		
		
		int choice = 0;
		
		// Continuing looping through the menu until the user exits
		while (choice !=10)
		{
			// Show the menu
			choice = showMenu();
			
			if (choice==-1)
				mi.printAll(cust,sales,inv);
			
			else if (choice==1)
				{
				mi.printSales(sales);
				}
			
			else if (choice ==2)
			{
				Collections.sort(inv);
				System.out.println("Current items in inventory listed alphabetically: ");
				int listNum = 1;
				try{
				for (Inventory i : inv)
				{
					System.out.println(listNum+ ": "+i.toStringOptionTwo());
					listNum++;
				}
				}
				catch (NullPointerException e){
					
				}
				
			}
			
			else if (choice==3)
				{
				try{
				Collections.sort(cust);
				System.out.println("Current Customers listed alphabetically by last name:");
				for (Customer c : cust)
					System.out.println(c.getLast() + ", " + c.getFirst());
				mi.bubbleSortCustFirst(cust);
				System.out.println("Current Customers listed alphabetically by first name:");
				for (Customer c : cust)
					System.out.println(c.getFirst() + " " + c.getLast());
				}
				catch (NullPointerException e)
				{
					
				}

				}
			else if (choice==4)
			{
				String rawName = mi.checkName(cust);
				char resultID = rawName.charAt(0);
				if(resultID=='0')
				{
					int index = mi.findCustomerIndex(cust, rawName);
					System.out.println(cust.get(index).toString());
				}
				else if(resultID=='1')
				{
					cust.add(mi.createCustomer(rawName));
					System.out.println("The customer has been added to the database.");
				}		
			}
			else if (choice==5)
				cust = mi.deleteCustomer(cust);
			else if (choice ==6)
			{
				msale = new MakeSale();
				newSale = msale.makeSale(cust, inv);
				sales.add(newSale);
			}
			else if(choice==7)
			{
				//This method and the corresponding methods that are called have trouble with double enters and one or  
				//two instances of a statement being double-printed. There were many continual loops at each catch
				//statement until blank scanner lines were added.
				int choiceZ = 0;
				boolean loop = true;
				while(loop)
				{
					try{
						System.out.printf("%5s\n%10s\n%10s\n%10s\n","Please choose from the following options:","1: Edit an Inventory Item", "2: Add an Inventory Item", 
								"3: Neither, return to main menu");
						choiceZ = input.nextInt();
						if(choiceZ>0 && choiceZ<4)
							loop = false;
						else
							System.out.println("That was not a valid option. Please try again.");
					}
					catch (InputMismatchException e)
					{
						System.out.println("That was not a valid option. Please try again.");
						input.nextLine();
					}
				}
				if (choiceZ == 1)
					mi.editInventory(inv);
				else if(choiceZ==2)
					mi.addInventoryItem(inv);			
						
			}
			else if(choice==8)
			{
				ArrayList<Sales> customList;
				mi.showConciseInventory(inv);
				customList = mi.salesForIndividualItemNum(sales);
				mi.sortSalesByDate(customList);
			}
			else if(choice==9)
			{
				mi.searchPartialNames(inv);
			}
			else if (choice==10) {
				System.out.println("Thanks for using my program!");
				ipop.outputCustListToFile(cust);
				ipop.outputInvListToFile(inv);
				ipop.outputSalesListToFile(sales);
				System.exit(0);
			}
		}			

	}
	
	public static int showMenu()
	{
		Scanner scan = new Scanner(System.in);
		System.out.println("\n\nPlease choose from one of the following options:");
		
		System.out.println("    (-1 to list all of the raw data)");
			// DONE FOR YOU but you need to fix the net sales cost
		
		System.out.println("1.  Print all sales and the total value");
		// each sales should have a sub-total (the one you fixed above) and then an overall total for all of the sales
		
		System.out.println("2.  Print all inventory items and total values");
		// should be sorted  alphabetically by the inventory name
		
		System.out.println("3.  List all current customers");
		// alphabetically by last and then first name
		
		System.out.println("4.  Add a customer");
		// make certain that they are unique.  No duplicate first plus last names
		
		System.out.println("5.  Delete a customer");
		// Indicate if they do not exist
		
		System.out.println("6.  Make a sale");
		// make certain to deduct the amount from inventory.  
		//Make certain that there is enough in the inventory for the sales
		// Make certain to add the sales to the sales list
		
		System.out.println("7.  Add or edit Inventory");
		// should be able to add to a current inventory item or add a new item
		// should be able to edit only the two prices
		// a sub-menu would work well here.
		
		System.out.println("8.  List all sales for a given item sorted by date");
		// show them all the inventory items and ask them for the inventory id
		// then show all sales for that item sorted by date
		
		System.out.println("9. Search for item by name or part name");
		// if I put in "maple", it should find all of the maples
		
		System.out.println("10. Exit");
		// should write out the files and close
		int value = scan.nextInt();
		
		return value;
	}

}
