/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dnframework;

import java.sql.SQLException;

/**
 *
 * @author tina
 */
public class QueryBuilder {
    String sql = "";

    public QueryBuilder() {
    }
    
    public QueryBuilder select(String subString) {
        sql+= "SELECT "+ subString;
        return this; //To change body of generated methods, choose Tools | Templates.
    }
    
    public QueryBuilder select() {
        sql = "SELECT * FROM ";
        return this; //To change body of generated methods, choose Tools | Templates.
    }
    
    public QueryBuilder where(String subString) {
        sql+= " WHERE "+subString;
        return this; //To change body of generated methods, choose Tools | Templates.
    }
    
    
    public QueryBuilder insert(String table,String[][] data) {
        try {

            //String table = "person";
            System.err.println(data[1].length);
            if (data[0].length < data[1].length) {
                System.err.println("values do not match");
            } else {
                //Fields Variables
                String fields = "";
                if (data[0].length > 0) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("`");
                    sb.append(data[0][0]);
                    for (int i = 1; i < data[0].length; i++) {
                        sb.append("`,`");
                        sb.append(data[0][i]);
                    }
                    sb.append("`");
                    fields = sb.toString();
                }

                //Values Variables
                String values = "";
                if (data[1].length > 0) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("\'");
                    sb.append(data[1][0]);
                    for (int i = 1; i < data[1].length; i++) {
                        sb.append("','");
                        sb.append(data[1][i]);
                    }
                    sb.append("\'");
                    values = sb.toString();
                }
                //SQL Query
                sql = "INSERT INTO " + table + "(" + fields + ") VALUES (" + values + ")";
                

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }
    
    
    public DNFrameworkSQLAdapter build() {
            return new DNFrameworkSQLAdapter(this,new application.Configuration().con);
        }
    public dnframework.DNFrameworkTable commit(){
    return new dnframework.DNFrameworkTable(this);
    }
}
