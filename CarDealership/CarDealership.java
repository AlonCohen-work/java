package b3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CarDealership {
	public static <T extends Comparable<T>> void sortDescenig(T[] arr) {
		for (int i = 0; i < arr.length - 1; i++) {
			for (int j = i - 1; j < arr.length - 1; i++) {
				if (arr[i].compareTo(arr[j]) < 0) {
					T temp = arr[i];
					arr[i] = arr[j];
					arr[j] = temp;
				}
			}
		}
	}

	public static void main(String[] args) throws FileNotFoundException {
		// take all the info from file and put it in array cars
		Car[] cars = null;
		Employee[] Employees = null;
		int countCar = 0;
		int countEmployees = 0;
		Scanner input = new Scanner(new File("C:\\Users\\ALON\\Desktop\\CarDealership\\CarDealership.txt.txt"));
		try {
			int numOfCars = input.nextInt();
			cars = new Car[numOfCars];
			for (int i = 0; i < numOfCars; i++) {
				int numcar = input.nextInt();
				int year = input.nextInt();
				String manufacturer = input.next();
				int kilo = input.nextInt();
				double price = input.nextDouble();
				cars[i] = new Car(numcar, year, manufacturer, kilo, price);
				if (cars[i].getNumcar() == 0) {
					i--;
					countCar++;
					if (input.hasNext() == false) {
						i++;
						cars[i] = null;
					}
				}

			}
			input.close();

		} catch (Exception e) {
			e.getMessage();
		}
		int countREAL = cars.length - countCar;
		Car[] carstemp = new Car[countREAL];
		for (int i = 0; i < carstemp.length; i++) {
			carstemp[i] = cars[i];
		}
		cars = carstemp;
		// take all the info from file and put it in array Employees make a gesment if
		// the parmter n
		int countF = 0;
		Scanner input1 = new Scanner(new File("C:\\Users\\ALON\\Desktop\\CarDealership\\Employee.txt.txt"));
		try {
			int numEMP = input1.nextInt();
			Employees = new Employee[numEMP];

			for (int i = 0; i < numEMP; i++) {
				Employees[i] = new Employee(input1.next(), input1.nextInt());
				Employees[i].setNumSell(input1.nextInt());

				if (Employees[i].getIdNum() == 0) {
					i--;
					countEmployees++;
					if (countF + countEmployees == Employees.length) {
						i++;
						Employees[i] = null;
					}
				} else
					countF++;

			}
			input1.close();
		} catch (Exception e) {
			e.getMessage();
		}
		int countREAL2 = Employees.length - countEmployees;
		Employee[] Employeestemp = new Employee[countREAL2];
		for (int i = 0; i < Employeestemp.length; i++) {
			Employeestemp[i] = Employees[i];
		}
		Employees = Employeestemp;

		Menu(cars, Employees);
	}

	public static void Menu(Car cars[], Employee Employees[]) {
		int sc = 5;
		Scanner input = new Scanner(System.in);
		do {

			System.out.println(
					"1.Show list of Employe \n" + "2.Show the cars that stil in the dealership \n" + "3.sold a car\n"
							+ "4.adding car to the dealership\n" + "5.End progrem\n" + "Please enter your choice:");

			sc = input.nextInt();

			// while the user put number different number from 1-5 ask again for case
			while (sc < 1 || sc > 5) {
				System.out.println("Input incorrect! Please enter correct input:");
				sc = input.nextInt();
			}
			switch (sc) {
			case 1:

				System.out.println("Your choice is " + sc);
				EmplFromTop(Employees);
				break;
			case 2:

				System.out.println("Your choice is " + sc);
				printUnsoldCars(cars);
				break;
			case 3:

				System.out.println("Your choice is " + sc);
				cars = sell(cars, Employees);
				break;
			case 4:

				System.out.println("Your choice is " + sc);
				cars = addCar(cars);
				break;
			case 5:
				try {
					PrintWriter carWriter = new PrintWriter(
							new FileWriter("C:\\Users\\ALON\\Desktop\\CarDealership\\CarDealership.txt.txt", false));
					carWriter.println(cars.length);
					for (Car Car : cars) {
						carWriter.println(Car.toString());
					}
					carWriter.close();
				} catch (IOException e) {
					System.out.println("Failed to update CarDealership file: " + e.getMessage());
				}
				sc = 6;
			}
		} while (sc != 6);
	}

	/**
	 * Function sell asking for good id of the empolye and good id of the car then
	 * they add sell to the empolye who sold the car and Update Employee file Update
	 * cardelarship file to have witout the car he sold Update sold to have the car
	 * who was sold
	 * 
	 * @param Car(array)
	 * @param Employee(array)
	 * @return Car(array) who don't have the car who was sold
	 */
	private static Car[] sell(Car[] cars, Employee[] employees) {
		Scanner input = new Scanner(System.in);
		Scanner input2 = new Scanner(System.in);
		boolean good = true;
		boolean flag = false;
		Employee userEmp = null;
		Car userCar = null;
		while (good) {
			try {
				System.out.println("please enter the id of the Employee");
				for (Employee Employee : employees) {
					if (Employee.getEmployName().equals(null)) {
						System.out.println("this Employee have a problem with his arguments");
					} else
						System.out.println(Employee.getEmployName() + " " + Employee.getIdNum());
				}

				int EmpId = input.nextInt();

				for (int i = 0; i < employees.length; i++) {

					if (employees[i].getIdNum() == EmpId) {
						flag = true;
						userEmp = employees[i];
						break;
					}

				}
				if (!flag)
					throw new InputMismatchException("Error! no employee with this id ");
				good = false;
			} catch (InputMismatchException e) {
				System.out.println(e.getMessage());
			}
		}
		good = true;
		flag = false;
		// doing the loop until the code get true id
		while (good) {
			try {
				System.out.println("please enter the number car you want ");
				for (Car Car : cars) {
					if (Car.getManufacturer().equals(null)) {
						System.out.println("this car have a problem with his arguments");
					}
					System.out.println(Car.getManufacturer() + " " + Car.getNumcar());
				}
				int carId = input2.nextInt();
				for (int i = 0; i < cars.length; i++) {

					if (cars[i].getNumcar() == carId) {
						flag = true;
						userCar = cars[i];
						break;
					}
				}
				if (!flag)
					throw new InputMismatchException("Error! no car with this number ");
				good = false;
			} catch (InputMismatchException e) {
				System.out.println(e.getMessage());
			}

		}
		// Update the sold file
		try {
			PrintWriter soldWriter = new PrintWriter(
					new FileWriter("C:\\Users\\ALON\\Desktop\\CarDealership\\sold.txt.txt", true));
			userEmp.carsell(userCar, soldWriter);
			soldWriter.close();
		} catch (IOException e) {
			System.out.println("Failed to update sold file: " + e.getMessage());
		}
		// Update the employee's sales information
		try (PrintWriter employeeWriter = new PrintWriter(
				new FileWriter("C:\\Users\\ALON\\Desktop\\CarDealership\\Employee.txt.txt", false))) {
			employeeWriter.println(employees.length);
			for (Employee employee : employees) {
				employeeWriter
						.println(employee.getEmployName() + " " + employee.getIdNum() + " " + employee.getNumSell());
			}
			employeeWriter.close();

		} catch (IOException e) {
			System.out.println("Failed to update employee file: " + e.getMessage());
		}
		int k = 0;
		Car[] cars2 = new Car[cars.length - 1];
		for (int i = 0; i < cars.length; i++) {
			if (!userCar.equals(cars[i])) {
				cars2[k] = cars[i];
				k++;
			}
		}
		// Update the CarDealership information file
		try {
			PrintWriter carWriter = new PrintWriter(
					new FileWriter("C:\\Users\\ALON\\Desktop\\CarDealership\\CarDealership.txt.txt", false));
			carWriter.println(cars2.length);
			for (Car Car : cars2) {
				carWriter.println(Car.toString());
			}
			carWriter.close();
		} catch (IOException e) {
			System.out.println("Failed to update CarDealership file: " + e.getMessage());
		}
		return cars2;
	}

	/**
	 * add Car to the array cars
	 * 
	 * @param Car(array)
	 * @return Car(array) who have the new car
	 */
	private static Car[] addCar(Car[] cars) {
		Car newCar;
		Scanner input = new Scanner(System.in);
		Car[] temp = null;
		try {

			System.out.println("enter car Number: ");
			int carNumber = input.nextInt();
			System.out.println("enter year Production: ");
			int yearProduction = input.nextInt();
			System.out.println("enter manufacturer: ");
			String manufacturer = input.next();
			System.out.println("enter kilometer: ");
			int km = input.nextInt();
			System.out.println("enter price: ");
			double price = input.nextInt();
			newCar = new Car(carNumber, yearProduction, manufacturer, km, price);

			temp = new Car[cars.length + 1];
			for (int i = 0; i < cars.length; i++) {
				temp[i] = cars[i];
			}
			temp[temp.length - 1] = newCar;
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println("argument not matching");
		}

		return temp;
	}

	/**
	 * Employee From Top sell to last one
	 * 
	 * @param Employee(array)
	 */
	private static void EmplFromTop(Employee[] employees) {

		for (int i = 0; i < employees.length - 1; i++) {
			for (int j = 0; j < employees.length - i - 1; j++) {
				if (employees[j].getNumSell() < employees[j + 1].getNumSell()) {
					Employee temp = employees[j];
					employees[j] = employees[j + 1];
					employees[j + 1] = temp;
				}
			}
		}
		for (Employee Employee : employees) {
			if (Employee.getIdNum() == 0) {
				System.out.println("");
			} else
				System.out.println(Employee);
		}

	}

	/**
	 * printUnsoldCars
	 * 
	 * @param car(array)
	 */
	private static void printUnsoldCars(Car[] cars) {
		for (Car Car : cars) {
			System.out.println("the Number car is: " + Car.getNumcar() + "\nthe YearProduc is: " + Car.getYearProduc()
					+ "\nthe manufacturer is: " + Car.getManufacturer() + "\nthe kilometer is: " + Car.getKilometer()
					+ "\nthe Price is: " + Car.getPrice() + "\n");
		}
	}
}
