
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.*;
import org.junit.rules.ExpectedException;
import java.util.List;
import java.util.ArrayList;
import org.junit.jupiter.api.Assertions;

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
        setUpDestinations();
    }
    
    /**
     * Test that `filterByBorough()` returns the correct list when given valid arguments.
     */
    @Test
    public void testFilterByBoroughWithValidArguments()
    {
        List<AirbnbListing> listings = new ArrayList<>();
        
        AirbnbListing listing1 = new AirbnbListing("7483279", "Double beedroom in Southwark", "78372", "Adam", "Hammersmith and Fulham", 51.46977981, -0.189799402, "Entire home/apt", 195, 1, 0, "12/8/2015", 1.00, 1, 0);
        AirbnbListing listing2 = new AirbnbListing("2584302", "Epic room in LONDON!", "23426", "Jeff", "Hammersmith and Fulham", 51.21937781, -0.133779402, "Private room", 195, 1, 0, "12/8/2015", 1.00, 1, 0);

        listings.add(new AirbnbListing("14403483", "Large room, sleeps 3, Brixton", "88550548", "Allison", "Lambeth", 51.47125306, -0.11250696, "Private room", 37, 2, 28, "03/03/2017", 4.12, 1, 254));
        listings.add(new AirbnbListing("9957622", "Double bed in Notting Hill", "51168635", "Serge", "Westminster", 51.51782111, -0.192291889, "Private room", 35, 2, 18, "30/12/2016", 1.67, 1, 0));
        listings.add(listing1);
        listings.add(listing2);
        listings.add(new AirbnbListing("7833588", "Bright DOUBLE ROOM, central LONDON", "34472628", "Tommaso", "Tower Hamlets", 51.52066587, -0.056124665, "Private room", 45, 1, 7, "07/12/2015", 0.37, 1, 0));
        listings.add(new AirbnbListing("9020269", "Spacious Room next to Richmond Par", "47094767", "Marcus", "Kingston upon Thames", 51.41945318, -0.286341833, "Private room", 30, 1, 1, "09/10/2016", 0.65, 2, 35));
        
        final List<AirbnbListing> filteredListings = ListingProcessor.filterByBorough(listings, "Hammersmith and Fulham");
        
        Assertions.assertAll(
            () -> assertEquals(filteredListings.size(), 2),
            () -> assertEquals(filteredListings.get(0), listing1),
            () -> assertEquals(filteredListings.get(1), listing2)
        );
    }
    
    /**
     * Test that `filterByBorough()` throws an IllegalArgumentException with the right message when given a null listings argument.
     */
    @Test
    public void testFilterByBoroughWithNullListings()
    {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
                    List<AirbnbListing> listings = null;

                    ListingProcessor.filterByBorough(listings, "Lambeth");
        });

        assertEquals("The provided listings argument is invalid.", exception.getMessage());
    }

    /**
     * Test that `filterByBorough()` throws an IllegalArgumentException with the right message when given an empty listings argument.
     */
    @Test
    public void testFilterByBoroughWithEmptyListings()
    {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
                    List<AirbnbListing> listings = new ArrayList<>();

                    ListingProcessor.filterByBorough(listings, "Lambeth");
        });

        assertEquals("The provided listings argument is invalid.", exception.getMessage());
    }

    /**
     * Test that `filterByBorough()` throws an IllegalArgumentException with the right message when given a null borough name argument.
     */
    @Test
    public void testFilterByBoroughWithNullBoroughName()
    {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
                    List<AirbnbListing> listings = new ArrayList<>();

                    listings.add(new AirbnbListing("14403483", "Large room, sleeps 3, Brixton", "88550548", "Allison", "Lambeth", 51.47125306, -0.11250696, "Private room", 37, 2, 28, "03/03/2017", 4.12, 1, 254));
                    
                    ListingProcessor.filterByBorough(listings, null);
        });
        
        assertEquals("The provided borough name argument is invalid.", exception.getMessage());
    }

    /**
     * Test that `filterByBorough()` throws an IllegalArgumentException with the right message when given an illegal (not in the list of known boroughs) borough name argument.
     */
    @Test
    public void testFilterByBoroughWithIllegalBoroughName()
    {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
                    List<AirbnbListing> listings = new ArrayList<>();

                    listings.add(new AirbnbListing("14403483", "Large room, sleeps 3, Brixton", "88550548", "Allison", "Lambeth", 51.47125306, -0.11250696, "Private room", 37, 2, 28, "03/03/2017", 4.12, 1, 254));

                    ListingProcessor.filterByBorough(listings, "Illegal Borough Name");
        });
        
        assertEquals("The provided borough name argument is invalid.", exception.getMessage());
    }
    
    /**
     * Test that `filterByPriceRange()` returns the correct list when given valid arguments.
     */
    @Test
    public void testFilterByPriceRangeWithValidArguments()
    {
        List<AirbnbListing> listings = new ArrayList<>();
        
        AirbnbListing listing1 = new AirbnbListing("7483279", "Double beedroom in Southwark", "78372", "Adam", "Hammersmith and Fulham", 51.46977981, -0.189799402, "Entire home/apt", 160, 1, 0, "12/8/2015", 1.00, 1, 0);
        AirbnbListing listing2 = new AirbnbListing("2584302", "Epic room in LONDON!", "23426", "Jeff", "Hammersmith and Fulham", 51.21937781, -0.133779402, "Private room", 200, 1, 0, "12/8/2015", 1.00, 1, 0);

        listings.add(new AirbnbListing("14403483", "Large room, sleeps 3, Brixton", "88550548", "Allison", "Lambeth", 51.47125306, -0.11250696, "Private room", 37, 2, 28, "03/03/2017", 4.12, 1, 254));
        listings.add(new AirbnbListing("9957622", "Double bed in Notting Hill", "51168635", "Serge", "Westminster", 51.51782111, -0.192291889, "Private room", 35, 2, 18, "30/12/2016", 1.67, 1, 0));
        listings.add(listing1);
        listings.add(listing2);
        listings.add(new AirbnbListing("7833588", "Bright DOUBLE ROOM, central LONDON", "34472628", "Tommaso", "Tower Hamlets", 51.52066587, -0.056124665, "Private room", 231, 1, 7, "07/12/2015", 0.37, 1, 0));
        listings.add(new AirbnbListing("9020269", "Spacious Room next to Richmond Par", "47094767", "Marcus", "Kingston upon Thames", 51.41945318, -0.286341833, "Private room", 30, 1, 1, "09/10/2016", 0.65, 2, 35));
        
        final List<AirbnbListing> filteredListings = ListingProcessor.filterByPriceRange(listings, 160, 200);
        
        Assertions.assertAll(
            () -> assertEquals(filteredListings.size(), 2),
            () -> assertEquals(filteredListings.get(0), listing1),
            () -> assertEquals(filteredListings.get(1), listing2)
        );
    }
    
    /**
     * Test that `filterByPriceRange()` throws an IllegalArgumentException with the right message when given a null listings argument.
     */
    @Test
    public void testFilterByPriceRangeWithNullListings()
    {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
                    List<AirbnbListing> listings = null;

                    ListingProcessor.filterByPriceRange(listings, 20, 40);
        });

        assertEquals("The provided listings argument is invalid.", exception.getMessage());
    }

    /**
     * Test that `filterByPriceRange()` throws an IllegalArgumentException with the right message when given an empty listings argument.
     */
    @Test
    public void testFilterByPriceRangeWithEmptyListings()
    {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
                    List<AirbnbListing> listings = new ArrayList<>();

                    ListingProcessor.filterByPriceRange(listings, 20, 40);
        });

        assertEquals("The provided listings argument is invalid.", exception.getMessage());
    }
    
    /**
     * Test that `filterByPriceRange()` throws an IllegalArgumentException with the right message when given illegal (the from-price is greater than the to-price) price range arguments.
     */
    @Test
    public void testFilterByPriceRangeWithIllegalPriceRange()
    {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            List<AirbnbListing> listings = new ArrayList<>();
            
            listings.add(new AirbnbListing("14403483", "Large room, sleeps 3, Brixton", "88550548", "Allison", "Lambeth", 51.47125306, -0.11250696, "Private room", 37, 2, 28, "03/03/2017", 4.12, 1, 254));
            listings.add(new AirbnbListing("9957622", "Double bed in Notting Hill", "51168635", "Serge", "Westminster", 51.51782111, -0.192291889, "Private room", 35, 2, 18, "30/12/2016", 1.67, 1, 0));
            listings.add(new AirbnbListing("7833588", "Bright DOUBLE ROOM, central LONDON", "34472628", "Tommaso", "Tower Hamlets", 51.52066587, -0.056124665, "Private room", 45, 1, 7, "07/12/2015", 0.37, 1, 0));
            listings.add(new AirbnbListing("9020269", "Spacious Room next to Richmond Par", "47094767", "Marcus", "Kingston upon Thames", 51.41945318, -0.286341833, "Private room", 30, 1, 1, "09/10/2016", 0.65, 2, 35));
            
            ListingProcessor.filterByPriceRange(listings, 40, 20);
        });
        
        assertEquals("The from-price argument cannot be greater than the to-price argument.", exception.getMessage());
    }
    
    /**
     * Test that `getNoOfPropertiesInBoroughWithMost()` returns the correct list when given valid arguments.
     */
    @Test
    public void testGetNoOfPropertiesInBoroughWithMostWithValidArguments()
    {
        List<AirbnbListing> listings = new ArrayList<>();
        
        listings.add(new AirbnbListing("14403483", "Large room, sleeps 3, Brixton", "88550548", "Allison", "Lambeth", 51.47125306, -0.11250696, "Private room", 37, 2, 28, "03/03/2017", 4.12, 1, 254));
        listings.add(new AirbnbListing("9957622", "Double bed in Notting Hill", "51168635", "Serge", "Westminster", 51.51782111, -0.192291889, "Private room", 35, 2, 18, "30/12/2016", 1.67, 1, 0));
        listings.add(new AirbnbListing("7483279", "Double beedroom in Southwark", "78372", "Adam", "Hammersmith and Fulham", 51.46977981, -0.189799402, "Entire home/apt", 160, 1, 0, "12/8/2015", 1.00, 1, 0));
        listings.add(new AirbnbListing("2584302", "Epic room in LONDON!", "23426", "Jeff", "Hammersmith and Fulham", 51.21937781, -0.133779402, "Private room", 200, 1, 0, "12/8/2015", 1.00, 1, 0));
        listings.add(new AirbnbListing("7833588", "Bright DOUBLE ROOM, central LONDON", "34472628", "Tommaso", "Hammersmith and Fulham", 51.52066587, -0.056124665, "Private room", 231, 1, 7, "07/12/2015", 0.37, 1, 0));
        listings.add(new AirbnbListing("9020269", "Spacious Room next to Richmond Par", "47094767", "Marcus", "Kingston upon Thames", 51.41945318, -0.286341833, "Private room", 30, 1, 1, "09/10/2016", 0.65, 2, 35));
        
        final int noOfPropertiesInBoroughWithMost = ListingProcessor.getNoOfPropertiesInBoroughWithMost(listings);
        
        assertEquals(noOfPropertiesInBoroughWithMost, 3); // The borough with the most properties (Hammersmith and Fulham) has three properties in it.
    }
    
    /**
     * Test that `getNoOfPropertiesInBoroughWithMost()` throws an IllegalArgumentException with the right message when given a null listings argument.
     */
    @Test
    public void testGetNoOfPropertiesInBoroughWithMostWithNullListings()
    {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
                    List<AirbnbListing> listings = null;

                    ListingProcessor.getNoOfPropertiesInBoroughWithMost(listings);
        });

        assertEquals("The provided listings argument is invalid.", exception.getMessage());
    }

    /**
     * Test that `getNoOfPropertiesInBoroughWithMost()` throws an IllegalArgumentException with the right message when given an empty listings argument.
     */
    @Test
    public void testGetNoOfPropertiesInBoroughWithMostWithEmptyListings()
    {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
                    List<AirbnbListing> listings = new ArrayList<>();

                    ListingProcessor.getNoOfPropertiesInBoroughWithMost(listings);
        });

        assertEquals("The provided listings argument is invalid.", exception.getMessage());
    }
    
    /**
     * Test that `getMaxPropertyPrice()` throws an IllegalArgumentException with the right message when given a null listings argument.
     */
    @Test
    public void testGetMaxPropertyPriceWithNullListings()
    {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
                    List<AirbnbListing> listings = null;

                    ListingProcessor.getMaxPropertyPrice(listings);
        });

        assertEquals("The provided listings argument is invalid.", exception.getMessage());
    }

    /**
     * Test that `getMaxPropertyPrice()` throws an IllegalArgumentException with the right message when given an empty listings argument.
     */
    @Test
    public void testGetMaxPropertyPriceWithEmptyListings()
    {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
                    List<AirbnbListing> listings = new ArrayList<>();

                    ListingProcessor.getMaxPropertyPrice(listings);
        });

        assertEquals("The provided listings argument is invalid.", exception.getMessage());
    }
    
    /**
     * Test that `getMaxPropertyPrice()` returns the correct list when given valid arguments.
     */
    @Test
    public void testGetMaxPropertyPriceWithValidArguments()
    {
        List<AirbnbListing> listings = new ArrayList<>();
        
        listings.add(new AirbnbListing("14403483", "Large room, sleeps 3, Brixton", "88550548", "Allison", "Lambeth", 51.47125306, -0.11250696, "Private room", 37, 2, 28, "03/03/2017", 4.12, 1, 254));
        listings.add(new AirbnbListing("9957622", "Double bed in Notting Hill", "51168635", "Serge", "Westminster", 51.51782111, -0.192291889, "Private room", 35, 2, 18, "30/12/2016", 1.67, 1, 0));
        listings.add(new AirbnbListing("7483279", "Double beedroom in Southwark", "78372", "Adam", "Hammersmith and Fulham", 51.46977981, -0.189799402, "Entire home/apt", 160, 1, 0, "12/8/2015", 1.00, 1, 0));
        listings.add(new AirbnbListing("2584302", "Epic room in LONDON!", "23426", "Jeff", "Hammersmith and Fulham", 51.21937781, -0.133779402, "Private room", 200, 1, 0, "12/8/2015", 1.00, 1, 0));
        listings.add(new AirbnbListing("7833588", "Bright DOUBLE ROOM, central LONDON", "34472628", "Tommaso", "Hammersmith and Fulham", 51.52066587, -0.056124665, "Private room", 231, 1, 7, "07/12/2015", 0.37, 1, 0));
        listings.add(new AirbnbListing("9020269", "Spacious Room next to Richmond Par", "47094767", "Marcus", "Kingston upon Thames", 51.41945318, -0.286341833, "Private room", 30, 1, 1, "09/10/2016", 0.65, 2, 35));
        
        final int maxPropertyPrice = ListingProcessor.getMaxPropertyPrice(listings);
        
        assertEquals(maxPropertyPrice, 231);
    }
    
    /**
     * Test that `getListingWithId()` throws an IllegalArgumentException with the right message when given a null listings argument.
     */
    @Test
    public void testGetListingWithIdWithNullListings()
    {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
                    List<AirbnbListing> listings = null;

                    ListingProcessor.getListingWithId(listings, "7833588");
        });

        assertEquals("The provided listings argument is invalid.", exception.getMessage());
    }

    /**
     * Test that `getListingWithId()` throws an IllegalArgumentException with the right message when given an empty listings argument.
     */
    @Test
    public void testGetListingWithIdWithEmptyListings()
    {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
                    List<AirbnbListing> listings = new ArrayList<>();

                    ListingProcessor.getListingWithId(listings, "7833588");
        });

        assertEquals("The provided listings argument is invalid.", exception.getMessage());
    }
    
    /**
     * Test that `getListingWithId()` throws an IllegalArgumentException with the right message when given a null ID argument.
     */
    @Test
    public void testGetListingWithIdWithNullId()
    {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
                    List<AirbnbListing> listings = new ArrayList<>();
        
                    listings.add(new AirbnbListing("14403483", "Large room, sleeps 3, Brixton", "88550548", "Allison", "Lambeth", 51.47125306, -0.11250696, "Private room", 37, 2, 28, "03/03/2017", 4.12, 1, 254));
                    listings.add(new AirbnbListing("9957622", "Double bed in Notting Hill", "51168635", "Serge", "Westminster", 51.51782111, -0.192291889, "Private room", 35, 2, 18, "30/12/2016", 1.67, 1, 0));
                    listings.add(new AirbnbListing("2584302", "Epic room in LONDON!", "23426", "Jeff", "Hammersmith and Fulham", 51.21937781, -0.133779402, "Private room", 200, 1, 0, "12/8/2015", 1.00, 1, 0));
                    listings.add(new AirbnbListing("7833588", "Bright DOUBLE ROOM, central LONDON", "34472628", "Tommaso", "Hammersmith and Fulham", 51.52066587, -0.056124665, "Private room", 231, 1, 7, "07/12/2015", 0.37, 1, 0));
                    listings.add(new AirbnbListing("9020269", "Spacious Room next to Richmond Par", "47094767", "Marcus", "Kingston upon Thames", 51.41945318, -0.286341833, "Private room", 30, 1, 1, "09/10/2016", 0.65, 2, 35));
                    
                    final AirbnbListing matchingListing = ListingProcessor.getListingWithId(listings, null);
        });
        
        assertEquals("The id argument cannot be null.", exception.getMessage());
    }
    
    /**
     * Test that `getListingWithId()` returns the correct list when given valid arguments.
     */
    @Test
    public void testGetListingWithIdWithValidArguments()
    {
        List<AirbnbListing> listings = new ArrayList<>();
        
        AirbnbListing listing1 = new AirbnbListing("7483279", "Double beedroom in Southwark", "78372", "Adam", "Hammersmith and Fulham", 51.46977981, -0.189799402, "Entire home/apt", 160, 1, 0, "12/8/2015", 1.00, 1, 0);
        
        listings.add(new AirbnbListing("14403483", "Large room, sleeps 3, Brixton", "88550548", "Allison", "Lambeth", 51.47125306, -0.11250696, "Private room", 37, 2, 28, "03/03/2017", 4.12, 1, 254));
        listings.add(new AirbnbListing("9957622", "Double bed in Notting Hill", "51168635", "Serge", "Westminster", 51.51782111, -0.192291889, "Private room", 35, 2, 18, "30/12/2016", 1.67, 1, 0));
        listings.add(listing1);
        listings.add(new AirbnbListing("2584302", "Epic room in LONDON!", "23426", "Jeff", "Hammersmith and Fulham", 51.21937781, -0.133779402, "Private room", 200, 1, 0, "12/8/2015", 1.00, 1, 0));
        listings.add(new AirbnbListing("7833588", "Bright DOUBLE ROOM, central LONDON", "34472628", "Tommaso", "Hammersmith and Fulham", 51.52066587, -0.056124665, "Private room", 231, 1, 7, "07/12/2015", 0.37, 1, 0));
        listings.add(new AirbnbListing("9020269", "Spacious Room next to Richmond Par", "47094767", "Marcus", "Kingston upon Thames", 51.41945318, -0.286341833, "Private room", 30, 1, 1, "09/10/2016", 0.65, 2, 35));
        
        final AirbnbListing matchingListing = ListingProcessor.getListingWithId(listings, "7483279");
        
        assertEquals(matchingListing, listing1);
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
        assertEquals("Double bedroom in Southwark",westminster.get(1));
        assertEquals("Spacious Room next to Richmond Par",westminster.get(2));

        List<String> milan = ListingProcessor.getPropertiesNameInBorough(customisedListings, "Milan");
        assertEquals(0,milan.size());
        
        List<String> empty = ListingProcessor.getPropertiesNameInBorough(customisedListings, null);
        assertEquals(empty.isEmpty(), true);
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
    
    /**
     * Test that if an invalid price string is entered into the .filterDestinations method then and IllegalArgumentException is thrown
     */
    @Test
    public void testFilterDestinationByWrongPricePassedAttraction()
    {
        DestinationListing invalidPriceDestination = new DestinationListing("Buckingham Palace", "London SW1A 1AA",  51.46977981,-0.189799402,"Tower Hamlet", "50");
        
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
                List<DestinationListing> invalidList = new ArrayList<>();
                invalidList.add(invalidPriceDestination);
                
                ListingProcessor.filterDestinations(invalidList, "Tower Hamlet", "50");
        });
        assertEquals("Invalid Price Passed", exception.getMessage());
    }
    
    /**
     * Test that if an invalid price string is entered into the .filterDestinations method then and IllegalArgumentException is thrown
     */
    @Test
    public void testFilterDestinationByWrongPricePassedPub()
    {
        DestinationListing invalidPriceDestination = new DestinationListing("Great Dover Pub", "London SW1A 1AA",  51.46977981,-0.189799402,"Tower Hamlet", "££££");
        
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
                List<DestinationListing> invalidList = new ArrayList<>();
                invalidList.add(invalidPriceDestination);
                
                ListingProcessor.filterDestinations(invalidList, "Tower Hamlet", "££££");
        });
        assertEquals("Invalid Price Passed", exception.getMessage());
    }
    
    @Test
    public void testFilterDestinations()
    {
        List<DestinationListing> filteredDestinationsFree = ListingProcessor.filterDestinations(customDestinations, "Tower Hamlets", "free");
        
        assertEquals(2, filteredDestinationsFree.size());
        
        List<DestinationListing> filteredDestinationsWrongBorough = ListingProcessor.filterDestinations(customDestinations, "Lewisham",  "£2.50 - £5.00");
        assertEquals(0,filteredDestinationsWrongBorough.size());
        
        
        List<DestinationListing> filteredDestinationsNonExistingPrice = ListingProcessor.filterDestinations(customDestinations, "Westminster",  "£5.00 - £7.00");
        assertEquals(0, filteredDestinationsNonExistingPrice.size());
        
        
        DestinationListing destination1 = new DestinationListing("Pub1", "Pub1", 51.42236388,-0.299202059,"Tower Hamlets", "££");
        DestinationListing destination2 = new DestinationListing("Pub2", "Pub2,", 51.38442425,-0.263391118,"Tower Hamlets", "££");
        
        List<DestinationListing> pubs = new ArrayList<>();
        pubs.add(destination1);
        pubs.add(destination2);
        
        List<DestinationListing> pubsTower = ListingProcessor.filterDestinations(pubs, "Tower Hamlets", "££");
        assertEquals(2,pubsTower.size());
        
        List<DestinationListing> emptyBorough = ListingProcessor.filterDestinations(pubs, null, "££");
        assertEquals(emptyBorough.isEmpty(), true);
        
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
                List<DestinationListing> emptyPrice = ListingProcessor.filterDestinations(pubs, "Tower Hamlets", null);
        });
        assertEquals("Invalid Price Passed", exception.getMessage());
    }

    @Test
    public void testGetPropertyListingByNames()
    {
     AirbnbListing property = ListingProcessor.getPropertyListingByNames(customisedListings,"Double bedroom in Southwark","Westminster");
     assertEquals("Double bedroom in Southwark",property.getName());
     assertNotEquals("IncorrectName",property.getName());
       
     
     AirbnbListing propertyWrong = ListingProcessor.getPropertyListingByNames(customisedListings,"Incorrect name","Westminster");
     assertEquals(propertyWrong,null);
     
     Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
                AirbnbListing propertyNull = ListingProcessor.getPropertyListingByNames(customisedListings,null,"Westminster");
        });
     assertEquals("Invalid listing name provided", exception.getMessage());
     
       
     AirbnbListing propertyBorough = ListingProcessor.getPropertyListingByNames(customisedListings,"Double bedroom in Southwark","Incorrect Borough Name");
     assertEquals(propertyBorough, null);
     
     Throwable exception1 = assertThrows(IllegalArgumentException.class, () -> {
                AirbnbListing boroughNull = ListingProcessor.getPropertyListingByNames(customisedListings,"Double bedroom in Southwark",null);
        });
     assertEquals("Invalid borough name provided", exception1.getMessage());
     
    }
    
    @Test
    public void testGetMin()
    {
        int[] array1 = {37, 35, 195, 45, 30, 30, 30};
        int[] emptyArray = {};
        int[] array2 = {-1,20,45,23,12};
        
        int actualMin = ListingProcessor.getMin(array1);
        assertEquals(30, actualMin);
        
        int negativeMin = ListingProcessor.getMin(array2);
        assertEquals(-1,negativeMin);
        
        int empty = ListingProcessor.getMin(emptyArray);
        assertEquals(0,empty);
    }
    
    @Test
    public void testGetMax()
    {
        int[] array1 = {37, 35, 195, 45, 30, 30, 30};
        int[] emptyArray = {};
        int[] array2 = {-1,-3,-4,0};
        
        int actualMax = ListingProcessor.getMax(array1);
        assertEquals(195, actualMax);
        
        int max = ListingProcessor.getMax(array2);
        assertEquals(0,max);
        
        int empty = ListingProcessor.getMax(emptyArray);
        assertEquals(0,empty);
    }
    
    @Test
    public void testRetrieveSpeciedAmount()
    {
        int[] array1 = {10,11,12,13,14,15};
        int from = 10;
        int to = 13;
        
        long total = ListingProcessor.retrieveSpecifiedAmount(array1, from,to);
        assertEquals(3,total);
        
        int[] array2 = {10,20,30,40,50,60};
        int from2 = 10;
        int to2 = 55;
        
        long total2 = ListingProcessor.retrieveSpecifiedAmount(array2,from2,to2);
        assertEquals(5, total2);
        
        int[] array3 = {};
        int from3 = 10;
        int to3 = 13;
        
        long total3 = ListingProcessor.retrieveSpecifiedAmount(array3,from3,to3);
        assertEquals(0,total3);
    }
    
    private static List<AirbnbListing> createListings()
    {
        List<AirbnbListing> customList = new ArrayList<>();

        AirbnbListing property1 = new AirbnbListing("14403483","Large room, sleeps 3, Brixton","88550548" ,"Allison", "Lambeth",51.47125306,-0.11250696,"Private room",37,2,28,"03/03/2017",4.12,1,254);
        AirbnbListing property2 = new AirbnbListing("9957622","Double bed in Notting Hill","51168635","Serge","Westminster",51.51782111,-0.192291889,"Private room",35,2,18, "30/12/2016",1.67,1,0);
        AirbnbListing property3 = new AirbnbListing("7483279","Double bedroom in Southwark", "78372", "Adam", "Hammersmith and Fulham", 51.46977981,-0.189799402,"Entire home/apt",195,1,0,"12/8/2015",1.00,1,0);
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
        AirbnbListing property3 = new AirbnbListing("7483279","Double bedroom in Southwark", "78372", "Adam", "Westminster", 51.46977981,-0.189799402,"Entire home/apt",195,1,0,"12/8/2015",1.00,1,0);
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
        //below are the attraction type destinations
        customDestinations = new ArrayList<>();
        DestinationListing destination1 = new DestinationListing("Tower of London", "Tower of London", 51.42236388,-0.299202059,"Tower Hamlets", "free");
        DestinationListing destination2 = new DestinationListing("London Eye", "Riverside Building, County Hall,", 51.38442425,-0.263391118,"Westminster", "£2.50 - £5.00");
        DestinationListing destination3 = new DestinationListing("Big Ben", "London SW1A 0AA",51.40190763,-0.278253045,"Tower Hamlets", "free");
        //invalid because of ending price range
        
        
        customDestinations.add(destination1);
        customDestinations.add(destination2);
        customDestinations.add(destination3);
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
    
    /**
     * Test that `getNumberOfReviews()` returns the correct value when given valid arguments.
     */
    @Test
    public void testGetNumberOfReviewsWithValidArguments()
    {
        List<AirbnbListing> listings = new ArrayList<>();
        
        listings.add(new AirbnbListing("14403483", "Large room, sleeps 3, Brixton", "88550548", "Allison", "Lambeth", 51.47125306, -0.11250696, "Private room", 37, 2, 28, "03/03/2017", 4.12, 1, 254));
        listings.add(new AirbnbListing("9957622", "Double bed in Notting Hill", "51168635", "Serge", "Westminster", 51.51782111, -0.192291889, "Private room", 35, 2, 18, "30/12/2016", 1.67, 1, 0));
        listings.add(new AirbnbListing("7483279", "Double beedroom in Southwark", "78372", "Adam", "Hammersmith and Fulham", 51.46977981, -0.189799402, "Entire home/apt", 160, 1, 0, "12/8/2015", 1.00, 1, 0));
        listings.add(new AirbnbListing("2584302", "Epic room in LONDON!", "23426", "Jeff", "Hammersmith and Fulham", 51.21937781, -0.133779402, "Private room", 200, 1, 0, "12/8/2015", 1.00, 1, 0));
        listings.add(new AirbnbListing("7833588", "Bright DOUBLE ROOM, central LONDON", "34472628", "Tommaso", "Hammersmith and Fulham", 51.52066587, -0.056124665, "Private room", 231, 1, 7, "07/12/2015", 0.37, 1, 0));
        listings.add(new AirbnbListing("9020269", "Spacious Room next to Richmond Par", "47094767", "Marcus", "Kingston upon Thames", 51.41945318, -0.286341833, "Private room", 30, 1, 1, "09/10/2016", 0.65, 2, 35));
        
        final int totalReviews = ListingProcessor.getNumberOfReviews(listings, 30, 40);
        
        assertEquals(totalReviews, 47);
    }
    
    /**
     * Test that `getNumberOfReviews()` throws an IllegalArgumentException with the right message when given a null listings argument.
     */
    @Test
    public void testGetNumberOfReviewsWithNullListings()
    {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
                    List<AirbnbListing> listings = null;

                    ListingProcessor.getNumberOfReviews(listings, 0, 500);
        });

        assertEquals("The provided listings argument is invalid.", exception.getMessage());
    }

    /**
     * Test that `getNumberOfReviews()` throws an IllegalArgumentException with the right message when given an empty listings argument.
     */
    @Test
    public void testGetNumberOfReviewsWithEmptyListings()
    {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
                    List<AirbnbListing> listings = new ArrayList<>();

                    ListingProcessor.getNumberOfReviews(listings, 0, 500);
        });

        assertEquals("The provided listings argument is invalid.", exception.getMessage());
    }
    
    /**
     * Test that `getNumberofListings()` returns the correct list size when given valid arguments.
     */
    @Test
    public void testGetNumberOfListingsWithValidArguments()
    {
        List<AirbnbListing> listings = new ArrayList<>();
        
        listings.add(new AirbnbListing("14403483", "Large room, sleeps 3, Brixton", "88550548", "Allison", "Lambeth", 51.47125306, -0.11250696, "Private room", 37, 2, 28, "03/03/2017", 4.12, 1, 254));
        listings.add(new AirbnbListing("9957622", "Double bed in Notting Hill", "51168635", "Serge", "Westminster", 51.51782111, -0.192291889, "Private room", 35, 2, 18, "30/12/2016", 1.67, 1, 0));
        listings.add(new AirbnbListing("7483279", "Double beedroom in Southwark", "78372", "Adam", "Hammersmith and Fulham", 51.46977981, -0.189799402, "Entire home/apt", 160, 1, 0, "12/8/2015", 1.00, 1, 0));
        listings.add(new AirbnbListing("2584302", "Epic room in LONDON!", "23426", "Jeff", "Hammersmith and Fulham", 51.21937781, -0.133779402, "Private room", 200, 1, 0, "12/8/2015", 1.00, 1, 0));
        listings.add(new AirbnbListing("7833588", "Bright DOUBLE ROOM, central LONDON", "34472628", "Tommaso", "Hammersmith and Fulham", 51.52066587, -0.056124665, "Private room", 231, 1, 7, "07/12/2015", 0.37, 1, 0));
        listings.add(new AirbnbListing("9020269", "Spacious Room next to Richmond Par", "47094767", "Marcus", "Kingston upon Thames", 51.41945318, -0.286341833, "Private room", 30, 1, 1, "09/10/2016", 0.65, 2, 35));
        
        long totalListings = ListingProcessor.getNumberOfListings(listings, 0, 500);
        
        
        assertEquals(totalListings, listings.size());
    }
    
    
    
    /**
     * Test that `getNumberofListings()` throws an IllegalArgumentException with the right message when given a null listings argument.
     */
    @Test
    public void testGetNumberOfListingsWithNullListings()
    {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
                    List<AirbnbListing> listings = null;

                    ListingProcessor.getNumberOfReviews(listings, 0, 500);
        });

        assertEquals("The provided listings argument is invalid.", exception.getMessage());
    }

    /**
     * Test that `getNumberOfListings()` throws an IllegalArgumentException with the right message when given an empty listings argument.
     */
    @Test
    public void testGetNumberOfListingsWithEmptyListings()
    {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
                    List<AirbnbListing> listings = new ArrayList<>();

                    ListingProcessor.getNumberOfReviews(listings, 0, 500);
        });

        assertEquals("The provided listings argument is invalid.", exception.getMessage());
    }
    
    /**
     * Test that `getTotalAvailableProperties()` returns the correct value when given valid arguments.
     */
    @Test
    public void testGetTotalAvailabilityWithValidArguments()
    {
        List<AirbnbListing> listings = new ArrayList<>();
        
        listings.add(new AirbnbListing("14403483", "Large room, sleeps 3, Brixton", "88550548", "Allison", "Lambeth", 51.47125306, -0.11250696, "Private room", 37, 2, 28, "03/03/2017", 4.12, 1, 254));
        listings.add(new AirbnbListing("9957622", "Double bed in Notting Hill", "51168635", "Serge", "Westminster", 51.51782111, -0.192291889, "Private room", 35, 2, 18, "30/12/2016", 1.67, 1, 0));
        listings.add(new AirbnbListing("7483279", "Double beedroom in Southwark", "78372", "Adam", "Hammersmith and Fulham", 51.46977981, -0.189799402, "Entire home/apt", 160, 1, 0, "12/8/2015", 1.00, 1, 0));
        listings.add(new AirbnbListing("2584302", "Epic room in LONDON!", "23426", "Jeff", "Hammersmith and Fulham", 51.21937781, -0.133779402, "Private room", 200, 1, 0, "12/8/2015", 1.00, 1, 0));
        listings.add(new AirbnbListing("7833588", "Bright DOUBLE ROOM, central LONDON", "34472628", "Tommaso", "Hammersmith and Fulham", 51.52066587, -0.056124665, "Private room", 231, 1, 7, "07/12/2015", 0.37, 1, 0));
        listings.add(new AirbnbListing("9020269", "Spacious Room next to Richmond Par", "47094767", "Marcus", "Kingston upon Thames", 51.41945318, -0.286341833, "Private room", 30, 1, 1, "09/10/2016", 0.65, 2, 35));
        
        long totalAvailability = ListingProcessor.getTotalAvailableProperties(listings, 0, 500);
        
        
        assertEquals(totalAvailability, 2);
    }
    
    
    
    /**
     * Test that `getTotalAvailableProperties()` throws an IllegalArgumentException with the right message when given a null listings argument.
     */
    @Test
    public void testGetTotalAvailabilityWithNullListings()
    {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
                    List<AirbnbListing> listings = null;

                    ListingProcessor.getTotalAvailableProperties(listings, 0, 500);
        });

        assertEquals("The provided listings argument is invalid.", exception.getMessage());
    }

    /**
     * Test that `getTotalAvailableProperties()` throws an IllegalArgumentException with the right message when given an empty listings argument.
     */
    @Test
    public void testGetTotalAvailabilityWithEmptyListings()
    {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
                    List<AirbnbListing> listings = new ArrayList<>();

                    ListingProcessor.getTotalAvailableProperties(listings, 0, 500);
        });

        assertEquals("The provided listings argument is invalid.", exception.getMessage());
    }
    
    /**
     * Test that `getNonPrivate()` returns the correct value when given valid arguments.
     */
    @Test
    public void testGetNonPrivateWithValidArguments()
    {
        List<AirbnbListing> listings = new ArrayList<>();
        
        listings.add(new AirbnbListing("14403483", "Large room, sleeps 3, Brixton", "88550548", "Allison", "Lambeth", 51.47125306, -0.11250696, "Private room", 37, 2, 28, "03/03/2017", 4.12, 1, 254));
        listings.add(new AirbnbListing("9957622", "Double bed in Notting Hill", "51168635", "Serge", "Westminster", 51.51782111, -0.192291889, "Private room", 35, 2, 18, "30/12/2016", 1.67, 1, 0));
        listings.add(new AirbnbListing("7483279", "Double beedroom in Southwark", "78372", "Adam", "Hammersmith and Fulham", 51.46977981, -0.189799402, "Entire home/apt", 160, 1, 0, "12/8/2015", 1.00, 1, 0));
        listings.add(new AirbnbListing("2584302", "Epic room in LONDON!", "23426", "Jeff", "Hammersmith and Fulham", 51.21937781, -0.133779402, "Private room", 200, 1, 0, "12/8/2015", 1.00, 1, 0));
        listings.add(new AirbnbListing("7833588", "Bright DOUBLE ROOM, central LONDON", "34472628", "Tommaso", "Hammersmith and Fulham", 51.52066587, -0.056124665, "Private room", 231, 1, 7, "07/12/2015", 0.37, 1, 0));
        listings.add(new AirbnbListing("9020269", "Spacious Room next to Richmond Par", "47094767", "Marcus", "Kingston upon Thames", 51.41945318, -0.286341833, "Private room", 30, 1, 1, "09/10/2016", 0.65, 2, 35));
        
        long totalNonPrivate = ListingProcessor.getNonPrivate(listings,"Entire home/apt", 0, 500);
        
        
        assertEquals(totalNonPrivate, 1);
    }
    
    
    
    /**
     * Test that `getNonPrivate()` throws an IllegalArgumentException with the right message when given a null listings argument.
     */
    @Test
    public void testGetNonPrivateWithNullListings()
    {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
                    List<AirbnbListing> listings = null;

                    ListingProcessor.getNonPrivate(listings,"Entire home/apt", 0, 500);
        });

        assertEquals("The provided listings argument is invalid.", exception.getMessage());
    }

    /**
     * Test that `getNonPrivate()` throws an IllegalArgumentException with the right message when given an empty listings argument.
     */
    @Test
    public void testGetNonPrivateWithEmptyListings()
    {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
                    List<AirbnbListing> listings = new ArrayList<>();

                    ListingProcessor.getNonPrivate(listings,"Entire home/apt", 0, 500);
        });

        assertEquals("The provided listings argument is invalid.", exception.getMessage());
    }
    
    /**
     * Test that `getNonPrivate()` throws an IllegalArgumentException with the right message when given an illegal room type argument.
     */
    @Test
    public void testGetNonPrivateWithIllegalString() 
    {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            List<AirbnbListing> listings = new ArrayList<>();
            listings.add(new AirbnbListing("7483279", "Double beedroom in Southwark", "78372", "Adam", "Hammersmith and Fulham", 51.46977981, -0.189799402, "Entire home/apt", 160, 1, 0, "12/8/2015", 1.00, 1, 0));
            
            ListingProcessor.getNonPrivate(listings, "Illegal Room Type" , 0, 500);
            
        });
        
        assertEquals("The provided room type argument does not exist", exception.getMessage());
    }
    
     /**
     * Test that `getNonPrivate()` throws an IllegalArgumentException with the right message when given a null room type argument.
     */
    @Test
    public void testGetNonPrivateWithNullRoomType()
    {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
                    List<AirbnbListing> listings = new ArrayList<>();

                    listings.add(new AirbnbListing("7483279", "Double beedroom in Southwark", "78372", "Adam", "Hammersmith and Fulham", 51.46977981, -0.189799402, "Entire home/apt", 160, 1, 0, "12/8/2015", 1.00, 1, 0));
                    
                    ListingProcessor.getNonPrivate(listings, null , 0, 500);
        });
        
        assertEquals("The provided room type argument does not exist", exception.getMessage());
    }
}
