package main.android.database.sqliteadapter;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import main.android.database.namedparameters.NamedParametersMap;
import main.android.database.namedparameters.exceptions.NamedParameterNotFoundException;
import main.android.database.rowmapper.RowMapper;
import main.android.database.rowmapper.RowMapperHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AndroidDatabaseUtil {

    private SQLiteDatabase sqLiteDatabase;

    public AndroidDatabaseUtil(SQLiteDatabase sqLiteDatabase) {
        this.sqLiteDatabase = sqLiteDatabase;
    }

    public <T> List<T> getList(String sql, RowMapper<T> rowMapper){
        return getList(sql, (String[])null, rowMapper);
    }

    public <T> List<T> getList(String sql, String[] sqlArgs, RowMapper<T> rowMapper){
        try(Cursor cursor = sqLiteDatabase.rawQuery(sql, sqlArgs)) {
            if (cursor != null && cursor.moveToFirst()) {
                RowMapperHandler handler = new RowMapperHandler(cursor);
                List<T> list = new ArrayList<>();
                do {
                    list.add(rowMapper.onMap(cursor.getPosition(), cursor, handler));
                } while (cursor.moveToNext());
                return list;
            }
        }
        return new ArrayList<>();
    }

    public <T> List<T> getList(String sql, NamedParametersMap namedParams, RowMapper<T> rowMapper)
            throws NamedParameterNotFoundException{
        List<Object> outArgs = new ArrayList<>();
        String parsedSql = parseNamedParameters(sql, namedParams, outArgs);
        String[] sqlArgs = objectParamsToStringArgs(outArgs);
        return getList(parsedSql, sqlArgs, rowMapper);
    }

    public <T> T getFirstObject(String sql, RowMapper<T> rowMapper){
        return getFirstObject(sql, (String[])null, rowMapper);
    }

    public <T> T getFirstObject(String sql, String[] sqlArgs, RowMapper<T> rowMapper){
        try(Cursor cursor = sqLiteDatabase.rawQuery(sql, sqlArgs)) {
            if (cursor != null && cursor.moveToFirst()) {
                RowMapperHandler handler = new RowMapperHandler(cursor);
                return rowMapper.onMap(cursor.getPosition(), cursor, handler);
            }
        }
        return null;
    }

    public <T> T getFirstObject(String sql, NamedParametersMap namedParams, RowMapper<T> rowMapper)
            throws NamedParameterNotFoundException{
        List<Object> outArgs = new ArrayList<>();
        String parsedSql = parseNamedParameters(sql, namedParams, outArgs);
        String[] sqlArgs = objectParamsToStringArgs(outArgs);
        return getFirstObject(parsedSql, sqlArgs, rowMapper);
    }

    public <T> T getLastObject(String sql, RowMapper<T> rowMapper){
        return getFirstObject(sql, (String[])null, rowMapper);
    }

    public <T> T getLastObject(String sql, String[] sqlArgs, RowMapper<T> rowMapper){
        try(Cursor cursor = sqLiteDatabase.rawQuery(sql, sqlArgs)) {
            if (cursor != null && cursor.moveToLast()) {
                RowMapperHandler handler = new RowMapperHandler(cursor);
                return rowMapper.onMap(cursor.getPosition(), cursor, handler);
            }
        }
        return null;
    }

    public <T> T getLastObject(String sql, NamedParametersMap namedParams, RowMapper<T> rowMapper)
            throws NamedParameterNotFoundException{
        List<Object> outArgs = new ArrayList<>();
        String parsedSql = parseNamedParameters(sql, namedParams, outArgs);
        String[] sqlArgs = objectParamsToStringArgs(outArgs);
        return getLastObject(parsedSql, sqlArgs, rowMapper);
    }

    private String[] objectParamsToStringArgs(List<Object> objectParams){
        String[] array = new String[objectParams.size()];
        for(int i = 0, size = objectParams.size(); i < size; i++){
            array[i] = SqlValueParser.parse(objectParams.get(i));
        }
        return array;
    }

    /**
     *  Método que converte uma sql com parâmetros nomeados em uma
     *  sql com parâmetros não nomeados, inserindo os parâmetros ordenadamente
     *  na lista recebida por parâmetro.
     *
     * @param sql é a sql base com os named parameters.
     *            Ex.: SELECT * FROM TABLE WHERE FIELD = #FIELDNAME
     * @param parameters é o objeto que contém os parâmtros nomeados.
     *                   Atenção: todos os parâmetros existentes na sql,
     *                   deverão conter no mapa, se faltar algum parâmetro,
     *                   é lançado uma {@link NamedParameterNotFoundException}.
     *
     * @param outArgs é a lista onde são inseridos os argumentos ordenadamente
     *
     * @return a sql com parâmetros não nomeados.
     * Ex.: SELECT * FROM TABLE WHERE FIELD = ?
     *
     * @throws NamedParameterNotFoundException se houver um parâmetro nomeado
     * na sql que não houver no mapa e parâmetros
     */
    private String parseNamedParameters(String sql, NamedParametersMap parameters, List<Object> outArgs)
            throws NamedParameterNotFoundException {
        if(sql == null || sql.trim().isEmpty())
            return "";

        String sqlResult = sql;

        Matcher m = Pattern.compile("[#]\\w+").matcher(sql);

        while (m.find()) {
            String parameter = m.group();
            String parameterKey = parameter.substring(1);

            if(parameters.containsKey(parameterKey)){
                Object value = parameters.getValue(parameterKey);
                sqlResult = sqlResult.replaceFirst(parameter, "?");
                outArgs.add(value);
            }else{
                throw new NamedParameterNotFoundException("Parameter '" + parameterKey + "' was not found in: " + parameters);
            }
        }
        return sqlResult;
    }



}
