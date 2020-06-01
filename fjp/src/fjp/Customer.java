/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fjp;

/**
 *
 * @author wwwmo
 */
public class Customer {
    private int id;
    private int phone;
    private int num_of_locker;
    private String username;
    private String pass;
    private String gender;
    private String email;
    private String adress;

   
    

    public Customer() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLocknumber() {
        return num_of_locker;
    }

    public void setLocknumber(int num_of_locker) {
        this.num_of_locker = num_of_locker;
    }

    public int getNum_of_locker() {
        return num_of_locker;
    }

    public void setNum_of_locker(int num_of_locker) {
        this.num_of_locker = num_of_locker;
    }
 public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

   


    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String address) {
        this.adress = address;
    }
    
    
            
}
