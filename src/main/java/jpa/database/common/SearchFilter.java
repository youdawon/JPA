package jpa.database.common;

import lombok.Data;

import java.util.HashMap;

@Data
public class SearchFilter {

    private HashMap<String, Object> searchMap = new HashMap<>();

    public void addFilter(String key, Object value) {
        this.searchMap.put(key, value);
    }

    public Object getFilterValue(String key){

        Object value = null;

        if(!searchMap.isEmpty() && searchMap.get(key) != null){
            return searchMap.get(key);
        }
        return value;
    }
}
