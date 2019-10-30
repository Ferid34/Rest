package az.rest.repository;

import az.rest.domain.Adam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;
import java.util.Optional;

@Repository
public class AdamReposiotoryImpl implements AdamRepository {


    private AdamRowMapper adamRowMapper = new AdamRowMapper();


    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public List<Adam> getAllAdams() {
        List<Adam> adamList = jdbcTemplate.query(SQLQuery.GET_ALL_ADAMS, adamRowMapper);
        return adamList;
    }

    @Override
    public Optional<Adam> getAdamById(long id) {
        Object[] objects = new Object[]{id};
        Optional<Adam> optionalAdam = Optional.empty();
        List<Adam> adamList = jdbcTemplate.query(SQLQuery.GET_ADAM_BY_ADAM_ID, objects, adamRowMapper);
        if (!adamList.isEmpty()) {
            optionalAdam = Optional.of(adamList.get(0));
        }
        return optionalAdam;
    }

    @Override
    public boolean updateAdamById(Adam adam) {
        Object[] objects=new Object[]{adam.getName(),adam.getAge(),adam.getId()};
       int count=jdbcTemplate.update(SQLQuery.UPDATE_ADAM_BY_ADAM_ID,objects);
        return count>0;
    }

    @Override
    public boolean deleteAdamById(long id) {
        Object[] objects=new Object[]{id};
       int count=jdbcTemplate.update(SQLQuery.DELETE_ADAM,objects);
        return count>0;
    }

    @Override
    public Adam insertAdam(Adam adam) {
        KeyHolder keyHolder=new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement=connection.prepareStatement(SQLQuery.INSERT_ADAM, Statement.RETURN_GENERATED_KEYS);
            //insert into adam (name,age) values(?,?)
            preparedStatement.setString(1,adam.getName());
            preparedStatement.setLong(2,adam.getAge());
            return  preparedStatement;
        },keyHolder);
        adam.setId(keyHolder.getKey().longValue());
        return adam;
    }

    @Override
    public int getAdamCount() {
        return    jdbcTemplate.queryForObject(SQLQuery.GET_ADAM_COUNT,Integer.class);

    }


    private class AdamRowMapper implements RowMapper<Adam> {

        @Override
        public Adam mapRow(ResultSet rs, int i) throws SQLException {
            Adam adam = new Adam();
            adam.setId(rs.getLong("id"));
            adam.setName(rs.getString("name"));
            adam.setAge(rs.getLong("age"));
            return adam;
        }
    }


}
