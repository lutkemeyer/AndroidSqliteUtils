package main.android.database.namedparameters;

import main.android.database.namedparameters.exceptions.ArgumentsListNullException;
import main.android.database.utils.SqlValueParseUtils;
import main.android.database.namedparameters.exceptions.NamedParameterNotFoundException;
import main.android.database.utils.ValueParseUtils;

import java.util.List;
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
                NamedParametersMap.NamedParameterMapObject value = parameters.getValue(parameterKey);
                if(value.isSqlInjection()){
                    sqlResult = sqlResult.replaceFirst(parameter, ValueParseUtils.parse(value.getValue()));
                }else{
                    sqlResult = sqlResult.replaceFirst(parameter, SqlValueParseUtils.parse(value.getValue()));
                }
            }else{
                throw new NamedParameterNotFoundException("Parameter '" + parameterKey + "' was not found in: " + parameters.toStringValues());
            }
        }
        return sqlResult;
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
     *
     * @throws ArgumentsListNullException se a lista de saída de argumentos
     * for nula
     */
    public static String parseNamedParameters(String sql, NamedParametersMap parameters, List<Object> outArgs)
            throws NamedParameterNotFoundException{
        if(sql == null || sql.trim().isEmpty())
            return "";

        String sqlResult = sql;

        Matcher m = Pattern.compile("[#]\\w+").matcher(sql);

        while (m.find()) {
            String parameter = m.group();
            String parameterKey = parameter.substring(1);

            if(parameters.containsKey(parameterKey)){
                NamedParametersMap.NamedParameterMapObject value = parameters.getValue(parameterKey);
                sqlResult = sqlResult.replaceFirst(parameter, "?");
                if(outArgs != null){
                    outArgs.add(value.getValue());
                }
            }else{
                throw new NamedParameterNotFoundException("Parameter '" + parameterKey + "' was not found in: " + parameters);
            }
        }
        return sqlResult;
    }

}
