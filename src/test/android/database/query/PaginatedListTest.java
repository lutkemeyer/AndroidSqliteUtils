package test.android.database.query;

import main.android.database.query.PaginatedList;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class PaginatedListTest {

    @Test
    public void test1(){
        PaginatedList<String> paginatedList = new PaginatedList<>();
        paginatedList.setTotalCount(20);
        paginatedList.setContentList(Arrays.asList("Guilherme", "Lesley"));
        Assert.assertEquals(20, paginatedList.getTotalCount());
        Assert.assertNotNull(paginatedList.getContentList());
        Assert.assertEquals(2, paginatedList.getContentList().size());
        Assert.assertEquals("Guilherme", paginatedList.getContentList().get(0));
    }
    @Test
    public void test2(){
        PaginatedList<String> paginatedList = new PaginatedList<>(20, Arrays.asList("Guilherme", "Lesley"));
        Assert.assertEquals(20, paginatedList.getTotalCount());
        Assert.assertNotNull(paginatedList.getContentList());
        Assert.assertEquals(2, paginatedList.getContentList().size());
        Assert.assertEquals("Guilherme", paginatedList.getContentList().get(0));
    }
    @Test
    public void test3(){
        PaginatedList<String> paginatedList = new PaginatedList<String>()
                .setTotalCount(20)
                .setContentList(Arrays.asList("Guilherme", "Lesley"));
        
        Assert.assertEquals(20, paginatedList.getTotalCount());
        Assert.assertNotNull(paginatedList.getContentList());
        Assert.assertEquals(2, paginatedList.getContentList().size());
        Assert.assertEquals("Guilherme", paginatedList.getContentList().get(0));
    }

    @Test
    public void totalCountTest1(){
        PaginatedList<String> paginatedList = new PaginatedList<>();
        paginatedList.setTotalCount(-1);
        Assert.assertEquals(0, paginatedList.getTotalCount());
    }
    @Test
    public void totalCountTest2(){
        PaginatedList<String> paginatedList = new PaginatedList<>();
        paginatedList.setTotalCount(0);
        Assert.assertEquals(0, paginatedList.getTotalCount());
    }
    @Test
    public void totalCountTest3(){
        PaginatedList<String> paginatedList = new PaginatedList<>();
        paginatedList.setTotalCount(1);
        Assert.assertEquals(1, paginatedList.getTotalCount());
    }
    @Test
    public void totalCountTest4(){
        PaginatedList<String> paginatedList = new PaginatedList<>();
        paginatedList.setTotalCount(1);
        Assert.assertEquals(1, paginatedList.getTotalCount());
    }
    @Test
    public void totalCountTest5(){
        PaginatedList<String> paginatedList = new PaginatedList<>();
        Assert.assertEquals(0, paginatedList.getTotalCount());
    }

    @Test
    public void contentListTest1(){
        PaginatedList<String> paginatedList = new PaginatedList<>();
        paginatedList.setContentList(Arrays.asList("Guilherme", "Lesley"));
        Assert.assertNotNull(paginatedList.getContentList());
        Assert.assertEquals(2, paginatedList.getContentList().size());
        Assert.assertEquals("Guilherme", paginatedList.getContentList().get(0));
        Assert.assertEquals("Lesley", paginatedList.getContentList().get(1));
    }
    @Test
    public void contentListTest2(){
        PaginatedList<String> paginatedList = new PaginatedList<>();
        paginatedList.setContentList(new ArrayList<>());
        Assert.assertNotNull(paginatedList.getContentList());
        Assert.assertEquals(0, paginatedList.getContentList().size());
    }
    @Test
    public void contentListTest3(){
        PaginatedList<String> paginatedList = new PaginatedList<>();
        paginatedList.setContentList(null);
        Assert.assertNotNull(paginatedList.getContentList());
        Assert.assertEquals(0, paginatedList.getContentList().size());
    }
    @Test
    public void contentListTest4(){
        PaginatedList<String> paginatedList = new PaginatedList<>();
        Assert.assertNotNull(paginatedList.getContentList());
        Assert.assertEquals(0, paginatedList.getContentList().size());
    }
}
