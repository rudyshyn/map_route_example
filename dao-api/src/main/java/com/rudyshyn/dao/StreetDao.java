package com.rudyshyn.dao;

import java.util.List;
import com.rudyshyn.model.Street;

public interface StreetDao {
    List<Street> getStreetsByName(String name);
    List<Street> getRouteByDijkstra(Long source, Long target);
    Street getStreetById(Long id);
    Street searchSegment(String longitude, String latitude);

    //List<Street> getRouteByDijkstra(String name1, String name2);
}

