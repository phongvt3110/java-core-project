package console;

import java.util.Date;
import java.util.Random;

public class TestPassVariable {
	public static void main(String[] args) {
		Person person = new Person();
		Random random = new Random();
		long heapSize = Runtime.getRuntime().freeMemory()/1024;
		System.out.println("Before loop: " + heapSize);
		System.out.println("Max memory: " + Runtime.getRuntime().maxMemory()/1024);
		System.out.println("Total memory: " + Runtime.getRuntime().totalMemory()/1024);
		for(int i = 0; i < 1000000; i ++) {				
			person.setId(random.nextInt());
			person.setName(random.ints(97, 123).limit(25).collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString());			
			person.setEmail(random.ints(97, 123).limit(25).collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString());
			person.setCreatedAt(new Date().toString());
			person.setUpdatedAt(new Date().toString());						
		}		
		System.out.println("After loop: " + Runtime.getRuntime().freeMemory()/1024);
	}	
}
