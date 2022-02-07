import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Payroll Class
 * Description: Takes in different files and runs different
 * 				tasks according to instructions
 * @author Onel Toma
 */
public class Payroll {
	private PrintWriter pw;
	private String delims = "[ ]+";
	private String[] tokens;
	private String line = "";
	private ObjectList payroll;
	
	/**
	 * Constructor takes in pw
	 * @param pw the printwriter sent in from driver
	 */
	public Payroll( PrintWriter pw ) {
		this.pw = pw;
		payroll = new ObjectList();
	}
	
	/**
	 * Imports the payroll file and goes through each line
	 * @param fileName name of the file
	 * @throws FileNotFoundException if fails opening
	 */
	public void importPayroll( String fileName ) throws FileNotFoundException 
	{
		Scanner payrollFileScan = new Scanner( new File( fileName ) );
		
		while ( payrollFileScan.hasNext() )
		{
			line = payrollFileScan.nextLine();
			
			Employee empl = parseEmployee( line );
			payroll.addLast( new ObjectListNode( empl ) );
		}
		
		payrollFileScan.close();
	}
	
	/**
	 * Imports the hired employees file
	 * @param fileName name of the file
	 * @throws FileNotFoundException if fails opening
	 */
	public void importHires( String fileName ) throws FileNotFoundException 
	{
		Scanner hireFileScan = new Scanner( new File( fileName ) );
		
		while ( hireFileScan.hasNext() )
		{
			line = hireFileScan.nextLine();
			
			Employee empl = parseEmployee( line );
			payroll.insert( new ObjectListNode( empl ) );
		}
		
		hireFileScan.close();
		
		System.out.print("\n\nNew Employee List (hired more)\n");
		pw.print("\n\nNew Employee List (hired more)\n");
		System.out.print( String.format( "%-12s%-11s","First Name", "Last Name"));
		pw.print( String.format( "%-12s%-11s","First Name", "Last Name"));
		ObjectListNode tempNode = payroll.getFirstNode();
		Employee tempEmpl;
		
		while ( tempNode != null )
		{
			tempEmpl = (Employee) tempNode.getInfo();
			tempEmpl.outputEmpName();
			tempNode = tempNode.getNext();

		}		
	}
	
	/**
	 * Imports the fired employees file
	 * @param fileName name of file
	 * @throws FileNotFoundException if fails to open
	 */
	public void importFired( String fileName ) throws FileNotFoundException 
	{
		Scanner fireFileScan = new Scanner( new File( fileName ) );
		Employee tempEmpl;
		ObjectListNode tempNode;
		ObjectListNode searchNode;
		
		while ( fireFileScan.hasNext() )
		{
			line = fireFileScan.nextLine();
			
			tokens = line.split( delims );
			String firstName = tokens[0];
			String lastName = tokens[1];
			
			searchNode = payroll.getFirstNode(); 
			
			while ( searchNode != null)
			{
				tempEmpl = (Employee) searchNode.getInfo();
				
				if(tempEmpl.getFirstName().equals(firstName) &&
						tempEmpl.getLastName().equals(lastName) )
				{
					payroll.remove( tempEmpl );
					break;
				}
				
				searchNode = searchNode.getNext();
			}

		}
		
		fireFileScan.close();
		
		System.out.print("\n\nNew Employee List (fired some)\n");
		pw.print("\n\nNew Employee List (fired some)\n");
		System.out.print( String.format( "%-12s%-11s","First Name", "Last Name"));
		pw.print( String.format( "%-12s%-11s","First Name", "Last Name"));
		
		tempNode = payroll.getFirstNode();

		while ( tempNode != null )
		{
			tempEmpl = (Employee) tempNode.getInfo();
			tempEmpl.outputEmpName();
			tempNode = tempNode.getNext();

		}		
	}
	
	/**
	 * Outputs the full header
	 */
	public void outputHeader() 
	{
		System.out.print( String.format( "%-12s%-11s%-8s%-8s%-6s%-7s", 
						"First Name", "Last Name", "Gender", "Tenure", "Rate", "Salary"));
		pw.print( String.format( "%-12s%-11s%-8s%-8s%-6s%-7s", 
				"First Name", "Last Name", "Gender", "Tenure", "Rate", "Salary"));
	}
	
