package madera.devicom;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class NewClient extends Activity  implements FetchDataFromApi{

    TextView test;
    Boolean client_in_base = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.new_client);

        final TextView idclient = (TextView) findViewById(R.id.idclient);
        final RadioButton monsieur = (RadioButton) findViewById(R.id.monsieur);
        final RadioButton madame = (RadioButton) findViewById(R.id.madame);
        final TextView vsurname = (TextView) findViewById(R.id.surname);
        final TextView vname = (TextView) findViewById(R.id.name);
        final TextView vemail = (TextView) findViewById(R.id.email);
        final TextView vphone = (TextView) findViewById(R.id.phone);
        final TextView vaddress = (TextView) findViewById(R.id.address);
        final TextView vpostal = (TextView) findViewById(R.id.postal);
        final TextView vcity = (TextView) findViewById(R.id.city);

        try{
            String clientid = getIntent().getSerializableExtra("id").toString();
            String clientcivilite;
            try{
                clientcivilite = getIntent().getSerializableExtra("civilite").toString();
            }catch(Exception e){
                clientcivilite = "Non renseigné";
            }
            String clientnom = getIntent().getSerializableExtra("nom").toString();
            String clientprenom = getIntent().getSerializableExtra("prenom").toString();
            String clientadresse = getIntent().getSerializableExtra("adresse").toString();
            String clientcodepostal = getIntent().getSerializableExtra("codepostal").toString();
            String clientville = getIntent().getSerializableExtra("ville").toString();
            String clientemail = getIntent().getSerializableExtra("email").toString();
            String clienttelephone = getIntent().getSerializableExtra("telephone").toString();

            if (clientcivilite == "monsieur"){
                monsieur.setChecked(true);
            } else if (clientcivilite == "madame"){
                madame.setChecked(true);
            }
            idclient.setText("Client : #" + clientid);
            vsurname.setText(clientprenom);
            vname.setText(clientnom);
            vemail.setText(clientemail);
            vphone.setText(clienttelephone);
            vaddress.setText(clientadresse);
            vpostal.setText(clientcodepostal);
            vcity.setText(clientville);
            client_in_base = true;

        } catch (Exception e) {
            System.out.println("No parameters");
        }

        ImageView back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NewClient.this, MainMenu.class));
                finish();
            }
        });
        Button newdevis = (Button) findViewById(R.id.newdevis);
        newdevis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (client_in_base == true) {
                    Intent myIntent = new Intent(NewClient.this, EditDevis.class);
                    myIntent.putExtra("surname", vsurname.getText().toString());
                    myIntent.putExtra("name", vname.getText().toString());
                    startActivity(myIntent);
                } else{
                    Toast.makeText(NewClient.this, "Veuillez d'abord créer un client.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        Button delete = (Button)findViewById(R.id.delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(NewClient.this, "Fonction en développement", Toast.LENGTH_SHORT).show();
            }
        });

        Button submit = (Button)findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String surname = vsurname.getText().toString();
                String name = vname.getText().toString();
                String email = vemail.getText().toString();
                String phone = vphone.getText().toString();
                String address = vaddress.getText().toString();
                String postal = vpostal.getText().toString();
                String city = vcity.getText().toString();
                int id = 1;
                String sex = "";
                if (madame.isChecked()) {
                    sex = "madame";
                } else if (monsieur.isChecked()) {
                    sex = "monsieur";}
                //date
                Calendar c = Calendar.getInstance();
                SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
                String formattedDate = df.format(c.getTime());

                String creationDate = formattedDate;
                String[] fields = {surname, name, phone, email, address, postal, city};
                if (fieldsVerification(fields) == true){
                    //FOR dev
                    //Client newClient = new Client(id, sex, "vincent", "gargat", "0681352011", "vincent.toto@yopmail.fr", "4 chemin marceau", "38100", "Grenoble", creationDate);
                    Client newClient = new Client(id, sex, surname, name, phone, email, address, postal, city, creationDate);
                    try {
                        createClient(newClient);
                        client_in_base = true;
                    }
                    catch(JSONException e){
                        //System.out.println(e);
                        e.printStackTrace();
                    }
                }
            }
        });

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radiogroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.monsieur) {
                    madame.setChecked(false);
                } else if (checkedId == R.id.madame) {
                    monsieur.setChecked(false);
                }
            }
        });


        final ListView lv = (ListView) findViewById(R.id.found_list);
        //final Button btn = (Button) findViewById(R.id.search_button);

        // Initializing a new String Array
        String[] fruits = new String[] {
                //"Cape Gooseberry",
                //"Capuli cherry"
        };

        // Create a List from String Array elements
        final List<String> fruits_list = new ArrayList<String>(Arrays.asList(fruits));

        // Create an ArrayAdapter from List
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, fruits_list);

        // DataBind ListView with items from ArrayAdapter
        lv.setAdapter(arrayAdapter);

       /* btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Add new Items to List
                fruits_list.add("Loquat");
                fruits_list.add("Pear");
                arrayAdapter.notifyDataSetChanged();
            }
        });*/
    }


    private boolean fieldsVerification(String[] fields){
        boolean empty = false;

        for(int i = 0; i < 7; i++) {
            if(fields[i].isEmpty()){
                empty = true;
            }
        }
        if(empty == true){
            Toast.makeText(NewClient.this, "Un des champs est vide. Tous les champs sont requis !", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }

    public void createClient(Client client) throws JSONException {
        this.test = (TextView) findViewById(R.id.linked_devis);

        JSONObject postDataParams = new JSONObject();
        //postDataParams.put("id", client.id);
        postDataParams.put("civilite", client.sex);
        postDataParams.put("nom", client.name);
        postDataParams.put("prenom", client.surname);
        postDataParams.put("adresse", client.address);
        postDataParams.put("codepostal", client.postalCode);
        postDataParams.put("ville", client.city);
        postDataParams.put("email", client.email);
        postDataParams.put("telephone", client.phone);
        //postDataParams.put("created_at", client.creationDate);

        new HttpPost(this, "client", postDataParams).execute();
    }

    public void fetchDataCallback(int code, String result) {
        System.out.println(code);
        //this.test.setText(result);
        Toast.makeText(NewClient.this, "Client enregistré", Toast.LENGTH_SHORT).show();
    }
}