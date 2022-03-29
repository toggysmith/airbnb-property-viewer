

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.*;
import java.util.List;
import java.util.ArrayList;
/**
 * The test class ListingProcessorTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class ListingProcessorTest
{
    //use for averageListing() and getBoroughs()
    private static List<AirbnbListing> customListings;
    //use for boroughWithNull()
    private static List<AirbnbListing> nullBoroughNameListing;
    private static List<AirbnbListing> nullList;
    
    private static List<AirbnbListing> customisedListings;
    
    private static List<DestinationListing> customDestinations;
    
    /**
     * Default constructor for test class ListingProcessorTest
     */
    public ListingProcessorTest()
    {
        customListings = createListings();
        setUpNullLists();
        setUpCustomisedListing();
    }
    
    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @BeforeEach
    public void setUp()
    {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @AfterEach
    public void tearDown()
    {
    }
    
    @Test
    public void testGetBoroughs() 
    {
        List<String> boroughs = ListingProcessor.getBoroughs(customListings);
        
        assertEquals(boroughs.get(0), "Lambeth");
        assertEquals(boroughs.get(1),"Westminster");
        assertEquals(boroughs.get(2),"Hammersmith and Fulham" );
        assertEquals(boroughs.get(3),"Tower Hamlets");
        assertEquals(boroughs.get(4),"Kingston upon Thames");
        
        
        assertNotEquals(boroughs.get(3), "Bromley");
        assertNotEquals(boroughs.get(4), "Yorkshire");
    }
    
    @Test
    public void testGetBoroughsNullListPassed()
    {
        List<String> boroughs = ListingProcessor.getBoroughs(nullList);
        assertEquals(0, boroughs.size());
    }
    
    @Test 
    public void testGetBoroughsNameNull()
    {
        List<String> boroughs = ListingProcessor.getBoroughs(nullBoroughNameListing);
        assertEquals(1, boroughs.size());
    }
    
    @Test
    public void testGetBoroughsInvalidBoroughName()
    {
        List<String> boroughs = ListingProcessor.getBoroughs(customListings);
        assertNotEquals(customListings.size(),boroughs.size());
        assertEquals(5,boroughs.size());
    }
    
    @Test
    public void testGetPropertiesNameInBorough()
    {
        List<String> lambeth = ListingProcessor.getPropertiesNameInBorough(customisedListings, "Lambeth");
        assertEquals("Large room, sleeps 3, Brixton", lambeth.get(0));
        
        List<String> westminster = ListingProcessor.getPropertiesNameInBorough(customisedListings, "Westminster");
        assertEquals(3, westminster.size());
        assertEquals("Double bed in Notting Hill", westminster.get(0));
        assertEquals("Double beedroom in Southwark",westminster.get(1));
        assertEquals("Spacious Room next to Richmond Par",westminster.get(2));
        
        List<String> milan = ListingProcessor.getPropertiesNameInBorough(customisedListings, "Milan");
        assertEquals(0,milan.size());
    }
    
    @Test
    public void testGetAveragePositionValidInput()
    {
        Position position1 = new Position(51.46255309285714, -0.1999653592857143);
        Position position2 = ListingProcessor.getAveragePosition(customListings);
        assertEquals(position1.getLatitude(), position2.getLatitude());
        assertEquals(position1.getLongitude(), position2.getLongitude());
    }
    
    @Test
    public void testGetAveragePositionNullInput()
    {
        Position position1 = ListingProcessor.getAveragePosition(null);
        assertNull(position1);
    }
    
    @Test
    public void testGetAveragePositionEmptyListInput()
    {
        Position position1 = ListingProcessor.getAveragePosition(new ArrayList<AirbnbListing>());
        assertNull(position1);
    }
    
    private static List<AirbnbListing> createListings()
    {
        List<AirbnbListing> customList = new ArrayList<>();
        
        AirbnbListing property1 = new AirbnbListing("14403483","Large room, sleeps 3, Brixton","88550548" ,"Allison", "Lambeth",51.47125306,-0.11250696,"Private room",37,2,28,"03/03/2017",4.12,1,254);
        AirbnbListing property2 = new AirbnbListing("9957622","Double bed in Notting Hill","51168635","Serge","Westminster",51.51782111,-0.192291889,"Private room",35,2,18, "30/12/2016",1.67,1,0);
        AirbnbListing property3 = new AirbnbListing("7483279","Double beedroom in Southwark", "78372", "Adam", "Hammersmith and Fulham", 51.46977981,-0.189799402,"Entire home/apt",195,1,0,"12/8/2015",1.00,1,0);
        AirbnbListing property4 = new AirbnbListing("7833588","Bright DOUBLE ROOM, central LONDON","34472628","Tommaso","Tower Hamlets",51.52066587,-0.056124665,"Private room",45,1,7,"07/12/2015",0.37,1,0);
        AirbnbListing property5 = new AirbnbListing("9020269","Spacious Room next to Richmond Par","47094767", "Marcus", "Kingston upon Thames",51.41945318,-0.286341833,"Private room",30,1,1,"09/10/2016",0.65,2,35);
        AirbnbListing invalidProperty = new AirbnbListing("447382","Spacious Room next to Richmond Par","47094767", "Marcus", "Milan",51.41944544,-0.286346933,"Private room",30,1,1,"09/10/2016",0.65,2,31);
        AirbnbListing invalidProperty2 = new AirbnbListing("382400","Spacious Room next to Richmond Par","47094767", "Marcus", "Coppenhagen",51.41945318,-0.276345833,"Private room",30,1,1,"09/10/2017",0.90,2,35);
        
        customList.add(property1);
        customList.add(property2);
        customList.add(property3);
        customList.add(property4);
        customList.add(property5);
        customList.add(invalidProperty);
        customList.add(invalidProperty2);        
        return customList;
    }
    
    private static void setUpNullLists()
    {
        nullList = new ArrayList<AirbnbListing>();
        nullBoroughNameListing = new ArrayList<AirbnbListing>();
        
        AirbnbListing invalidProperty = new AirbnbListing("447382","Spacious Room next to Richmond Par","47094767", "Marcus", null,51.41944544,-0.286346933,"Private room",30,1,1,"09/10/2016",0.65,2,31);
        AirbnbListing invalidProperty2 = new AirbnbListing("382400","Spacious Room next to Richmond Par","47094767", "Marcus", "Westminster",51.41945318,-0.276345833,"Private room",30,1,1,"09/10/2017",0.90,2,35);
        
        nullBoroughNameListing.add(invalidProperty);
        nullBoroughNameListing.add(invalidProperty2);
    }
    
    private static void setUpCustomisedListing()
    {
        customisedListings = new ArrayList<>();
        
        AirbnbListing property1 = new AirbnbListing("14403483","Large room, sleeps 3, Brixton","88550548" ,"Allison", "Lambeth",51.47125306,-0.11250696,"Private room",37,2,28,"03/03/2017",4.12,1,254);
        AirbnbListing property2 = new AirbnbListing("9957622","Double bed in Notting Hill","51168635","Serge","Westminster",51.51782111,-0.192291889,"Private room",35,2,18, "30/12/2016",1.67,1,0);
        AirbnbListing property3 = new AirbnbListing("7483279","Double beedroom in Southwark", "78372", "Adam", "Westminster", 51.46977981,-0.189799402,"Entire home/apt",195,1,0,"12/8/2015",1.00,1,0);
        AirbnbListing property4 = new AirbnbListing("7833588","Bright DOUBLE ROOM, central LONDON","34472628","Tommaso","Tower Hamlets",51.52066587,-0.056124665,"Private room",45,1,7,"07/12/2015",0.37,1,0);
        AirbnbListing property5 = new AirbnbListing("9020269","Spacious Room next to Richmond Par","47094767", "Marcus", "Westminster",51.41945318,-0.286341833,"Private room",30,1,1,"09/10/2016",0.65,2,35);
        AirbnbListing invalidProperty = new AirbnbListing("447382","Spacious Room next to Richmond Par","47094767", "Marcus", "Milan",51.41944544,-0.286346933,"Private room",30,1,1,"09/10/2016",0.65,2,31);
        AirbnbListing invalidProperty2 = new AirbnbListing("382400","Spacious Room next to Richmond Par","47094767", "Marcus", "Milan",51.41945318,-0.276345833,"Private room",30,1,1,"09/10/2017",0.90,2,35);
        
        customisedListings.add(property1);
        customisedListings.add(property2);
        customisedListings.add(property3);
        customisedListings.add(property4);
        customisedListings.add(property5);
        customisedListings.add(invalidProperty);
        customisedListings.add(invalidProperty2);
    }
    
    private static void setUpDestinations()
    {
        customDestinations = new ArrayList<>();
        DestinationListing destination1 = new DestinationListing("Tower of London", "Tower of London", 51.42236388,-0.299202059,"Tower Hamlet", "free");
       // DestinationListing destination2 = new DestinationListing();
        
    }

    
    @Test
    public void testGetListingPricesValidInput()
    {
        int[] array1 = {37, 35, 195, 45, 30, 30, 30};
        int[] test = ListingProcessor.getListingPrices(customisedListings);
        assertEquals(array1.length, test.length);
        for (int i = 0; i < array1.length && i < test.length; i++)
        {
            assertEquals(array1[i], test[i]);
        }
    }
    
    @Test
    public void testGetListingPricesEmptyInput()
    {
        int[] empty = {};
        int[] test = ListingProcessor.getListingPrices(new ArrayList<AirbnbListing>());
        assertEquals(empty.length, test.length);
        for (int i = 0; i < empty.length && i < test.length; i++)
        {
            assertEquals(empty[i], test[i]);
        }
    }
    
    @Test
    public void testGetListingPricesNullInput()
    {
        int[] empty = {};
        int[] test = ListingProcessor.getListingPrices(nullList);
        assertEquals(empty.length, test.length);
        for (int i = 0; i < empty.length && i < test.length; i++)
        {
            assertEquals(empty[i], test[i]);
        }
    }
    
    @Test
    public void testGetListingReviewsValidInput()
    {
        int[] array1 = {28, 18, 0, 7, 1, 1, 1};
        int[] test = ListingProcessor.getListingReviews(customisedListings);
        assertEquals(array1.length, test.length);
        for (int i = 0; i < array1.length && i < test.length; i++)
        {
            assertEquals(array1[i], test[i]);
        }
    }
    
    @Test
    public void testGetListingReviewsEmptyInput()
    {
        int[] empty = {};
        int[] test = ListingProcessor.getListingReviews(new ArrayList<AirbnbListing>());
        assertEquals(empty.length, test.length);
        for (int i = 0; i < empty.length && i < test.length; i++)
        {
            assertEquals(empty[i], test[i]);
        }
    }
    
    @Test
    public void testGetListingReviewsNullInput()
    {
        int[] empty = {};
        int[] test = ListingProcessor.getListingReviews(nullList);
        assertEquals(empty.length, test.length);
        for (int i = 0; i < empty.length && i < test.length; i++)
        {
            assertEquals(empty[i], test[i]);
        }
    }
    
    @Test
    public void testGgetListingMinNightsValidInput()
    {
        int[] array1 = {2, 2, 1, 1, 1, 1, 1};
        int[] test = ListingProcessor.getListingMinNights(customisedListings);
        assertEquals(array1.length, test.length);
        for (int i = 0; i < array1.length && i < test.length; i++)
        {
            assertEquals(array1[i], test[i]);
        }
    }
    
    @Test
    public void testGetListingMinNightsEmptyInput()
    {
        int[] empty = {};
        int[] test = ListingProcessor.getListingMinNights(new ArrayList<AirbnbListing>());
        assertEquals(empty.length, test.length);
        for (int i = 0; i < empty.length && i < test.length; i++)
        {
            assertEquals(empty[i], test[i]);
        }
    }
    
    @Test
    public void testGetListingMinNightsNullInput()
    {
        int[] empty = {};
        int[] test = ListingProcessor.getListingMinNights(nullList);
        assertEquals(empty.length, test.length);
        for (int i = 0; i < empty.length && i < test.length; i++)
        {
            assertEquals(empty[i], test[i]);
        }
    }
}



