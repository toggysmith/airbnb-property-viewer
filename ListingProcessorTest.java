

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
    private static List<AirbnbListing> customListings;
    /**
     * Default constructor for test class ListingProcessorTest
     */
    public ListingProcessorTest()
    {
        customListings = createListings();
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
        
        assertNotEquals(customListings.size(),boroughs.size());
        
    }
    
    
    
    private static List<AirbnbListing> createListings()
    {
        List<AirbnbListing> customList = new ArrayList<>();
        
        AirbnbListing property1 = new AirbnbListing("14403483","Large room, sleeps 3, Brixton","88550548" ,"Allison", "Lambeth",51.47125306,-0.11250696,"Private room",37,2,28,"03/03/2017",4.12,1,254);
        AirbnbListing property2 = new AirbnbListing("9957622","Double bed in Notting Hill","51168635","Serge","Westminster",51.51782111,-0.192291889,"Private room",35,2,18, "30/12/2016",1.67,1,0);
        AirbnbListing property3 = new AirbnbListing("7483279","Double beedroom in Southwark", "78372", "Adam", "Hammersmith and Fulham", 51.46977981,-0.189799402,"Entire home/apt",195,1,0,"12/8/2015",1.00,1,0);
        AirbnbListing property4 = new AirbnbListing("7833588","Bright DOUBLE ROOM, central LONDON","34472628","Tommaso","Tower Hamlets",51.52066587,-0.056124665,"Private room",45,1,7,"07/12/2015",0.37,1,0);
        AirbnbListing property5 = new AirbnbListing("9020269","Spacious Room next to Richmond Par","47094767", "Marcus", "Kingston upon Thames",51.41945318,-0.286341833,"Private room",30,1,1,"09/10/2018",0.65,2,35);
        AirbnbListing invalidProperty = new AirbnbListing("9020269","Spacious Room next to Richmond Par","47094767", "Marcus", "Milan",51.41945318,-0.286341833,"Private room",30,1,1,"09/10/2018",0.65,2,35);
        AirbnbListing invalidProperty2 = new AirbnbListing("9020269","Spacious Room next to Richmond Par","47094767", "Marcus", "Coppenhagen",51.41945318,-0.286341833,"Private room",30,1,1,"09/10/2018",0.65,2,35);
        
        customList.add(property1);
        customList.add(property2);
        customList.add(property3);
        customList.add(property4);
        customList.add(property5);
        customList.add(invalidProperty);
        customList.add(invalidProperty2);
        
        return customList;
    }
    
    @Test
    public void testGetAveragePositionValidInput()
    {
        Position position1 = new Position(51.462554198571425, -0.2013926307142857);
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
}

