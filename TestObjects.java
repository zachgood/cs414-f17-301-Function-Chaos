package Model;

import java.io.Serializable;

public class TestObjects implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public String string1 = "New String!";
	public String string2 = "Other New String!";
	public int newInt = 92839;
	
	public String toString() {
		StringBuilder out = new StringBuilder();
		
		out.append(string1 + "\n");
		out.append(string2 + "\n");
		out.append(newInt + "\n");
		
		return out.toString();
	}
}
