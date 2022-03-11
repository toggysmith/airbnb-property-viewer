import javafx.scene.shape.Polygon;
import javafx.scene.paint.Color;

public class Hexagon extends Polygon
{
    private final static double r = 60.0;
    private final static double n = Math.sqrt(r * r * 0.75);
    private final static double TILE_WIDTH = 2 * n;
    private final static double TILE_HEIGHT = 2 * r;
    
    private double startingX = 0;
    private double startingY = 0;
    
    public Hexagon(double x, double y)
    {
        getPoints().addAll(x, y,
                           x, y + r,
                           x + n, y + r * 1.5,
                           x + TILE_WIDTH, y + r,
                           x + TILE_WIDTH, y,
                           x + n, y - r * 0.5);
        
        setFill(Color.WHITE);
        setStrokeWidth(5);
        setStroke(Color.BLACK);
        
        this.startingX = x;
        this.startingY = y;
    }
    
    public double getCenterX()
    {
        return startingX + TILE_WIDTH / 2;
    }
    
    public double getCenterY()
    {
        return startingY + TILE_HEIGHT / 4;
    }
}