package com.rudyshyn.dao;

import java.util.List;
import com.rudyshyn.model.Point;

public interface PointDao {
    List<Point> getPointsByAmenity(String amenity);
    List<Point> getPointsByTourism(String tourism);
    List<Point> getPointsByHistoric(String historic);
    List<Point> getPointsByShop(String shop);
    List<Point> getPointsByLeisure(String leisure);
}