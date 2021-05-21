package console;

public class Person {
	private int id;
	private String name;
	private String email;
	private String createdAt;
	private String updatedAt;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	public String getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}
	public String toString() {
		return String.format("[id = %d, name = %s, email = %s, createdAt= %s, updatedAt = %s]", id, name, email, createdAt, updatedAt);
	}
}
