package main.android;

import java.math.BigDecimal;

public abstract class BigDecimalObservableValueChangedListener extends ObservableValueChangedListener<BigDecimal> {
    @Override
    public BigDecimal parse(String originalValue) {
        try{
            if(originalValue != null && originalValue.contains(","))
                originalValue = originalValue.replace(",", ".");
            return BigDecimal.valueOf(Double.parseDouble(originalValue));
        }catch (Exception e){
            return BigDecimal.ZERO;
        }
    }
}
