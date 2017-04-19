package gruppnan.timeline;

import android.icu.text.SimpleDateFormat;

import org.apache.commons.lang.math.NumberUtils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import 	java.net.HttpURLConnection;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
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
        try {
            if(cname == null || fromDate == null || toDate == null || !NumberUtils.isNumber(fromDate)|| !NumberUtils.isNumber(toDate)){

                //System.out.println(SearchHist.toString()+"\nError "+ cname);
                return null;
            }

            String[] response;
            String res;
            String id = SearchHist.get(cname);
            URL icsUrl = null;
            Pattern namCourPat = Pattern.compile("value=\"(.*?)\"");
           // response = sendHttpGet("https://se.timeedit.net/web/chalmers/db1/public/ri.html?h=t&sid=3&p="+fromDate+".x%2C"+toDate+".x&objects="+id+"&ox=0&types=0&fe=0#iCalDialogContent").split("\\n");;
            response = sendHttpGet("https://se.timeedit.net/web/chalmers/db1/public/ri.html?h=t&sid=3&p=20170102.x%2C20170731.x&objects=201969.182&ox=0&types=0&fe=0").split("\\n");
            for(int i = 0;i<response.length;i++){
                if(response[i].contains("4 veckor")){
                    Matcher m2 = namCourPat.matcher(response[i+1]);
                    if(m2.find()){
                            icsUrl = new URL(m2.group(1));
                    }
                }
            }
            if(icsUrl == null){
                return null;
            }
            HttpURLConnection con = (HttpURLConnection) icsUrl.openConnection();
            con.setRequestProperty("Accept-Encoding","gzip");
            InputStream in = new BufferedInputStream(new GZIPInputStream(con.getInputStream()));

            parseIcs(readInStream(in));
            con.disconnect();
        }catch(MalformedURLException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
       // System.out.println(res);
        return null;

    }

    public String sendHttpGet(String urlString){
        try {
            String s;
            URL url = new URL(urlString);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            con.setReadTimeout(8000);
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

            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    private ArrayList<String[]> parseIcs(String icsFile){
        ArrayList<String[]>  events = new ArrayList<>();
        String[] icsLines = icsFile.split("\\r\\n");

        System.out.println(icsFile);
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyyMMdd'T'HHmmss'Z'");
        Date dateTest;
        String[] event;

        for(int i = 0;i<icsLines.length;i++){

            try {

                if(icsLines[i].equals("BEGIN:VEVENT")) {

                    event = new String[5];
                    i++;
                    while(i<icsLines.length && !icsLines[i].equals("END:VEVENT") ){
                        if((icsLines[i].contains(":"))&& !icsLines[i].equals("BEGIN:VEVENT")) {
                            switch (icsLines[i].substring(0,icsLines[i].indexOf(':'))) {
                                case "SUMMARY":
                                    System.out.println(icsLines[i+1]);
                                    event[0]= icsLines[i].substring(icsLines[i].indexOf(':')+1,icsLines[i].length());
                                    break;
                                case "DTSTART":
                                    event[1]= sdf.parse(icsLines[i].substring(icsLines[i].indexOf(':')+1,icsLines[i].length())).toString();
                                    break;
                                case "DTEND":
                                    event[2]= sdf.parse(icsLines[i].substring(icsLines[i].indexOf(':')+1,icsLines[i].length())).toString();
                                    break;
                                case "DESCRIPTION":
                                    event[3]=icsLines[i].substring(icsLines[i].indexOf(':')+1,icsLines[i].length());
                                    break;
                                case "LOCATION":
                                    event[4]=icsLines[i].substring(icsLines[i].indexOf(':')+1,icsLines[i].length());
                                    break;
                                default:
                                    //System.out.println(icsLines[i]);
                                    break;
                            }
                        }
                        i++;
                        if(icsLines[i].equals("END:VEVENT"))
                            events.add(event);
                    }
                }

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }



        return events;

    }
}
