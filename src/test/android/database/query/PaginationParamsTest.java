package test.android.database.query;

import main.android.database.query.PaginationParams;
import org.junit.Assert;
import org.junit.Test;

public class PaginationParamsTest {

    @Test
    public void test1(){
        PaginationParams params = new PaginationParams();
        params.setLimit(10);
        params.setPageNumber(1);
        Assert.assertEquals(10, params.getLimit());
        Assert.assertEquals(1, params.getPageNumber());
    }
    @Test
    public void test2(){
        PaginationParams params = new PaginationParams();
        params.setLimit(0);
        params.setPageNumber(0);
        Assert.assertEquals(0, params.getLimit());
        Assert.assertEquals(0, params.getPageNumber());
    }
    @Test
    public void test3(){
        PaginationParams params = new PaginationParams();
        params.setLimit(-1);
        params.setPageNumber(-1);
        Assert.assertEquals(0, params.getLimit());
        Assert.assertEquals(0, params.getPageNumber());
    }
    @Test
    public void test4(){
        PaginationParams params = new PaginationParams()
                .setLimit(-1)
                .setPageNumber(-1);
        Assert.assertEquals(0, params.getLimit());
        Assert.assertEquals(0, params.getPageNumber());
    }
    @Test
    public void test5(){
        PaginationParams params = new PaginationParams(10, 1);
        Assert.assertEquals(10, params.getLimit());
        Assert.assertEquals(1, params.getPageNumber());
    }
    @Test
    public void test6(){
        PaginationParams params = new PaginationParams(0, 0);
        Assert.assertEquals(0, params.getLimit());
        Assert.assertEquals(0, params.getPageNumber());
    }
    @Test
    public void test7(){
        PaginationParams params = new PaginationParams(-1, -1);
        Assert.assertEquals(0, params.getLimit());
        Assert.assertEquals(0, params.getPageNumber());
    }
}
