package xmlDemo2;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class ObjectToXml {
	public static void main(String[] args) throws JAXBException, FileNotFoundException {
		JAXBContext context = JAXBContext.newInstance(Department.class);
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		Department deptDepartment = getDepartment();
		// Write data to console
		marshaller.marshal(deptDepartment, System.out);
		// Write data to file xml
		marshaller.marshal(deptDepartment, new FileOutputStream("dept.xml"));
	}
	public static Department getDepartment() {
		List<Employee> employees = new ArrayList<>();
		Employee emp1 = new Employee(1, "GP Coder", 1000, 28);
        Employee emp2 = new Employee(2, "LN Devil", 700, 27);
        employees.add(emp1);
        employees.add(emp2);
        
        Department department = new Department("D01", "IT Support");
        department.setEmployees(employees);
        return department;
	}
}
