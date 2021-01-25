package main.android;

public abstract class ObservableValueChangedListener<T> implements TextWatcher{

    protected abstract T parse(String originalValue);

    public abstract void onValueChanged(T oldV, T newV);

    @Override
    public void onBeforeTextChanged(Object obj, String oldV, String newV) {
    }
    @Override
    public void onTextChange(Object obj, String oldV, String newV) {
    }
    @Override
    public void onAfterTextChanged(Object obj, String oldV, String newV) {
        onValueChanged(parse(oldV), parse(newV));
    }
}
