import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Project Description: Employees are run through the program and the
 * 						appropriate actions are taken based on instructions with each employee
 * 						being organized into a linked list
 * Author: Onel Toma
 * User Instructions: The 3 different txt file will be read in for program to run
 * 
 * Driver Class
 * Description: Uses main() in order to run the program
 * @author Onel Toma
 */

public class Driver {
	public static void main(String[] args) throws IOException {
		PrintWriter pw = new PrintWriter(new FileWriter("csis.txt")); 
		Payroll payroll = new Payroll(pw);
		
		payroll.outputHeader();
		payroll.importPayroll("payfile.txt");
		payroll.outputEmployeesContent();
		payroll.countEmployees();
		payroll.outputFemaleEmp();
		payroll.outputSpecificWeeklyEmpl();
		payroll.giveEligibleRaises();
		payroll.sortPayroll();
		payroll.importHires("hirefile.txt");
		payroll.importFired("firefile.txt");
		
		pw.close();
	} 
}
