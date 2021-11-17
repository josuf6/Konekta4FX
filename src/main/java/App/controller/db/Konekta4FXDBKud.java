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
        String query = "select winner, count(winner) as ganadas from puntuazioak order by ganadas desc limit 10"; //se podrían sacar más datos de la partida como el rival o la fecha
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

    public void insertPartida(String winner, String loser, int moves, String tiempo){
        String query = "insert into puntuazioak (winner, loser, moves, tiempo, data) values ('" +winner+ "','" +loser+ "'," +moves+ ","+tiempo+",datetime('now','localtime'))";
        DBKud.getDBKud().execSQL(query);
    }

}
