package madera.devicom;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

public class LigneProd extends Activity{

    String iddevis;
    String name;
    String client;
    String amount;
    String status;
    String step;
    String modules;
    String creation_date;
    String update_date;
    String gamme;
    String ligneprod;
    String ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.lignes_produit);

        try{ //From EditDevis
            iddevis = getIntent().getSerializableExtra("id").toString();
            name = getIntent().getSerializableExtra("nom").toString();
            client = getIntent().getSerializableExtra("client").toString();
            amount = getIntent().getSerializableExtra("montant").toString();
            status = getIntent().getSerializableExtra("status").toString();
            step = getIntent().getSerializableExtra("etape").toString();
            modules = getIntent().getSerializableExtra("modules").toString();
            creation_date = getIntent().getSerializableExtra("datec").toString();
            update_date = getIntent().getSerializableExtra("dateu").toString();
            gamme = getIntent().getSerializableExtra("gamme").toString();
            ligneprod = getIntent().getSerializableExtra("ligneprod").toString();
            ref = getIntent().getSerializableExtra("ref").toString();

        } catch (Exception e){
            System.out.println("EXCEPTION IN LIGNEPROD FROM EDITDEVIS " + e);
        }


        ImageView back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LigneProd.this, EditDevis.class));
                finish();
            }
        });

        ImageView ilp1 = (ImageView) findViewById(R.id.imglp1);
        ImageView ilp2 = (ImageView) findViewById(R.id.imglp2);
        ImageView ilp3 = (ImageView) findViewById(R.id.imglp3);
        ImageView ilp4 = (ImageView) findViewById(R.id.imglp4);
        ImageView ilp5 = (ImageView) findViewById(R.id.imglp5);
        ImageView ilp6 = (ImageView) findViewById(R.id.imglp6);

        ilp1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLigneprod("1", "Plain pied contemporain");
            }
        });
        ilp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLigneprod("2", "Plain pied moderne");
            }
        });
        ilp3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLigneprod("3", "Plain pied pierre");
            }
        });
        ilp4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLigneprod("4", "Plain pied toit verdure");
            }
        });
        ilp5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLigneprod("5", "Etage contemporain");
            }
        });
        ilp6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLigneprod("6", "Etage moderne");
            }
        });

    }

    private void setLigneprod(String lpid, String newligneprod){

        Intent myIntent = new Intent(LigneProd.this, EditDevis.class);
        myIntent.putExtra("id", iddevis);
        myIntent.putExtra("nom", name);
        myIntent.putExtra("montant", amount);
        myIntent.putExtra("datec", creation_date);
        myIntent.putExtra("dateu", update_date);
        myIntent.putExtra("client", client);
        myIntent.putExtra("status", status);
        myIntent.putExtra("etape", step);
        myIntent.putExtra("modules", lpid);
        myIntent.putExtra("gamme", gamme);
        myIntent.putExtra("ligneprod", newligneprod);
        myIntent.putExtra("ref", ref);

        startActivity(myIntent);
        finish();
    }
}
