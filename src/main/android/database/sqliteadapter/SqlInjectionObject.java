package main.android.database.sqliteadapter;

public class SqlInjectionObject implements ISqlParameterValue{

    private StringBuilder sb = new StringBuilder();

    public SqlInjectionObject() {
    }
    public SqlInjectionObject(String initialValue){
        append(initialValue);
    }

    public SqlInjectionObject append(String sqlInjection){
        if(sqlInjection != null){
            if(sb.length() > 0){
                sb.append(" \n").append(sqlInjection);
            }else{
                sb.append(sqlInjection);
            }
        }
        return this;
    }

    @Override
    public String toSqlParameterValueString() {
        return sb.toString();
    }
}
