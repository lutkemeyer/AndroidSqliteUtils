package main.android.value;

public abstract class BindableValueChangedListener<T> implements ObservableValueChangedListener<T> {

    private ChangeableValue<T> changeableValue;

    public BindableValueChangedListener() {
    }
    public BindableValueChangedListener(ChangeableValue<T> changeableValue) {
        bind(changeableValue);
    }

    @Override
    public void onValueChanged(T oldV, T newV) {
        if(changeableValue != null)
            changeableValue.set(newV);
    }

    public BindableValueChangedListener<T> bind(ChangeableValue<T> changeableValue){
        this.changeableValue = changeableValue;
        return this;
    }
}
