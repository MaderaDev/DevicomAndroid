package madera.devicom;

public interface FetchDataFromApi {
    // method called when server's data get fetched
    public void fetchDataCallback (int code, String result);
}