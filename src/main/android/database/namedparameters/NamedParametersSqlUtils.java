package main.android.database.namedparameters;

import main.android.database.namedparameters.exceptions.NamedParameterNotFoundException;
import main.android.database.sqliteadapter.SqlValueParser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NamedParametersSqlUtils {

    /**
     * Método que subsitui os parâmetros pelos valores na sql.
     *
     * @param sql é a sql base com named parameters
     * @param parameters é o mapa com os parâmetros nomeados
     *
     * @return a sql com os parâmetros substituidos
     * @throws NamedParameterNotFoundException se houver um parâmetro nomeado
     * na sql que não houver no mapa e parâmetros
     */
    public static String substituteNamedParameters(String sql, NamedParametersMap parameters)
            throws NamedParameterNotFoundException{
        if(sql == null || sql.trim().isEmpty())
            return "";

        String sqlResult = sql;

        Matcher m = Pattern.compile("[#]\\w+").matcher(sql);

        while (m.find()) {
            String parameter = m.group();
            String parameterKey = parameter.substring(1);

            if(parameters.containsKey(parameterKey)){
                Object value = parameters.getValue(parameterKey);
                sqlResult = sqlResult.replaceFirst(parameter, SqlValueParser.parse(value));
            }else{
                throw new NamedParameterNotFoundException("Parameter '" + parameterKey + "' was not found in: " + parameters);
            }
        }

        return sqlResult;
    }



}
