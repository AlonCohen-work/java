package b3;

import java.io.IOException;
import java.io.PrintWriter;

public class Employee {
	private String EmployName;
	private int IdNum;
	private int numSell;

// Constructor that accepts parameters
	/**
	 * Constructor that accepts parameters
	 * 
	 * @param String EmployName
	 * @param int    IdNum
	 */
	public Employee(String EmployName, int IdNum) {
		try {
			setEmployName(EmployName);
			setIdNum(IdNum);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void carsell(Car c1, PrintWriter MyWriter) {
		c1.carsell(MyWriter);
		this.numSell++;
	}

	public int payEmploye() {
		int pay = 6000 + this.numSell * 100;
		return pay;
	}

	public int compareTo(Employee other2) {
		int s1 = this.numSell;
		int s2 = other2.getNumSell();

		if (s1 == s2) {
			return 0;
		}
		if (s1 < s2) {
			return -1;
		}
		if (s1 > s2) {
			return 1;
		}
		return IdNum;
	}

	public String getEmployName() {
		return EmployName;
	}

	public int getIdNum() {
		return IdNum;
	}

	public int getNumSell() {
		return numSell;
	}

	public void setEmployName(String employName) throws IOException {
		boolean isValide = true;

		for (int i = 0; i < employName.length(); i++) {
			char ch = employName.charAt(i);
			if (ch >= 65 && ch <= 90 || ch >= 97 && ch <= 122) {
				continue;
			}
			isValide = false;
		}
		if (isValide == true) {
			EmployName = employName;
		} else
			throw new IOException("EmployName cannot be number");

	}

	public void setIdNum(int idNum) throws IOException {
		String s = Integer.toString(idNum);
		if (s.length() != 9) {
			throw new IOException("IdNum cannot be less then 9 digits");
		}
		IdNum = idNum;
	}

	public void setNumSell(int numSell) {
		this.numSell = numSell;
	}

	public String toString() {
		return "Name: " + this.EmployName + "\nID:" + this.IdNum + " \nnumber of sell " + this.numSell + " \nsalary:"
				+ payEmploye() + " \n";
	}
}