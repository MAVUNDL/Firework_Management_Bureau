package acsse.csc2a.fmb.model;

import java.util.StringTokenizer;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Bembe Skhumbuzo student number: 222074138
 *
 * This class stores properties of a Firework of type Rocket Firework
 * it inherits form the super class Fireworks and implements a void method from
 * and interface IDisplayable
 */
public class Rocket_Firework extends Firework implements IDisplayable {
    // colour emitted by Rocket before explosion
    protected E_COLOUR Black_powder_Colour;
    // the quantity of star spores released upon explosion
    protected int Star_Count;
    // the dispersion radius of the star spores from the explosion epicenter
    protected double Star_Radius;

    /**
     *  this is a constructor to initialize a Rocket Firework
     * @param ID is the name of the Firework ID
     * @param Firework_name is the name of the Firework
     * @param firework_length is the length of the firework fuse
     * @param Colour_Base  is the colour emitted by the firework
     * @param colour is the colour of the black powder
     * @param star_Count is the number of Star Spores a Rocket Firework releases at the point of explosion
     * @param star_Radius is the radial that Star Spores cover from the Rocket’s explosion point, determining the spread of the display
     */
    Rocket_Firework(String ID, String Firework_name, double firework_length, E_COLOUR Colour_Base, E_COLOUR colour, int star_Count, double star_Radius) {
        // channing constructors
        super(ID, Firework_name, firework_length, Colour_Base);
        //initialize values
        this.Black_powder_Colour = colour;
        this.Star_Count = star_Count;
        this.Star_Radius = star_Radius;
    }
    /**
     * this function will validate the line containing the data for a Rocket Firework against a pattern
     * @param line is the line to be matched against the pattern
     * @return returns a status whether the line matched the pattern or not
     */
   public static boolean validate(String line) {
        // this contains the pattern to be matched by the line provided base on the given regex
        Pattern pattern = Pattern.compile("^((FR)\\d{6}\\t(([\\w])*\\s[\\w]+)\\t[(\\d.)]+\\t[\\w]+\\t\\d+\\t[(\\d.)]+\\t[\\w]+)$");
        // this matches the given line against the given pattern
        Matcher matcher = pattern.matcher(line);
        // this boolean will be set to true if line matched the pattern or false if line did not match the pattern
        boolean pattern_matched = matcher.find();
        // this boolean will be updated and returned at end of function based on the conditions below
        boolean returning_boolean = false;
        // check if the pattern was matched or not
        if (pattern_matched == true)
        {
            // if the pattern matched set the boolean to be returned to true indicating that the line matched the pattern
            returning_boolean = true;
        }
        else
        {
            // if the line was not matched print this statement to the console
            System.out.println("Error:The line does not pattern for Firework of type Rocket Firework");
        }
        // this will return true if the line matched the pattern or false if line did not match the pattern
        return returning_boolean;
    }
    /**
     * this function will be used to extract the information from the line when it matched the pattern
     * @param line is the line contain the data to be extracted
     * @return and array containing the extracted data
     */
    public static String[] extract_information(String line)
    {
        // create a tokenizer to extract data from the line
        StringTokenizer tokenizer = new StringTokenizer(line,"\t");
        // store each extracted part from the line by the tokenizer on a temporary variable
        String Id = tokenizer.nextToken();
        String Name = tokenizer.nextToken();
        String length = tokenizer.nextToken();
        String colour = tokenizer.nextToken();
        String Star_Count = tokenizer.nextToken();
        String Star_Radius = tokenizer.nextToken();
        String Black_powder_Colour = tokenizer.nextToken();
        // create a collection of all the parts
        String[] data = {Id,Name,length,colour,Star_Count,Star_Radius,Black_powder_Colour};
        // return that collection
        return data;
    }
    /**
     * Rocket implements a static method to process and extract data from a line on a file
     * that contains data for a Firework of type Rocket
     * @param Line the line from the file that contains the data
     * @return returns an Instance of Rocket Firework with the valid data
     */
   public  static Rocket_Firework processLine(String Line)
    {
        // create a vector strings to store valid data from the files
        Vector<String> valid_data = new Vector<>();
        // ensure that the pattern matched before instantiating the Rocket firework object
        if(validate(Line) != false)
        {
            /*
             first extract the data from the line if the line matched the pattern using a Tokenizer
             store the extracted data on a vector
             */
            for(String _data_valid:extract_information(Line))
            {
                valid_data.addElement(_data_valid);
            }
            // these variables will be used to store some data specific to the data type each variable
            E_COLOUR firework_colour = null;
            E_COLOUR black_powder_colour = null;
            int star_count = 0;
            double fuse_length = 0.0;
            double star_radius = 0.0;
            // used the try block to do some casting and handle any corresponding exception thrown
            try
            {
                fuse_length = Double.parseDouble(valid_data.elementAt(2)); // Firework_Fuse_length
                firework_colour = E_COLOUR.valueOf(valid_data.elementAt(3)); // Colour_container
                black_powder_colour = E_COLOUR.valueOf(valid_data.elementAt(6)); // Black_powder_Colour
                star_count = Integer.parseInt(valid_data.elementAt(4));  // Star_Count
                star_radius = Double.parseDouble(valid_data.elementAt(5));
            }
            catch (NumberFormatException formatException)
            {
                System.out.println("Cannot cast to double or integer: Rocket firework - data type mismatch");
            }
            catch (IllegalArgumentException illegalArgumentException)
            {
                System.out.println("Cannot cast to enum of type E_COLOUR: Rocket firework - the colour does not exist on the colour list");
            }
            /*
             When casting the colour as a Colour in E_COLOUR and find that it does not exist on the Enum, I threw an exception
             and handle it now I am ensuring I do not return a Rocket Firework with a null colour.
             */
            if(black_powder_colour == null)
            {
                System.out.println("Rocket Firework: the colour does not exist on the colour list");
                return null;
            }
            // initialize and return the Rocket firework object with valid Data
            return new Rocket_Firework(
                    valid_data.elementAt(0),  // Firework_ID
                    valid_data.elementAt(1),  // Firework_Name
                    fuse_length,  // Firework_Fuse_length
                    firework_colour,// Colour_container
                    black_powder_colour, // Black_powder_Colour
                    star_count,  // Star_Count
                    star_radius  // Star_Radius
            );
        }
        else
        {
            System.out.println("Rocket Firework: Invalid data cannot be stored");
            return null;
        }
    }
    /**
     * Rocket Firework overrides the display function from the IDisplayable interface
     * to output textual representation of it internal state on the console
     */
    @Override
    public void display()
    {
        try
        {
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~ Rocket Firework information ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println(STR."Firework ID: \{this.Firework_ID}");
            System.out.println(STR."Firework Name:\{this.Firework_Name}");
            System.out.println(STR."Firework fuse-length: \{this.Firework_Fuse_length} (seconds)");
            System.out.println(STR."Firework Colour: \{this.Colour_container}");
            System.out.println(STR."Colour emitted before explosion: \{this.Black_powder_Colour}");
            System.out.println(STR."Quality of pores released: \{this.Star_Count}");
            System.out.println(STR."Radius covered by explosion: \{this.Star_Radius}");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        }
        catch(NullPointerException nullPointerException)
        {
            System.out.println("Error Rocket Firework has no data: Basically this firework is empty");
        }
    }
    /**
     * Rocket Fireworks overrides the toString method of the object class
     * @return returns a textual representation of a Rocket Firework object
     */
    @Override
    public String toString()
    {
        return "~~~~~~~~~~~~~~~~~~~~~~~~~ Rocket Firework information ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" +
                super.toString() +
                STR."\nColour emitted before explosion: \{this.Black_powder_Colour}" +
                STR."\nQuality of pores released: \{this.Star_Count}" +
                STR."\nRadius covered by explosion: \{this.Star_Radius}" +
                "\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
    }
}
