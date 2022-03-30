import javafx.scene.shape.Polygon;
import javafx.scene.shape.StrokeType;
import javafx.scene.paint.Color;

/**
 * Responsible for creating a hexagon-shaped polygon.
 */
public class Hexagon extends Polygon
{
    private final static Color FILL_COLOR = Color.WHITE;
    private final static Color STROKE_COLOR = Color.BLACK;
    
    /**
     * Creates a hexagon-shaped polygon based on the given values.
     * 
     * @param x The relative x position of the hexagon.
     * @param y The relative y position of the hexagon.
     * @param stroke_width The thickness of the hexagon's outline,
     * @param radius The radius of the hexagon from the center to the furthest outer vertex.
     */
    public Hexagon(double x, double y, int stroke_width, double radius)
    {
        final double N = Math.sqrt(radius * radius * 0.75);
        final double TILE_WIDTH = 2 * N;
        final double TILE_HEIGHT = 2 * radius;
        
        getPoints().addAll(x, y,
                x, y + radius,
                x + N, y + radius * 1.5,
                x + TILE_WIDTH, y + radius,
                x + TILE_WIDTH, y,
                x + N, y - radius * 0.5);
        
        setFill(FILL_COLOR);
        setStrokeType(StrokeType.INSIDE);
        setStrokeWidth(stroke_width);
        setStroke(STROKE_COLOR);
    }
}