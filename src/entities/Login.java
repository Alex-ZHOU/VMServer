package entities;

public class Login {

	/**
	 * 用户信息
	 */
	private User User = new User();

	/**
	 * 返回的数据
	 */
	private String Return;

	public User getUser() {
		return User;
	}

	public void setUser(User user) {
		User = user;
	}

	public String getReturn() {
		return Return;
	}

	public void setReturn(String return1) {
		Return = return1;
	}

	public class User {
		/**
		 * 账户名
		 */
		private String UserName;

		/**
		 * 密码
		 */
		private String Password;

		/**
		 * 用户id号
		 */
		private int UserId;

		public String getUserName() {
			return UserName;
		}

		public void setUserName(String userName) {
			UserName = userName;
		}

		public String getPassword() {
			return Password;
		}

		public void setPassword(String password) {
			Password = password;
		}

		public int getUserId() {
			return UserId;
		}

		public void setUserId(int userId) {
			this.UserId = userId;
		}
	}

}
