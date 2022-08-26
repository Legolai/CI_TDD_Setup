package dk.cphbusiness;

import java.util.Objects;

public class User {
    private int id;
    private String fname;
    private String lname;
    private String password;
    private String phone;
    private String address;


    public User(int id, String fname, String lname, String password, String phone, String address) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.password = password;
        this.phone = phone;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return getId() == user.getId() && Objects.equals(getFname(), user.getFname()) && Objects.equals(getLname(), user.getLname()) && Objects.equals(getPassword(), user.getPassword()) && Objects.equals(getPhone(), user.getPhone()) && Objects.equals(getAddress(), user.getAddress());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFname(), getLname(), getPassword(), getPhone(), getAddress());
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
