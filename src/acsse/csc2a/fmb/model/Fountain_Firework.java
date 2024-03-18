package acsse.csc2a.fmb.model;

import java.util.IllegalFormatException;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * @author Bembe Skhumbuzo student number: 222074138
 *
 * This class stores properties of a Firework of type Fountain Firework
 * it inherits form the super class Fireworks and implements a void method from
 * and interface IDisplayable
 */
public class Fountain_Firework extends Firework implements IDisplayable
{
    // total display time for the Fountain
    protected double Duration;
    // a sequence of colours exhibited throughout the fountain's display duration
    protected E_COLOUR[] Transition_Colours;

    /**
     * This is a constructor to initialize a Fountain Firework
     * @param ID is the name of the Firework ID
     * @param Firework_name is the name of the Firework
     * @param firework_length is the length of the firework fuse
     * @param Colour is the colour emitted by the firework
     * @param Duration is the duration of the Fountain display in seconds
     * @param colours is the sequence of colours emitted during the Fountain Display
     */
    Fountain_Firework(String ID, String Firework_name, double firework_length, E_COLOUR Colour, double Duration, E_COLOUR[] colours)
    {
        // channing the constructors
        super(ID, Firework_name,firework_length,Colour);
        // Initializing values
        this.Duration = Duration;
        this.Transition_Colours = colours;

    }
    /**
     * this function will validate the line containing the data for a Fountain Firework against a pattern
     * @param line is the line to be matched against the pattern
     * @return returns a status whether the line matched the pattern or not
     */
    public static  boolean validate(String line)
    {
        // this contains the pattern to be matched by the line provided base on the given regex
        Pattern pattern = Pattern.compile("^((FF)\\d{6}\\t[\\w\\s]+\\t[\\d.\\d]+\\t[\\w]+\\t\\d+\\t(\\[\\w+(\\||)\\w+\\]))$");
        // this matches the given line against the given pattern
        Matcher matcher = pattern.matcher(line);
        // this boolean will be set to true if line matched the pattern or false if line did not match the pattern
        boolean line_matched = matcher.find();
        // this boolean will be updated and returned at end of function based on the conditions below
        boolean return_status = false;
        // check if the pattern was matched or not
        if(line_matched == true)
        {
            // if the pattern matched set the boolean to be returned to true indicating that the line matched the pattern
            return_status = true;
        }
        else
        {
            // if the line was not matched print this statement to the console
            System.out.println("Error:The line does not pattern for Firework of type Fountain Firework");
        }
        // returns true if line matched the pattern or false if line did not match pattern
        return return_status;
    }
    /**
     * this function will extract the sequence of colours from the line
     * @param line is the line containing that sequence
     * @return returns an array storing those colours
     */
    public static E_COLOUR[] extract_colours_from_line(String line)
    {
        // this pattern a for this [colour 1| colour 2]
        Pattern pattern = Pattern.compile("\\[(.*?)\\]");
        // this will match the pattern
        Matcher matcher = pattern.matcher(line);
        // created and array to store the colour
        String[] string_line_for_colour = null;
        // create an array to store colours extracted from the line
        E_COLOUR[] Colours = null;
        // if the pattern matched
        if(matcher.find() == true)
        {
            // use the pattern to find the group matched by this: (.*?) where the group looks like this: colour 1| colour 2
            // then split the group into two strings and store each string into an array of strings
            string_line_for_colour = matcher.group(1).split("\\|");
            try
            {
                // cast each string into a Colour of type E_COLOUR and store it on an array of Colours
                Colours = new E_COLOUR[string_line_for_colour.length];
                for(int i = 0; i < string_line_for_colour.length; i++)
                {
                    Colours[i] = E_COLOUR.valueOf(string_line_for_colour[i]);
                }

            }
            catch (IllegalArgumentException exception)
            {
                System.out.println("Cannot cast to enum of type E_COLOUR: Fountain firework -  The colour does not exist on the colour list");
            }
        }
        // returns the array containing colours
        return Colours;
    }

