package com.rudyshyn.service;

import java.util.List;
import com.rudyshyn.model.Street;
import com.rudyshyn.model.Point;

public interface GeoService {
    /*List<Point>  getPointsByAmenity(String amenity);
    List<Point>  getPointsByTourism(String tourism);
    List<Point>  getPointsByHistoric(String historic);
    List<Point>  getPointsByShop(String shop);
    List<Point>  getPointsByLeisure(String leisure);*/

    //List<Street> getRouteByDijkstra(String source, String target);
    List<Street> searchSegment(String lon, String lat);
    List<Street> getRouteByDijkstra(Long source, Long target);
}
