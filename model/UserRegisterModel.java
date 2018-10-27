
package model;

public class UserRegisterModel {
    private String username,password,adress,age;

    public UserRegisterModel(String username, String password, String adress, String age) {
        this.username = username;
        this.password = password;
        this.adress = adress;
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
    
}
