/**
 * Enum Class to represent the string "no min" and "no max" in the main pane combo boxes to facilitate equailty checks when converting the string from the combo box into an Integer
 *
 * @author Adam Murray (K21003575)
 * @author Augusto Favero (K21059800)
 * @author Mathew Tran (K21074020)
 * @author Tony Smith (K21064940)
 * @version 1.0.0
 */
public enum RangeBoxEnum
{
 NOMAX("No max"),NOMIN("No min");
 
 private final String name;       

 /**
  * Constructor for RangeBoxEnum
  */
 private RangeBoxEnum(String s)
 {
        name = s;
 }
 
 /**
  * @return String The name of the Enum.
  */
 @Override
 public String toString()
 {
       return this.name;
 }
}
