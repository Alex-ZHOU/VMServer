package mysql.entities;

public class BaseInfo {
	private int usr_id;
	
	private String usr_account;
	
	private String nickname;
	
	private int record_times;
	
	private double average_db;
	
	private int max_db;
	
	private int min_db;
	
	private double record_minter;
	
	private int head_protrait;

	public int getUsr_id() {
		return usr_id;
	}

	public void setUsr_id(int usr_id) {
		this.usr_id = usr_id;
	}

	public String getUsr_account() {
		return usr_account;
	}

	public void setUsr_account(String usr_account) {
		this.usr_account = usr_account;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getRecord_times() {
		return record_times;
	}

	public void setRecord_times(int record_times) {
		this.record_times = record_times;
	}

	public double getAverage_db() {
		return average_db;
	}

	public void setAverage_db(double average_db) {
		this.average_db = average_db;
	}

	public int getMax_db() {
		return max_db;
	}

	public void setMax_db(int max_db) {
		this.max_db = max_db;
	}

	public int getMin_db() {
		return min_db;
	}

	public void setMin_db(int min_db) {
		this.min_db = min_db;
	}

	public double getRecord_minter() {
		return record_minter;
	}

	public void setRecord_minter(double record_minter) {
		this.record_minter = record_minter;
	}

	public int getHead_protrait() {
		return head_protrait;
	}

	public void setHead_protrait(int head_protrait) {
		this.head_protrait = head_protrait;
	}
	
	
}
