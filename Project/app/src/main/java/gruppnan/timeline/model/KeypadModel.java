package gruppnan.timeline.model;

/**
 * Created by carlo on 2017-05-25.
 */

public class KeypadModel {

    int seconds, minutes, hours;
    int nbrOfDigits;
    StringBuilder textBuilder;


    public KeypadModel() {
        textBuilder = new StringBuilder();
        textBuilder.append("000000");
    }

    public void addDigit(String number) {
        if(nbrOfDigits < 6) {
            textBuilder.append(number);
            textBuilder.delete(0, 1);
            updateTime();
            nbrOfDigits++;
        }
    }

    public void removeDigit() {
        if(nbrOfDigits > 0) {
            textBuilder.delete(textBuilder.length() - 1, textBuilder.length());
            textBuilder.insert(0, "0");
            updateTime();
            nbrOfDigits--;
        }
    }


    public void updateTime() {
        seconds = Integer.parseInt(textBuilder.substring(textBuilder.length()-2, textBuilder.length()));
        minutes = Integer.parseInt(textBuilder.substring(textBuilder.length()-4, textBuilder.length()-2));
        hours = Integer.parseInt(textBuilder.substring(textBuilder.length()-6, textBuilder.length()-4));
    }

    public long getTime() {
        return (seconds*1000) + (minutes*60*1000) + (hours*60*60*1000);
    }


    public int getHours() {
        return  hours;
    }

    public int getMinutes() {
        return  minutes;
    }

    public int getSeconds() {
        return  seconds;
    }
}
