import java.io.PrintWriter;

/**
 * Employee Class
 * Description: Contains information and methods for employee
 * @author Onel Toma
 */

public class Employee implements Comparable{
	private PrintWriter pw;
	private String firstName;
	private String lastName;
	private char gender;
	private int tenure;
	private char rate;
	private double salary; 
	
	/**
	 * Employee constructor
	 * @param firstName First name of employee 
	 * @param lastName Last name of employee
	 * @param gender Gender of employee
	 * @param tenure Tenure of employee
	 * @param rate Rate for employee
	 * @param salary Salary for employee
	 * @param pw Printwriter sent in from driver
	 */
	public Employee( String firstName, String lastName, char gender, int tenure, char rate, double salary, PrintWriter pw )
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.tenure = tenure;
		this.rate = rate;
		this.salary = salary;
		this.pw = pw;
	}
	
	/**
	 * Outputs all employee contents
	 */
	public void outputEmployeeContents()
	{
		System.out.printf( String.format("\n%-12s%-12s%-9c%-7d%-5c%-7.2f", firstName, lastName, 
							gender, tenure, rate, salary));
		pw.printf( String.format("\n%-12s%-12s%-9c%-7d%-5c%-7.2f", firstName, lastName, 
				gender, tenure, rate, salary));
	}
	
	/**
	 * Outputs the first and last name and salary of the employee
	 */
	public void outputNameSalary()
	{
		System.out.printf( String.format("\n%-12s%-11s%-7.2f", firstName, lastName, salary));
		pw.printf( String.format("\n%-12s%-11s%-7.2f", firstName, lastName, salary));
	}
	
	/**
	 * Outputs employee first and last name
	 */
	public void outputEmpName()
	{
			System.out.printf( String.format("\n%-12s%-12s", firstName, lastName));
			pw.printf( String.format("\n%-12s%-12s", firstName, lastName));
	}
	
	/**
	 * Outputs the name of female employee
	 */
	public void outputFemaleEmpName()
	{
		if (gender == 'F') 
		{
			System.out.printf( String.format("\n%-12s%-12s", firstName, lastName));
			pw.printf( String.format("\n%-12s%-12s", firstName, lastName));
		}
	}
	
	/**
	 * Outputs the specific employee content based on salary, tenure, and rate
	 */
	public void outputSpecificEmplContent()
	{
		double yearSalary = salary * 4 * 12;
		if (yearSalary > 35000.0 && tenure >= 5 && rate == 'W') 
		{
			outputNameSalary();
		}
	}
	
	/**
	 * Gives a raise for eligible employee
	 */
	public void giveEligibleRaise()
	{
		if(rate == 'H' && salary < 10.0)
		{
			salary += 0.75;
			outputNameSalary();
		}
		
		if(rate == 'W' && salary < 350.0)
		{
			salary += 50.0;
			outputNameSalary();
		}
	}
	
	/**
	 * Getter for first name of employee
	 * @return first name
	 */
	public String getFirstName()
	{
		return firstName;
	}
	
	/**
	 * Getter for last name of employee
	 * @return last name
	 */
	public String getLastName()
	{
		return lastName;
	}
	
	/**
	 * Compares the last name of this employee and employee given
	 * If have same last name, first name will be compared
	 * @param o object being compared
	 */
	public int compareTo(Object o) 
	{
		Employee e = (Employee) o;
		
		int result = this.lastName.compareTo( e.lastName );
		
		if(result == 0)
		{
			return this.firstName.compareTo( e.firstName );
		}
		
		return result;
	}
	
}
