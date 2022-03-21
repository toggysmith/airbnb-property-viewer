/**
 * @author Tony Smith (K21064940)
 */

window.onload = init;

function init() {
    const map = new ol.Map({
        view: new ol.View({
            center: new ol.proj.fromLonLat([-0.115937, 51.511437]),
            zoom: 10
        }),
        layers: [
            new ol.layer.Tile({
                source: new ol.source.OSM()
            })
        ],
        target: "js-map"
    })
}