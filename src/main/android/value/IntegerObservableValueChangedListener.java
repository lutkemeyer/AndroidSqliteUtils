package main.android.value;

public interface IntegerObservableValueChangedListener extends ObservableValueChangedListener<Integer> {
    @Override
    default Integer parse(String originalValue) {
        try{
            if(originalValue != null && originalValue.contains(","))
                originalValue = originalValue.substring(0, originalValue.indexOf(","));
            if(originalValue != null && originalValue.contains("."))
                originalValue = originalValue.substring(0, originalValue.indexOf("."));
            return Integer.valueOf(originalValue);
        }catch (Exception e){
            return 0;
        }
    }
}
