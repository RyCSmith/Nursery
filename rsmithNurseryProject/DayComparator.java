package rsmithNurseryProject;

import java.util.Comparator;

public class DayComparator implements Comparator<Sales>{
	
	public int compare(Sales s1, Sales s2)
	{
		Day day1 = s1.getDateSold();
		Day day2 = s2.getDateSold();
		int year1 = day1.getYear();
		int year2 = day2.getYear();
		int month1 = day1.getMonth();
		int month2 = day2.getMonth();
		int num1 = day1.getDay();
		int num2 = day2.getDay();
		if(year1>year2)
			return 1;
		else if (year1<year2)
			return -1;
		else
		{
			if(month1>month2)
				return 1;
			else if (month1<month2)
				return -1;
			else
			{
				if(num1>num2)
					return 1;
				else if (num1<num2)
					return -1;
				else 
					return 0;	
			}
		}
		
	}

}
