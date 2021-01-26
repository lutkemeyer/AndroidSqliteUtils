package test.android.value;

import main.android.value.IntegerObservableValueChangedListener;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

public class IntegerObservableValueChangedListenerTest {

    private final int[] bd = new int[]{0, 0};
    IntegerObservableValueChangedListener listener = (oldV, newV) -> {
        bd[0] = oldV;
        bd[1] = newV;
    };

    @Before
    public void beforeMethod(){
        bd[0] = 0;
        bd[1] = 0;
    }

    @Test
    public void onValueChanged1Test(){
        listener.onAfterTextChanged(null, null, null);
        Assert.assertEquals(0, bd[0]);
        Assert.assertEquals(0, bd[1]);
    }
    @Test
    public void onValueChanged2Test(){
        listener.onAfterTextChanged(null, null, "");
        Assert.assertEquals(0, bd[0]);
        Assert.assertEquals(0, bd[1]);
    }
    @Test
    public void onValueChanged3Test(){
        listener.onAfterTextChanged(null, null, "1");
        Assert.assertEquals(0, bd[0]);
        Assert.assertEquals(1, bd[1]);
    }
    @Test
    public void onValueChanged4Test(){
        listener.onAfterTextChanged(null, "0", "1");
        Assert.assertEquals(0, bd[0]);
        Assert.assertEquals(1, bd[1]);
    }
    @Test
    public void onValueChanged5Test(){
        listener.onAfterTextChanged(null, "0.0", "1.0");
        Assert.assertEquals(0, bd[0]);
        Assert.assertEquals(1, bd[1]);
    }
    @Test
    public void onValueChanged6Test(){
        listener.onAfterTextChanged(null, "0.1", "1.1");
        Assert.assertEquals(0, bd[0]);
        Assert.assertEquals(1, bd[1]);
    }
    @Test
    public void onValueChanged7Test(){
        listener.onAfterTextChanged(null, "0,1", "1,1");
        Assert.assertEquals(0, bd[0]);
        Assert.assertEquals(1, bd[1]);
    }
    @Test
    public void onValueChanged8Test(){
        listener.onAfterTextChanged(null, "01", "001");
        Assert.assertEquals(1, bd[0]);
        Assert.assertEquals(1, bd[1]);
    }

}
