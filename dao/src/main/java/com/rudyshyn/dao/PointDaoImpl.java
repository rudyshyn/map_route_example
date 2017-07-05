package com.rudyshyn.dao;

import com.rudyshyn.model.Point;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.List;
import java.sql.ResultSet;
import javax.sql.DataSource;
import java.sql.SQLException;


public class PointDaoImpl implements PointDao {

    private JdbcTemplate jdbcTemplate;
    private  NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Value("${getPointsByAmenity.sql}")
    private String GET_POINTS_BY_AMENITY_SQL;
    @Value("${getPointsByTourism.sql}")
    private String GET_POINTS_BY_TOURISM_SQL;
    @Value("${getPointsByHistoric.sql}")
    private String GET_POINTS_BY_HISTORIC_SQL;
    @Value("${getPointsByShop.sql}")
    private String GET_POINTS_BY_SHOP_SQL;
    @Value("${getPointsByLeisure.sql}")
    private String GET_POINTS_BY_LEISURE_SQL;


    //  Не используются в работе. Служат для объединения проектов.

    public PointDaoImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public List<Point> getPointsByAmenity(String amenity) {
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource("amenity", amenity);
        return namedParameterJdbcTemplate.query(GET_POINTS_BY_AMENITY_SQL, sqlParameterSource, new PointRowMapper());
    }

    @Override
    public List<Point> getPointsByTourism(String tourism) {
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource("tourism", tourism);
        return namedParameterJdbcTemplate.query(GET_POINTS_BY_TOURISM_SQL, sqlParameterSource, new PointRowMapper());
    }

    @Override
    public List<Point> getPointsByHistoric(String historic) {
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource("historic", historic);
        return namedParameterJdbcTemplate.query(GET_POINTS_BY_HISTORIC_SQL, sqlParameterSource, new PointRowMapper());
    }

    @Override
    public List<Point> getPointsByShop(String shop) {
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource("shop", shop);
        return namedParameterJdbcTemplate.query(GET_POINTS_BY_SHOP_SQL, sqlParameterSource, new PointRowMapper());
    }

    @Override
    public List<Point> getPointsByLeisure(String leisure) {
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource("leisure", leisure);
        return namedParameterJdbcTemplate.query(GET_POINTS_BY_LEISURE_SQL, sqlParameterSource, new PointRowMapper());
    }

    private class PointRowMapper implements RowMapper<Point> {
        @Override
        public Point mapRow(ResultSet resultSet, int i) throws SQLException {
            Point TestPoint = new Point(
                    resultSet.getLong("point_id"),
                    resultSet.getLong("street_id"),
                    resultSet.getString("point_name"),
                    resultSet.getString("coords"),
                    resultSet.getString("amenity"));
            return TestPoint;
        }
    }
}
