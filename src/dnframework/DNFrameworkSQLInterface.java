/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dnframework;

/**
 *
 * @author tina
 */
public interface DNFrameworkSQLInterface {
    public  void insert(String table,String[][] data);
    public  void update(String table,String[][] data,int id);
    public  void update(String table,String[][] data,String field,String value);
    //public void update(String [][] data, Object ... params);
    public  void delete(String table,int id);
    public  void delete(String table,String field,String value);
}
