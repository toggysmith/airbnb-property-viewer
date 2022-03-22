
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

// This layer shows the finished drawn polygons.
const drawnPolygonsLayer = new ol.layer.Vector({
    source: new ol.source.Vector(),
    style: new ol.style.Style({
        stroke: new ol.style.Stroke({
            color: 'rgba(100, 255, 0, 1)', // Outline colour
            width: 2,
        }),
        fill: new ol.style.Fill({
            color: 'rgba(100, 255, 0, 0.3)', // Fill colour
        }),
    }),
});

// This layer show the line we're currently drawing.
const previewLine = new ol.Feature({
    geometry: new ol.geom.LineString([]),
});

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

/*
    Map
*/
const map = new ol.Map({
    view: new ol.View({
        center: new ol.proj.fromLonLat([-0.115937, 51.511437]),
        zoom: 11,
    }),
    layers: [rasterLayer, drawnPolygonsLayer, propertyMarkersLayer],
    target: "js-map" // Change this to 'map'
});

map.on("click", function(e) {
	map.forEachFeatureAtPixel(e.pixel, function (feature, layer) {
		if (layer == propertyMarkersLayer)
		{
			openPropertyWindow(feature.getId());
		}
	})
});


/*
    Other
*/
let drawing = false;
let drawInteraction, tracingFeature, startPoint, endPoint;

drawInteraction = new ol.interaction.Draw({
    source: drawnPolygonsLayer.getSource(),
    type: "Polygon",
});

drawInteraction.on('drawstart', () => {
    drawnPolygonsLayer.getSource().clear()
    drawing = true;
});

let completedPolygon = null;

drawInteraction.on('drawend', (e) => {
    completedPolygon = e;
    drawing = false;
    previewLine.getGeometry().setCoordinates([]);
    tracingFeature = null;
});

map.addInteraction(drawInteraction);

var propertyMarkers = {};

/*
    Methods for interaction with JavaFX
*/

let properties = [];

function addMarkers(fromPrice, toPrice) {
    properties.forEach(function(property) {
        if (property.price >= fromPrice && property.price <= toPrice) {
            if (completedPolygon.feature.getGeometry().intersectsCoordinate(new ol.proj.fromLonLat([property.longitude, property.latitude]))) {
                var marker = new ol.Feature(new ol.geom.Point(ol.proj.fromLonLat([property.longitude, property.latitude])));
			marker.setId(property.id);
                propertyMarkersLayer.getSource().addFeature(marker);
            }
        }
    });
}

function openPropertyWindow(property){ 
        window.javaMarker.openPropertyWindow(property);
}

function clearMarkers() {
    propertyMarkersLayer.getSource().clear();
}

function switchToDrawingMode() {
    clearMarkers();
    map.addInteraction(drawInteraction);
}

function switchToMarkerMode(fromPrice, toPrice) {
    map.removeInteraction(drawInteraction);
    addMarkers(fromPrice, toPrice);
}

function refreshMarkers(fromPrice, toPrice) {
    clearMarkers();
    addMarkers(fromPrice, toPrice);
}

function addProperty(property) {
    properties.push(property);
}

