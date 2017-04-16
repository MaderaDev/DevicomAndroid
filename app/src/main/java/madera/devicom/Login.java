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

public class Login extends Activity {

    //ApiRequest request = new ApiRequest();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.login);


        TextView passwd = (TextView)findViewById(R.id.forgot_password);
        passwd.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(Login.this, "TODO feature", Toast.LENGTH_SHORT).show();
            }
        });

        Button submit = (Button)findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

            TextView uservalue = (TextView) findViewById(R.id.username);
            String username = uservalue.getText().toString();
            TextView passvalue = (TextView) findViewById(R.id.password);
            String password = passvalue.getText().toString();


                String chain = "http://localhost:8000/api/";

                /*try {
                    String response = request.get(chain);
                    TextView vresponse = (TextView) findViewById(R.id.response);
                    vresponse.setText(response);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }*/





            if(username.equals("") && password.equals("")){
                startActivity(new Intent(Login.this, MainMenu.class));
            } else {
                Toast.makeText(Login.this, "Identifiants invalides", Toast.LENGTH_SHORT).show();
            }
            }
        });
    }
}