    /**
     * this function will be used to extract the information from the line when it matched the pattern
     * @param line is the line contain the data to be extracted
     * @return and array containing the extracted data
     */
    public static String[] extract_Fountain_firework_infor(String line)
    {
        // create a tokenizer to extract data from the line
        StringTokenizer tokenizer = new StringTokenizer(line, "\t");
        // store each extracted part from the line by the tokenizer on a temporary variable
        String _F_ID = tokenizer.nextToken();
        String _F_Name = tokenizer.nextToken();
        String _F_length = tokenizer.nextToken();
        String _F_Colour = tokenizer.nextToken();
        String _F_Duration = tokenizer.nextToken();
        String _F_Trans_Colours = tokenizer.nextToken();
        // return the array containing the extracted data
        return new String[]{_F_ID,_F_Name,_F_length,_F_Colour,_F_Duration,_F_Trans_Colours};
    }

    /**
     * Fountain implements a static method to process and extract data from a line on a file
     * that contains data for a Firework of type Fountain
     * @param Line the line from the file that contains the data
     * @return returns an Instance of Fountain Firework with the valid data
     */
    public  static Fountain_Firework processLine(String Line)
    {
        //
        Vector<String> _valid_data = new Vector<>();
        //
        if(validate(Line) != false)
        {
            /*
             first extract the data from the line if the line matched the pattern using a Tokenizer
             store the extracted data on a vector
             */
            for(String _information: extract_Fountain_firework_infor(Line))
            {
                _valid_data.addElement(_information);
            }
            // creating variables to store values that will be casted
            double fuse_lenth = 0.0;
            double duration = 0.0;
            E_COLOUR firework_colour = null;
            // use this try block to handle the casting in oder to catch any corresponding exceptions thrown
            try
            {
               fuse_lenth = Double.parseDouble(_valid_data.elementAt(2));
               duration = Double.parseDouble(_valid_data.elementAt(4));
               firework_colour = E_COLOUR.valueOf(_valid_data.elementAt(3));
            }
            catch (NumberFormatException formatException)
            {
                System.out.println("Cannot cast to double: Fountain firework fuse length and Display duration");
            }
            catch (IllegalArgumentException argumentException)
            {
                System.out.println("Cannot cast to enum of type E_COLOUR: Fountain Firework Colour does not exist on the colour list");
            }
            /*
             When casting the colour as a Colour in E_COLOUR and find that it does not exist on the Enum, I threw an exception
             and handle it now I am ensuring I do not return a Rocket Firework with a null colour.
             */
            if(firework_colour == null)
            {
                System.out.println("Fountain Firework: the colour does not exist on the colour list");
                return null;
            }
            // return a Fountain firework with valid information
            return  new Fountain_Firework(
                    _valid_data.elementAt(0), // firework ID
                    _valid_data.elementAt(1), // firework Name
                    fuse_lenth, // firework fuse length
                    firework_colour, // firework colour
                    duration, // duration length of Fountain Display
                    extract_colours_from_line(_valid_data.elementAt(5)) // colours exhibited during fountain's display duration
            );
        }
        else
        {
            System.out.println("Fountain Firework: Invalid data cannot be stored");
            return null;
        }
    }

    /**
     * Fountain Firework overrides the display function from the IDisplayable interface
     * to output textual representation of it internal state on the console
     */
    @Override
    public void display()
    {
        try
        {
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~ Fountain Firework information ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println(STR."Firework ID: \{this.Firework_ID}");
            System.out.println(STR."Firework Name: \{this.Firework_Name}");
            System.out.println(STR."Firework fuse-length: \{this.Firework_Fuse_length} (seconds)");
            System.out.println(STR."Firework Colour: \{this.Colour_container}");
            System.out.println(STR."Duration of Fountain: \{this.Duration}");
            System.out.print(STR."Colours exhibited by during Fountain: \{this.Transition_Colours[0]} | \{this.Transition_Colours[1]}");
            System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        }
        catch(NullPointerException nullPointerException)
        {
            System.out.println("Error Fountain Firework has no data: Basically this firework is empty");
        }
    }
    /**
     * Fountain Fireworks overrides the toString method of the object class
     * @return returns a textual representation of a Fountain Firework object
     */
    @Override
    public String toString() {
        return "~~~~~~~~~~~~~~~~~~~~~~~~~ Fountain Firework information ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" +
                super.toString() +
                STR."\nDuration of Fountain: \{this.Duration}" +
                STR."\nColours exhibited by during Fountain: \{this.Transition_Colours[0]} | \{this.Transition_Colours[1]}" +
                "\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
    }
}

