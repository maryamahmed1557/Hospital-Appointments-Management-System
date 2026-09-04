public abstract class User {

    protected String id;
    protected String name;
    protected String userName;
    protected String password;
    protected String role;
    
    public User() {}

    public User(String id, String name, String userName, String password) {
        this.id = id;
        this.name = name;
        this.userName = userName;
        this.password = password;
    }

    public String getiD() {
        return id;
    }
    
    public void setiD(String iD) {
        this.id = iD;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    public  boolean login(String user,String pass){
        if (this.userName.equalsIgnoreCase(user)&&this.password.equals(pass)){
            System.out.println("Log in successful! welcome:"+this.name);
            return true;
        }else{
            System.out.println("Error user name or passord incorrect");
            return false;
        }
    }

    
}