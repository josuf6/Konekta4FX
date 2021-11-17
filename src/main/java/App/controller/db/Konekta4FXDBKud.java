package App.controller.db;

import App.models.TablaAmaiera;

import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public class Konekta4FXDBKud {

   private static final Konekta4FXDBKud instantzia = new Konekta4FXDBKud();

   public static  Konekta4FXDBKud getInstantzia(){
       return instantzia;
   }

   public List<TablaAmaiera> getPuntuazioak(){
        String query = "select winner, count(winner) as ganadas from puntuazioak group by winner order by ganadas desc;"; //se podrían sacar más datos de la partida como el rival o la fecha
        ResultSet rs = DBKud.getDBKud().execSQL(query);
        List<TablaAmaiera> emaitza = new ArrayList<>();

        try {
            while (rs.next()) {
                String izena = rs.getString("winner");
                Integer puntuak = rs.getInt("ganadas");
                TablaAmaiera t = new TablaAmaiera(izena, puntuak);
                emaitza.add(t);
            }
        } catch(SQLException throwables){
            throwables.printStackTrace();
        }

        return emaitza;
    }

    public void insertPartida(String winner, String loser, int moves, String tiempo){
        String query = "insert into puntuazioak (winner, loser, moves, tiempo, data) values ('" +winner+ "','" +loser+ "',"+moves+",'"+tiempo+"',datetime('now','localtime'))";
        DBKud.getDBKud().execSQL(query);
    }

}
