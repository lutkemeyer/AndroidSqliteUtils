package main.android.value;

import main.android.TextWatcher;

public interface ObservableValueChangedListener<T> extends TextWatcher {

    T parse(String originalValue);

    void onValueChanged(T oldV, T newV);

    @Override
    default void onBeforeTextChanged(Object obj, String oldV, String newV) {
    }
    @Override
    default void onTextChange(Object obj, String oldV, String newV) {
    }
    @Override
    default void onAfterTextChanged(Object obj, String oldV, String newV) {
        onValueChanged(parse(oldV), parse(newV));
    }
}
