package main.android.database.utils;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.sun.istack.internal.NotNull;
import main.android.database.namedparameters.NamedParametersMap;
import main.android.database.namedparameters.NamedParametersSqlUtils;
import main.android.database.namedparameters.exceptions.NamedParameterNotFoundException;
import main.android.database.utils.rowmapper.RowMapper;
import main.android.database.utils.rowmapper.RowMapperHandler;

import java.util.ArrayList;
import java.util.List;

public class AndroidDatabaseUtil {

    private SQLiteDatabase sqLiteDatabase;

    public AndroidDatabaseUtil(@NotNull SQLiteDatabase sqLiteDatabase) {
        this.sqLiteDatabase = sqLiteDatabase;
    }

    public <T> List<T> getList(String sql, RowMapper<T> rowMapper){
        return getList(sql, (String[])null, rowMapper);
    }

    public <T> List<T> getListFromNamedParametersSql(String sql, NamedParametersMap parametersMap, RowMapper<T> rowMapper){
        try {
            List<Object> outArgs = new ArrayList<>();
            String parsedSql = NamedParametersSqlUtils.parseNamedParameters(sql, parametersMap, outArgs);
            String[] paramList = new String[outArgs.size()];
            for(int i = 0, size = outArgs.size(); i < size; i++){
                paramList[i] = ValueParseUtils.parse(outArgs.get(i));
            }
            return getList(parsedSql, paramList, rowMapper);
        } catch (NamedParameterNotFoundException e) {
            e.printStackTrace();
        }
        return new ArrayList<T>();
    }

    public <T> List<T> getList(String sql, String[] sqlArgs, RowMapper<T> rowMapper){
        List<T> list = new ArrayList<>();
        if(sqLiteDatabase == null || rowMapper == null)
            return list;
        Cursor cursor = sqLiteDatabase.rawQuery(sql, sqlArgs);
        if(cursor != null && cursor.moveToFirst()){
            RowMapperHandler handler = new RowMapperHandler(cursor);
            do {
                list.add(rowMapper.onMap(cursor.getPosition(), cursor, handler));
            }while(cursor.moveToNext());
            cursor.close();
        }
        return list;
    }

    public <T> T getFirstObject(String sql, RowMapper<T> rowMapper){
        return getFirstObject(sql, null, rowMapper);
    }

    public <T> T getFirstObject(String sql, String[] sqlArgs, RowMapper<T> rowMapper){
        if(sqLiteDatabase == null || rowMapper == null)
            return null;
        T object = null;
        Cursor cursor = sqLiteDatabase.rawQuery(sql, sqlArgs);
        if(cursor != null && cursor.moveToFirst()){
            RowMapperHandler handler = new RowMapperHandler(cursor);
            object = rowMapper.onMap(cursor.getPosition(), cursor, handler);
            cursor.close();
        }
        return object;
    }

    public <T> T getLastObject(String sql, RowMapper<T> rowMapper){
        return getFirstObject(sql, null, rowMapper);
    }

    public <T> T getLastObject(String sql, String[] sqlArgs, RowMapper<T> rowMapper){
        if(sqLiteDatabase == null || rowMapper == null)
            return null;
        Cursor cursor = sqLiteDatabase.rawQuery(sql, sqlArgs);
        if(cursor != null && cursor.moveToLast()){
            RowMapperHandler handler = new RowMapperHandler(cursor);
            T object = rowMapper.onMap(cursor.getPosition(), cursor, handler);
            cursor.close();
            return object;
        }
        return null;
    }

}
