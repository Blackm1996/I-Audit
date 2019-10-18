

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Objects;

public class Access_Entry extends Entry
{
    private String original_value;
    private String IP;
    private String coordinates;
    private String location;
    public Access_Entry(){}

    public Access_Entry(String entry)
    {
        this.original_value=entry;
        String[] parts=entry.split("\\s+");
        this.actor=parts[3];
        this.db_name=parts[4];
        this.IP=parts[5];
        String s=parts[1]+"T"+parts[2];
        this.timeStamp=LocalDateTime.parse(s);
        this.location=parts[6];
        try {
            coordinate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void coordinate() throws Exception {
        String url = "http://www.mapquestapi.com/geocoding/v1/address?key=UcrLTp0CfC4ZGRJrnksKG02DlHgmyOta&location="+location;

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        //add request header
        //con.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = con.getResponseCode();
        //System.out.println("\nSending 'GET' request to URL : " + url);
        //System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        JSONObject myResponse = new JSONObject(response.toString());
        //print result
        JSONArray res=myResponse.getJSONArray("results");
        JSONObject locObj=res.getJSONObject(0);
        //System.out.println(locObj.getJSONObject("providedLocation").getString("location"));
        JSONArray loc=locObj.getJSONArray("locations");
        JSONObject la=loc.getJSONObject(0).getJSONObject("latLng");
        coordinates=la.getDouble("lat")+","+la.getDouble("lng");
    }
    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }


    public String getDb_name() {
        return db_name;
    }

    public void setDb_name(String db_name) {
        this.db_name = db_name;
    }

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj==null)
            return false;
        if(! (obj instanceof Access_Entry))
            return false;
        Access_Entry e=(Access_Entry) obj;
        if(!(this.IP.equals(e.IP)))
            return false;
        if(!(this.actor.equals(e.actor)))
            return false;
        if (!this.db_name.equals(e.db_name))
            return false;
        if (!this.coordinates.equals(e.coordinates))
            return false;

        return this.timeStamp.equals(e.timeStamp);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(IP,actor,timeStamp,db_name,coordinates);
    }

    public String toString()
    {
        return original_value+" "+coordinates;
    }
}
