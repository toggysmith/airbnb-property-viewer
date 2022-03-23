
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
    layers: [rasterLayer, propertyMarkersLayer],
    target: "js-map" // Change this to 'map'
});


/*
    Methods for interaction with JavaFX
*/



function addProperty(property) {
	var marker = new ol.Feature(new ol.geom.Point(ol.proj.fromLonLat([property.longitude, property.latitude])));
			marker.setId(property.id);
                propertyMarkersLayer.getSource().addFeature(marker);
}

function setLongLat(long, lat)
{
	map.getView().setCenter(ol.proj.fromLonLat([long, lat]));
}