package entities;

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
    private int userId;

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
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
