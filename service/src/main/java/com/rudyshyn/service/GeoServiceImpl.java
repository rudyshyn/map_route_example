package com.rudyshyn.service;

import com.rudyshyn.dao.PointDao;
import com.rudyshyn.dao.StreetDao;
import com.rudyshyn.model.Street;
import com.rudyshyn.model.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class GeoServiceImpl implements GeoService {

    @Autowired
    PointDao pointDao;
    @Autowired
    StreetDao streetDao;

    public void setPointDao(PointDao pointDao){
        this.pointDao = pointDao;
    }

    public void setStreetDao(StreetDao streetDao){
        this.streetDao = streetDao;
    }

    private List<Street> streets = new ArrayList<>();

   /* @Override
    public List<Point> getPointsByAmenity(String amenity) {
        points = pointDao.getPointsByAmenity(amenity);
        return points;
    }*/

    @Override
    public List<Street> getRouteByDijkstra(Long name1, Long name2) {
        streets = streetDao.getRouteByDijkstra(name1, name2);
        return streets;
    }

    @Override
    public List<Street> searchSegment(String firstCoords, String secondCoords) {
        String first[] = firstCoords.split(",");
        String second[] = secondCoords.split(",");
        streets.add(streetDao.searchSegment( first[0],  first[1]));
        streets.add(streetDao.searchSegment(second[0], second[1]));
        return streets;
    }
}
