package xmlDemo2;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class XMLToObject {
	public static void main(String[] args) {
		try {
			File file = new File("dept.xml");
			JAXBContext context = JAXBContext.newInstance(Department.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			Department deptDepartment = (Department)unmarshaller.unmarshal(file);
			System.out.println(deptDepartment);
		} catch (JAXBException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
