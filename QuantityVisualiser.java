// @TODO: Refactor class

import javafx.scene.shape.Circle;
import javafx.scene.Group;
import javafx.scene.layout.HBox;
import javafx.scene.shape.SVGPath;
import java.io.FileInputStream;

/**
 * This class should create a graphical representation
 * of a given quantity within a range.
 *
 * It does this by using size and color as visual
 * indicators.
 */
public class QuantityVisualiser extends Group
{
    /**
     * The current quantity and the possible range of quantities.
     */
    private long currentQuantity, rangeUpperBound;

    /**
     * The contained used to hold the property icons.
     */
    private HBox iconContainer;
    
    /**
     * The property icon SVG (shared among all quantity visualisers).
     */
    private static String propertyIconSVG = "M23.121,9.069,15.536,1.483a5.008,5.008,0,0,0-7.072,0L.879,9.069A2.978,2.978,0,0,0,0,11.19v9.817a3,3,0,0,0,3,3H21a3,3,0,0,0,3-3V11.19A2.978,2.978,0,0,0,23.121,9.069ZM15,22.007H9V18.073a3,3,0,0,1,6,0Zm7-1a1,1,0,0,1-1,1H17V18.073a5,5,0,0,0-10,0v3.934H3a1,1,0,0,1-1-1V11.19a1.008,1.008,0,0,1,.293-.707L9.878,2.9a3.008,3.008,0,0,1,4.244,0l7.585,7.586A1.008,1.008,0,0,1,22,11.19Z";

    /**
     * Retrieve the starting current quantity of this
     * visualiser and the (inclusive) range.
     */
    public QuantityVisualiser()
    {
        super();

        // Initialise the instance variables:
        iconContainer = new HBox();
        getChildren().add(iconContainer);
        this.rangeUpperBound = rangeUpperBound;
    }

    /**
     * Set the current quantity.
     *
     * @param newQuantity The new current quantity.
     */
    public void setCurrentQuantity(long newQuantity)
    {
        this.currentQuantity = newQuantity;
        updateGraphic();
    }
    
    /**
     * Set the upper range bound.
     * 
     * @param rangeUpperBound The upper range bound.
     */
    public void setRangeUpperBound(long rangeUpperBound)
    {
        this.rangeUpperBound = rangeUpperBound;
        updateGraphic();
    }

    /**
     * Update the circle graphic by changing its
     * size and color to reflect the quantity.
     */
    private void updateGraphic()
    {
        iconContainer.getChildren().clear();
        
        double normalisedValue = currentQuantity / (double) rangeUpperBound;
        
        int noOfPropertyIcons = 0;
        
        if (normalisedValue == 0.00) noOfPropertyIcons = 0;
        else if (normalisedValue <= 0.33) noOfPropertyIcons = 1;
        else if (normalisedValue <= 0.66) noOfPropertyIcons = 2;
        else if (normalisedValue <= 1.00) noOfPropertyIcons = 3;
        
        if (iconContainer.getChildren().size() != noOfPropertyIcons)
        {
            for (int i = 0; i < noOfPropertyIcons; i++)
            {
                SVGPath icon = new SVGPath();
                
                icon.setContent(propertyIconSVG);
                icon.setScaleX(0.8);
                icon.setScaleY(0.8);
                
                iconContainer.getChildren().add(icon);
            }
        }
    }
}
