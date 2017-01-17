/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dnframework;

import java.sql.Connection;

/**
 *
 * @author tina
 */
public class DNFrameworkTable extends DNFrameworkSQLAdapter{
    String table;

    public DNFrameworkTable() {
        //super(null);
    }

    public DNFrameworkTable(String table) {
        this.table = table;
    }
    
    
    

    public DNFrameworkTable(Connection con) {
        super(con);
    }
    
    public DNFrameworkTable(String table,Connection con) {
        super(con);
        this.table = table;
    }

    public DNFrameworkTable(QueryBuilder builder, Connection con) {
        super(builder, con);
    }
    
    public DNFrameworkTable(QueryBuilder builder) {
        super(builder);
    }
    
    

    @Override
    public void delete(String table, String field, String value) {
        super.delete(table, field, value); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(String table, int id) {
        super.delete(table, id); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(String table, String[][] data, String field, String value) {
        super.update(table, data, field, value); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(String table, String[][] data, int id) {
        super.update(table, data, id); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void insert(String table, String[][] data) {
        super.insert(table, data); //To change body of generated methods, choose Tools | Templates.
    }
    
    //-------------------------------------------------------
    @Override
    public DNFrameworkSQLAdapter where(String subString) {
        return super.where(subString); //To change body of generated methods, choose Tools | Templates.
    }
    

    
    
}
