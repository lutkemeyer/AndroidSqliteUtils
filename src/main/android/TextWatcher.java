package main.android;

public interface TextWatcher {
    void onBeforeTextChanged(Object obj, String oldV, String newV);
    void onTextChange(Object obj, String oldV, String newV);
    void onAfterTextChanged(Object obj, String oldV, String newV);
}
