package main.android.database.namedparameters;


import main.android.database.sqliteadapter.SqlValueParser;

import java.util.HashMap;
import java.util.Map;

public class NamedParametersMap {

    private Map<String, Object> parameters;

    public NamedParametersMap(Map<String, Object> initialValues) {
        this();
        if(initialValues != null && !initialValues.isEmpty())
            parameters.putAll(initialValues);
    }

    public NamedParametersMap(String key, Object value) {
        this();
        put(key, value);
    }

    public NamedParametersMap() {
        this.parameters = new HashMap<>();
    }

    public NamedParametersMap put(String key, Object value){
        if(key != null && !key.trim().isEmpty() && value != null)
            this.parameters.put(key, value);
        return this;
    }

    public NamedParametersMap remove(String key){
        if(key != null && !key.trim().isEmpty() && parameters.containsKey(key))
            this.parameters.remove(key);
        return this;
    }

    public boolean containsKey(String key){
        return key != null && this.parameters.containsKey(key);
    }

    public Object getValue(String key){
        return containsKey(key) ? this.parameters.get(key) : null;
    }

    @Override
    public String toString() {
        return parameters.toString();
    }

    public String toSqlValuesString(boolean formated){
        if(formated){
            StringBuilder sb = new StringBuilder();
            sb.append("{").append("\n");
            for(Map.Entry<String, Object> entry : parameters.entrySet()){
                String key = entry.getKey();
                String value = SqlValueParser.parse(entry.getValue());
                String type = entry.getValue() != null ? entry.getValue().getClass().getTypeName() : "null";
                sb.append("\t").append("\"").append(key).append("\"").append(" (").append(type).append(")").append(" : ").append(value).append("\n");
            }
            sb.append("}").append("\n");
            return sb.toString();
        }else{
            Map<String, String> map = new HashMap<>(parameters.size());
            for(Map.Entry<String, Object> entry : parameters.entrySet()){
                map.put(entry.getKey(), SqlValueParser.parse(entry.getValue()));
            }
            return map.toString();
        }
    }
}
