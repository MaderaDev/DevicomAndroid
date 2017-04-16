package madera.devicom;

import java.util.Date;

public class Devis {

    /*public String city;
    public int postalCode;
    public String address;*/
    public Date createDate;
    public Date updateDate;
    public Client client;

    public Gamme gamme;
    public LigneProd ligneProd;
    public Ref reference;

    public Devis(/*String city, int postalCode, String address, */Date createDate, Date updateDate, String client){
        /*this.city = city;
        this.postalCode = postalCode;
        this.address = address;*/
        this.createDate = createDate;
        this.updateDate=updateDate;

        //search client in database
        //this.client = client;


    }

    public void addGamme(Gamme gamme){
        this.gamme = gamme;
    }

    public void addLigneProd(LigneProd ligneprod) {
        this.ligneProd = ligneprod;
    }

    public void addRef (Ref ref) {
        this.reference = ref;
    }

}
