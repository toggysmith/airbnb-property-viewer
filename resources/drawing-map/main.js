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
                source: new ol.source.BingMaps({
                    key: 'LKWeGV5hgC5HHHG4V7hY~iB_5lc6FjIxDktEpAU2dXw~AnwVbzV433LA6i_4U4NcMYmZ0dUpAPanUAJt3Ru2SWuEIjFnZyZ1gnG8neDCI1I2',
                    imagerySet: "AerialWithLabelsOnDemand",
                  }),
            })
        ],
        target: "js-map"
    })
}