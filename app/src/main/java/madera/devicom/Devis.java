package madera.devicom;

import java.util.Random;

public class Devis {

    /*public String city;
    public int postalCode;
    public String address;*/
    public String createDate;
    public String updateDate;
    public String client;
    public String idcom;
    public String montant;
    public String address;
    public String gamme;
    public String lignerod;
    public String reference;
    public String status;
    public String step;
    public String modules;

    public Devis(String address, String createDate, String updateDate, String client, String idcom, String montant, String gamme, String ligneprod, String ref, String status, String step){
        this.address = address;
        this.createDate = createDate;
        this.updateDate=updateDate;
        this.client = client;
        this.idcom = idcom;
        this.montant = montant;
        this.gamme = gamme;
        this.lignerod = ligneprod;
        this.reference = ref;
        this.status = status;
        this.step = step;

        Random r = new Random();
        this.modules = "";
        for (int i = 0;i<=10; i++){
            int number = r.nextInt(100 - 1) + 1;
            this.modules += Integer.toString(number) + ";";
        }
        this.modules = this.modules.substring(0,  this.modules.length()-1);

    }
}