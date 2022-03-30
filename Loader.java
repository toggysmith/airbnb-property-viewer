
/**
 * Abstract class Loader - write a description of the class here
 *
 * @author (your name here)
 * @version (version number or date here)
 */
public abstract class Loader
{
        /*
     * @param doubleString The string to be converted to Double type.
     * @return The Double value of the string, or -1.0 if the string is either empty or just whitespace.
     */
    protected static Double convertDouble(String doubleString) throws Exception
    {
        if (doubleString != null && !doubleString.trim().equals(""))
        {
            return Double.parseDouble(doubleString);
        }

        return -1.0;
    }

    /*
     * @param intString The string to be converted to Integer type.
     * @return The Integer value of the string, or -1 if the string is either empty or just whitespace.
     */
    protected static Integer convertInt(String intString)
    {
        if (intString != null && !intString.trim().equals(""))
        {
            return Integer.parseInt(intString);
        }

        return -1;
    }
}
