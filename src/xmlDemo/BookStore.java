package xmlDemo;

import java.util.List;

@XmlRootElement(name = "bookstore", nameSpace = "com.gpcoder.combine.annoation.reflection")
public class BookStore {
	@XmlElement(name = "name")
	private String name;
	
	@XmlElement(name = "location")
	private String location;
	
	@XmlElementWrapper(name = "booklist")
	private List<Book> bookList;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public List<Book> getBookList() {
		return bookList;
	}

	public void setBookList(List<Book> bookList) {
		this.bookList = bookList;
	}
	
	@Override
    public String toString() {
        return "BookStore [name=" + name + ", location=" + location + "]";
    }
	
}
