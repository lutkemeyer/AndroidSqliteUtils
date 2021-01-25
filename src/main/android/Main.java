package main.android;

import android.database.sqlite.SQLiteDatabase;
import main.android.database.namedparameters.NamedParametersMap;
import main.android.database.utils.AndroidDatabaseUtil;
import main.android.database.utils.rowmapper.RowMapper;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        SQLiteDatabase database = null;
        /*
        RowMapper<Carro> rowMapper = (index, cursor, handler) -> {
            Carro carro = new Carro();
            carro.setModelo(cursor.getString(cursor.getColumnIndex("MODELO")));
            carro.setPlaca(cursor.getString(cursor.getColumnIndex("PLACA")));
            return carro;
        };

        AndroidSqliteUtil util = new AndroidSqliteUtil(database);
        List<Carro> carros = util.getList("SELECT * FROM CARRO", rowMapper);
        */


        RowMapper<Integer> rowMapper = (index, cursor, handler) -> {
            return 1;
        };

        int count = new AndroidDatabaseUtil(database).getFirstObject("SELECT COUNT(*) FROM TABELA", rowMapper);








        String sql =
                "SELECT * " +
                "FROM TABELA " +
                "WHERE CAMPO = #CODIGO AND LISTA IN(#LISTA)";

        NamedParametersMap parameters = new NamedParametersMap()
                .put("CODIGO", 5)
                .put("LABEL", "A")
                .put("LISTA", Arrays.asList("A", "B", "C"));

        System.out.println(parameters.toStringValues(true));
/*
        try {

            System.out.println( NamedParametersSqlUtils.substituteNamedParameters(sql, parameters) );

        } catch (NamedParameterNotFoundException e) {
            e.printStackTrace();
        }
*/

    }


    public static class Carro {

        private String modelo;
        private String placa;

        public Carro() {
            this(null, null);
        }

        public Carro(String modelo, String placa) {
            this.modelo = modelo;
            this.placa = placa;
        }

        public String getModelo() {
            return modelo;
        }

        public void setModelo(String modelo) {
            this.modelo = modelo;
        }

        public String getPlaca() {
            return placa;
        }

        public void setPlaca(String placa) {
            this.placa = placa;
        }
    }
}
