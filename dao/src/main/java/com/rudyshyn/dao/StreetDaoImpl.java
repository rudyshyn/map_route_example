package com.rudyshyn.dao;

import com.rudyshyn.model.Street;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.List;
import java.sql.ResultSet;
import javax.sql.DataSource;
import java.sql.SQLException;


public class StreetDaoImpl implements StreetDao {

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Value("${getStreetById.sql}")
    private String GET_STREET_BY_ID_SQL;
    @Value("${getStreetsByName.sql}")
    private String GET_STREETS_BY_NAME_SQL;
    @Value("${getRoute.sql}")
    private String GET_ROUTE_SQL;
    @Value("${getSearchSegment.sql}")
    private String SEARCH_SEGMENT_SQL;


    public StreetDaoImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public Street getStreetById(Long id) {
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource("id", id);
        return namedParameterJdbcTemplate.queryForObject(GET_STREET_BY_ID_SQL, sqlParameterSource, new StreetRowMapper());
    }

    @Override
    public List<Street> getStreetsByName(String name) {
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource("name", name);
        return namedParameterJdbcTemplate.query(GET_STREETS_BY_NAME_SQL, sqlParameterSource, new StreetRowMapper());
    }

    @Override
    public List<Street> getRouteByDijkstra(Long source, Long target) {
        MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
        sqlParameterSource.addValue("source", source);
        sqlParameterSource.addValue("target", target);
        return namedParameterJdbcTemplate.query(GET_ROUTE_SQL, sqlParameterSource, new StreetRowMapper());
    }

    @Override
    public Street searchSegment(String lon, String lat) {
        MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
        sqlParameterSource.addValue("lon", lon);
        sqlParameterSource.addValue("lat", lat);
        return namedParameterJdbcTemplate.queryForObject(SEARCH_SEGMENT_SQL, sqlParameterSource, new StreetRowMapper());
    }

    /*@Override
    public List<Street> getRouteByDijkstra(String name1, String name2) {
        SqlParameterSource sqlParameterSource1 = new MapSqlParameterSource("name", name1);
        SqlParameterSource sqlParameterSource2 = new MapSqlParameterSource("name", name2);
        List<Street> sourceStreet = namedParameterJdbcTemplate.query(
                GET_STREETS_BY_NAME_SQL,
                sqlParameterSource1,
                new StreetRowMapper());
        List<Street> targetStreet = namedParameterJdbcTemplate.query(
                GET_STREETS_BY_NAME_SQL,
                sqlParameterSource2,
                new StreetRowMapper());
        return getRouteByDijkstra(sourceStreet.get(0).getSource(), targetStreet.get(0).getTarget());
    }*/


    private class StreetRowMapper implements RowMapper<Street> {
        @Override
        public Street mapRow(ResultSet resultSet, int i) throws SQLException {
            Street street = new Street(
                    resultSet.getLong("osm_id"),
                    resultSet.getString("name"),
                    resultSet.getString("coords"),
                    resultSet.getLong("source"),
                    resultSet.getLong("target")
                   /* resultSet.getInt("node")*/);
            return street;
        }
    }
}

