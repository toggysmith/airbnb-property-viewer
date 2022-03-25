/*
    Layers
*/

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

map.addLayer(drawnPolygonsLayer);

// This layer show the line we're currently drawing.
const previewLine = new ol.Feature({
    geometry: new ol.geom.LineString([]),
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