package main.android.value;

public interface OnValueChangeListener<T> {
    void onValueChanged(T oldValue, T newValue);
}

