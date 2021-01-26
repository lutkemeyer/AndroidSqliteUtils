package main.android.value;

import java.math.BigDecimal;

public interface BigDecimalObservableValueChangedListener extends ObservableValueChangedListener<BigDecimal> {
    @Override
    default BigDecimal parse(String originalValue) {
        try{
            if(originalValue != null && originalValue.contains(","))
                originalValue = originalValue.replace(",", ".");
            return BigDecimal.valueOf(Double.parseDouble(originalValue));
        }catch (Exception e){
            return BigDecimal.ZERO;
        }
    }
}
