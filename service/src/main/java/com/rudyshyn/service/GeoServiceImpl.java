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

    private List<Point> points = new ArrayList<>();
    List<Street> streets = new ArrayList<>();

    @Override
    public List<Point> getPointsByAmenity(String amenity) {
        points = pointDao.getPointsByAmenity(amenity);
        return points;
    }

    @Override
    public List<Point> getPointsByTourism(String tourism) {
        points = pointDao.getPointsByTourism(tourism);
        return points;
    }

    @Override
    public List<Point> getPointsByHistoric(String historic) {
        points = pointDao.getPointsByHistoric(historic);
        return points;
    }

    @Override
    public List<Point> getPointsByShop(String shop) {
        points = pointDao.getPointsByShop(shop);
        return points;
    }

    @Override
    public List<Point> getPointsByLeisure(String leisure) {
        points = pointDao.getPointsByLeisure(leisure);
        return points;
    }

    @Override
    public List<Street> getRouteByDijkstra(String name1, String name2) {
        streets = streetDao.getRouteByDijkstra(name1, name2);
        return streets;
    }

   /* private List<Point> deleteSamePoints(List<Point> points){
        List<Point> newPoints = new ArrayList<>();
        Point buf;
        for(int i = 0; i < points.size(); i++){
            buf = points.get(i);
            for(int j = i; j < points.size(); j++){
                if(buf.getId() == points.get(j).getId()){
                    if (buf.getDistance() > points.get(j).getDistance()){
                        buf = points.get(j);
                    }
                }
            }
            if(!contains(newPoints, buf.getId())){
                newPoints.add(buf);
            }
        }
        return newPoints;
    }


    private boolean contains(List<Point> points, long id){
        for (Point point : points) {
            if(point.getId() == id)
                return  true;
        }
        return false;
    }
    */
}
