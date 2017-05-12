package madera.devicom;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

public class Gamme extends Activity{

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
        setContentView(R.layout.gammes);

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
            System.out.println("EXCEPTION IN GAMME FROM EDITDEVIS " + e);
        }


        ImageView back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Gamme.this, EditDevis.class));
                finish();
            }
        });

        TextView desc_classique = (TextView) findViewById(R.id.gclassiquedesc);
        TextView desc_medium = (TextView) findViewById(R.id.gmediumdesc);
        TextView desc_premium = (TextView) findViewById(R.id.gpremiumdesc);

        desc_classique.setText("Les maisons de la gamme Classique offrent tout le savoir-faire de Madera.\n\nLes finitions sont soignées et de qualité.");
        desc_medium.setText("Les maisons de la gamme Medium promettent une composition de matériaux de meilleure qualité ainsi qu'un suivi du vieillissement de la construction.");
        desc_premium.setText("Les maisons de la gamme Premium offrent des matériaux nobles et une finition irréprochable.\n\nLa construction bénéficie aussi d'un suivi du vieillissement et d'une étude offerte pour l'agrandissement potentiel de la structure");


        ImageView pclassique = (ImageView) findViewById(R.id.pclassique);
        ImageView pmedium = (ImageView) findViewById(R.id.pmedium);
        ImageView ppremium = (ImageView) findViewById(R.id.ppremium);

        pclassique.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setGamme("Classique");
            }
        });
        pmedium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setGamme("Medium");
            }
        });
        ppremium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setGamme("Premium");
            }
        });
    }


    private void setGamme(String newgamme){

        Intent myIntent = new Intent(Gamme.this, EditDevis.class);
        myIntent.putExtra("id", iddevis);
        myIntent.putExtra("nom", name);
        myIntent.putExtra("montant", amount);
        myIntent.putExtra("datec", creation_date);
        myIntent.putExtra("dateu", update_date);
        myIntent.putExtra("client", client);
        myIntent.putExtra("status", status);
        myIntent.putExtra("etape", step);
        myIntent.putExtra("modules", modules);
        myIntent.putExtra("gamme", newgamme);
        myIntent.putExtra("ligneprod", ligneprod);
        myIntent.putExtra("ref", ref);

        startActivity(myIntent);
        finish();
    }
}
