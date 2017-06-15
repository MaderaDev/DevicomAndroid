package madera.devicom;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Utilities {

    public List<Map<String, String>> stringToMap(String in/*, Integer before, Integer after, String patternLVL1, String patternLVL2, String patternLVL3*/){

        Integer before = 2;
        Integer after = 2;
        String patternLVL1 = "\\},\\{";
        String patternLVL2 = "\",\"";
        String patternLVL3 = "\":\"";

        in = in.replace("id\":", "id\":\"");
        in = in.replace(",\"nom", "\",\"nom");
        in = in.replace("\\u00e8", "è");
        in = in.replace("\\u00e9", "é");

        /*JSONParser parser = new JSONParser();
        Object obj = parser.parse(s);
        JSONArray array = (JSONArray)obj;*/

        //in.replaceAll("\"", "");

        // in = data
        // before = nb chars to remove before data
        // after = nb chars to remove after data
        // pattern = pattern to split

        //System.out.println(in);
        in = in.substring(before, in.length()-after);           //remove curly brackets
        //System.out.println(in);
        List<String> infosClient = Arrays.asList(in.split(patternLVL1));
        //String[] infosClient = in.split(patternLVL1);         //split the string to create key-value pairs

        int size = infosClient.size();
       // Map[] myarray = new String[size];
        List<Map<String, String>> list = new ArrayList<>();
        String key;
        String value;
        for(int i = 0; i < size; i++){
            //System.out.println(infosClient.get(i));
            List<String> oneClient = Arrays.asList(infosClient.get(i).split(patternLVL2));
            Map<String,String> infos = new HashMap<>();
            for(int j = 0; j < oneClient.size(); j++){
                //System.out.println(oneClient.get(j));
                List<String> info = Arrays.asList(oneClient.get(j).split(patternLVL3));
                System.out.println(info.get(0) + " : " + info.get(1));
                key = info.get(0).replace("\"", "").toString();
                value = info.get(1).replace("\"", "").toString();

                infos.put(key, value);
            }
           System.out.println(infos);
            list.add(infos);
        }
        //System.out.println(list);
        return list;
    }

    /*public String getPrettyString(String string) {

        System.out.println(string);

        List<String> mylist = Arrays.asList(string.split("|"));
        for(int i=0; i<mylist.size(); i++){
            System.out.println(mylist.get(i));
        }


        return "toto";
    }*/
}
