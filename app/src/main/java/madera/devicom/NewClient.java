package madera.devicom;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class NewClient extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.new_client);

        Button submit = (Button)findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                TextView vsurname = (TextView) findViewById(R.id.surname);
                String surname = vsurname.getText().toString();
                TextView vname = (TextView) findViewById(R.id.name);
                String name = vname.getText().toString();
                TextView vemail = (TextView) findViewById(R.id.email);
                String email = vemail.getText().toString();
                TextView vphone = (TextView) findViewById(R.id.phone);
                String sphone = vphone.getText().toString();
                int phone = Integer.parseInt(sphone);
                TextView vaddress = (TextView) findViewById(R.id.address);
                String address = vaddress.getText().toString();
                TextView vpostal = (TextView) findViewById(R.id.postal);
                String spostal = vpostal.getText().toString();
                int postal = Integer.parseInt(spostal);
                TextView vcity = (TextView) findViewById(R.id.city);
                String city = vcity.getText().toString();

                Client client = new Client(surname, name, phone, email, address, postal, city);
            }
        });

    }
}