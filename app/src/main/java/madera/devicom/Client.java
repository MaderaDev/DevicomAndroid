package madera.devicom;

public class Client {

    public int id;
    public String sex;
    public String surname;
    public String name;
    public String phone;
    public String email;
    public String address;
    public String postalCode;
    public String city;
    public String creationDate;

    public Client(int id, String sex, String surname, String name, String phone, String email, String address, String postalCode, String city, String creationDate){
        this.id = id;
        this.sex = sex;
        this.surname = surname;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address=address;
        this.postalCode = postalCode;
        this.city = city;
        this.creationDate = creationDate;
    }

}
