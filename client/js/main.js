$(document).ready(function(){
    var STREETS_URL = "http://localhost:8088/streets/";
    var POINT_URL = "http://localhost:8088/point/"


    url = "http://{s}.localhost:8088/tile/{z}/{x}/{y}.png"
	var map = L.map('map', {
	    zoomControl: false,
        center: [52.096046, 23.734529],
        zoom: 14
    });

    L.control.zoom({
         position:'topright'
    }).addTo(map);

	if (typeof(Number.prototype.toRad) === "undefined") {
	  Number.prototype.toRad = function() {
	    return this * Math.PI / 180;
	  }
	}

	function getTileURL(lat, lon, zoom) {
	    var xtile = parseInt(Math.floor( (lon + 180) / 360 * (1<<zoom) ));
	    var ytile = parseInt(Math.floor( (1 - Math.log(Math.tan(lat.toRad()) + 1 / Math.cos(lat.toRad())) / Math.PI) / 2 * (1<<zoom) ));
	    return "" + zoom + "/" + xtile + "/" + ytile;
	}

 // cd READY/Диплом/Измененные/map/


	L.tileLayer(url, {
	    maxZoom: 18
	}).addTo(map);

    function displayLocation(position) {

    var lat = 52.06529;
    var lng = 23.74298;

    var lat1 = 52.10009;
    var lng1 = 23.77508;
    //var lat = position.coords.latitude;
    //var lng = position.coords.longitude;
        console.log(lat + " : " + lng);
        L.marker([lat, lng]).addTo(map);
        L.marker([lat1, lng1]).addTo(map);
        map.setView([lat, lng], 14);
    }

    navigator.geolocation.getCurrentPosition(displayLocation);

	var source;
	var target;

	$('#SearchRouteBtn').click(function () {
	    source = $('#source').val();
	    target = $('#target').val();
	    console.log(typeof(+source) + " : " + typeof(+target));

        getLineStreets();

	});


    var firstCoords;
    var secondCoords;

	$('#SearchSegmentBtn').click(function() {
	    firstCoords = $('firstCoords').val();
	    secondCoords = $('secondCoords').val();
	    console.log(firstCoords + " : " + secondCoords);

	    getLineSegment();
	 })


    function getLineSegment() {
        console.log('getLineSegment');
        $.ajax({
            type: 'GET',
            url: POINT_URL + firstCoords + "/" + secondCoords,
            dataType: "json",
            success: renderStreets,
            error: function(jqXHR, textStatus, errorThrown) {
                console.log(jqXHR, textStatus, errorThrown);
                alert('findAll: ' + textStatus);
            }
        })
    }

	function getLineStreets() {
                console.log('getLineStreets');
                $.ajax({
                    type: 'GET',
                    url: STREETS_URL + +source + "/" + +target,
                    dataType: "json", // data type of response
                    success: renderStreets,
                    error: function(jqXHR, textStatus, errorThrown) {
                        console.log(jqXHR, textStatus, errorThrown);
                        alert('findAll: ' + textStatus);
                    }
                });
        }

    var tag;
    var value;

    var polylines = new Array();
    var marker = new Array();

    function renderMarkers(data) {
            for(i=0;i<marker.length;i++) {
                map.removeLayer(marker[i]);
                }
            dto = data == null ? [] : (data instanceof Array ? data : [data]);
            $.each(dto, function (index, street) {
                addMarker(street);
            });
    }

    function addMarker(point){
        var buf = point.coordinates.split(',');
        var newMarker = L.marker([buf[0], buf[1]]);
        marker.push(newMarker);
        newMarker.addTo(map).bindPopup(point.name).openPopup();
    }

    function renderStreets(data){
        var max = 0;
        for(i=0;i<polylines.length;i++) {
                    map.removeLayer(polylines[i]);
                    }
        dto = data == null ? [] : (data instanceof Array ? data : [data]);
        $.each(dto, function (index, street) {
            drawLine(street);
         });

    }

    function drawLine(street){
        console.log('drawLine');
        var color = '#0000FF';
        var line = new Array();
        var p = new Array();

        var buf = street.coordinates.split(',');
        var buf = street.coordinates.split('(')[1];
        buf = buf.split(')')[0];
        buf = buf.split(',');

        for(i = 0; i < buf.length; i++){
            p.splice(0, 2);
            line.push([buf[i].split(' ')[1],buf[i].split(' ')[0]]);
        }

        var newLine = L.polyline(line, {color: color});

        polylines.push(newLine);
        newLine.addTo(map);
    }
});