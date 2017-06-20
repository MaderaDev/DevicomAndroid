package madera.devicom;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;


public class HttpPost extends AsyncTask<Void, Void, String> {

    //private String apiUrl = "http://10.0.2.2:8000/api/";
    private String apiUrl = "http://maderadev.herokuapp.com/";

    String apiDestination;
    FetchDataFromApi callbackInterface;
    String postDataParams;
    int responseCode;

    HttpPost(FetchDataFromApi callbackInterface, String apiDestination, JSONObject postDataParams) {
        this.apiDestination = apiDestination;
        this.callbackInterface = callbackInterface;
        try {this.postDataParams = getPostDataString(postDataParams);}
        catch (Exception e) {System.out.println(e);}
    }
    protected void onPreExecute() { }
    @Override
    protected String doInBackground(Void... urls) {

        try {
            //10.0.2.2 because of android emulator
            URL url = new URL(this.apiUrl + apiDestination);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            urlConnection.setRequestMethod("POST");
            urlConnection.setRequestProperty("USER-AGENT", "Mozilla/5.0");
            urlConnection.setRequestProperty("ACCEPT-LANGUAGE", "en-US,en;0.5");
            urlConnection.setDoOutput(true);

            DataOutputStream dStream = new DataOutputStream(urlConnection.getOutputStream());
            dStream.writeBytes(this.postDataParams);
            dStream.flush();
            dStream.close();
            responseCode = urlConnection.getResponseCode();
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
        } catch (IOException e) {
            Log.e("ERROR", e.getMessage(), e);
            return null;
        } catch (Exception e) {
            Log.e("ERROR", e.getMessage(), e);
            return null;
        }
    }

    protected void onPostExecute(String response) {
        if (response == null) {
            response = "No data";
        }
        this.callbackInterface.fetchDataCallback(responseCode, response);
    }

    public String getPostDataString(JSONObject params) throws Exception {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        Iterator<String> itr = params.keys();
        while(itr.hasNext()){
            String key= itr.next();
            Object value = params.get(key);
            if (first)
                first = false;
            else
                result.append("&");
            result.append(URLEncoder.encode(key, "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(value.toString(), "UTF-8"));
        }
        return result.toString();
    }

}
