package madera.devicom;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class ApiRequest extends AsyncTask<Void, Void, String> {

    private Exception exception;
    TextView responseView;
    String apiDestination;
    String valueSearched;
    FetchDataFromApi callbackInterface;

    ApiRequest(TextView responseView, String apiDestination, String valueSearched, FetchDataFromApi callbackInterface) {
        this.responseView = responseView;
        this.apiDestination = apiDestination;
        this.valueSearched = valueSearched;

        this.callbackInterface = callbackInterface;

    }


    protected void onPreExecute() {
        responseView.setText("");
    }

    @Override
    protected String doInBackground(Void... urls) {

        try {
            //10.0.2.2 because of android emulator
            URL url = new URL("http://10.0.2.2:8000/api/" + apiDestination);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line).append("\n");
                }
                bufferedReader.close();
                return stringBuilder.toString();
            } finally {
                urlConnection.disconnect();
            }
        } catch (Exception e) {
            Log.e("ERROR", e.getMessage(), e);
            return null;
        }
    }

    protected void onPostExecute(String response) {
        if (response == null) {
            response = "Response is null";
        }
        Log.i("INFO", response);
        this.callbackInterface.fetchDataCallback(response);

        /*try {
            JSONObject object = (JSONObject) new JSONTokener(response).nextValue();
            printable = object.getString(valueSearched);
            //int likelihood = object.getInt("likelihood");
            //JSONArray photos = object.getJSONArray("photos");
        } catch (JSONException err) {
            Log.e("ERROR", String.valueOf(err));
        }

        //responseView.setText(printable);*/
    }

}