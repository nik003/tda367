package gruppnan.timeline;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import 	java.net.HttpURLConnection;


/**
 * Created by Nikolai on 2017-04-06.
 */

public class TimeEditFetcher {
    public TimeEditFetcher(){


    }
    public boolean courseExist(){
        return true;
    }


    public String getIcs(){return null;}

    public String sendHttpGet(String urlString){
        try {
            String s;
            URL url = new URL(urlString);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(con.getInputStream());
            s = readInStream(in);
            System.out.println(s);
            return s;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;

    }
    private String readInStream(InputStream in){
        StringBuffer sb = new StringBuffer();
        try {
            while (in.available() > 0) {
                sb.append((char)in.read());
            }
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
