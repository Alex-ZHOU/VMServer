package android;

import entities.User;
import utils.EncapsulateParseJson;

public class JSONObject {

	private String jsonString;

	public static void main(String[] args) {
		JSONObject obj = new JSONObject();
		obj.put("Login2048", "Succee");
		obj.put("Username", "111123");
		obj.put("CheckerBoard", "dasjhdakj");
		obj.put("CurrentScores", "1");
		obj.put("BastScores", "1");
		System.out.println(obj.toString());
		User user = new User();
		user.setUserId(22);
		user.setPassword("HAHHA");
		System.out.println(EncapsulateParseJson.encapsulate(user));;
	}

	public JSONObject() {
		jsonString = "";
	}

	public void put(String key, String value) {
		String temp = '"' + key + '"' + ":" + '"' + value + '"' + ',';
		jsonString += temp;
	}

	public void clear() {
		jsonString = "";
	}

	public String toString() {
//		double len = jsonString.length();
//		jsonString = jsonString.substring(0, (int) (len - 1));
//		// System.out.println(jsonString);
//		return "{" + jsonString + "}";
		return "{" + jsonString.substring(0, (int) (jsonString.length() - 1)) + "}";
	}
}
