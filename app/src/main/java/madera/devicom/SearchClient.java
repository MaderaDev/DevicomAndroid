package madera.devicom;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class SearchClient extends Activity implements FetchDataFromApi{

    private String data;
    ListView lv;
    String searchparameters = "";
    List<Map<String, String>> clientsretrieved;
    List<Map<String, String>> displayedClients;
    TextView number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.search_client);

        ImageView back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SearchClient.this, MainMenu.class));
                finish();
            }
        });

        this.lv = (ListView) findViewById(R.id.found_list);
        final Button btn = (Button) findViewById(R.id.search_button);
        final TextView parameters = (TextView)findViewById(R.id.searchclient);
        this.number = (TextView)findViewById(R.id.numberresults);

        getClients();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchparameters = parameters.getText().toString();
                //Hide keyboard
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput (InputMethodManager.SHOW_FORCED, 0);

                searchClient(searchparameters);
            }
        });

    }

    public Comparator<Map<String, String>> mapComparator = new Comparator<Map<String, String>>() {
        public int compare(Map<String, String> m1, Map<String, String> m2) {
            return m1.get("nom").compareTo(m2.get("nom"));
        }
    };

    private void getClients(){
        new ApiRequest(this, "client").execute();
    }

    public void fetchDataCallback(int code, String result) {
        System.out.println(code);
        data = result;
        sortClients();
        checkAllFields(this.clientsretrieved);
        renderData(this.clientsretrieved);
    }

    private void checkAllFields( List<Map<String, String>> toverif){
        String[] fields = {"id", "civilite", "prenom", "nom", "adresse", "ville", "codepostal", "email", "telephone"};
        for (int i=0; i<toverif.size(); i++) {
            for (int j=0; j<fields.length; j++) {

                try {
                    if (toverif.get(i).get(fields[j]).equals(null)) {
                        this.clientsretrieved.get(i).put(fields[j], "Non renseigné");
                    }
                }catch(Exception e){
                    this.clientsretrieved.get(i).put(fields[j], "Non renseigné");
                }
            }
        }
    }

    private void sortClients(){
        Utilities utils = new Utilities();
        this.clientsretrieved = utils.stringToMap(data);
    }

    private void searchClient(String searchparameters){

        if(searchparameters.isEmpty()){
            System.out.println("no parameters");
            System.out.println("Jackson".contains("jack"));
            getClients();
            return;
        }
        ArrayList<Map<String, String>> clientsfound = new ArrayList<Map<String, String>>();
        Map<String, String> temporaire;
        System.out.println("Size = " + this.clientsretrieved.size());
        System.out.println(this.clientsretrieved.get(1).keySet());
        String value;
        for(int i=0; i<this.clientsretrieved.size(); i++){
            Boolean found = false;
            System.out.println(i);
            for (String key: this.clientsretrieved.get(i).keySet()) {
                value = this.clientsretrieved.get(i).get(key);
                if (key != "id" && key != "civilite"){
                    //System.out.println("key = " + key + " - value = " +  this.clientsfound.get(i).get(key));
                    found = value.toLowerCase().contains(searchparameters.toLowerCase());
                    //System.out.println(this.clientsfound.get(i).get(key).indexOf(searchparameters));
                }
                if(found == true){
                    System.out.println(key + " = " + value);
                    temporaire = this.clientsretrieved.get(i);
                    clientsfound.add(temporaire);
                    break;
                }
            }
            if(found == false){
            }
        }
        renderData(clientsfound);
    }

    LinearLayout linear;

    private void renderData(List<Map<String, String>> clientsArray){

        String temp = "";
        Button[] btnWord = new Button[clientsArray.size()];
        linear = (LinearLayout) findViewById(R.id.buttons);
        linear.setOrientation(LinearLayout.VERTICAL);
        linear.removeAllViews();

        if(clientsArray.isEmpty()) {
            this.number.setText("Nombre de résultats : 0");
        } else{
            this.number.setText("Nombre de résultats : " + clientsArray.size());
            Collections.sort(clientsArray, mapComparator);
            for (int i = 0; i < clientsArray.size(); i++){
                temp = clientsArray.get(i).get("nom") + " " + clientsArray.get(i).get("prenom") + " - " + clientsArray.get(i).get("ville") + " (" + clientsArray.get(i).get("codepostal") + ")" ;
                btnWord[i] = new Button(this);
                btnWord[i].setHeight(50);
                btnWord[i].setWidth(WindowManager.LayoutParams.FILL_PARENT);
                btnWord[i].setTag(i);
                btnWord[i].setText(temp);
                btnWord[i].setOnClickListener(btnClicked);
                btnWord[i].setGravity(Gravity.LEFT | Gravity.CENTER);
                linear.addView(btnWord[i]);
            }
            displayedClients = clientsArray;
        }
        if (data == "") {
            data = "Impossible de joindre le serveur de Madera";
        }
    }

    View.OnClickListener btnClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Object index = v.getTag();
            System.out.println(displayedClients.get(Integer.parseInt(index.toString())));
            //Toast.makeText(getApplicationContext(), "clicked button " + index, Toast.LENGTH_SHORT).show();

            //List<Map<String, String>> displayedClients;
            int i = Integer.parseInt(index.toString());

            Intent myIntent = new Intent(SearchClient.this, NewClient.class);
            myIntent.putExtra("id", displayedClients.get(i).get("id"));
            myIntent.putExtra("civilite", displayedClients.get(i).get("civilite"));
            myIntent.putExtra("nom", displayedClients.get(i).get("nom"));
            myIntent.putExtra("prenom", displayedClients.get(i).get("prenom"));
            myIntent.putExtra("adresse", displayedClients.get(i).get("adresse"));
            myIntent.putExtra("codepostal", displayedClients.get(i).get("codepostal"));
            myIntent.putExtra("ville", displayedClients.get(i).get("ville"));
            myIntent.putExtra("email", displayedClients.get(i).get("email"));
            myIntent.putExtra("telephone", displayedClients.get(i).get("telephone"));

            System.out.println(displayedClients.get(i).get("id"));
            System.out.println(displayedClients.get(i).get("civilite"));
            System.out.println(displayedClients.get(i).get("nom"));
            System.out.println(displayedClients.get(i).get("prenom"));
            System.out.println(displayedClients.get(i).get("adresse"));
            System.out.println(displayedClients.get(i).get("codepostal"));
            System.out.println(displayedClients.get(i).get("ville"));
            System.out.println(displayedClients.get(i).get("email"));
            System.out.println(displayedClients.get(i).get("telephone"));

            startActivity(myIntent);
        }
    };



}