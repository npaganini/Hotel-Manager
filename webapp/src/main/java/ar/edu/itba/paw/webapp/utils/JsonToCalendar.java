package ar.edu.itba.paw.webapp.utils;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class JsonToCalendar extends XmlAdapter<String, Calendar> {

//    String is yyyy-MM-dd
    @Override
    public Calendar unmarshal(String v) throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date =  simpleDateFormat.parse(v);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    @Override
    public String marshal(Calendar v) throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(v.getTime());
    }
}
