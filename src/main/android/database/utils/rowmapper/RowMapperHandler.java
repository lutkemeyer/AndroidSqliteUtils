package main.android.database.utils.rowmapper;


import android.database.Cursor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RowMapperHandler {

    private Cursor cursor;
    private List<String> columnNames;
    private int count;

    public RowMapperHandler(Cursor cursor) {
        this.cursor = cursor;
        this.columnNames = cursor != null ? Arrays.asList(cursor.getColumnNames()) : new ArrayList<>();
        this.count = cursor != null ? cursor.getCount() : 0;
    }

    public boolean containsColumn(String columnName){
        if(columnName == null)
            return false;
        return columnNames.contains(columnName);
    }

    public int getCount() {
        return count;
    }
}
