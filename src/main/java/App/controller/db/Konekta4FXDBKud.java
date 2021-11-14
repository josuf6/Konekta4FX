package App.controller.db;

import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Konekta4FXDBKud {

   private static final Konekta4FXDBKud instantzia = new Konekta4FXDBKud();

   public static  Konekta4FXDBKud getInstantzia(){
       return instantzia;
   }

   public ArrayList<String> getPuntuazioak(){
        String query = "select winner, points from puntuazioak order by points desc limit 10";
        ResultSet rs = DBKud.getDBKud().execSQL(query);
        ArrayList<String> urlList = new ArrayList<>();
        if (rs != null) {
            try {
               while (rs.next()) {
                   String emaitza = rs.getString("winner") + " : " + rs.getString("points");
                   urlList.add(emaitza);
               }
           } catch(SQLException throwables){
               throwables.printStackTrace();
           }
        }
        return urlList;
    }

    public void insertPartida(String winner, String loser, int points){
        String query = "insert into puntuazioak (winner, loser, points) values ('" +winner+ "','" +loser+ "'," +points+ ")";
        DBKud.getDBKud().execSQL(query);
    }

}
