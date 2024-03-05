package org.jsp.lombok;

public class SaveEmployee {
	public static void main(String[] args) {
		Employee e1 = new Employee(1, "abc", "ase", 23456, "asd", "tgl");
		System.out.println(e1);

		Employee e2 = new Employee();
		e1.setId(25);
		System.out.println(e1);
		System.out.println(e2);

		Employee e3 = Employee.builder().desg("wa").email("rf").id(2).name("fd").password("hyg").phone(4567).build();
		System.out.println(e3);
	}
}
