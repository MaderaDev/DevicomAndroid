package madera.devicom;

public class Devis {

    /*public String city;
    public int postalCode;
    public String address;*/
    public String createDate;
    public String updateDate;
    public String client;
    public String montant;
    public String address;
    public String gamme;
    public String lignerod;
    public String reference;
    public String status;
    public String step;

    public Devis(String address, String createDate, String updateDate, String client, String montant, String gamme, String ligneprod, String ref, String status, String step){
        this.address = address;
        this.createDate = createDate;
        this.updateDate=updateDate;
        this.client = client;
        this.montant = montant;
        this.gamme = gamme;
        this.lignerod = ligneprod;
        this.reference = ref;
        this.status = status;
        this.step = step;
    }
}
