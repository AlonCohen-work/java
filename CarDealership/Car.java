package b3;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.InputMismatchException;

public class Car {
	private int Numcar;
	private int YearProduc;
	private String manufacturer;
	private int kilometer;
	private double price;

	
	/**Constructor that accepts parameters 
	 * @param int (Numcar) tell the id of the car
	 * @param int (YearProduc) tell when it was create
	 * @param String (manufacturer) tell who create it
	 * @param int (kilometer) how much kilometers it drive until now  
	 *  @param double (price) how much it cost
		*/
	public Car(int Numcar, int YearProduc, String manufacturer, int kilometer, double price) {
		try {
			String s = Integer.toString(Numcar);
			if (s.length() != 6) {
				throw new IOException("IdNum cannot be less or more then 6 digits");
			}

			if (YearProduc < 1800 || YearProduc > 2023) {
				throw new ArithmeticException("year cannot be negetive");
			}

			if (kilometer < 0) {
				throw new ArithmeticException("the kilometer need e positive");
			}

			if (price <= 0) {
				throw new ArithmeticException("the price need e positive");
			}
			setPrice(price);
			setKilometer(kilometer);
			setManufacturer(manufacturer);
			setYearProduc(YearProduc);
			setNumcar(Numcar);
		} catch (Exception e) {
			System.out.println(e + " becuse of it the car argument is not good");
		}
	}

	public void setNumcar(int numcar) {
		this.Numcar = numcar;
	}

	// Function that set the year of the prodecut and return a message if the year
	// is good
	public void setYearProduc(int yearProduc) {
		this.YearProduc = yearProduc;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public void setKilometer(int kilometers) {
		this.kilometer = kilometers;
	}

	public void setPrice(double price) {
		this.price = price;

	}
	/**Function that lower the price 
	 * @param double (per) tell how much persent to lower the price of car
	 * @throws ArithmeticException if the per is negtive becuse it cannot be negative
		*/
	public void LowValue(double per) {
		double s = 0;

		if (per <= 0) {
			throw new ArithmeticException("cannot be negetive");
		}

		else {
			double u = this.price;
			s = (this.price * per);
			this.price = u - (s / 100);
			if (u - this.price > 5000) {
				this.price = u;
				throw new ArithmeticException("cannot give discount more then 5000");
			}
		}
	}
	/**Function sell take the information about the car and send it to the file
	 * @param PrintWriter (MyWriter) tell to which file 
		*/
	public void carsell(PrintWriter MyWriter) {
		MyWriter.println(toString());
		System.out.println("car been sold");
		MyWriter.close();
	}

	public String toString() {
		return this.Numcar + " " + this.YearProduc + " " + this.manufacturer + " " + this.kilometer + " " + this.price
				+ "\n";

	}

	public int getNumcar() {
		return Numcar;
	}

	public int getYearProduc() {
		return YearProduc;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public int getKilometer() {
		return kilometer;
	}

	public double getPrice() {
		return price;
	}
}
