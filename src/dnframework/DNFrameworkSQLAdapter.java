/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dnframework;

import application.Configuration;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
//import model.Authenticator;

/**
 *
 * @author tina
 */
public class DNFrameworkSQLAdapter implements DNFrameworkSQLInterface {

    protected Connection con;
    protected QueryBuilder builder;
    public String sql = "";
    String table = "";
    private boolean isAuthentic;
    String category,email,username;
    

    public DNFrameworkSQLAdapter() {
//        this.con = new application.Configuration().con;
        this.con = Configuration.getConnectionInstance();
    }
    
    public DNFrameworkSQLAdapter(String table) {
        this.table = table;
//        this.con = new application.Configuration().con;
        this.con = Configuration.getConnectionInstance();
    }

    public void setTable(String table) {
        this.table = table;
    }
    
    public String createQuery(){
    return sql;}
    
    public String getQuery(){
    return sql;}

    public boolean isIsAuthentic() {
        return isAuthentic;
    }

    public String getCategory() {
        return category;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }
    
    
    
    

    public DNFrameworkSQLAdapter(Connection con) {
        this.con = new application.Configuration().con;
    }

    public DNFrameworkSQLAdapter(QueryBuilder builder,Connection con) {
        this.con = con;
        this.builder = builder;
    }
    
    public DNFrameworkSQLAdapter(QueryBuilder builder) {
        this.builder = builder;
    }
    
    

