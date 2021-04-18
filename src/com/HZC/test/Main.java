package com.HZC.test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Calendar cal = Calendar.getInstance();
        System.out.println(cal.get(Calendar.MONTH));
        Date date = new Date();
        SimpleDateFormat slp = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String str = slp.format(date);
        System.out.println(str);
        System.out.println(Integer.parseInt("09"));
        System.out.println("eee");

    }
}
