package main.android.database.utils;

public class QueryAppender {

    /**
     * Método usado para concatenar trechos de sql.
     * A cada parâmetro passado, é adicionado um espaço e uma quebra de linha automaticamente.
     *
     * @param strs são os trechos de sql a serem concatenados
     *
     * @return todos os parâmetros concatenados ou vazio se não for passado nada ou for passado nulo
     */
    public static String append(String... strs){
        if(strs != null && strs.length > 0)
            return String.join(" \n", strs);
        return "";
    }

}
