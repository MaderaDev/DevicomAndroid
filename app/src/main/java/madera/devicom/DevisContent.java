package madera.devicom;

<<<<<<< HEAD
import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class DevisContent extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.devis_content);

    }
}
=======
/**
 * Created by vince on 05/04/2017.
 */

public class devisContent {
}
>>>>>>> c7d9809d67111bb20e01fc9f2205cde03561a922
