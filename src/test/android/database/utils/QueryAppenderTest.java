package test.android.database.utils;

import main.android.database.utils.QueryAppender;
import org.junit.Assert;
import org.junit.Test;

public class QueryAppenderTest {

    @Test
    public void append1Test(){
        String sql = QueryAppender.append("SELECT * FROM CARRO");
        Assert.assertEquals("SELECT * FROM CARRO", sql);
    }
    @Test
    public void append2Test(){
        String sql = QueryAppender.append(
            "SELECT",
            "*",
            "FROM",
            "CARRO"
        );
        Assert.assertEquals("SELECT \n* \nFROM \nCARRO", sql);
    }
    @Test
    public void append3Test(){
        String sql = QueryAppender.append(null);
        Assert.assertEquals("", sql);
    }
    @Test
    public void append4Test(){
        String sql = QueryAppender.append(new String[]{null, "SELECT"});
        Assert.assertEquals("null \nSELECT", sql);
    }
}
