package test.android.value;

import main.android.value.BigDecimalObservableValueChangedListener;
import org.junit.*;

import java.math.BigDecimal;

public class BigDecimalObservableValueChangedListenerTest {

    private final BigDecimal[] bd = new BigDecimal[]{null, null};
    BigDecimalObservableValueChangedListener listener = (oldV, newV) -> {
        bd[0] = oldV;
        bd[1] = newV;
    };

    @Before
    public void beforeMethod(){
        bd[0] = null;
        bd[1] = null;
    }

    @Test
    public void onValueChanged1Test(){
        listener.onAfterTextChanged(null, null, null);
        Assert.assertEquals(BigDecimal.ZERO, bd[0]);
        Assert.assertEquals(BigDecimal.ZERO, bd[1]);
    }
    @Test
    public void onValueChanged2Test(){
        listener.onAfterTextChanged(null, null, "");
        Assert.assertEquals(BigDecimal.ZERO, bd[0]);
        Assert.assertEquals(BigDecimal.ZERO, bd[1]);
    }
    @Test
    public void onValueChanged3Test(){
        listener.onAfterTextChanged(null, null, "1");
        Assert.assertEquals(BigDecimal.ZERO, bd[0]);
        Assert.assertEquals(BigDecimal.ONE.setScale(1), bd[1]);
    }
    @Test
    public void onValueChanged4Test(){
        listener.onAfterTextChanged(null, "0", "1");
        Assert.assertEquals(BigDecimal.ZERO.setScale(1), bd[0]);
        Assert.assertEquals(BigDecimal.ONE.setScale(1), bd[1]);
    }
    @Test
    public void onValueChanged5Test(){
        listener.onAfterTextChanged(null, "0.0", "1.0");
        Assert.assertEquals(BigDecimal.ZERO.setScale(1), bd[0]);
        Assert.assertEquals(BigDecimal.ONE.setScale(1), bd[1]);
    }
    @Test
    public void onValueChanged6Test(){
        listener.onAfterTextChanged(null, "0.1", "1.1");
        Assert.assertEquals(BigDecimal.valueOf(0.1), bd[0]);
        Assert.assertEquals(BigDecimal.valueOf(1.1), bd[1]);
    }
    @Test
    public void onValueChanged7Test(){
        listener.onAfterTextChanged(null, "0,1", "1,1");
        Assert.assertEquals(BigDecimal.valueOf(0.1), bd[0]);
        Assert.assertEquals(BigDecimal.valueOf(1.1), bd[1]);
    }
}
