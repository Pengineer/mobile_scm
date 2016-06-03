package hust.test;

public class TestAction {

	private String username;
	
	public String execute() {
		System.out.println(username);
		
		username = "welcome " + username;
		return "res";
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	
}
