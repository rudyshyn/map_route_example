$(document).ready(function(){
    var POINTS_TOURISM_URL = "http://localhost:8088/point/tourism/";
    var POINTS_AMENITY_URL = "http://localhost:8088/point/amenity/";
    var POINTS_HISTORIC_URL = "http://localhost:8088/point/historic/";
    var POINTS_SHOP_URL = "http://localhost:8088/point/shop/";
    var POINTS_LEISURE_URL = "http://localhost:8088/point/leisure/";
    var STREETS_URL = "http://localhost:8088/streets";

    url = "http://{s}.localhost:8088/tile/{z}/{x}/{y}.png"
	var map = L.map('map', {
	    zoomControl: false,
        center: [52.096046, 23.734529],
        zoom: 14
    });
    //add zoom control with your options
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

	L.tileLayer(url, {
	    maxZoom: 18
	}).addTo(map);

    var tag;
    var value;

    $('#SearchBtn').click(function () {
        setTagAndValue();
        console.log(tag);
        if(tag == 'tourism'){
            getPointsByTourism();
        }
        if(tag == 'historic'){
            getPointsByHistoric();
        }
        if(tag == 'shop'){
            getPointsByShop();
        }
        if(tag == 'amenity'){
            getPointsByAmenity();
        }
        if(tag == 'leisure'){
            getPointsByLeisure();
        }
        getStreets();
    });

    $('')
    $('#place').click(function () {
        $("#place").val("")
        tag = null;
        value = null;
    });

    function displayLocation(position) {
        var lat = position.coords.latitude;
        var lng = position.coords.longitude;
        L.marker([lat, lng]).addTo(map);
        map.setView([lat, lng], 14);
    }

    navigator.geolocation.getCurrentPosition(displayLocation);

    var polylines = new Array();
    var marker = new Array();
    function renderMarkers(data) {
            for(i=0;i<marker.length;i++) {
                map.removeLayer(marker[i]);
                }
            dto = data == null ? [] : (data instanceof Array ? data : [data]);
            $.each(dto, function (index, point) {
                addMarker(point);
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
            if(street.interestObjectsQuantity > max)
                max = street.interestObjectsQuantity;
         });
        $.each(dto, function (index, street) {
            drawLine(street);
         });

    }

    function drawLine(street, max){
        console.log('drawLine');
        var color = '#CA2121';
        var line = new Array();
        var p = new Array();

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








    function getPointsByTourism() {
            console.log('getPointsByTourism ' + value);
            $.ajax({
                type: 'GET',
                url: POINTS_TOURISM_URL + value,
                dataType: "json", // data type of response
                success: renderMarkers,
                error: function(jqXHR, textStatus, errorThrown) {
                    console.log(jqXHR, textStatus, errorThrown);
                    alert('findAll: ' + textStatus);
                }
            });
    }

    function getPointsByAmenity() {
            console.log('getPointsByAmenity ' + value);
            $.ajax({
                type: 'GET',
                url: POINTS_AMENITY_URL + value,
                dataType: "json", // data type of response
                success: renderMarkers,
                error: function(jqXHR, textStatus, errorThrown) {
                    console.log(jqXHR, textStatus, errorThrown);
                    alert('findAll: ' + textStatus);
                }
            });
    }

    function getPointsByHistoric() {
            console.log('getPointsByHistoric');
            $.ajax({
                type: 'GET',
                url: POINTS_HISTORIC_URL + value,
                dataType: "json", // data type of response
                success: renderMarkers,
                error: function(jqXHR, textStatus, errorThrown) {
                    console.log(jqXHR, textStatus, errorThrown);
                    alert('findAll: ' + textStatus);
                }
            });
    }

    function getPointsByShop() {
            console.log('getPointsByShop');
            $.ajax({
                type: 'GET',
                url: POINTS_SHOP_URL + value,
                dataType: "json", // data type of response
                success: renderMarkers,
                error: function(jqXHR, textStatus, errorThrown) {
                    console.log(jqXHR, textStatus, errorThrown);
                    alert('findAll: ' + textStatus);
                }
            });
    }

    function getPointsByLeisure() {
            console.log('getPointsByLeisure');
            $.ajax({
                type: 'GET',
                url: POINTS_LEISURE_URL + value,
                dataType: "json", // data type of response
                success: renderMarkers,
                error: function(jqXHR, textStatus, errorThrown) {
                    console.log(jqXHR, textStatus, errorThrown);
                    alert('findAll: ' + textStatus);
                }
            });
    }

    function getStreets() {
            console.log('getStreets');
            $.ajax({
                type: 'GET',
                url: STREETS_URL,
                dataType: "json", // data type of response
                success: renderStreets,
                error: function(jqXHR, textStatus, errorThrown) {
                    console.log(jqXHR, textStatus, errorThrown);
                    alert('findAll: ' + textStatus);
                }
            });
    }
function setTagAndValue(){
    var val = $('#place').val();
        // tag tourism
        if(val == 'Мотель') {
            tag = 'tourism';
            value = 'motel'
        }
        if(val == 'Хостел') {
            tag = 'tourism';
            value = 'hostel'
        }
        if(val == 'Отель') {
            tag = 'tourism';
            value = 'hotel'
        }
        if(val == 'Гостевой дом') {
            tag = 'tourism';
            value = 'guest_house'
        }
        if(val == 'Галлерея') {
            tag = 'tourism';
            value = 'gallery'
        }
        if(val == 'Интересные места') {
            tag = 'tourism';
            value = 'attraction'
        }
        if(val == 'Музей') {
            tag = 'tourism';
            value = 'museum'
        }
        // tag historic
        if(val == 'Поле битвы') {
            tag = 'historic';
            value = 'battlefield'
        }
        if(val == 'Замок') {
            tag = 'historic';
            value = 'castle'
        }
        if(val == 'Мемориал ВОВ') {
            tag = 'historic';
            value = 'memorial'
        }
        if(val == 'Исторический памятник') {
            tag = 'historic';
            value = 'monument'
        }
        if(val == 'Руины') {
            tag = 'historic';
            value = 'ruins'
        }
        // tag shop
        if(val == 'Супермаркет') {
            tag = 'shop';
            value = 'supermarket'
        }
        if(val == 'Магазин одежды') {
            tag = 'shop';
            value = 'clothes'
        }
        if(val == 'Ювелирный магазин') {
            tag = 'shop';
            value = 'jewelry'
        }
        if(val == 'Магазин часов') {
            tag = 'shop';
            value = 'watches'
        }
        if(val == 'Магазин косметики') {
            tag = 'shop';
            value = 'cosmetics'
        }
        if(val == 'Книжный магазин') {
            tag = 'shop';
            value = 'books'
        }
        if(val == 'Табачный магазин') {
            tag = 'shop';
            value = 'tobacco'
        }
        if(val == 'Магазин алкогольной продукции') {
            tag = 'shop';
            value = 'alcohol'
        }
        if(val == 'Булочная') {
            tag = 'shop';
            value = 'bakery'
        }
        if(val == 'Фотостудия') {
            tag = 'shop';
            value = 'photo'
        }
        if(val == 'Магазин компьютеров') {
            tag = 'shop';
            value = 'computer'
        }
        if(val == 'Магазин мобильных телефонов') {
            tag = 'shop';
            value = 'mobile_phone'
        }
        // tag amenity
        if(val == 'Бар') {
            tag = 'amenity';
            value = 'bar'
        }
        if(val == 'Барбекю') {
            tag = 'amenity';
            value = 'bbq'
        }
        if(val == 'Кафе') {
            tag = 'amenity';
            value = 'cafe'
        }
        if(val == 'Фастфуд') {
            tag = 'amenity';
            value = 'fast_food'
        }
        if(val == 'Паб') {
            tag = 'amenity';
            value = 'pub'
        }
        if(val == 'Ресторан') {
            tag = 'amenity';
            value = 'restaurant'
        }
        if(val == 'Библиотека') {
            tag = 'amenity';
            value = 'library'
        }
        if(val == 'АЗС') {
            tag = 'amenity';
            value = 'fuel'
        }
        if(val == 'АТМ') {
            tag = 'amenity';
            value = 'atm'
        }
        if(val == 'Банк') {
            tag = 'amenity';
            value = 'bank'
        }
        if(val == 'Больница') {
            tag = 'amenity';
            value = 'hospital'
        }
        if(val == 'Поликлиника') {
            tag = 'amenity';
            value = 'clinic'
        }
        if(val == 'Стоматолог') {
            tag = 'amenity';
            value = 'dentist'
        }
        if(val == 'Аптека') {
            tag = 'amenity';
            value = 'pharmacy'
        }
        if(val == 'Ветеренарная больница') {
            tag = 'amenity';
            value = 'veterinary'
        }
        if(val == 'Казино') {
            tag = 'amenity';
            value = 'casino'
        }
        if(val == 'Кинотеатр') {
            tag = 'amenity';
            value = 'cinema'
        }
        if(val == 'Ночной клуб') {
            tag = 'amenity';
            value = 'nightclub'
        }
        if(val == 'Милиция') {
            tag = 'amenity';
            value = 'police'
        }
        if(val == 'Почта') {
            tag = 'amenity';
            value = 'post_office'
        }
        if(val == 'Туалет') {
            tag = 'amenity';
            value = 'toilets'
        }
        if(val == 'Театр') {
            tag = 'amenity';
            value = 'theatre'
        }
        // tag leisure
        if(val == 'Тренажёрный зал') {
            tag = 'leisure';
            value = 'fitness_centre'
        }
        if(val == 'Стадион') {
            tag = 'leisure';
            value = 'stadium'
        }
        if(val == 'Сауна') {
            tag = 'leisure';
            value = 'sauna'
        }

        if(tag == null && value == null)
            alert('Выберите место из списка');
        console.log(tag + ' ' + value);
}
});