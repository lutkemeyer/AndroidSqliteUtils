package main.android;

import main.android.value.BindableValueChangedListener;
import main.android.value.ChangeableValue;

public class Main {
    public static void main(String[] args) {

        new BindableValueChangedListener<String>() {
            @Override
            public String parse(String originalValue) {
                return null;
            }
        }.bind(new ChangeableValue<>());

    }
}
