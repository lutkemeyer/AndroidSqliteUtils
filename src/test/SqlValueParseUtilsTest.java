package test;

import main.android.database.utils.ISqlParameterValue;
import main.android.database.utils.SqlValueParseUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class SqlValueParseUtilsTest {

    @Test
    public void parseInt1Test(){
        Assert.assertEquals("1", SqlValueParseUtils.parse(1));
    }
    @Test
    public void parseInt2Test(){
        Assert.assertEquals("0", SqlValueParseUtils.parse(0));
    }
    @Test
    public void parseInt3Test(){
        Assert.assertEquals("-1", SqlValueParseUtils.parse(-1));
    }

    @Test
    public void parseFloat1Test(){
        Assert.assertEquals("1.0", SqlValueParseUtils.parse(1f));
    }
    @Test
    public void parseFloat2Test(){
        Assert.assertEquals("0.0", SqlValueParseUtils.parse(0f));
    }
    @Test
    public void parseFloat3Test(){
        Assert.assertEquals("-1.0", SqlValueParseUtils.parse(-1f));
    }

    @Test
    public void parseDouble1Test(){
        Assert.assertEquals("1.0", SqlValueParseUtils.parse(1d));
    }
    @Test
    public void parseDouble2Test(){
        Assert.assertEquals("0.0", SqlValueParseUtils.parse(0d));
    }
    @Test
    public void parseDouble3Test(){
        Assert.assertEquals("-1.0", SqlValueParseUtils.parse(-1d));
    }

    @Test
    public void parseList1Test(){
        List<String> list = Arrays.asList("1","2","3");
        Assert.assertEquals("\"1\", \"2\", \"3\"", SqlValueParseUtils.parse(list));
    }
    @Test
    public void parseList2Test(){
        List<Character> list = Arrays.asList('1','2','3');
        Assert.assertEquals("\"1\", \"2\", \"3\"", SqlValueParseUtils.parse(list));
    }
    @Test
    public void parseList3Test(){
        List<Integer> list = Arrays.asList(1,2,3);
        Assert.assertEquals("1, 2, 3", SqlValueParseUtils.parse(list));
    }
    @Test
    public void parseList4Test(){
        List<Object> list = Arrays.asList(1,"2",3);
        Assert.assertEquals("1, \"2\", 3", SqlValueParseUtils.parse(list));
    }
    @Test
    public void parseList5Test(){
        Assert.assertEquals("", SqlValueParseUtils.parse(new ArrayList()));
    }
    @Test
    public void parseList6Test(){
        Assert.assertEquals("", SqlValueParseUtils.parse((List)null));
    }
    @Test
    public void parseList7Test(){
        List<Integer> list = Arrays.asList(1,2,3,3);
        Assert.assertEquals("1, 2, 3", SqlValueParseUtils.parse(list));
    }

    @Test
    public void parseSet1Test(){
        Set<String> set = new HashSet<>(Arrays.asList("1","2","3"));
        Assert.assertEquals("\"1\", \"2\", \"3\"", SqlValueParseUtils.parse(set));
    }
    @Test
    public void parseSet2Test(){
        Set<Integer> set = new HashSet<>(Arrays.asList(1,2,3));
        Assert.assertEquals("1, 2, 3", SqlValueParseUtils.parse(set));
    }
    @Test
    public void parseSet3Test(){
        Assert.assertEquals("", SqlValueParseUtils.parse(new HashSet<>()));
    }
    @Test
    public void parseSet4Test(){
        Assert.assertEquals("", SqlValueParseUtils.parse((Set)null));
    }
    @Test
    public void parseSet5Test(){
        Set<Integer> set = new HashSet<>(Arrays.asList(1,2,3,3));
        Assert.assertEquals("1, 2, 3", SqlValueParseUtils.parse(set));
    }

    @Test
    public void parseArray1Test(){
        Object[] array = Arrays.asList(1,2,3).toArray();
        Assert.assertEquals("1, 2, 3", SqlValueParseUtils.parse(array));
    }
    @Test
    public void parseArray2Test(){
        Object[] array = Arrays.asList("1","2","3").toArray();
        Assert.assertEquals("\"1\", \"2\", \"3\"", SqlValueParseUtils.parse(array));
    }
    @Test
    public void parseArray3Test(){
        Assert.assertEquals("", SqlValueParseUtils.parse(new Object[]{}));
    }
    @Test
    public void parseArray4Test(){
        Object[] array = Arrays.asList(1,2,3,3).toArray();
        Assert.assertEquals("1, 2, 3", SqlValueParseUtils.parse(array));
    }

    @Test
    public void parseBoolean1Test(){
        Assert.assertEquals("1", SqlValueParseUtils.parse(true));
    }
    @Test
    public void parseBoolean2Test(){
        Assert.assertEquals("0", SqlValueParseUtils.parse(false));
    }

    @Test
    public void parseSqlParameterValue1Test(){
        List<ClassTest> list = Arrays.asList(new ClassTest("Test1"), new ClassTest("Test2"));
        Assert.assertEquals("\"Test1\", \"Test2\"", SqlValueParseUtils.parse(list));
    }

    @Test
    public void parseMap1Test(){
        Map<String, Integer> map = new HashMap<>();
        map.put("key1", 1);
        map.put("key2", 2);
        map.put("key3", 3);
        Assert.assertEquals("1, 2, 3", SqlValueParseUtils.parse(map));
    }
    @Test
    public void parseMap2Test(){
        Map<String, String> map = new HashMap<>();
        map.put("key1", "1");
        map.put("key2", "2");
        map.put("key3", "3");
        Assert.assertEquals("\"1\", \"2\", \"3\"", SqlValueParseUtils.parse(map));
    }
    @Test
    public void parseMap3Test(){
        Map<String, ClassTest> map = new HashMap<>();
        map.put("key1", new ClassTest("class1"));
        map.put("key2", new ClassTest("class2"));
        map.put("key3", new ClassTest("class3"));
        Assert.assertEquals("\"class1\", \"class2\", \"class3\"", SqlValueParseUtils.parse(map));
    }




    private static class ClassTest implements ISqlParameterValue {

        private String name;

        public ClassTest(String name) {
            this.name = name;
        }

        @Override
        public String toSqlParameterValueString() {
            return "\"" + name + "\"";
        }
    }

}
