package rsmithNurseryProject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


public class InputOutput {
	
	public InputOutput()
	{
		
	}
	public void outputCustListToFile(ArrayList<Customer> c)
	{
		
		ObjectOutputStream oos=null;
		FileOutputStream fos = null;
		try{
		File f = new File("CustomerList.ser");
		fos = new FileOutputStream(f);
		oos = new ObjectOutputStream(fos);
		oos.writeObject(c);
		oos.writeInt(Customer.nextNum);
		}
		catch (IOException e)
		{
			System.err.println("Input/Output Exception Thrown. Your changes will not be saved." );
		}
		finally
		{
			try {
				oos.close();
			} catch (IOException e) {}
		}
	}
	public ArrayList<Customer> readFromCustList()
	{
		ObjectInputStream ois = null;
		ArrayList<Customer> alc = new ArrayList<Customer>();
		try{
		ois = new ObjectInputStream( new FileInputStream("CustomerList.ser"));
		alc = (ArrayList<Customer>) ois.readObject();
		Customer.nextNum = ois.readInt();
		}
		catch (IOException e)
		{
			System.err.println("An IOException occured. We were not able to load your current data.");
		}
		catch (ClassNotFoundException e)
		{
			System.err.println("A ClassNotFoundException occured");
		}
		return alc;
	}
	public void outputInvListToFile(ArrayList<Inventory> c)
	{
		
		ObjectOutputStream oos=null;
		FileOutputStream fos = null;
		try{
		File f = new File("InventoryList.ser");
		fos = new FileOutputStream(f);
		oos = new ObjectOutputStream(fos);
		oos.writeObject(c);
		oos.writeInt(Inventory.nextNum);
		}
		catch (IOException e)
		{
			System.err.println("Input/Output Exception Thrown. Your changes will not be saved." );
		}
		finally
		{
			try {
				oos.close();
			} catch (IOException e) {}
		}
	}
	public ArrayList<Inventory> readFromInvList()
	{
		ObjectInputStream ois = null;
		ArrayList<Inventory> ali = new ArrayList<Inventory>();
		try{
		ois = new ObjectInputStream( new FileInputStream("InventoryList.ser"));
		ali = (ArrayList<Inventory>) ois.readObject();
		Inventory.nextNum = ois.readInt();
		}
		catch (IOException e)
		{
			System.err.println("An IOException occured. We were not able to load your current data.");
		}
		catch (ClassNotFoundException e)
		{
			System.err.println("A ClassNotFoundException occured");
		}
		return ali;
	}
	public void outputSalesListToFile(ArrayList<Sales> c)
	{
		
		ObjectOutputStream oos=null;
		FileOutputStream fos = null;
		try{
		File f = new File("SalesList.ser");
		fos = new FileOutputStream(f);
		oos = new ObjectOutputStream(fos);
		oos.writeObject(c);
		oos.writeInt(Sales.nextNum);
		}
		catch (IOException e)
		{
			System.err.println("Input/Output Exception Thrown. Your changes will not be saved." );
		}
		finally
		{
			try {
				oos.close();
			} catch (IOException e) {}
		}
	}
	public ArrayList<Sales> readFromSalesList()
	{
		ObjectInputStream ois = null;
		ArrayList<Sales> als = new ArrayList<Sales>();
		try{
		ois = new ObjectInputStream( new FileInputStream("SalesList.ser"));
		als = (ArrayList<Sales>) ois.readObject();
		Sales.nextNum = ois.readInt();
		}
		catch (IOException e)
		{
			System.err.println("An IOException occured. We were not able to load your current data.");
		}
		catch (ClassNotFoundException e)
		{
			System.err.println("A ClassNotFoundException occured");
		}
		return als;
	}
}
