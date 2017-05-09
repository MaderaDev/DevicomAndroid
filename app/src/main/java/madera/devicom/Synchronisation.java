package madera.devicom;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

public class Synchronisation extends AppCompatActivity implements FetchDataFromApi {

    EditText emailText;
    TextView responseView;
    String data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.synchronisation);


        TextView online = (TextView)findViewById(R.id.online);
        online.setText("Connecté à Madera");

        // TODO: cette vérification ne fonctionne pas !!
        try {
            new ApiRequest(this, "").execute();
        } catch (Exception e) {
            online.setText("Connection à Madera impossible");
            online.setTextColor(Integer.parseInt("#ff0000"));
        }

        new ApiRequest(this, "").execute();
    }

    public void fetchDataCallback(int code, String result) {
        data = result;
        renderData();
    }

    private void renderData(){
        if (data == "") {
            data = "Impossible de joindre le serveur de Madera";
        }
        responseView.setText(data);
    }
}
