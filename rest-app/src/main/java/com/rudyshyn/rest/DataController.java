package com.rudyshyn.rest;

import com.rudyshyn.service.GeoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.rudyshyn.model.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
public class DataController {

    @Autowired
    private GeoService geoService;

   /*

    @RequestMapping(value = "/point/leisure/{leisure}", method = RequestMethod.GET)
    public @ResponseBody
    List<Point> getPointByLeisure(@PathVariable(value = "leisure") String leisure) {
        return geoService.getPointsByShop(leisure);
    }*/

    /*
        Возвращает список точек, по которой строится маршрут
     */

    @RequestMapping(value = "/streets/{source}/{target}", method = RequestMethod.GET)
    public @ResponseBody
    List<Street> getPointStreets(@PathVariable(value = "source") Long source,
                                 @PathVariable(value = "target") Long target) {
        System.out.println(source + " : " + target);
        return geoService.getRouteByDijkstra(source, target);
        //return list;
    }

    @RequestMapping(value = "/point/{firstCoords}/{secondCoords},", method = RequestMethod.GET)
    public @ResponseBody
    List<Street> searchSegment(@PathVariable(value = "firstCoords") String firstCoords,
                               @PathVariable(value = "secondCoords") String secondCoords) {
        System.out.println(firstCoords + " : " + secondCoords);
        return geoService.searchSegment(firstCoords, secondCoords);
    }
}