	/**
	 * Outputs the first, last name and salary header
	 */
	private void outputNameSalaryHeader()
	{
		System.out.print( String.format( "%-12s%-11s%-7s", "First Name", "Last Name", "Salary"));
		pw.print( String.format( "%-12s%-11s%-7s", "First Name", "Last Name", "Salary"));
	}
	
	/**
	 * Parses each line from payroll file and gets the contents into employee
	 * @param line of text from file
	 * @return new employee object with contents in it
	 */
	private Employee parseEmployee( String line )
	{
		tokens = line.split( delims );
		String firstName = tokens[0];
		String lastName = tokens[1];
		char gender = tokens[2].charAt(0);
		int tenure = Integer.parseInt( tokens[3] );
		char rate = tokens[4].charAt(0);
		double salary = Double.parseDouble( tokens[5] );
		
		return new Employee( firstName, lastName, gender, tenure, rate, salary, pw );
	}
	
	/**
	 * Ouputs the full contents of employees
	 */
	public void outputEmployeesContent()
	{
		ObjectListNode tempNode = payroll.getFirstNode();
		Employee tempEmpl;
		
		while ( tempNode != null )
		{
			tempEmpl = (Employee) tempNode.getInfo();
			tempEmpl.outputEmployeeContents();
			tempNode = tempNode.getNext();

		}		
	}
	
	/**
	 * Counts the number of employees and outputs
	 */
	public void countEmployees()
	{
		System.out.printf("\n\nNumber of Employees: %d", payroll.size());
		pw.printf("\n\nNumber of Employees: %d", payroll.size());
	}
	
	/**
	 * Outputs female employees and data needed
	 */
	public void outputFemaleEmp() 
	{
		System.out.println("\n\n  Female Employees");
		pw.println("\n\n  Female Employees");
		System.out.print( String.format( "%-12s%-11s","First Name", "Last Name"));
		pw.print( String.format( "%-12s%-11s","First Name", "Last Name"));
		
		ObjectListNode tempNode = payroll.getFirstNode();
		Employee tempEmpl;
		
		while ( tempNode != null )
		{
			tempEmpl = (Employee) tempNode.getInfo();
			tempEmpl.outputFemaleEmpName();
			tempNode = tempNode.getNext();

		}		
	}
	
	/**
	 * Outputs the weekly employees that make more than $35,000
	 * per year and have a tenure of at least 5 years
	 */
	public void outputSpecificWeeklyEmpl()
	{
		System.out.println("\n\nWeekly Employees with salary > $35k and tenure >= 5 years");
		pw.println("\n\nWeekly Employees with salary > $35k and tenure >= 5 years");
		outputNameSalaryHeader();
		
		ObjectListNode tempNode = payroll.getFirstNode();
		Employee tempEmpl;
		
		while ( tempNode != null )
		{
			tempEmpl = (Employee) tempNode.getInfo();
			tempEmpl.outputSpecificEmplContent();
			tempNode = tempNode.getNext();
		}
	}
	
	/**
	 * Gives a raise to eligible employees
	 * and outputs data
	 */
	public void giveEligibleRaises()
	{
		System.out.println("\n\nEmployees that recieved a raise");
		pw.println("\n\nEmployees that recieved a raise");
		outputNameSalaryHeader();
		
		ObjectListNode tempNode = payroll.getFirstNode();
		Employee tempEmpl;
		
		while ( tempNode != null )
		{
			tempEmpl = (Employee) tempNode.getInfo();
			tempEmpl.giveEligibleRaise();
			tempNode = tempNode.getNext();
		}
	}
	
	/**
	 * Sorts the payroll list by last name and outputs data
	 */
	public void sortPayroll()
	{
		System.out.println("\n\n    Sorted Employees");
		pw.println("\n\n    Sorted Employees");
		outputNameSalaryHeader();
		ObjectList newList = new ObjectList();
		
		while ( !payroll.isEmpty() )
		{
			newList.insert( payroll.removeFirst() );
		}
		
		payroll = newList;	
		
		ObjectListNode tempNode = payroll.getFirstNode();
		Employee tempEmpl;
		
		while ( tempNode != null )
		{
			tempEmpl = (Employee) tempNode.getInfo();
			tempEmpl.outputNameSalary();
			tempNode = tempNode.getNext();
		}
	}
}
