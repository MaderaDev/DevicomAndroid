package madera.devicom;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

public class MainMenu extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.main_menu);


        LinearLayout searchcust = (LinearLayout) findViewById(R.id.searchcust);
        searchcust.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainMenu.this, SearchClient.class));
            }
        });

        LinearLayout createcust = (LinearLayout) findViewById(R.id.createcust);
        createcust.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainMenu.this, NewClient.class));
            }
        });

        LinearLayout searchdev = (LinearLayout) findViewById(R.id.searchdev);
        searchdev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainMenu.this, SearchDevis.class));
            }
        });

        LinearLayout createdev = (LinearLayout) findViewById(R.id.createdev);
        createdev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainMenu.this, EditDevis.class));
            }
        });

        LinearLayout sync = (LinearLayout) findViewById(R.id.sync);
        sync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainMenu.this, Synchronisation.class));
            }
        });
    }
}
