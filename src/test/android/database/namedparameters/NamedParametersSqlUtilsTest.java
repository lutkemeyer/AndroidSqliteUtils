package test.android.database.namedparameters;

import main.android.database.namedparameters.NamedParametersMap;
import main.android.database.namedparameters.NamedParametersSqlUtils;
import main.android.database.namedparameters.exceptions.NamedParameterNotFoundException;
import org.junit.Assert;
import org.junit.Test;

public class NamedParametersSqlUtilsTest {
    @Test
    public void substituteNamedParameters1Test(){

        String sql = "SELECT * FROM TABELA WHERE CAMPO = #CAMPO";

        NamedParametersMap map = new NamedParametersMap();
        map.put("CAMPO", "Guilherme");

        try {
            String parsedSql = NamedParametersSqlUtils.substituteNamedParameters(sql, map);
            Assert.assertEquals("SELECT * FROM TABELA WHERE CAMPO = \"Guilherme\"", parsedSql);
        } catch (NamedParameterNotFoundException e) {
            e.printStackTrace();
            Assert.fail();
        }
    }
    @Test
    public void substituteNamedParameters2Test(){

        String sql = "SELECT * FROM TABELA WHERE CAMPO = #CAMPO1";

        NamedParametersMap map = new NamedParametersMap();
        map.put("CAMPO1", "Teste 1");
        map.put("CAMPO2", "Teste 2");

        try {
            String parsedSql = NamedParametersSqlUtils.substituteNamedParameters(sql, map);
            Assert.assertEquals("SELECT * FROM TABELA WHERE CAMPO = \"Teste 1\"", parsedSql);
        } catch (NamedParameterNotFoundException e) {
            e.printStackTrace();
            Assert.fail();
        }
    }
    @Test
    public void substituteNamedParameters3Test(){

        String sql = "SELECT * FROM TABELA WHERE CAMPO = #CAMPO1";

        NamedParametersMap map = new NamedParametersMap();

        try {
            NamedParametersSqlUtils.substituteNamedParameters(sql, map);
            Assert.fail();
        } catch (NamedParameterNotFoundException e) {
            Assert.assertNotNull(e);
            Assert.assertEquals("Parameter 'CAMPO1' was not found in: {}", e.getMessage());
        } catch (Exception e){
            Assert.fail();
        }
    }
    @Test
    public void substituteNamedParameters4Test(){

        String sql = "SELECT * FROM TABELA WHERE CAMPO IN(#SUBSELECT1)";
        String subselect = "SELECT NOME FROM TABELA";

        NamedParametersMap map = new NamedParametersMap();
        map.put("SUBSELECT1", subselect);

        try {
            String parsedSql = NamedParametersSqlUtils.substituteNamedParameters(sql, map);
            Assert.assertEquals("SELECT * FROM TABELA WHERE CAMPO IN(SELECT NOME FROM TABELA)", parsedSql);
        } catch (NamedParameterNotFoundException e) {
            e.printStackTrace();
            Assert.fail();
        }
    }
    @Test
    public void substituteNamedParameters5Test(){

        String sql = "SELECT * FROM TABELA WHERE CAMPO = #CAMPO1";

        NamedParametersMap map = new NamedParametersMap();
        map.put("INTEGER", 1);
        map.put("DOUBLE", 5.4d);
        map.put("STRING", "Guilherme");
        map.put("FLOAT", 3.4f);
        map.put("NULL", null);

        try {
            NamedParametersSqlUtils.substituteNamedParameters(sql, map);
            Assert.fail();
        } catch (NamedParameterNotFoundException e) {
            Assert.assertNotNull(e);
            Assert.assertEquals("Parameter 'CAMPO1' was not found in: {STRING=\"Guilherme\", FLOAT=3.4, NULL=null, DOUBLE=5.4, INTEGER=1}", e.getMessage());
        } catch (Exception e){
            Assert.fail();
        }
    }


}
