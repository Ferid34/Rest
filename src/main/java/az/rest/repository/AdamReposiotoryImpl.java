package az.rest.repository;

import az.rest.Adam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
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
