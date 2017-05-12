package madera.devicom;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class Login extends Activity implements FetchDataFromApi{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.login);

        TextView passwordvalue = (TextView) findViewById(R.id.password);


        TextView fgpasswd = (TextView)findViewById(R.id.forgot_password);
        fgpasswd.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(Login.this, "Fonction en développement", Toast.LENGTH_SHORT).show();
            }
        });

        Button submit = (Button)findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

            try {authentification();}
            catch(Exception e){System.out.println(e);}
            }
        });
    }

    public void authentification() throws JSONException {
        TextView uservalue = (TextView) findViewById(R.id.username);
        String username = uservalue.getText().toString();
        TextView passvalue = (TextView) findViewById(R.id.password);
        String password = passvalue.getText().toString();

        JSONObject postDataParams = new JSONObject();
        postDataParams.put("email", "admin@madera.fr");
        postDataParams.put("password", "madera");
        //postDataParams.put("email", username);
        //postDataParams.put("password", password);


        new HttpPost(this, "auth", postDataParams).execute();
        //for dev
        startActivity(new Intent(Login.this, MainMenu.class));
    }

    public void fetchDataCallback(int code, String result) {
        System.out.println(code);

        TextView passvalue = (TextView) findViewById(R.id.password);
        String password = passvalue.getText().toString();

        if (code == 401){
            Toast.makeText(Login.this, "Identifiants invalides", Toast.LENGTH_SHORT).show();
        } else if (code == 200){
            startActivity(new Intent(Login.this, MainMenu.class));
        } else {
            Toast.makeText(Login.this, "Impossible de contacter le serveur", Toast.LENGTH_SHORT).show();
        }
        passvalue.setText("");

    }
}