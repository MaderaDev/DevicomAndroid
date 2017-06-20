package madera.devicom;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class ApiRequest extends AsyncTask<Void, Void, String> {

    //private String apiUrl = "http://10.0.2.2:8000/api/";
    private String apiUrl = "http://maderadev.herokuapp.com/api/";

    String apiDestination;
    FetchDataFromApi callbackInterface;
    int responseCode;

    ApiRequest(FetchDataFromApi callbackInterface, String apiDestination) {
        this.apiDestination = apiDestination;
        this.callbackInterface = callbackInterface;

    }

    protected void onPreExecute() { }

    @Override
    protected String doInBackground(Void... urls) {

        try {
            //10.0.2.2 because of android emulator
            URL url = new URL(this.apiUrl + apiDestination);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                responseCode = urlConnection.getResponseCode();
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
        //Log.i("INFO", response);
        this.callbackInterface.fetchDataCallback(responseCode, response);
    }

}