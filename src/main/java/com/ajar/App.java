package com.ajar;

import com.ajar.Gui.GuiDownload;
import static java.lang.System.console;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class App {

    /**
     * @param args
     */
    public static void main(String[] args) {
        
         try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            //UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        /* Turn off metal's use of bold fonts */
        UIManager.put("swing.boldMetal", Boolean.FALSE);
        
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                  new GuiDownload().setVisible(true);
            }
        });
//        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yy");
//
//        String dateInString = "15-Mar-16";
//        try {
//
//            Date date = df.parse(dateInString);
//            System.out.println(date);
//            System.out.println(df.format(date));
//
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
// Read data from DB
//        Session session = HibernateSessionManager.getSessionFactory().openSession();
//        session.beginTransaction();
//        System.out.println("Reading Data from Tickler !");
//        String HQL_QUERY = "from Tickler";
//        org.hibernate.Query query = session.createQuery(HQL_QUERY);
//        List list = query.list();
//
//        System.out.println("Retrieving " + list.size() + " Records:");
//        for (Iterator it = list.iterator(); it.hasNext();) {            
//            Tickler t = (Tickler) it.next();
//            System.out.println("----------");
//            System.out.println("ID:" + t.getTicklerID()+ "  Company Name : " + t.getTicklerName());            
//            System.out.println("----------");
//
//            //// Get STOCK data from Yahoo
//            GregorianCalendar start = new GregorianCalendar(2016, 2, 10);
//            GregorianCalendar end = new GregorianCalendar(2016, 2, 13);
//            StockDownloader test = new StockDownloader(t.getTicklerID(), start, end);
//                        
//    }
//        System.out.println("Done ------ **** ------ !!!");   
//        System.exit(0);
//    }
    }
}
