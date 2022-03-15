import javafx.scene.shape.Circle;
import javafx.scene.Group;

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
    private long currentQuantity, rangeLowerBound, rangeUpperBound;

    /**
     * The circle used as a visual indicator.
     */
    private Circle circle;

    /**
     * Retrieve the starting current quantity of this
     * visualiser and the (inclusive) range.
     *
     * If the lower bound of the range is more than
     * the upper bound of the range, the two are swapped.
     *
     * @param startingQuantity The initial quantity visualised.
     * @param rangeLowerBound The smallest quantity that can be visualised.
     * @param rangeUpperBound The greatest quantity that can be visualised.
     */
    public QuantityVisualiser(long startingQuantity, long rangeLowerBound, long rangeUpperBound) throws Exception
    {
        super();

        // Swap range bounds if necessary.
        if (rangeLowerBound > rangeUpperBound)
        {
            long temp = rangeLowerBound;
            rangeLowerBound = rangeUpperBound;
            rangeUpperBound = temp;
        }

        // Initialise the instance variables:
        circle = new Circle();
        this.getChildren().add(circle);
        this.rangeLowerBound = rangeLowerBound;
        this.rangeUpperBound = rangeUpperBound;
        setCurrentQuantity(startingQuantity);

        // Make sure the starting quantity is within the range.
        if (!isWithinRange(startingQuantity))
        {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Check whether a given quantity is within the
     * range of quantities allowed.
     *
     * @param quantity The quantity being checked.
     */
    private boolean isWithinRange(long quantity)
    {
        return quantity >= rangeLowerBound && quantity <= rangeUpperBound;
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
     * Update the circle graphic by changing its
     * size and color to reflect the quantity.
     */
    private void updateGraphic()
    {
        final int startingRadius = 5;
        final int radiusMultiplier = 12;
        double radius = (currentQuantity - rangeLowerBound) / (double) (rangeUpperBound - rangeLowerBound);
        radius *= radiusMultiplier;

        circle.setCenterX(0);
        circle.setCenterY(0);
        circle.setRadius(startingRadius + radius);
    }
}
