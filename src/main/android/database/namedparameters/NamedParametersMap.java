package main.android.database.namedparameters;

import main.android.database.utils.SqlValueParseUtils;
import main.android.database.utils.ValueParseUtils;

import java.util.HashMap;
import java.util.Map;

public class NamedParametersMap {

    private Map<String, NamedParameterMapObject> parameters;

    public NamedParametersMap(Map<String, Object> initialValues) {
        this();
        if(initialValues != null && !initialValues.isEmpty()){
            for(Map.Entry<String, Object> entry : initialValues.entrySet()){
                parameters.put(entry.getKey(), new NamedParameterMapObject(entry.getValue()));
            }
        }
    }

    public NamedParametersMap(String key, Object value, boolean injectInSql){
        this();
        put(key, value, injectInSql);
    }

    public NamedParametersMap(String key, Object value) {
        this(key, value, false);
    }

    public NamedParametersMap() {
        this.parameters = new HashMap<>();
    }

    public NamedParametersMap put(String key, Object value){
        return put(key, value, false);
    }

    public NamedParametersMap put(String key, Object value, boolean isSqlInjection){
        if(key != null && !key.trim().isEmpty())
            this.parameters.put(key, new NamedParameterMapObject(value, isSqlInjection));
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

    public NamedParameterMapObject getValue(String key){
        return containsKey(key) ? this.parameters.get(key) : null;
    }

    @Override
    public String toString() {
        return parameters.toString();
    }

    public String toStringValues(){
        return toStringValues(false);
    }
    public String toStringValues(boolean formated){
        if(formated){
            StringBuilder sb = new StringBuilder();
            sb.append("{").append("\n");
            for(Map.Entry<String, NamedParameterMapObject> entry : parameters.entrySet()){
                NamedParameterMapObject objectValue = entry.getValue();
                String key = entry.getKey();
                String stringValue = objectValue.isSqlInjection() ? ValueParseUtils.parse(objectValue.value) : SqlValueParseUtils.parse(objectValue.value);
                String type = objectValue.value != null ? objectValue.value.getClass().getTypeName() : "null";
                sb.append("\t").append("\"").append(key).append("\"").append(" (").append(type).append(")").append(" : ").append(stringValue).append("\n");
            }
            sb.append("}").append("\n");
            return sb.toString();
        }else{
            Map<String, String> map = new HashMap<>(parameters.size());
            for(Map.Entry<String, NamedParameterMapObject> entry : parameters.entrySet()){
                map.put(entry.getKey(), SqlValueParseUtils.parse(entry.getValue().value));
            }
            return map.toString();
        }
    }

    public class NamedParameterMapObject{

        private Object value;
        private boolean isSqlInjection;

        public NamedParameterMapObject(Object value, boolean isSqlInjection) {
            this.value = value;
            this.isSqlInjection = isSqlInjection;
        }

        public NamedParameterMapObject(Object value) {
            this(value, false);
        }

        public Object getValue() {
            return value;
        }

        public void setValue(Object value) {
            this.value = value;
        }

        public boolean isSqlInjection() {
            return isSqlInjection;
        }

        public void setSqlInjection(boolean sqlInjection) {
            this.isSqlInjection = sqlInjection;
        }
    }

}
