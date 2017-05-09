package madera.devicom;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Data {

    public String devis[];
    public String clients[];

    public static String data;



    public static void saveData(String data, Context context){
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("devicom.data", Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
            Log.d("Exception", "File write successful");
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    public static void loadData(Context context) {

        String result = "";

        try {
            InputStream inputStream = context.openFileInput("devicom.data");

            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ((receiveString = bufferedReader.readLine()) != null) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                result = stringBuilder.toString();
            }
        } catch (FileNotFoundException e) {
            Log.i("Data activity", "File not found: " + e.toString());
            File newFile = new File("/data/data/madera.devicom/files/devicom.data");
            try {
                newFile.createNewFile();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            Log.i("Data activity", "Created new file");
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }
        Log.d("devicom data", result);
        data = result;
    }
}
