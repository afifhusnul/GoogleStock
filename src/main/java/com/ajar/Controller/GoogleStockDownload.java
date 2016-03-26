/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ajar.Controller;

import com.ajar.config.HibernateSessionManager;
import com.ajar.entity.TrxStock;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import org.hibernate.Session;

/**
 *
 * @author nusnafif
 */
public class GoogleStockDownload {

    public static final int DATE = 0;
    public static final int OPEN = 0;
    public static final int HIGH = 0;
    public static final int LOW = 0;
    public static final int CLOSE = 0;
    public static final int VOLUME = 0;
    public static final int ADJCLOSE = 0;

    private ArrayList<Date> dates;
    private ArrayList<Double> opens;
    private ArrayList<Double> highs;
    private ArrayList<Double> lows;
    private ArrayList<Double> closes;
    private ArrayList<Integer> volumes;
    private ArrayList<Double> adjcloses;

    SimpleDateFormat formatter = new SimpleDateFormat("MMM+dd+yyyy");
    SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yy");

    public GoogleStockDownload(String symbol, Date start, Date end) {

        dates = new ArrayList<Date>();
        opens = new ArrayList<Double>();
        highs = new ArrayList<Double>();
        lows = new ArrayList<Double>();
        closes = new ArrayList<Double>();
        volumes = new ArrayList<Integer>();
//        adjcloses = new ArrayList<Double>();                

////        https://www.google.com/finance/historical?q=IDX:ABBA&startdate=01+01,+2016&enddate=03+14,+2016&output=csv
        String url = "https://www.google.com/finance/historical?q=IDX:" + symbol
                + "&startdate=" + formatter.format(start.getTime())
                + "&enddate=" + formatter.format(end.getTime())
                + "&output=csv";

        try {
            URL googlefin = new URL(url);
            URLConnection data = googlefin.openConnection();
            Scanner input = new Scanner(data.getInputStream());

            if (input.hasNext()) //Skip line, its just header
            {
                input.nextLine();
            }

//            Save to DB 
            Session session = HibernateSessionManager.getSessionFactory().openSession();
            session.beginTransaction();
            // Start read line
            while (input.hasNextLine()) {
                String line = input.nextLine();
                // TODO - Correct data to the correct arraylist
                // Print all result directly to prompt                 
//                System.out.println(symbol+","+line);  
//                Afif's property to save into Table DB

                String str = symbol + "," + line;
                String strArray[] = str.split(",");

                TrxStock trx = new TrxStock();
                String dtq = strArray[1];
                Date dtTrx = df.parse(dtq);
                trx.setTicklerID(strArray[0]);
                trx.setTicklerDT(dtTrx);
                trx.setTicklerOpen(Float.valueOf(strArray[2] + 0));
                trx.setTicklerHigh(Float.valueOf(strArray[3] + 0));
                trx.setTicklerLow(Float.valueOf(strArray[4] + 0));
                trx.setTicklerClose(Float.valueOf(strArray[5] + 0));
                trx.setTicklerVol(Float.valueOf(strArray[6]));
//                trx.setTicklerInt(Float.valueOf(strArray[7]));
                session.save(trx);

                for (int i = 0; i < 100000; i++) {
                    if (i % 50 == 0) { //20, same as the JDBC batch size
                        //flush a batch of inserts and release memory:
                        session.flush();
                        session.clear();
                    }
                }
            }
            session.getTransaction().commit();
            System.out.println("Save to DB Success");
            session.close();
        } catch (Exception e) {
            System.err.println(e);
        }

    }

    public ArrayList<Date> getDates() {
        return dates;
    }

    public ArrayList<Double> getOpens() {
        return opens;
    }

}
