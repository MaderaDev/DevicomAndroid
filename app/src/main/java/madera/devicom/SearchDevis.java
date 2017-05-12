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

public class SearchDevis extends Activity {

    public FakeDevis fdev = new FakeDevis();

    private String data;
    ListView lv;
    String searchparameters = "";
    List<Map<String, String>> devisretrieved;
    List<Map<String, String>> displayedClients;
    TextView number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.search_devis);

        ImageView back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SearchDevis.this, MainMenu.class));
                //finish();
            }
        });

        this.lv = (ListView) findViewById(R.id.found_list);
        final Button btn = (Button) findViewById(R.id.searchdev);
        final TextView parameters = (TextView)findViewById(R.id.searchdevisparams);
        this.number = (TextView)findViewById(R.id.numberresults);

        getDevis();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchparameters = parameters.getText().toString();
                //Hide keyboard
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput (InputMethodManager.SHOW_FORCED, 0);

                searchDevis(searchparameters);
            }
        });


        List<Map<String, String>> devisList;
        Map<String, String> map1;

    }

    public Comparator<Map<String, String>> mapComparator = new Comparator<Map<String, String>>() {
        public int compare(Map<String, String> m1, Map<String, String> m2) {
            return m1.get("nom").compareTo(m2.get("nom"));
        }
    };

    private void getDevis(){
        this.devisretrieved = fdev.mydevislist;
        checkAllFields(this.devisretrieved);
        renderData(this.devisretrieved);
    }


    private void checkAllFields( List<Map<String, String>> toverif){
        String[] fields = {"id", "nom", "montant", "client", "status", "etape", "modules", "gamme", "ligneprod", "ref", "datec", "dateu"};
        for (int i=0; i<toverif.size(); i++) {
            for (int j=0; j<fields.length; j++) {
                try {
                    if (toverif.get(i).get(fields[j]).equals(null)) {
                        this.devisretrieved.get(i).put(fields[j], "Non renseigné");
                    }
                }catch(Exception e){
                    this.devisretrieved.get(i).put(fields[j], "Non renseigné");
                }
            }
        }
    }

    private void searchDevis(String searchparameters){

        if(searchparameters.isEmpty()){
            getDevis();
            return;
        }
        ArrayList<Map<String, String>> devisfound = new ArrayList<Map<String, String>>();
        Map<String, String> temporaire;
        System.out.println("Size = " + this.devisretrieved.size());
        System.out.println(this.devisretrieved.get(1).keySet());
        String value;
        for(int i=0; i<this.devisretrieved.size(); i++){
            Boolean found = false;
            System.out.println(i);
            for (String key: this.devisretrieved.get(i).keySet()) {
                value = this.devisretrieved.get(i).get(key);
                if (key != "id" && key != "civilite"){
                    found = value.toLowerCase().contains(searchparameters.toLowerCase());
                }
                if(found == true){
                    System.out.println(key + " = " + value);
                    temporaire = this.devisretrieved.get(i);
                    devisfound.add(temporaire);
                    break;
                }
            }
            if(found == false){
            }
        }
       renderData(devisfound);
    }

    LinearLayout linear;

    private void renderData(List<Map<String, String>> devisArray){

        String temp = "";
        Button[] btnWord = new Button[devisArray.size()];
        linear = (LinearLayout) findViewById(R.id.buttons);
        linear.setOrientation(LinearLayout.VERTICAL);
        linear.removeAllViews();

        if(devisArray.isEmpty()) {
            this.number.setText("Nombre de résultats : 0");
        } else{
            this.number.setText("Nombre de résultats : " + devisArray.size());
            Collections.sort(devisArray, mapComparator);
            for (int i = 0; i <devisArray.size(); i++){
                temp = devisArray.get(i).get("nom");
                btnWord[i] = new Button(this);
                btnWord[i].setHeight(50);
                btnWord[i].setWidth(WindowManager.LayoutParams.FILL_PARENT);
                btnWord[i].setTag(i);
                btnWord[i].setText(temp);
                btnWord[i].setOnClickListener(btnClicked);
                btnWord[i].setGravity(Gravity.LEFT | Gravity.CENTER);
                linear.addView(btnWord[i]);
            }
            displayedClients = devisArray;
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
            int i = Integer.parseInt(index.toString());

            Intent myIntent = new Intent(SearchDevis.this, EditDevis.class);
            myIntent.putExtra("id", displayedClients.get(i).get("id"));
            myIntent.putExtra("nom", displayedClients.get(i).get("nom"));
            myIntent.putExtra("montant", displayedClients.get(i).get("montant"));
            myIntent.putExtra("datec", displayedClients.get(i).get("datec"));
            myIntent.putExtra("dateu", displayedClients.get(i).get("dateu"));
            myIntent.putExtra("client", displayedClients.get(i).get("client"));
            myIntent.putExtra("status", displayedClients.get(i).get("status"));
            myIntent.putExtra("etape", displayedClients.get(i).get("etape"));
            myIntent.putExtra("modules", displayedClients.get(i).get("modules"));
            myIntent.putExtra("gamme", displayedClients.get(i).get("gamme"));
            myIntent.putExtra("ligneprod", displayedClients.get(i).get("ligneprod"));
            myIntent.putExtra("ref", displayedClients.get(i).get("ref"));

            startActivity(myIntent);
        }
    };
}
