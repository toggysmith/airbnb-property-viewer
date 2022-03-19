// @TODO: Refactor class

import javafx.scene.shape.Polygon;
import javafx.scene.shape.StrokeType;
import javafx.scene.paint.Color;

public class Hexagon extends Polygon
{
    private final static double r = 60.0;
    private final static double n = Math.sqrt(r * r * 0.75);
    private final static double TILE_WIDTH = 2 * n;
    private final static double TILE_HEIGHT = 2 * r;
    
    public final static int STROKE_WIDTH = 4;
    
    public Hexagon(double x, double y)
    {
        getPoints().addAll(x, y,
                x, y + r,
                x + n, y + r * 1.5,
                x + TILE_WIDTH, y + r,
                x + TILE_WIDTH, y,
                x + n, y - r * 0.5);
        
        setFill(Color.WHITE);
        setStrokeType(StrokeType.INSIDE);
        setStrokeWidth(STROKE_WIDTH);
        setStroke(Color.BLACK);
    }
}