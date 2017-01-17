/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.ResultSet;

/**
 *
 * @author /dev/null
 */
public class PeopleController extends model.People{
    String[] fields = {"name", "age", "company"};
    private String name;
    
    private String age;
    private String company;

    public PeopleController() {
        setTable(table);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
    
    /*
    * * -- Override super class (DNFrameworkSQLAdapter) methods --
    */
    
    public void insert(String data[][]){
        add(data);
    }
    
    public void insert(String values[]){
        String data[][] = {fields,values};
        add(data);
    }
    public void update(String data[][],int id){
        update(table, data, id);
    }
    
    public void update(String[][] data, String field, String value){
        update(table, data, field, value);
    }
    
    public void update(String[] values,int id){
        String data[][] = {fields,values};
        update(table, data, id);
    }
    
    public void update(String[] values, String field, String value){
        String data[][] = {fields,values};
        update(table, data, field, value);
    }
    
    public dnframework.DNFrameworkSQLAdapter set(String[] Values){
        return set(fields, Values);
    }
    
    public void delete(int id){
        delete(table, id);
    }
    
    public void delete(String field, String value){
        delete(table, field, value);
        
        
    }
    
    
    public ResultSet getRows(){
        return get(fields);
    }
    
}
