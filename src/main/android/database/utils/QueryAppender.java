package main.android.database.utils;

public class QueryAppender {

    public static String append(String... strs){
        return String.join(" \n", strs);
    }

}
