package madera.devicom;


public class Client {

    public String surname;
    public String name;
    public int phone;
    public String email;
    public String address;
    public int postalCode;
    public String city;

    public Client(String surname, String name, int phone, String email, String address, int postalCode, String city){
        this.surname = surname;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address=address;
        this.postalCode = postalCode;
        this.city = city;
    }

}
