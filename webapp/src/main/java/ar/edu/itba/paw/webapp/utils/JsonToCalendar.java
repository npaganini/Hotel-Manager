package ar.edu.itba.paw.webapp.utils;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class JsonToCalendar extends XmlAdapter<String, Calendar> {

//    String is dd-mm-yyyy
    @Override
    public Calendar unmarshal(String v) throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date =  simpleDateFormat.parse(v);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    @Override
    public String marshal(Calendar v) throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return simpleDateFormat.format(v.getTime());
    }
}
