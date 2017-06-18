package com.rudyshyn.rest;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@CrossOrigin
@RestController
public class MapController {

    @Autowired
    private ServletContext servletContext;

    @RequestMapping (value = "/tile/{zoom}/{x}/{y}", method = RequestMethod.GET, produces = MediaType.IMAGE_PNG_VALUE)
    @ResponseBody
    public byte[] getImageAsByteArray1(@PathVariable(value = "zoom") Integer zoom,
                                       @PathVariable(value = "x") Integer x,
                                       @PathVariable(value = "y") Integer y) throws IOException {
        String tileLocation = "/home/renton/osm/tiles/" + zoom + "/" + x + "/" + y + ".png";

        if(new File(tileLocation).exists()) {
            InputStream in = new FileInputStream(tileLocation);
            return IOUtils.toByteArray(in);
        }
        return null;
    }

}
