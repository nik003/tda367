package gruppnan.timeline;

import org.apache.commons.lang.math.NumberUtils;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import 	java.net.HttpURLConnection;
import java.util.Arrays;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;


/**
 * Created by Nikolai on 2017-04-06.
 */

public class TimeEditFetcher {
    HashMap<String,String> SearchHist;
    public TimeEditFetcher(){
        SearchHist = new HashMap<>();

    }
    public boolean courseExist(){
        return true;
    }

    public String searchCourse(String Cname){
        if(Cname == null || Cname == ""){
            return "No course found";
        }
        String[] response;
        String courseNam;
        Pattern idNumPat = Pattern.compile("data-id=\"(.*?)\"");
        Pattern namCourPat = Pattern.compile("data-name=\"(.*?)\"");
        StringBuffer sb = new StringBuffer();
        response = sendHttpGet("https://se.timeedit.net/web/chalmers/db1/public/objects.html?max=15&fr=t&partajax=t&;im=f&sid=3&l=sv_SE&search_text="+ Cname +"&types=182").split("\\n");
        for(int i = 0;i<response.length; i++){
            Matcher m = namCourPat.matcher(response[i]);
            if(m.find()){
                courseNam = m.group(1);
                sb.append(courseNam + "\n");
                m = idNumPat.matcher(response[i]);
                m.find();
                SearchHist.put(courseNam,m.group(1));
            }
        }
        if(sb.length()==0){
            return "No course found";
        }
        //System.out.println(SearchHist.toString());
        //System.out.println(Arrays.toString(response));
        return sb.toString();
    }
    public String getIcs(String cname, String fromDate, String toDate){//||!SearchHist.containsKey(cname)
        if(cname == null || fromDate == null || toDate == null || !NumberUtils.isNumber(fromDate)|| !NumberUtils.isNumber(toDate)){

            //System.out.println(SearchHist.toString()+"\nError "+ cname);
            return null;
        }

        String[] response;
        String res;
        String id = SearchHist.get(cname);

       // response = sendHttpGet("https://se.timeedit.net/web/chalmers/db1/public/ri.html?h=t&sid=3&p="+fromDate+".x%2C"+toDate+".x&objects="+id+"&ox=0&types=0&fe=0#iCalDialogContent").split("\\n");;
        response = sendHttpGet("https://se.timeedit.net/web/chalmers/db1/public/ri.html?h=t&sid=3&p=20170102.x%2C20170731.x&objects=201969.182&ox=0&types=0&fe=0").split("\\n");
        for(int i = 0;i<response.length;i++){
            if(response[i].contains("4 veckor")){
                System.out.println(response[i+1]);
            }
        }
       // System.out.println(res);
        return null;
    }

    public String sendHttpGet(String urlString){
        try {
            String s;
            URL url = new URL(urlString);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            con.setReadTimeout(80000);
            con.setConnectTimeout(10000);
            con.setRequestProperty("Accept-Encoding","gzip");
            con.setAllowUserInteraction(true);

            InputStream in = new BufferedInputStream(new GZIPInputStream(con.getInputStream()));


            s = readInStream(in);

            con.disconnect();
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
            System.out.println(in.available());
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
