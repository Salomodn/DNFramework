/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dnframework.QueryBuilder;
import java.sql.Connection;

/**
 *
 * @author /dev/null
 */
public class People extends dnframework.DNFrameworkTable {

    protected String table = "people";

    public People(Connection con) {
        super(con);
    }

    public People(QueryBuilder builder, Connection con) {
        super(builder, con);

    }

    public People() {

    }

    public void add(String[][] data) {
        insert(table, data);
    }


}
