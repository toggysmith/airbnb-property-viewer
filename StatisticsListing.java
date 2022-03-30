/**
 * Represents the details of one borough 
 * This is essentially one row in the data table. 
 * Each column
 * has a corresponding field.
 *
 * @author Adam Murray (K21003575)
 * @author Augusto Favero (K21059800)
 * @author Mathew Tran (K21074020)
 * @author Tony Smith (K21064940)
 * @version 1.0.0
 */
public class StatisticsListing
{
    private String code;
    private String boroughName;
    private double popDensity;
    private int medIncome;
    private double crimeRate;
    private double avgTransportAccess;
    private double lifeSatisfaction;
    private double worthwileScore;
    private double happinessScore;
    private double anxietyScore;
    /**
     * * Constructor for StatisticsListing.
     * @param code The code of the listing.
     * @param boroughName The name of the borough.
     * @param popDensity The population density of the borough.
     * @param medIncome The medium income of the borough
     * @param crimeRate The crime rate per thousand people.
     * @param avgTransportAccess The average transport accessibility score.
     * @param lifeSatisfaction The life satisfaction score.
     * @param worthwileScore The worthwileness score.
     * @param happinessScore The happiness score.
     * @param anxietyScore The anxiety score of a borough
     */
    public StatisticsListing(String code, String boroughName, double popDensity, int medIncome, 
                             double crimeRate, double avgTransportAccess, double lifeSatisfaction, 
                             double worthwileScore, double happinessScore, double anxietyScore) 
                             
    {
        this.code = code;
        this.boroughName = boroughName;
        this.popDensity = popDensity;
        this.medIncome = medIncome;
        this.crimeRate = crimeRate;
        this.avgTransportAccess = avgTransportAccess;
        this.lifeSatisfaction = lifeSatisfaction;
        this.worthwileScore = worthwileScore;
        this.happinessScore = happinessScore;
        this.anxietyScore = anxietyScore;
        
    }
    
    /**
     * @return The code of the borough
     */
    public String getCode() {
        return code;
    }
    
    /**
     * @return The name of the borough
     */
    public String getBoroughName() {
        return boroughName;
    }
    
    /**
     * @return The population density
     */
    public double getPopDensity() {
        return popDensity;
    }
    
    /**
     * @return The medium income
     */
    public double getMedIncome() {
        return medIncome;
    }
    
    /**
     * @return The crime rate
     */
    public double getCrimeRate() {
        return crimeRate;
    }
    
    /**
     * @return The transport accessibilty score
     */
    public double getAvgTransportAccess() {
        return avgTransportAccess;
    }
    
    /**
     * @return The life satisfaction score
     */
    public double getLifeSatisfaction() {
        return lifeSatisfaction;
    }
    
    /**
     * @return The worthwileness score
     */
    public double getWorthwileScore() {
        return worthwileScore;
    }
    
    /**
     * @return The happiness score
     */
    public double getHappinessScore() {
        return happinessScore;
    }
    
    /**
     * @return The anxiety score
     */
    public double getAnxietyScore() {
        return anxietyScore;
    }
    
    /**
     * Over rides the toString method to now output all the details of this listing.
     * Returns "StatisticsListing{}" with all its details inside the {} 
     * in the form: detail name + "='" + detail + "\'"
     * @return The StatisticListing details.
     */
    @Override
    public String toString() {
        return "StatisticListing{" +
                "code='" + code + '\'' +
                ", boroughName='" + boroughName + '\'' +
                ", popDensity='" + popDensity + '\'' +
                ", medIncome='" + medIncome + '\'' +
                ", crimeRate='" + crimeRate + '\'' +
                ", avgTransportAccess=" + avgTransportAccess +
                ", lifeSatisfaction=" + lifeSatisfaction +
                ", worthwileScore='" + worthwileScore + '\'' +
                ", happinessScore=" + happinessScore +
                ", anxietyScore=" + anxietyScore +
                
                '}';
    }
    
}

