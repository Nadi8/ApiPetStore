package files;

public class payload {

	public static String createUser(String id, String username, String firstName, String lastName) {

		String payload = "{\r\n" + "  \"id\": "+id+",\r\n" + "  \"username\": \""+username+"\",\r\n"
				+ "  \"firstName\": \""+firstName+"\",\r\n" + "  \"lastName\": \""+lastName+"\"\r\n" + " \r\n" + "}";
		return payload;

	}
}
