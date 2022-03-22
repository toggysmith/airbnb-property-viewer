
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
            scale: [0.1, 0.1],
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
        zoom: 10,
    }),
    layers: [rasterLayer, drawnPolygonsLayer, propertyMarkersLayer],
    target: "js-map" // Change this to 'map'
});


/*
    Other
*/
let drawing = false;
let drawInteraction, tracingFeature, startPoint, endPoint;

// Used to start/end tracing around a feature
map.on("click", (event) => {
    if (!drawing) {
        return;
    }

    // Clear current tracing feature + preview
    previewLine.getGeometry().setCoordinates([]);
    tracingFeature = null;
});

// Used to show a preview of the result of the tracing
map.on('pointermove', (event) => {
    if (tracingFeature && drawing) {
        let coord = null;
        map.forEachFeatureAtPixel(
            event.pixel,
            (feature) => {
            if (tracingFeature === feature) {
                coord = map.getCoordinateFromPixel(event.pixel);
            }
          },
          getFeatureOptions
        );

        let previewCoords = [];
        if (coord) {
            endPoint = tracingFeature.getGeometry().getClosestPoint(coord);
            previewCoords = getPartialRingCoords(
                tracingFeature,
                startPoint,
                endPoint
            );
        }
        previewLine.getGeometry().setCoordinates(previewCoords);
    }
});

drawInteraction = new ol.interaction.Draw({
    source: drawnPolygonsLayer.getSource(),
    type: "Polygon",
});

drawInteraction.on('drawstart', () => {
    drawnPolygonsLayer.getSource().clear()
    drawing = true;
});

drawInteraction.on('drawend', () => {
    drawing = false;
    previewLine.getGeometry().setCoordinates([]);
    tracingFeature = null;
});

map.addInteraction(drawInteraction);

/*
    Methods for interaction with JavaFX
*/

function addMarkers(longitude, latitude) {
    var marker = new ol.Feature(new ol.geom.Point(ol.proj.fromLonLat([longitude, latitude])));
    propertyMarkersLayer.getSource().addFeature(marker);
}

function clearMarkers() {
    propertyMarkersLayer.getSource().clear();
}

function setListOfProperties(listOfProperties) {

}