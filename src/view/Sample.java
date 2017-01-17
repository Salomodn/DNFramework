/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;


import java.sql.ResultSet;

/**
 *
 * @author /dev/null
 */
public class Sample {

    public static void main(String[] args) {
//        add();
//        update();
//        delete();
        showRecords();
    }

    private static void add() {
        controller.PeopleController controller = new controller.PeopleController();
        String name = "Salomon";
        String age = "24";
        String company = "Google";
        String[] values = {name,
            age,
            company};
        controller.insert(values);

    }

    private static void update() {
        controller.PeopleController controller = new controller.PeopleController();
        String[] Fields = {"name", "age", "company"};
        String name = "Salomodn";
        String age = "24";
        String company = "Google";
        String[] Values = {name,
            age,
            company};
        String query = controller.update().set(Fields, Values).where("`name` = 'Salomon'").createQuery();

        controller.update(query);
    }

    private static void delete() {
        controller.PeopleController controller = new controller.PeopleController();
        controller.delete(2);
    }

    private static void showRecords() {
        ResultSet rs = new controller.PeopleController().getRows();
        try {
            while (rs.next()) {
                String name = rs.getString("name");
                String age = rs.getString("age");
                String company = rs.getString("company");
                System.out.println("Name: "+name+"\n"+
                        "Age: "+age+"\n"+
                        "Company: "+company+"\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
