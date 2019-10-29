package az.rest.repository;

public class SQLQuery {
    public static final String GET_ALL_ADAMS= " select  id,name,age from adam where status=1 ";
    public static final String GET_ADAM_BY_ADAM_ID= "  select  id,name,age from adam where status=1 and id=? ";


   ;
}
