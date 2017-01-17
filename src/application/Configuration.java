/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;
import org.apache.commons.dbcp.BasicDataSource;

/**
 *
 * @author tina
 */
public class Configuration {
    public static Connection con;

    public Configuration() {
        try {
            Properties props = new Properties();
            //FileInputStream fis = null;
            BasicDataSource ds = new BasicDataSource();
            
            String file = "/application/db.properties";
            InputStream fins = getClass().getResourceAsStream(file); 

            try {
                //fis = new FileInputStream("db.properties");
                //File file = new File(getClass().getResource(Config.PROPERTIES_FILE).getFile());
                
                //fis = new FileInputStream(file);
                //props.load(fis);
                if(fins!=null)
                props.load(fins);
                
            } catch (IOException e) {
                e.printStackTrace();
                //return null;
            }
            ds.setDriverClassName(props.getProperty("MYSQL_DB_DRIVER_CLASS"));
            ds.setUrl(props.getProperty("MYSQL_DB_URL"));//MYSQL_URL
            ds.setUsername(props.getProperty("MYSQL_DB_USERNAME"));
            ds.setPassword(props.getProperty("MYSQL_DB_PASSWORD"));
            //return ds.getConnection();
            this.con = ds.getConnection();
            System.out.println(con);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public static Connection getConnectionInstance() {
        if (con == null) {
            try {
            Properties props = new Properties();
            //FileInputStream fis = null;
            BasicDataSource ds = new BasicDataSource();
            
            String file = "/application/db.properties";
            InputStream fins = Configuration.class.getResourceAsStream(file); 

            try {
                //fis = new FileInputStream("db.properties");
                //File file = new File(getClass().getResource(Config.PROPERTIES_FILE).getFile());
                
                //fis = new FileInputStream(file);
                //props.load(fis);
                if(fins!=null)
                props.load(fins);
                
            } catch (IOException e) {
                e.printStackTrace();
                //return null;
            }
            ds.setDriverClassName(props.getProperty("MYSQL_DB_DRIVER_CLASS"));
            ds.setUrl(props.getProperty("MYSQL_DB_URL"));//MYSQL_URL
            ds.setUsername(props.getProperty("MYSQL_DB_USERNAME"));
            ds.setPassword(props.getProperty("MYSQL_DB_PASSWORD"));
            //return ds.getConnection();
            Configuration.con = ds.getConnection();
            System.out.println(con);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        }
        return con;
    }
    public static void main(String[] args) {
        Configuration c = new Configuration();
        
    }

}
