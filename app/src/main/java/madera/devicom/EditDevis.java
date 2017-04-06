package madera.devicom;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class EditDevis extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.edit_devis);

        //Numéro du devis
        TextView iddevis = (TextView)findViewById(R.id.iddevis);
        iddevis.setKeyListener(null);

        //date
        Calendar c = Calendar.getInstance();
        System.out.println("Current time => " + c.getTime());
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        String formattedDate = df.format(c.getTime());

        //Date de céation
        TextView datec = (TextView)findViewById(R.id.datec);
        datec.setKeyListener(null);
        datec.setText(formattedDate);

        //Date de modification
        TextView dateu = (TextView)findViewById(R.id.dateu);
        dateu.setKeyListener(null);
        dateu.setText(formattedDate);


        //dates

        TextView vcdate = (TextView) findViewById(R.id.datec);
        vcdate.setText(formattedDate);
        TextView vudate = (TextView) findViewById(R.id.dateu);
        vudate.setText(formattedDate);

        /*Button submit = (Button)findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });*/

        Button cancel = (Button)findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(EditDevis.this, "Modification annulée", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(EditDevis.this, MainMenu.class));
            }
        });

        Button submit = (Button)findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Toast.makeText(EditDevis.this, "Devis enregistré", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(EditDevis.this, MainMenu.class));
            }
        });
    }
}