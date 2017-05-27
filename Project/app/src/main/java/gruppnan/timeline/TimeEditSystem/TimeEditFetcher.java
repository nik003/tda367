package gruppnan.timeline.TimeEditSystem;

import org.apache.commons.lang.math.NumberUtils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import 	java.net.HttpURLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;



/**
 * Created by Nikolai on 2017-04-06.
 * Webscrapes the time edit page to get the courses.
 */

public class TimeEditFetcher {
    private String courseName;
    HashMap<String,String> SearchHist;
    final String[] icsStructure = {"DTSTART","DTEND","UID:","DTSTAMP:","LAST-MODIFIED:","SUMMARY:","LOCATION:","DESCRIPTION:","END:"};
    public TimeEditFetcher(){
        SearchHist = new HashMap<>();

    }


    /**
     * Gets all courses matching the input in timeEdit
     * @param Cname the course name to be searched for
     * @return the list of courses
     */
    public List<String> searchCourse(String Cname){
        ArrayList<String> courseList = new ArrayList<>();
        if(Cname == null || Cname.equals("")){
            courseList.add("No course found");
            return courseList;
        }
        String[] response;
        String courseNam;

        Pattern idNumPat = Pattern.compile("data-id=\"(.*?)\"");
        Pattern namCourPat = Pattern.compile("data-name=\"(.*?)\"");

        response = sendHttpGet("https://se.timeedit.net/web/chalmers/db1/public/objects.html?max=15&fr=t&partajax=t&;im=f&sid=3&l=sv_SE&search_text="+ Cname +"&types=182").split("\\n");
        for(int i = 0;i<response.length; i++){
            Matcher m = namCourPat.matcher(response[i]);
            if(m.find()){
                courseNam = m.group(1);
                courseList.add(courseNam);
               // sb.append(courseNam + "\n");
                m = idNumPat.matcher(response[i]);
                m.find();
                SearchHist.put(courseNam,m.group(1));
            }
        }
        if(courseList.size()==0){
            courseList.add("No course found");
            return null;
        }
        //System.out.println(SearchHist.toString());
        //System.out.println(Arrays.toString(response));
        return courseList;
    }

    /**
     * Gets the ics data from timeedit and parses it into events
     * @param cname the full course name
     * @param fromDate   From which date to start get events
     * @param toDate     To which date to end get events
     * @return           A list of the received and parsed events
     */
    public List<TimeEditEvent> getIcs(String cname, String fromDate, String toDate){//||!SearchHist.containsKey(cname)
        ArrayList<TimeEditEvent> events = null;
        try {
            courseName = cname;
            if(cname == null || fromDate == null || toDate == null || !NumberUtils.isNumber(fromDate)|| !NumberUtils.isNumber(toDate)){

                //System.out.println(SearchHist.toString()+"\nError "+ cname);
                return null;
            }

            String[] response;
            String res;
            String id = SearchHist.get(cname);
            URL icsUrl = null;
            Pattern namCourPat = Pattern.compile("value=\"(.*?)\"");
            response = sendHttpGet("https://se.timeedit.net/web/chalmers/db1/public/ri.html?h=t&sid=3&p="+fromDate+".x%2C"+toDate+".x&objects="+id+"&ox=0&types=0&fe=0#iCalDialogContent").split("\\n");

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

            events = (ArrayList)parseIcs(readInReader(in));
            con.disconnect();
        }catch(MalformedURLException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
       // System.out.println(res);
        if(events==null)
        {return null;}
        else{return events;}

    }

    /**
     * Sends a http get request
     * @param urlString the url to send to
     * @return the response
     */

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

    /**
     * Reads an bufferedReader
     * @param in the reader to be read
     * @return the string from the  reader
     */
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

    /**
     * Parses the ICS data according to the data structure in timeEdit
     * @param icsFile the data string to be parsed
     * @return the list of events created from the parsed data
     */
    private List<TimeEditEvent> parseIcs(String icsFile){
        ArrayList<TimeEditEvent>  events = new ArrayList<>();
        TimeEditEvent teEvent;
        String[] icsLines = icsFile.split("\\r\\n");
        final String[] wantedFields= {"DTSTART","DTEND","SUMMARY:","LOCATION:","DESCRIPTION:","END:"};


        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd'T'HHmmss'Z'");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd");
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

                             if(!isValiDateFormat(event[indx],sdf)) {
                                 event[indx] = sdf.format(sdf2.parse(event[indx]));
                             }




                        }catch (ParseException e){
                            System.out.println(e);
                        }

                        i = Integer.parseInt(recVars[1]);
                        indx++;

                    }else{
                        if(event!=null) {
                            teEvent = new TimeEditEvent(sdf.parse(event[0]), sdf.parse(event[1]), event[2], event[3], event[4], courseName);
                            events.add(teEvent);

                        }
                        event = null;
                    }
                }
            } catch(Exception e){
                    e.printStackTrace();
            }

        }

        return events;

    }

    /**
     * Checks if the data is in a valid date format
     * @param data the data to be checked
     * @param sdf   The date format to be used
     * @return true if the date is in a valid format, false otherwise
     */
    private boolean isValiDateFormat(String data, SimpleDateFormat sdf){
        Date date = null;
        try{
            SimpleDateFormat sDF = sdf;
            date = sdf.parse(data);
            if (!data.equals(sdf.format(date))) {
                date = null;
            }

        }catch (ParseException e) {
            //System.out.println(e);
        }
        return date!=null;
    }

    /**
     * A recursive function for parsing ics data
     * @param file      the data to be parsed
     * @param nextMatch the next field to be found
     * @param index the index of the data array to be parsed
     * @return      A string array with all the parsed data
     */
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

    /**
     * gets the next next expected field in the ICS structure
     * @param curField the current field
     * @return  the next field in the structure
     */
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
