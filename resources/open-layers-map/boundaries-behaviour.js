/*
    Layers
*/

// This layer shows the boundaries of the London boroughs
var defaultStyle = new ol.style.Style({
    fill: new ol.style.Fill({
        color: [0, 0, 255, 0.25],
    }),
    stroke: new ol.style.Stroke({
        color: [0, 0, 255, 0.75],
        width: 2,
    }),
});

const geoJsonData = new ol.source.Vector({
    url: "./london-borough-boundaries.geojson",
    format: new ol.format.GeoJSON()
})

const boroughBoundariesLayer = new ol.layer.VectorImage({
    source: geoJsonData,
    style: defaultStyle,
    visible: false,
});

map.addLayer(boroughBoundariesLayer);



/*
    Methods for interaction with JavaFX
*/

function setBoroughBoundariesVisibility(areVisible) {
    boroughBoundariesLayer.setVisible(areVisible);
}