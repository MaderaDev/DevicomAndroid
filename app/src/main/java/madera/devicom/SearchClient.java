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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class SearchClient extends Activity implements FetchDataFromApi{

    private String data;
    ListView lv;
    String searchparameters = "";
    JSONArray clientsretrieved;
    JSONArray displayedClients;
    TextView number;
    Utilities utils = new Utilities();

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
                searchparameters = parameters.getText().toString().toLowerCase();
                //Hide keyboard

                View view = getCurrentFocus();
                if (view != null) {
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
                try {
                    searchClient(searchparameters);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private void getClients(){
        new ApiRequest(this, "client").execute();
    }

    public void fetchDataCallback(int code, String data) {
        System.out.println(code);

        System.out.println(data);
        JSONArray clientsArray = null;
        try {
            clientsArray=new JSONArray(data);
            /*for (int i = 0; i < clientsArray.length(); i++) {
                JSONObject thisclient = clientsArray.getJSONObject(i);
                System.out.println(thisclient);
                String name = thisclient.getString("prenom");
                System.out.println(name);
            }*/
            clientsretrieved = clientsArray;
            renderData(clientsArray);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void searchClient(String searchparameters) throws JSONException {

        if(searchparameters.isEmpty()){
            getClients();
            return;
        }
        JSONArray clientsfound = new JSONArray();

        for (int i = 0; i < clientsretrieved.length(); i++) {
            JSONObject thisclient = clientsretrieved.getJSONObject(i);
            for(Iterator iterator = thisclient.keys(); iterator.hasNext();) {
                String key = (String) iterator.next();
                String fieldcontent = thisclient.getString(key).toString().toLowerCase();
                if (fieldcontent.contains(searchparameters)){
                    System.out.println(fieldcontent);
                    clientsfound.put(clientsretrieved.getJSONObject(i));
                    break;
                }
            }
        }
        renderData(clientsfound);
    }

    LinearLayout linear;

    private void renderData(JSONArray clientsArray) throws JSONException {

        String temp = "";
        Button[] btnWord = new Button[clientsArray.length()];
        linear = (LinearLayout) findViewById(R.id.buttons);
        linear.setOrientation(LinearLayout.VERTICAL);
        linear.removeAllViews();

        if(clientsArray.length() == 0) {
            this.number.setText("Nombre de résultats : 0");
        } else{
            this.number.setText("Nombre de résultats : " + clientsArray.length());
            for (int i = 0; i < clientsArray.length(); i++){
                JSONObject thisclient = clientsArray.getJSONObject(i);

                String[] clientsinfos = {"client", thisclient.getString("nom"), thisclient.getString("prenom"), thisclient.getString("ville"), thisclient.getString("codepostal")};
                temp = utils.prettyChain(clientsinfos);
                //temp = thisclient.getString("nom") + " - " + thisclient.getString("prenom") + " -> " + thisclient.getString("ville") + " (" + thisclient.getString("codepostal") + ")";
                btnWord[i] = new Button(this);
                btnWord[i].setHeight(50);
                btnWord[i].setWidth(WindowManager.LayoutParams.FILL_PARENT);
                btnWord[i].setTag(i);
                btnWord[i].setText(temp);
                btnWord[i].setOnClickListener(btnClicked);
                btnWord[i].setGravity(Gravity.LEFT | Gravity.CENTER);
                linear.addView(btnWord[i]);

                clientsArray.getJSONObject(i).getString("nom");
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

            try {
                Object tagIndex = v.getTag();
                int index = Integer.parseInt(tagIndex.toString());

                System.out.println(displayedClients.getJSONObject(index));

                Intent myIntent = new Intent(SearchClient.this, NewClient.class);
                myIntent.putExtra("id", displayedClients.getJSONObject(index).getString("id"));
                myIntent.putExtra("civilite", displayedClients.getJSONObject(index).getString("civilite"));
                myIntent.putExtra("nom", displayedClients.getJSONObject(index).getString("nom"));
                myIntent.putExtra("prenom", displayedClients.getJSONObject(index).getString("prenom"));
                myIntent.putExtra("adresse", displayedClients.getJSONObject(index).getString("adresse"));
                myIntent.putExtra("codepostal", displayedClients.getJSONObject(index).getString("codepostal"));
                myIntent.putExtra("ville", displayedClients.getJSONObject(index).getString("ville"));
                myIntent.putExtra("email", displayedClients.getJSONObject(index).getString("email"));
                myIntent.putExtra("telephone", displayedClients.getJSONObject(index).getString("telephone"));
                startActivity(myIntent);

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    };
}