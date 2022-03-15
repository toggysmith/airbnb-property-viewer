public enum BoroughEnum
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
    
    public final int X;
    public final int Y;
    public final String ABBREVIATION;
    public final String NAME;
    
    private BoroughEnum(int x, int y, String name)
    {
        this.X = x;
        this.Y = y;
        this.ABBREVIATION = name(); // Because calling BoroughEnum.name() and BoroughEnum.NAME is confusing
        this.NAME = name;
    }
}