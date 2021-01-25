package main.android.database.rowmapper;


import android.database.Cursor;
import main.android.database.utils.rowmapper.RowMapperHandler;

public interface RowMapper<T> {
    T onMap(int index, Cursor cursor, RowMapperHandler handler);
}
