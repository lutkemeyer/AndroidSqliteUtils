package main.android.value;

import java.util.ArrayList;
import java.util.List;

public class ChangeableValue<T> {

    private T value;
    private List<OnValueChangeListener<T>> listeners = new ArrayList<>();

    public ChangeableValue(OnValueChangeListener<T> listener){
        addListener(listener);
    }
    public ChangeableValue(T value) {
        set(value);
    }
    public ChangeableValue() {
    }

    private void notifyListeners(T oldValue, T newValue){
        for(OnValueChangeListener<T> listener : listeners){
            listener.onValueChanged(oldValue, newValue);
        }
    }
    public ChangeableValue<T> addListener(OnValueChangeListener<T> listener){
        if(listener != null)
            this.listeners.add(listener);
        return this;
    }
    public ChangeableValue<T> removeListener(OnValueChangeListener<T> listener){
        if(listener != null)
            this.listeners.remove(listener);
        return this;
    }
    public ChangeableValue<T> removeAllListeners(){
        this.listeners.clear();
        return this;
    }
    public T get() {
        return value;
    }
    public T getOrDefault(T defaultValue){
        return value != null ? value : defaultValue;
    }
    public boolean isNull(){
        return value == null;
    }
    public ChangeableValue<T> set(T value) {
        T oldValue = this.value;
        this.value = value;
        notifyListeners(oldValue, value);
        return this;
    }

    @Override
    public String toString() {
        return "ChangeableValue{" + value + '}';
    }
}
