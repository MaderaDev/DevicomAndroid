package madera.devicom;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class EditDevis extends Activity implements FetchDataFromApi{

    Calendar c = Calendar.getInstance();
    SimpleDateFormat df = new SimpleDateFormat("dd MMM yyyy");
    String formattedDate = df.format(c.getTime());

    String iddevis = "Nouveau";
    String name = "";
    String amount = "";
    String status = "Brouillon";
    String step = "Devis ouvert";
    String modules = "";
    String client = "";
    String creation_date = formattedDate;
    String update_date = formattedDate;
    String gamme = "";
    String ligneprod = "";
    String ref = "";

    TextView vcdate;
    TextView vudate;
    TextView vstatus;
    TextView vstep;
    TextView vdevname;
    TextView vmontant;
    TextView vclient;
    TextView viddevis;
    TextView vgamme;
    TextView vligneprod;
    TextView vref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.edit_devis);

        vcdate = (TextView) findViewById(R.id.datec);
        vudate = (TextView) findViewById(R.id.dateu);
        vstatus = (TextView) findViewById(R.id.status);
        vstep = (TextView) findViewById(R.id.step);
        vdevname = (TextView) findViewById(R.id.address);
        vmontant = (TextView) findViewById(R.id.price);
        vclient = (TextView) findViewById(R.id.customer);
        viddevis = (TextView) findViewById(R.id.iddevis);
        vgamme = (TextView) findViewById(R.id.sgamme);
        vligneprod = (TextView) findViewById(R.id.sprod);
        vref = (TextView) findViewById(R.id.sref);

        vcdate.setKeyListener(null);
        vudate.setKeyListener(null);
        vmontant.setKeyListener(null);
        vstep.setKeyListener(null);
        vstatus.setKeyListener(null);
        vgamme.setKeyListener(null);
        vligneprod.setKeyListener(null);
        vref.setKeyListener(null);

        vcdate.setText(formattedDate);
        vudate.setText(formattedDate);
        vstatus.setText(status);
        vstep.setText(step);
        viddevis.setText("Devis : " + iddevis);


        try { // When comming from NewClient
            /*String oldsurname = getIntent().getSerializableExtra("surname").toString();
            String oldname = getIntent().getSerializableExtra("name").toString();
            client = oldname + " " + oldsurname;*/
            String idclient = getIntent().getSerializableExtra("id").toString();
            vclient.setText(idclient);
        } catch(Exception e){
            System.out.println("EXCEPTION IN EDITDEVIS FROM NEWCLIENT " + e);
        }
        try{ // When comming from SearchDevis or Gamme or LigneProd or Ref
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
            viddevis.setText("Devis : " + iddevis);
            vclient.setText(client);
            vcdate.setText(creation_date);
            vudate.setText((update_date));
            vstatus.setText(status);
            vstep.setText(step);
            vdevname.setText(name);
            vmontant.setText(amount + "€");
            vgamme.setText(gamme);
            vligneprod.setText(ligneprod);
            vref.setText(ref);
        }catch(Exception e){
            System.out.println("EXCEPTION IN EDITDEVIS FROM SEARCHDEVIS, GAMME, LIGNEPROD OR REF " + e);
        }

        ImageView back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EditDevis.this, MainMenu.class));
                finish();
            }
        });

        Button cancel = (Button)findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(EditDevis.this, "Modification annulée", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(EditDevis.this, MainMenu.class));
                finish();
            }
        });

        Button searchgamme = (Button) findViewById(R.id.selectgamme);
        searchgamme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFields();
                startCustomActivity(Gamme.class);
            }
        });
        Button searchprod = (Button) findViewById(R.id.selectprod);
        searchprod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vref.setText("");
                getFields();
                startCustomActivity(LigneProd.class);
            }
        });
        Button searchref = (Button) findViewById(R.id.selectref);
        searchref.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFields();
                if(ligneprod.isEmpty()){
                    Toast.makeText(EditDevis.this,"Veuillez d'abord choisir une gamme", Toast.LENGTH_SHORT).show();
                }else{
                    startCustomActivity(Ref.class);
                }
            }
        });

        //Numéro du devis
        TextView iddevis = (TextView)findViewById(R.id.iddevis);
        iddevis.setKeyListener(null);

        Button submit = (Button)findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                getFields();
                Devis devis = new Devis(name, creation_date, update_date, client, "1", amount, gamme, ligneprod, ref, status, step);
                try {
                    createDevis(devis);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private void startCustomActivity(Class activity){
        Intent myIntent = new Intent(EditDevis.this, activity);
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
        myIntent.putExtra("ref", ref);
        startActivity(myIntent);
    }

    private void getFields(){
        this.name = this.vdevname.getText().toString();
        this.creation_date = this.vcdate.getText().toString();
        this.update_date = this.vudate.getText().toString();
        this.client = this.vclient.getText().toString();
        this.status = this.vstatus.getText().toString();
        this.step = this.vstep.getText().toString();
        this.gamme = this.vgamme.getText().toString();
        this.ligneprod = this.vligneprod.getText().toString();
        this.ref = this.vref.getText().toString();
    }

    public void createDevis(Devis devis) throws JSONException {

        JSONObject postDataParams = new JSONObject();
        //postDataParams.put("id", client.id);
        postDataParams.put("nom", devis.address);
        postDataParams.put("montant", Integer.parseInt(devis.montant));
        postDataParams.put("id_utilisateur", devis.idcom);
        postDataParams.put("id_client", devis.client);
        postDataParams.put("status", devis.status);
        postDataParams.put("etape", "Devis_ouvert");
        postDataParams.put("modules", devis.modules);
        //postDataParams.put("created_at", client.creationDate);

        new HttpPost(this, "api/devis", postDataParams).execute();
    }

    public void fetchDataCallback(int code, String result) {
        System.out.println(code);
        System.out.println(result);
        //this.test.setText(result);
        Toast.makeText(EditDevis.this, "Devis enregistré", Toast.LENGTH_SHORT).show();
    }


}