package madera.devicom;

import android.app.Activity;
<<<<<<< HEAD
import android.content.Intent;
=======
>>>>>>> c7d9809d67111bb20e01fc9f2205cde03561a922
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
<<<<<<< HEAD
import android.widget.Toast;
=======
>>>>>>> c7d9809d67111bb20e01fc9f2205cde03561a922

import java.text.SimpleDateFormat;
import java.util.Calendar;

<<<<<<< HEAD
public class EditDevis extends Activity {
=======
public class NewDevis extends Activity {
>>>>>>> c7d9809d67111bb20e01fc9f2205cde03561a922

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
<<<<<<< HEAD
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
=======
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

>>>>>>> c7d9809d67111bb20e01fc9f2205cde03561a922
            }
        });
    }
}