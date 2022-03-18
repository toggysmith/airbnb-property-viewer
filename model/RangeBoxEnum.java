package model;

// Project
import model.*;
import view.*;
import controller.*;

/**
 * Enumeration class RangeBoxEnum - write a description of the enum class here
 *
 * @author (your name here)
 * @version (version number or date here)
 */
public enum RangeBoxEnum
{
 NOMAX("No max"),NOMIN("No min");
 
 private final String name;       

 private RangeBoxEnum(String s)
 {
        name = s;
 }
 
 public String toString()
 {
       return this.name;
 }
 
 
 
 
 
}
