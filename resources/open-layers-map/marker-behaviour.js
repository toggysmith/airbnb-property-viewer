/*
    Layers
*/

// This layer shows the markers for the properties.
var propertyMarkersLayer = new ol.layer.Vector({
    source: new ol.source.Vector(),
    style: new ol.style.Style({
        image: new ol.style.Icon({
            anchor: [0.5, 1],
            scale: [0.04, 0.04],
            src: "marker.png",
        }),
    }),
});

map.addLayer(propertyMarkersLayer);



/*
    Map
*/

let areMarkersClickable = false;

map.on("click", function(e) {
    if (!areMarkersClickable) return;

	var markerFound = false;
	map.forEachFeatureAtPixel(e.pixel, function (feature, layer) {
		if (layer == propertyMarkersLayer && markerFound == false)
		{
			openPropertyWindow(feature.getId());
			markerFound = true;
		}
	})
});



/*
    Other
*/

var propertyMarkers = {};



/*
    Methods for interaction with JavaFX
*/

function addMarker(property) {
    var marker = new ol.Feature(new ol.geom.Point(ol.proj.fromLonLat([property.longitude, property.latitude])));
    marker.setId(property.id);
    propertyMarkersLayer.getSource().addFeature(marker);
}

function addMarkersForPropertiesInPriceRange(fromPrice, toPrice) {
    properties.forEach(function(property) {
        if (property.price >= fromPrice && property.price <= toPrice) {
            if (completedPolygon.feature.getGeometry().intersectsCoordinate(new ol.proj.fromLonLat([property.longitude, property.latitude]))) {
                addMarker(property);
            }
        }
    });
}

function openPropertyWindow(property){ 
        window.jsToJavaBridge.openPropertyWindow(property);
}

function clearMarkers() {
    propertyMarkersLayer.getSource().clear();
}

function refreshMarkers(fromPrice, toPrice) {
    clearMarkers();
    addMarkersForPropertiesInPriceRange(fromPrice, toPrice);
}