/**
 * Responsible for holding every borough on the map along with their relative x- and y-coordinates, long name and
 * the abbreviation of their name.
 *
 * @author Adam Murray (K21003575)
 * @author Augusto Favero (K21059800)
 * @author Mathew Tran (K21074020)
 * @author Tony Smith (K21064940)
 * @version 1.0.0
 */
public enum Borough
{
    // Row 1:
    ENFI(3, 0, "Enfield"),
    // Row 2:
    BARN(2, 1, "Barnet"),
    HRGY(3, 1, "Haringey"),
    WALT(4, 1, "Waltham Forest"),
    // Row 3:
    HRRW(0, 2, "Harrow"),
    BREN(1, 2, "Brent"),
    CAMD(2, 2, "Camden"),
    ISLI(3, 2, "Islington"),
    HACK(4, 2, "Hackney"),
    REDB(5, 2, "Redbridge"),
    HAVE(6, 2, "Havering"),
    // Row 4:
    HILL(0, 3, "Hillingdon"),
    EALI(1, 3, "Ealing"),
    KENS(2, 3, "Kensington and Chelsea"),
    WSTM(3, 3, "Westminster"),
    TOWH(4, 3, "Tower Hamlets"),
    NEWH(5, 3, "Newham"),
    BARK(6, 3, "Barking and Dagenham"),
    // Row 5: 
    HOUN(0, 4, "Hounslow"),
    HAMM(1, 4, "Hammersmith and Fulham"),
    WAND(2, 4, "Wandsworth"),
    CITY(3, 4, "City of London"),
    GWCH(4, 4, "Greenwich"),
    BEXL(5, 4, "Bexley"),
    // Row 6:
    RICH(1, 5, "Richmond upon Thames"),
    MERT(2, 5, "Merton"),
    LAMB(3, 5, "Lambeth"),
    STHW(4, 5, "Southwark"),
    LEWS(5, 5, "Lewisham"),
    // Row 7:
    KING(1, 6, "Kingston upon Thames"),
    SUTT(2, 6, "Sutton"),
    CROY(3, 6, "Croydon"),
    BROM(4, 6, "Bromley");
    
    /**
     * The relative x-position of the borough on the map.
     */
    private final int X;
    
    /**
     * The relative y-position of the borough on the map.
     */
    private final int Y;
    
    /**
     * The long name of the borough. (E.g. "Kingston upon Thames" instead of "KING")
     */
    private final String NAME;
    
    /**
     * Initialises the enum fields.
     * @param x The relative x-position of the borough on the map.
     * @param y The relative y-position of the borough on the map.
     * @param name The long name of the borough.
     */
    Borough(int x, int y, String name)
    {
        this.X = x;
        this.Y = y;
        this.NAME = name;
    }
    
    /**
     * @return The relative x-position of the borough on the map.
     */
    public int getX() { return X; }
    
    /**
     * @return The relative y-position of the borough on the map.
     */
    public int getY() { return Y; }
    
    /**
     * @return The long name of the borough. (E.g. "Kingston upon Thames" instead of "KING")
     */
    public String getName() { return NAME; }
    
    /**
     * @return The abbreviation of the borough's name. (E.g. "KING" instead of "Kingston upon Thames"). NOTE: This is
     *         the same as the enum's name and therefore `name()` could be used instead but this is confusing given
     *         the `NAME` field and `getName()` methods.
     */
    public String getAbbreviation() { return name(); }
}