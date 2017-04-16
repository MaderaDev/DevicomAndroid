package madera.devicom;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
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

        responseView = (TextView) findViewById(R.id.responseView);
        emailText = (EditText) findViewById(R.id.emailText);

        Button queryButton = (Button) findViewById(R.id.queryButton);

        new ApiRequest(responseView, "client", "", this).execute();


        queryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //new ApiRequest(responseView, "client", "", this).execute();
            }
        });

    }

    public void fetchDataCallback(String result) {
        data = result;
        renderData();
    }

    private void renderData(){
        responseView.setText(data);
    }
}
