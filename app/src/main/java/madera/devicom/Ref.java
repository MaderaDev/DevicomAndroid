package madera.devicom;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Ref extends Activity{
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
        setContentView(R.layout.references);

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
            System.out.println("EXCEPTION IN REFERENCE FROM EDITDEVIS " + e);
        }


        ImageView back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setReference(ref);
            }
        });

        final ImageView ilp1 = (ImageView) findViewById(R.id.imglp1);
        ImageView ilp2 = (ImageView) findViewById(R.id.imglp3);
        ImageView ilp3 = (ImageView) findViewById(R.id.imglp5);
        ImageView ilp4 = (ImageView) findViewById(R.id.imglp2);
        ImageView ilp5 = (ImageView) findViewById(R.id.imglp4);
        ImageView ilp6 = (ImageView) findViewById(R.id.imglp6);

        final RelativeLayout largeimage = (RelativeLayout) findViewById(R.id.large);

        TextView text1 = (TextView) findViewById(R.id.textlp1);
        TextView text2 = (TextView) findViewById(R.id.textlp3);
        TextView text3 = (TextView) findViewById(R.id.textlp5);
        TextView text4 = (TextView) findViewById(R.id.textlp2);
        TextView text5 = (TextView) findViewById(R.id.textlp4);
        TextView text6 = (TextView) findViewById(R.id.textlp6);

        text1.setText("Référence 1");
        text2.setText("Référence 2");
        text3.setText("Référence 3");


        if (Integer.parseInt(modules) == 1){
            ilp1.setBackgroundResource(R.drawable.ref1);
            ilp2.setBackgroundResource(R.drawable.ref2);
            ilp3.setBackgroundResource(R.drawable.ref3);

        } else if (Integer.parseInt(modules) == 2){
            ilp1.setBackgroundResource(R.drawable.ref7);
            ilp2.setBackgroundResource(R.drawable.ref2);
            ilp3.setBackgroundResource(R.drawable.ref4);
            ilp4.setBackgroundResource(R.drawable.ref13);
            ilp5.setBackgroundResource(R.drawable.ref1);
            text4.setText("Référence 4");
            text5.setText("Référence 5");

        } else if (Integer.parseInt(modules) == 3){
            ilp1.setBackgroundResource(R.drawable.ref11);
            ilp2.setBackgroundResource(R.drawable.ref4);
            ilp3.setBackgroundResource(R.drawable.ref5);
            ilp4.setBackgroundResource(R.drawable.ref6);
            ilp5.setBackgroundResource(R.drawable.ref9);
            text4.setText("Référence 4");
            text5.setText("Référence 5");

        } else if (Integer.parseInt(modules) == 4){
            ilp1.setBackgroundResource(R.drawable.ref1);
            ilp2.setBackgroundResource(R.drawable.ref7);
            ilp3.setBackgroundResource(R.drawable.ref4);

        } else if (Integer.parseInt(modules) == 5){
            ilp1.setBackgroundResource(R.drawable.ref7);
            ilp2.setBackgroundResource(R.drawable.ref12);
            ilp3.setBackgroundResource(R.drawable.ref11);
            ilp4.setBackgroundResource(R.drawable.ref3);
            text4.setText("Référence 4");

        } else if (Integer.parseInt(modules) == 6){
            ilp1.setBackgroundResource(R.drawable.ref8);
            ilp2.setBackgroundResource(R.drawable.ref6);
            ilp3.setBackgroundResource(R.drawable.ref5);

        }

        largeimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                largeimage.setVisibility(View.GONE);
            }
        });

        ilp1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setReference("Référence 1");
                /*largeimage.setBackground(ilp1.getBackground());
                largeimage.setVisibility(View.VISIBLE);*/
            }
        });
        ilp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setReference("Référence 2");
            }
        });
        ilp3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setReference("Référence 3");
            }
        });
        ilp4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setReference("Référence 4");
            }
        });
        ilp5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setReference("Référence 5");
            }
        });
        ilp6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setReference("Référence 6");
            }
        });

    }

    private void setReference(String newref){

        Intent myIntent = new Intent(Ref.this, EditDevis.class);
        myIntent.putExtra("id", iddevis);
        myIntent.putExtra("nom", name);
        myIntent.putExtra("montant", amount);
        myIntent.putExtra("datec", creation_date);
        myIntent.putExtra("dateu", update_date);
        myIntent.putExtra("client", client);
        myIntent.putExtra("status", status);
        myIntent.putExtra("etape", step);
        myIntent.putExtra("modules", modules);
        myIntent.putExtra("gamme", gamme);
        myIntent.putExtra("ligneprod", ligneprod);
        myIntent.putExtra("ref", newref);

        startActivity(myIntent);
        finish();
    }
}
