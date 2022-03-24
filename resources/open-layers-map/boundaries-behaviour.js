/*
    Layers
*/

// This layer shows the boundaries of the London boroughs
var defaultStyle = new ol.style.Style({
    fill: new ol.style.Fill({
        color: [255,0,0,0.5]
    }),
    stroke: new ol.style.Stroke({
        color: [255,0,0,1],
        width: 1
    }),
});

var selectedStyle = new ol.style.Style({
    fill: new ol.style.Fill({
        color: [64,196,64,1]
    }),
    stroke: new ol.style.Stroke({
        color: [64,196,64,1],
        width: 1
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

map.on("click", function(e) {
	var markerFound = false;

    // Reset all the borough boundary shapes to the default style
    var features = boroughBoundariesLayer.getSource().getFeatures();
    features.forEach(feature => feature.setStyle(defaultStyle));

    // Then set the selected one to the selected style
	map.forEachFeatureAtPixel(e.pixel, function (feature, layer) {
		if (layer == boroughBoundariesLayer && markerFound == false)
		{
			feature.setStyle(selectedStyle);
			markerFound = true;
		}
	});
});



/*
    Methods for interaction with JavaFX
*/

function highlightBorough(boroughName) {
    var features = boroughBoundariesLayer.getSource().getFeatures();

    features.forEach(feature => {
        if (feature.get("Name") == boroughName) {
            feature.setStyle(selectedStyle);
        } else {
            feature.setStyle(defaultStyle);
        }
    })
}

function setBoroughBoundariesVisibility(areVisible) {
    boroughBoundariesLayer.setVisible(areVisible);
}