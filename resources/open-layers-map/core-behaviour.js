/*
    Layers
*/

// This layer shows Bing maps.
const rasterLayer = new ol.layer.Tile({
    source: new ol.source.BingMaps({
        key: 'LKWeGV5hgC5HHHG4V7hY~iB_5lc6FjIxDktEpAU2dXw~AnwVbzV433LA6i_4U4NcMYmZ0dUpAPanUAJt3Ru2SWuEIjFnZyZ1gnG8neDCI1I2',
        imagerySet: "AerialWithLabelsOnDemand",
    }),
});

/*
    Map
*/

const map = new ol.Map({
    view: new ol.View({
        center: new ol.proj.fromLonLat([0.6648702159618555, 51.56039371464776]),
        zoom: 5,
    }),
    layers: [rasterLayer],
    target: "js-map" // Change this to 'map'
});



/*
    Methods for interaction with JavaFX
*/

let properties = [];

function switchToDrawingMode() {
    clearMarkers();
    map.addInteraction(drawInteraction);
}

function switchToMarkerMode(fromPrice, toPrice) {
    map.removeInteraction(drawInteraction);
    addMarkersForPropertiesInPriceRange(fromPrice, toPrice);
}

function setLongLat(long, lat) {
	map.getView().setCenter(ol.proj.fromLonLat([long, lat]));
}

function setZoom(zoom) {
    map.getView().setZoom(zoom);
}

function addProperty(property) {
    properties.push(property);
}

function enableMarkerClicking() {
    areMarkersClickable = true;
}