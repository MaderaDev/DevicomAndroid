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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class SearchDevis extends Activity implements FetchDataFromApi {

    private String data;
    ListView lv;
    String searchparameters = "";
    JSONArray devisretrieved;
    JSONArray displayedDevis;
    TextView number;
    Utilities utils = new Utilities();

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
                searchparameters = parameters.getText().toString().toLowerCase();
                //Hide keyboard
                View view = getCurrentFocus();
                if (view != null) {
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
                try {
                    searchDevis(searchparameters);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private void getDevis(){
        new ApiRequest(this, "devis").execute();
    }

    public void fetchDataCallback(int code, String data) {
        System.out.println(code);
        System.out.println(data);
        JSONArray devisArray = null;
        try {
            devisArray=new JSONArray(data);
            devisretrieved = devisArray;
            renderData(devisArray);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void searchDevis(String searchparameters) throws JSONException {

        if(searchparameters.isEmpty()){
            getDevis();
            return;
        }

        JSONArray devisfound = new JSONArray();

        for (int i = 0; i < devisretrieved.length(); i++) {
            JSONObject thisdevis = devisretrieved.getJSONObject(i);
            for(Iterator iterator = thisdevis.keys(); iterator.hasNext();) {
                String key = (String) iterator.next();
                String fieldcontent = thisdevis.getString(key).toString().toLowerCase();
                if (fieldcontent.contains(searchparameters)){
                    System.out.println(fieldcontent);
                    devisfound.put(devisretrieved.getJSONObject(i));
                    break;
                }
            }
        }
        renderData(devisfound);
    }

    LinearLayout linear;


    private void renderData(JSONArray devisArray) throws JSONException {

        String temp = "";
        Button[] btnWord = new Button[devisArray.length()];
        linear = (LinearLayout) findViewById(R.id.buttons);
        linear.setOrientation(LinearLayout.VERTICAL);
        linear.removeAllViews();

        if(devisArray.length() == 0) {
            this.number.setText("Nombre de résultats : 0");
        } else{
            this.number.setText("Nombre de résultats : " + devisArray.length());
            for (int i = 0; i < devisArray.length(); i++){
                JSONObject thisdevis = devisArray.getJSONObject(i);

                String[] devisinfos = {"devis", thisdevis.getString("nom"), thisdevis.getString("montant"), thisdevis.getString("status")};
                temp = utils.prettyChain(devisinfos);
                btnWord[i] = new Button(this);
                btnWord[i].setHeight(50);
                btnWord[i].setWidth(WindowManager.LayoutParams.FILL_PARENT);
                btnWord[i].setTag(i);
                btnWord[i].setText(temp);
                btnWord[i].setOnClickListener(btnClicked);
                btnWord[i].setGravity(Gravity.LEFT | Gravity.CENTER);
                linear.addView(btnWord[i]);

                devisArray.getJSONObject(i).getString("nom");
            }
            displayedDevis = devisArray;
        }
        if (data == "") {
            data = "Impossible de joindre le serveur de Madera";
        }
    }

    View.OnClickListener btnClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            try {
                Object tagIndex = v.getTag();
                int index = Integer.parseInt(tagIndex.toString());

                System.out.println(displayedDevis.getJSONObject(index));

                Intent myIntent = new Intent(SearchDevis.this, EditDevis.class);
                myIntent.putExtra("id", displayedDevis.getJSONObject(index).getString("id"));
                myIntent.putExtra("nom", displayedDevis.getJSONObject(index).getString("nom"));
                myIntent.putExtra("montant", displayedDevis.getJSONObject(index).getString("montant"));
                myIntent.putExtra("solde", displayedDevis.getJSONObject(index).getString("solde"));
                myIntent.putExtra("created_at", displayedDevis.getJSONObject(index).getString("created_at"));
                myIntent.putExtra("updated_at", displayedDevis.getJSONObject(index).getString("updated_at"));
                myIntent.putExtra("status", displayedDevis.getJSONObject(index).getString("status"));
                myIntent.putExtra("etape", displayedDevis.getJSONObject(index).getString("etape"));
                myIntent.putExtra("id_utilisateur", displayedDevis.getJSONObject(index).getString("id_utilisateur"));
                myIntent.putExtra("id_client", displayedDevis.getJSONObject(index).getString("id_client"));
                startActivity(myIntent);

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    };

}