    @Override
    public void insert(String table,String[][] data) {
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
                String query = "INSERT INTO " + table + "(" + fields + ") VALUES (" + values + ")";
                System.out.println(query);
                con.createStatement().executeUpdate(query);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void insert(String query) {
        try {
            System.out.println(query);
            //sJOptionPane.showMessageDialog(null, query);
            con.createStatement().executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(DNFrameworkSQLAdapter.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void update(String table,String[][] data, int id) {
        try {
            String input = "";
            if (data[0].length > 0) {
                StringBuilder sb = new StringBuilder();
                sb.append("`");
                sb.append(data[0][0]);
                for (int i = 1; i < data[0].length; i++) {
                    sb.append("` = ?,`");
                    sb.append(data[0][i]);
                }
                sb.append("`");
                input = sb.toString();
            }
            //return input;
            //String query = createUpdateQuery(table, Fields, position);
//        String query = "UPDATE `" + table + "` SET " +
//                input;
            StringBuilder queryBuilder = new StringBuilder("UPDATE `" + table + "` SET " +
                    input).append(" = ?").append(" WHERE `").append("id").append("`= ?");
            String query = queryBuilder.toString();
            System.out.println("Query = " + query);
            //Connection con = new DBConnection().getConnection();
            PreparedStatement ps = con.prepareStatement(query);
            int intVal = 0;
            String stringVal = "";
            try {
                for (int i = 1; i <= data[1].length; i++) {
                    if ((data[0][i - 1]).matches("^(-?)\\d+$")) {
                        int numParam = Integer.parseInt(data[1][i - 1]);
                        intVal = Integer.parseInt(data[1][i - 1]);
                        ps.setInt(i, numParam);
                        ps.setInt(i + 1, intVal);
                        //JOptionPane.showMessageDialog(null, i + ", " + numParam);
                    } else if (!(data[0][i - 1]).matches("^(-?)\\d+$")) {
                        String stringParam = data[1][i - 1];
                        stringVal = String.valueOf(id);
                        ps.setString(i, stringParam);
                        ps.setString(i + 1, stringVal);
                        // JOptionPane.showMessageDialog(null, i + ", " + stringParam);
                    }
                }
                ps.executeUpdate();
                
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
//                ps.close();
//                con.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DNFrameworkSQLAdapter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(String table,String[][] data, String field, String value) {
        
        try {
            String input = "";
            if (data[0].length > 0) {
                StringBuilder sb = new StringBuilder();
                sb.append("`");
                sb.append(data[0][0]);
                for (int i = 1; i < data[0].length; i++) {
                    sb.append("` = ?,`");
                    sb.append(data[0][i]);
                }
                sb.append("`");
                input = sb.toString();
            }
            //return input;
            //String query = createUpdateQuery(table, Fields, position);
//        String query = "UPDATE `" + table + "` SET " +
//                input;
            StringBuilder queryBuilder = new StringBuilder("UPDATE `" + table + "` SET " +
                    input).append(" = ?").append(" WHERE `").append(field).append("`= ?");
            String query = queryBuilder.toString();
            System.out.println("Query = " + query);
            //Connection con = new DBConnection().getConnection();
            PreparedStatement ps = con.prepareStatement(query);
            int intVal = 0;
            String stringVal = "";
            try {
                for (int i = 1; i <= data[1].length; i++) {
                    if ((data[0][i - 1]).matches("^(-?)\\d+$")) {
                        int numParam = Integer.parseInt(data[1][i - 1]);
                        intVal = Integer.parseInt(data[1][i - 1]);
                        ps.setInt(i, numParam);
                        ps.setInt(i + 1, intVal);
                        //JOptionPane.showMessageDialog(null, i + ", " + numParam);
                    } else if (!(data[0][i - 1]).matches("^(-?)\\d+$")) {
                        String stringParam = data[1][i - 1];
                        stringVal = String.valueOf(value);
                        ps.setString(i, stringParam);
                        ps.setString(i + 1, stringVal);
                        // JOptionPane.showMessageDialog(null, i + ", " + stringParam);
                    }
                }
                ps.executeUpdate();
                
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
//                ps.close();
//                con.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DNFrameworkSQLAdapter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void update(String query) {
        try {
            System.out.println(query);
            //sJOptionPane.showMessageDialog(null, query);
            con.createStatement().executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(DNFrameworkSQLAdapter.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void delete(String table,int id) {
        String output = "DELETE FROM `" + table + "` WHERE ";
        
        StringBuilder sb = new StringBuilder();
            sb.append("`");
            sb.append("id");
            sb.append("`");
            output += sb.toString();
        
        String queryBuilder = output + " = '"+String.valueOf(id)+"'";
        String query = queryBuilder;

        output = query;
        System.err.println(query);
        try {
            int updateCount = con.createStatement().executeUpdate(query);
            if(updateCount ==0)
            System.err.println("record not deleted");
            else
            if(updateCount >0)
            System.out.println("record  deleted");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String table,String field, String value) {
      String output = "DELETE FROM `" + table + "` WHERE ";
        
        StringBuilder sb = new StringBuilder();
            sb.append("`");
            sb.append(field);
            sb.append("`");
            output += sb.toString();
        
        String queryBuilder = output + " = '"+value+"'";
        String query = queryBuilder;

        output = query;
        System.err.println(query);
        try {
            int updateCount = con.createStatement().executeUpdate(query);
            if(updateCount ==0)
            System.err.println("record not deleted");
            else
            if(updateCount >0)
            System.out.println("record  deleted");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    
    //-----------------------------------------------------------------------------
    public DNFrameworkSQLAdapter insert(){
        sql =  "INSERT ";
        return this;
    }
    
    public DNFrameworkSQLAdapter into(String[] Fields){
        //Fields Variables
                String fields = "";
                if (Fields.length > 0) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("`");
                    sb.append(Fields[0]);
                    for (int i = 1; i < Fields.length; i++) {
                        sb.append("`,`");
                        sb.append(Fields[i]);
                    }
                    sb.append("`");
                    fields = sb.toString();
                }
        sql +=  "INTO " + table + "(" + fields + ") " ;
        return this;
    }
    
    
    
    public DNFrameworkSQLAdapter values(String[] Values){
        //Values Variables
                String values = "";
                if (Values.length > 0) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("\'");
                    sb.append(Values[0]);
                    for (int i = 1; i < Values.length; i++) {
                        sb.append("','");
                        sb.append(Values[i]);
                    }
                    sb.append("\'");
                    values = sb.toString();
                }
        sql +=  "VALUES (" + values + ")";
        return this;
    }
    public DNFrameworkSQLAdapter update(){
        sql =  "UPDATE "+ table +" ";
        return this;
    }
    
    
    public DNFrameworkSQLAdapter set(String[] Fields, String[] Values){
        String output = "";
        if (Fields.length > 0) {
            StringBuilder sb = new StringBuilder();
            for (int counter = 0; counter < Values.length; counter++) {
                String temp = "`" + Fields[counter] + "`" + " = \'" + Values[counter] + "\'";
                sb.append(temp);
                sb.append(",");

            }

            output = sb.toString();
        }

        output = output.substring(0, output.length() - 1) + ' ';
        sql +=  "SET "+output;
        return this;
    }
    
    
    public DNFrameworkSQLAdapter commit(){
        try {
            con.createStatement().executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }
    
    
    public DNFrameworkSQLAdapter select(){
        sql =  "SELECT * FROM "+table;
        return this;
    }
    
    public DNFrameworkSQLAdapter select(String subString){
        sql =  "SELECT "+subString+" FROM "+table;
        return this;
    }
    public DNFrameworkSQLAdapter select(String[] fields){
        sql =  "SELECT ";
        //Fields Variables
                if (fields.length > 0) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("`");
                    sb.append(fields[0]);
                    for (int i = 1; i < fields.length; i++) {
                        sb.append("`,`");
                        sb.append(fields[i]);
                    }
                    sb.append("`");
                    sb.append(" FROM ");
                    sb.append("`");
                    sb.append(table);
                    sb.append("`");
                   sql +=   sb.toString();
                }
        return this;
    }
    public DNFrameworkSQLAdapter from(String table){
        sql += new StringBuilder(" FROM ").append("`").append(table).append("`").toString();
        return this;
    }
    public DNFrameworkSQLAdapter where(String subString){
        sql += " WHERE "+subString;
        return this;
    }
    
    public DNFrameworkSQLAdapter where(String[] Fields,Object ... Values) {
        String output = "";
        if (Fields.length != Values.length) {
            System.err.println("Data does not match");
        } else {
            output = " WHERE ";
        if (Fields.length > 1) {
            StringBuilder sb = new StringBuilder();
            for (int counter = 0; counter < Values.length; counter++) {
                String temp = "`" + Fields[counter] + "`" + " = \'" + Values[counter] + "\'";
                sb.append(temp);
                sb.append(" AND ");

            }

            output += sb.toString();
            output = output.substring(0, output.length() - 5) + ' ';
        } else if (Fields.length == 1) {
            StringBuilder sb = new StringBuilder();
            for (int counter = 0; counter < Values.length; counter++) {
                String temp = "`" + Fields[counter] + "`" + " = \'" + Values[counter] + "\'";
                sb.append(temp);
                sb.append(",");

            }

            output += sb.toString();
            output = output.substring(0, output.length() - 1) + ' ';
        }

        
        //return output;
        }
        sql += output;
        return this;
    }
    
    
    public DNFrameworkSQLAdapter where(String[][] data) {
        String output = "";
        if (data[0].length != data[1].length) {
            System.err.println("Data does not match");
        } else {
            output = " WHERE ";
        if (data[0].length > 1) {
            StringBuilder sb = new StringBuilder();
            for (int counter = 0; counter < data[1].length; counter++) {
                String temp = "`" + data[0][counter] + "`" + " = \'" + data[1][counter] + "\'";
                sb.append(temp);
                sb.append(" AND ");

            }

            output += sb.toString();
            output = output.substring(0, output.length() - 5) + ' ';
        } else if (data[0].length == 1) {
            StringBuilder sb = new StringBuilder();
            for (int counter = 0; counter < data[1].length; counter++) {
                String temp = "`" + data[0][counter] + "`" + " = \'" + data[1][counter] + "\'";
                sb.append(temp);
                sb.append(",");

            }

            output += sb.toString();
            output = output.substring(0, output.length() - 1) + ' ';
        }

        
        //return output;
        }
        sql += output;
        return this;
    }
    
    public DNFrameworkSQLAdapter or(String subString){
        sql += " OR "+subString;
        return this;
    }
    
    public DNFrameworkSQLAdapter or(String[][] data) {
        
        String output = "";
        if (data[0].length != data[1].length) {
            System.err.println("Data does not match");
        } else {
            output = " AND ";
        if (data[0].length > 1) {
            StringBuilder sb = new StringBuilder();
            for (int counter = 0; counter < data[1].length; counter++) {
                String temp = "`" + data[0][counter] + "`" + " = \'" + data[1][counter] + "\'";
                sb.append(temp);
                sb.append(" AND ");

            }

            output += sb.toString();
            output = output.substring(0, output.length() - 5) + ' ';
        } else if (data[0].length == 1) {
            StringBuilder sb = new StringBuilder();
            for (int counter = 0; counter < data[1].length; counter++) {
                String temp = "`" + data[0][counter] + "`" + " = \'" + data[1][counter] + "\'";
                sb.append(temp);
                sb.append(",");

            }

            output += sb.toString();
            output = output.substring(0, output.length() - 1) + ' ';
        }

        
        //return output;
        }
        sql+=output;
        return this;
    }

    public DNFrameworkSQLAdapter and(String query) {
        
        sql += query;
        return this;
    }
    
    public DNFrameworkSQLAdapter and(String[][] data) {
        String output = "";
        if (data[0].length != data[1].length) {
            System.err.println("Data does not match");
        } else {
            output = " AND ";
        if (data[0].length == 1) {
            StringBuilder sb = new StringBuilder();
            for (int counter = 0; counter < data[1].length; counter++) {
                String temp = "`" + data[0][counter] + "`" + " = \'" + data[1][counter] + "\'";
                sb.append(temp);
                sb.append(",");

            }

            output += sb.toString();
            output = output.substring(0, output.length() - 1) + ' ';
        }

        
        //return output;
        }
        sql += output;
        return this;
    }
    
    public DNFrameworkSQLAdapter orderBy(String subString){
        sql += " ORDER BY `"+subString+"`";
        return this;
    }
    
    public DNFrameworkSQLAdapter ASC(){
        sql += " ASC";
        return this;
    }
    
    public DNFrameworkSQLAdapter DESC(){
        sql += " DESC";
        return this;
    }
    
    
    
    public DNFrameworkSQLAdapter authenticate(String sqlQuery){
        boolean answer = false;
        ResultSet rs;
        try {
            int count = 0;
            System.out.println(sqlQuery);
            rs = con.createStatement().executeQuery(sqlQuery);
            while (rs.next()) {
                count++;
                category = rs.getString("category");
                //email = rs.getString("username");
                username = rs.getString("username");
            }
            if (count == 0) {
                JOptionPane.showMessageDialog(null, "Access Denied. Invalid Username or Password", "System Message", JOptionPane.ERROR_MESSAGE);
            } else if (count == 1) {
                answer = true;
                isAuthentic = answer;
               // System.out.println("Counter :" + count);
                
            } else if (count > 1) {
                JOptionPane.showMessageDialog(null, "Duplicate Users", "System Message", JOptionPane.ERROR_MESSAGE);
            }
        } catch (HeadlessException | SQLException e) {
            e.printStackTrace();
        }
        return this;
    }
    
    public static TableModel rsToTableModel(ResultSet rs) {
        try {
            ResultSetMetaData metaData = rs.getMetaData();
            int i = metaData.getColumnCount();
            Vector v1 = new Vector();
            for (int j = 0; j < i; j++) {
                v1.addElement(metaData.getColumnLabel(j + 1));
            }
            Vector v2 = new Vector();
            while (rs.next()) {
                Vector v3 = new Vector();
                for (int k = 1; k <= i; k++) {
                    v3.addElement(rs.getObject(k));
                }
                v2.addElement(v3);
            }
            return new DefaultTableModel(v2, v1);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public static List rsToNestedList(ResultSet paramResultSet) {
        try {
            ResultSetMetaData localResultSetMetaData = paramResultSet.getMetaData();
            int i = localResultSetMetaData.getColumnCount();
            ArrayList localArrayList1 = new ArrayList();
            for (int j = 0; j < i; j++) {
                localArrayList1.add(localResultSetMetaData.getColumnLabel(j + 1));
            }
            ArrayList localArrayList2 = new ArrayList();
            while (paramResultSet.next()) {
                ArrayList localArrayList3 = new ArrayList();
                for (int k = 1; k <= i; k++) {
                    localArrayList3.add(paramResultSet.getObject(k));
                }
                localArrayList2.add(localArrayList3);
            }
            return localArrayList2;
        } catch (Exception localException) {
            localException.printStackTrace();
        }
        return null;
    }
    
    
    
    public ResultSet get(String[] fields) {
        String input = "SELECT ";
        if (fields.length > 0) {
            StringBuilder sb = new StringBuilder();
            sb.append("`");
            sb.append(fields[0]);
            for (int i = 1; i < fields.length; i++) {
                sb.append("`");
                sb.append(",");
                sb.append("`");
                sb.append(fields[i]);

            }
            sb.append("`");
            input += sb.toString()+" FROM `"+table+"`";
        }
        System.out.println(input); 
        try {
            return con.createStatement().executeQuery(input);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;//To change body of generated methods, choose Tools | Templates.
    }
    
    public ResultSet get(String query) {
        System.out.println(query); 
        try {
            return con.createStatement().executeQuery(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;//To change body of generated methods, choose Tools | Templates.
    }
    
    public List<String> get(String query,String columnLabel) {
        List<String> list = new ArrayList();
        System.out.println(query); 
        try {
            ResultSet rs = con.createStatement().executeQuery(query);
            while(rs.next()){
                list.add(rs.getString(columnLabel));
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;//To change body of generated methods, choose Tools | Templates.
    }
    
    

}
