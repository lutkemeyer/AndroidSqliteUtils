package main.android.database.sqliteadapter;

import java.util.*;

public class SqlValueParser {

    public static String parse(int value){
        return String.valueOf(value);
    }

    public static String parse(double value){
        return String.valueOf(value);
    }

    public static String parse(float value){
        return String.valueOf(value);
    }

    public static String parse(Object[] array){
        if(array == null || array.length == 0)
            return "";

        array = new HashSet<>(Arrays.asList(array)).toArray();

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < array.length - 1; i++){
            sb.append( parse( array[i] ) ).append(", ");
        }
        sb.append( parse( array[array.length-1] ) );

        return sb.toString();
    }

    /**
     * Método que transforma os valores da coleção passada por parâmetro.
     * Elimina valores repetidos
     *
     * @param collection
     * @param <T>
     * @return
     */
    public static <T> String parse(Collection<T> collection){
        if(collection == null)
            return "";
        return parse(collection.toArray());
    }

    public static <K,V> String parse(Map<K,V> map){
        if(map == null || map.isEmpty())
            return "";
        return parse(map.values());
    }

    public static String parse(boolean value){
        return value ? "1" : "0";
    }

    public static String nullValue(){
        return "null";
    }

    public static String parse(String value){
        if(value == null)
            return "\"\"";
        if(value.startsWith("\"") && value.endsWith("\""))
            return value;
        return "\"" + value + "\"";
    }

    public static String parse(Object value){
        if(value == null)
            return SqlValueParser.nullValue();
        if(value instanceof Boolean)
            return parse((boolean)value);
        if(value instanceof Integer)
            return parse((int)value);
        if(value instanceof Float)
            return parse((float)value);
        if(value instanceof Double)
            return parse((double)value);
        if(value instanceof Collection)
            return parse(((Collection)value).toArray());
        if(value instanceof CharSequence)
            return parse(value.toString());
        if(value instanceof Character)
            return parse(value.toString());
        if(value instanceof Map)
            return parse((Map)value);
        if(value instanceof ISqlParameterValue)
            return parse((ISqlParameterValue)value);
        return value.toString();
    }

    public static String parse(ISqlParameterValue value){
        if(value == null)
            return "";
        return value.toSqlParameterValueString();
    }
}
