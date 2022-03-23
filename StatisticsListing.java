/**
 * Write a description of class StatisticsListing here.
 *
 * @author (your name)
 * @version (a version number or a date)
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
    
    public String getCode() {
        return code;
    }
    
    public String getBoroughName() {
        return boroughName;
    }
    
    public double getPopDensity() {
        return popDensity;
    }
    
    public double getMedIncome() {
        return medIncome;
    }
    public double getCrimeRate() {
        return crimeRate;
    }
    
    public double getAvgTransportAccess() {
        return avgTransportAccess;
    }
    
    public double getLifeSatisfaction() {
        return lifeSatisfaction;
    }
    
    public double getWorthwileScore() {
        return worthwileScore;
    }
    
    public double getHappinessScore() {
        return happinessScore;
    }
    
    public double getAnxietyScore() {
        return anxietyScore;
    }
    
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

