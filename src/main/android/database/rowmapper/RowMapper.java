package main.android.database.rowmapper;

import android.database.Cursor;

public interface RowMapper<T> {
    T onMap(int index, Cursor cursor, RowMapperHandler handler);
}
