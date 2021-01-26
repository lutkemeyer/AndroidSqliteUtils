package main.android.database.query;

import java.util.HashMap;
import java.util.Map;

public class FilterParams {

    private Map<String, Object> params = new HashMap<>();

    public FilterParams() {
    }

    public Object get(String key){
        return params.get(key);
    }

    public Object getOrDefault(String key, Object defaultValue){
        if(params.containsKey(key))
            return params.get(key);
        return defaultValue;
    }

    public FilterParams put(String key, Object value){
        params.put(key, value);
        return this;
    }
}
