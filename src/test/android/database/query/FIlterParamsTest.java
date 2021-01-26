package test.android.database.query;

import main.android.database.query.FilterParams;
import org.junit.Assert;
import org.junit.Test;

public class FIlterParamsTest {

    @Test
    public void get1Test(){
        FilterParams params = new FilterParams();
        Assert.assertEquals(params.get(null), null);
    }
    @Test
    public void get2Test(){
        FilterParams params = new FilterParams();
        Assert.assertEquals(params.get(""), null);
    }
    @Test
    public void get3Test(){
        FilterParams params = new FilterParams();
        params.put("TESTE", 1);
        Assert.assertEquals(params.get("TESTE"), 1);
    }
    @Test
    public void get4Test(){
        FilterParams params = new FilterParams();
        params.put("TESTE", "1");
        Assert.assertEquals(params.get("TESTE"), "1");
    }
    @Test
    public void get5Test(){
        FilterParams params = new FilterParams();
        params.put("TESTE", 4.5D);
        Assert.assertEquals(params.get("TESTE"), 4.5D);
    }
    @Test
    public void get6Test(){
        FilterParams params = new FilterParams();
        params.put("TESTE", 4.5f);
        Assert.assertEquals(params.get("TESTE"), 4.5f);
    }

    @Test
    public void getOrDefault1Test(){
        FilterParams params = new FilterParams();
        Assert.assertEquals(params.getOrDefault("TESTE", null), null);
    }
    @Test
    public void getOrDefault2Test(){
        FilterParams params = new FilterParams();
        Assert.assertEquals(params.getOrDefault("TESTE", 1), 1);
    }
    @Test
    public void getOrDefault3Test(){
        FilterParams params = new FilterParams();
        Assert.assertEquals(params.getOrDefault("TESTE", "1"), "1");
    }
    @Test
    public void getOrDefault4Test(){
        FilterParams params = new FilterParams();
        params.put("TESTE", 1);
        Assert.assertEquals(params.getOrDefault("TESTE", 2), 1);
    }
}
