package az.rest.repository;

public class SQLQuery {
    public static final String GET_ALL_ADAMS = " select  id,name,age from adam where status=1 ";
    public static final String GET_ADAM_BY_ADAM_ID = "  select  id,name,age from adam where status=1 and id=? ";
    public static final String UPDATE_ADAM_BY_ADAM_ID = "  update adam set name=?  , age=? where id =? ";
    public static final String INSERT_ADAM = "  insert into adam (name,age) values(?,?) ";
    public static final String DELETE_ADAM = "  update adam set status=0 where id =? ";




}
