package gruppnan.timeline;

import org.apache.commons.lang.math.NumberUtils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import 	java.net.HttpURLConnection;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;



/**
 * Created by Nikolai on 2017-04-06.
 */

public class TimeEditFetcher {
    HashMap<String,String> SearchHist;
    final String[] icsStructure = {"DTSTART","DTEND","UID:","DTSTAMP:","LAST-MODIFIED:","SUMMARY:","LOCATION:","DESCRIPTION:","END:"};
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
            BufferedReader in = new BufferedReader(new InputStreamReader(new GZIPInputStream(con.getInputStream()),"UTF-8"));

            parseIcs(readInReader(in));
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
            con.setRequestProperty("Accept-Charset","utf-8");
            con.setAllowUserInteraction(true);

            BufferedReader in = new BufferedReader(new InputStreamReader(new GZIPInputStream(con.getInputStream()),"UTF-8"));


            s = readInReader(in);

            con.disconnect();
            return s;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }
    private String readInReader(BufferedReader in){
        StringBuffer sb = new StringBuffer();
        try {
            while (in.ready()) {
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
        final String[] wantedFields= {"DTSTART","DTEND","SUMMARY:","LOCATION:","DESCRIPTION:","END:"};

       // System.out.println(icsFile);
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyyMMdd'T'HHmmss'Z'");
        Date dateTest;
        int indx =0;
        String[] event = null;

        for(int i = 0;i<icsLines.length;i++) {


            try {

                if (icsLines[i].equals("BEGIN:VEVENT")) {
                    indx = 0;
                    event = new String[5];
                }
                //System.out.println(icsLines[i].contains(wantedFields[indx])+ " " + icsLines[i] + " "+ wantedFields[indx]);
                if(icsLines[i].contains(wantedFields[indx])) {

                    if (indx < 5 && event!=null) {

                        String[] recVars = recuString(icsLines, getNextField(wantedFields[indx]), i);

                        event[indx] = recVars[0].substring(recVars[0].indexOf(':')+1,recVars[0].length());

                        //System.out.println(event[indx]);
                        try {
                         if(indx==0||indx==1)
                            event[indx]=sdf.parse(event[indx]).toString();
                        }catch (ParseException e){}

                        i = Integer.parseInt(recVars[1]);
                        indx++;

                    }else{
                        events.add(event);
                        event = null;

                    }
                }
            } catch(Exception e){
                    e.printStackTrace();
            }

        }

        return events;

    }
    private String[] recuString(String[] file, String nextMatch, int index){
            String[] retVals;
            String s;
            if(!file[index+1].contains(nextMatch)){
                retVals = recuString(file,nextMatch,index+1);
                retVals[0]= file[index] +retVals[0] ;
                return retVals;
            }
            retVals = new String[2];
            retVals[0] = file[index].trim().replace("\\n"," ").replace("\\", "");
            //System.out.println(file[index]+"\n");
            retVals[1] = index+"";
            return retVals;


    }
    private String getNextField(String curField){
        for(int i = 0;i<icsStructure.length;i++) {
            if (icsStructure[i].equals(curField)) {
                //System.out.println(icsStructure[i + 1]);
                return icsStructure[i + 1];
            }
        }
        throw new IllegalArgumentException();

    }
}
