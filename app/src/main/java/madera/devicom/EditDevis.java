package madera.devicom;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class NewDevis extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.new_devis);

        TextView iddevis = (TextView)findViewById(R.id.iddevis);
        iddevis.setKeyListener(null);

        Button submit = (Button)findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Calendar c = Calendar.getInstance();
                System.out.println("Current time => " + c.getTime());
                SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
                String formattedDate = df.format(c.getTime());
                TextView vcdate = (TextView) findViewById(R.id.datec);
                vcdate.setText(formattedDate);


            /*TextView vcdate = (TextView) findViewById(R.id.surname);
            Date cdate = vcdate.setText();
            TextView vudate = (TextView) findViewById(R.id.name);
            String udate = vudate.getText().toString();
            TextView vclient = (TextView) findViewById(R.id.email);
            String client = vclient.getText().toString();

            Devis devis = new Devis(cdate, udate, client);*/

            }
        });
    }
}