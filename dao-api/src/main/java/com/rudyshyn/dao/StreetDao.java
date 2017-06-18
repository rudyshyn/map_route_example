package com.rudyshyn.dao;

import java.util.List;
import com.rudyshyn.model.Street;

public interface StreetDao {
    List<Street> getStreetsByName(String name);
    Street getStreetById(Long id);
    List<Street> getRouteByDijkstra(int source, int target);
    List<Street> getRouteByDijkstra(String name1, String name2);
}

