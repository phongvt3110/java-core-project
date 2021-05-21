package xmlDemo;

import java.util.ArrayList;
import java.util.List;

public class ObjectToXmlExample {
	public static void main(String[] args) throws NoSuchFieldException, SecurityException {
		List<Book> listBooks = new ArrayList<Book>();
		Book book1 = new Book();
		book1.setIsbn("978-0-13-708189-9");
		book1.setName("Core Java Volume I");
        book1.setAuthor("Horstmann, Cay S. and Cornell");
        book1.setPublisher("Oracle");
        listBooks.add(book1);
 
        Book book2 = new Book();
        book2.setIsbn("978-3832180577");
        book2.setName("Spring MVC Beginner’s Guide");
        book2.setAuthor("Amuthan G");
        book2.setPublisher("Packt Pub");
        listBooks.add(book2);
 
        Book book3 = new Book();
        book3.setIsbn("999-1234567890");
        book3.setName("Java 8 in Action");
        book3.setAuthor("Raoul-Gabriel Urma");
        book3.setPublisher("Manning Publications Co.");
        listBooks.add(book3);
 
        // create bookstore, assigning book
        BookStore bookStore = new BookStore();
        bookStore.setName("Fraport Bookstore");
        bookStore.setLocation("Frankfurt Airport");
        bookStore.setBookList(listBooks);
 
        System.out.println(bookStore);
        System.out.println("bookList: ");
        
        for(Book book: listBooks) {
        	System.out.println("+" + book.toString() + "\n");
        }
        System.out.println("\n Convert to xml: \n");
        String xmlString = ObjectToXmlHelper.convertToXml(bookStore);
        System.out.println(xmlString);
	}
}
