package acsse.csc2a.fmb.model;

import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Bembe Skhumbuzo 222074138
 * This class will contain the information of the firework
 */
public abstract class Firework implements IDisplayable
{
    // class variables
    protected String Firework_ID;
    protected String Firework_Name;
    protected double Firework_Fuse_length;
    protected E_COLOUR Colour_container;
    private static String Fountain_firework_data;
    private static String Rocket_firework_data;
    private static int Fountain_value = 0;
    private static int Rocket_value = 0;

    /**
     * This is the default constructor
     */
    Firework()
    {
        this.Firework_ID = "SBV22BRAVO";
        this.Firework_Name = "Shining Star";
        this.Firework_Fuse_length = 13.54;
        this.Colour_container = E_COLOUR.BLUE;
    }

    /**
     * This is the parameterised constructor
     * @param ID is the name of the ID fir the firework
     * @param Firework_name the name of the firework
     * @param firework_length the length of the fuse
     * @param Colour the colour of the firework
     */
    Firework(String ID, String Firework_name, double firework_length, E_COLOUR Colour)
    {
        this.Firework_ID = ID;
        this.Firework_Name = Firework_name;
        this.Firework_Fuse_length = firework_length;
        this.Colour_container = Colour;
    }

    /**
     * This is the copy constructor
     * @param object is the object we want to copy of type Firework
     */
    Firework(Firework object)
    {
        this.Firework_ID = object.Firework_ID;
        this.Firework_Name =object.Firework_Name;
        this.Firework_Fuse_length = object.Firework_Fuse_length;
        this.Colour_container = object.Colour_container;
    }

    /**
     * This function will allow us to interact with the Enum get colour for the firework
     * @param colour_index is the colour our firework must have
     * @return the colour of the fireworks
     */
    private E_COLOUR ensure_colour(String colour_index)
    {
        switch(colour_index.toUpperCase())
        {
            case "BLUE":
                return this.Colour_container = E_COLOUR.BLUE;
            case "GREEN":
                return this.Colour_container = E_COLOUR.GREEN;
            case "YELLOW":
                return this.Colour_container = E_COLOUR.YELLOW;
            case "RED":
                return this.Colour_container = E_COLOUR.RED;
            case "WHITE":
                return this.Colour_container = E_COLOUR.WHITE;
            case "CYAN":
                return this.Colour_container = E_COLOUR.CYAN;
            case "MAGENTA":
                return this.Colour_container = E_COLOUR.MAGENTA;
            default:
                 System.out.println("Error!! colour does not exist on the colour list");
        }
        return null;
    }
    // Accessor functions

    /**
     * This is an accessor for the Firework ID
     * @return returns the Firework ID
     */
    final String getFirework_ID()
    {
        return this.Firework_ID;
    }

    /**
     * This is an accessor for the Firework name
     * @return returns the name of the Firework
     */
    final String getFirework_Name()
    {
        return this.Firework_Name;
    }

    /**
     * This is an accessor for the fuse length of the Firework
     * @return returns the length of the fuse
     */
    final double getFirework_Fuse_length()
    {
        return this.Firework_Fuse_length;
    }

    /**
     * This is an accessor for the Colour of the Firework
     * @return returns the colour for the fire work
     */
    final E_COLOUR getColour_container()
    {
        return this.Colour_container;
    }

    /**
     * This function will be used to set the ID of the firework
     * @param ID is the name if the ID
     */
    public void setFirework_ID(String ID)
    {
        this.Firework_ID = ID;
    }

    /**
     *  This function will be used to set the name of the firework
     * @param firework_Name is the name of the firework
     */
    public void setFirework_Name(String firework_Name)
    {
        this.Firework_Name = firework_Name;
    }

    /**
     * This function will be used to set the length of the fuse the firework has
     * @param firework_Fuse_length is the length of the fuse
     */
    public void setFirework_Fuse_length(double firework_Fuse_length)
    {
        this.Firework_Fuse_length = firework_Fuse_length;
    }

    /**
     * This function will used to set the colour of the fuse
     * @param colour is the colour we want to set
     */
    public void setColour_container(E_COLOUR colour)
    {
        this.Colour_container = colour;
    }


    /**
     * This function will print the details of the Firework
     */
    public void Firework_information()
    {
        System.out.println("\n----------~Information  about the firework used~----------");
        System.out.printf("%s%s%s", "ID of the Firework: ", this.Firework_ID, "\n");
        System.out.printf("%s%s%s", "Name of the Firework: ", this.Firework_Name, "\n");
        System.out.printf("%s%s%s", "Length of the Firework Fuse: ", this.Firework_Fuse_length, "\n");
        System.out.printf("%s%s%s", "Colour produced by the Firework: ", this.Colour_container, "\n");
    }

    /**
     * this function will validate the line containing the data for a Firework against a pattern
     * @param line is the line to be matched against the pattern
     * @return returns a status whether the line matched the pattern or not
     */
    public static boolean validate(String line)
    {
        // this pattern will be used to validate line containing data for  A firework: Either a Rocket Firework or a Fountain Firework
        Pattern pattern = Pattern.compile("(FR\\d{6}\\t[\\w\\s\\w]+\\t\\d.\\d+\\t[\\w]+\\t\\d+\\t\\d.\\d+\\t[\\w]+)|(FF\\d{6}\\t[\\w\\s\\w]+\\t\\d.\\d+\\t[\\w]+\\t\\d+\\t\\[\\w+\\||\\w+\\])");
        // this will match the pattern for a Firework: Either a Rocket Firework or a Fountain Firework
        Matcher matcher = pattern.matcher(line);
        // this will return True if the pattern matched false if it did not match
        boolean matched = matcher.find();
        // this is the boolean we will return if the line matched the pattern to indicate a successful validation
        boolean to_be_returned = false;
        // updating the boolean
        if(matched == true)
        {
            to_be_returned = matched;
            // checking if the pattern matched for Rocket firework or Fountain firework
            if(matcher.group(1) != null)
            {
                // then extract that line  and store it on a string
                Rocket_firework_data = matcher.group(1);
                // update this value to keep track if indeed we got a match for Rocket firework, this will come in handy on the process line method
                Rocket_value++;
            }
            else if(matcher.group(2) != null)
            {
                // then extract that line  and store it on a string
                Fountain_firework_data = matcher.group(2);
                // update this value to keep track if indeed we got a match for Fountain firework, this will come in handy on the process line method
                Fountain_value++;
            }
        }
        else
        {
            System.out.println("The line does not match the pattern for a Firework: Firework (Super class)");
        }
        // this will return true if the pattern matched , false if the pattern did not match
        return to_be_returned;
    }
    /**
     * Firework implements a static method to process and extract data from a line on a file
     * that contains data for a Firework of type Fountain or Rocket
     * @param line the line from the file that contains the data
     * @return returns an Instance of Firework with the valid data
     */
    public static Firework processLine(String line)
    {
        if(validate(line) != false && (Rocket_value > 0))
        {
            // use the tokenizer method to extract the data and set the array returned by the method to this array
            String[] rocket_firework = Rocket_Firework.extract_information(Rocket_firework_data);
            return new Rocket_Firework(
                rocket_firework[0], // firework ID
                rocket_firework[1], // firework Name
                Double.parseDouble(rocket_firework[2]), // firework fuse length
                E_COLOUR.valueOf(rocket_firework[3]), // Firework colour
                E_COLOUR.valueOf(rocket_firework[4]), // black powder colour
                Integer.parseInt(rocket_firework[5]), // star count
                Double.parseDouble(rocket_firework[6]) // star radius
            );

        } else if(validate(line) != false && (Fountain_value > 0))
        {
            // use the tokenizer method to extract the data and set the array returned by the method to this array
            String[] fountain_firework = Fountain_Firework.extract_Fountain_firework_infor(Fountain_firework_data);
            return new Fountain_Firework(
                    fountain_firework[0], // firework ID
                    fountain_firework[1], // firework name
                    Double.parseDouble(fountain_firework[2]), // firework fuse length
                    E_COLOUR.valueOf(fountain_firework[3]), // firework colour
                    Double.parseDouble(fountain_firework[4]), // duration length of Fountain display
                    Fountain_Firework.extract_colours_from_line(fountain_firework[5]) // colours exhibited during fountain's display duration
            );
        }
        else
        {
            System.out.println("Firework (super class): Cannot instantiate a Firework with invalid data");
            return null;
        }
    }
    /**
     * Firework overrides the display function from the IDisplayable interface
     * to output textual representation of it internal state on the console
     */
    @Override
    public void display()
    {
        if(Rocket_Firework.validate(Rocket_firework_data) != false && (Rocket_value > 0))
        {
            // Take in the line that contains data for a Rocket firework and validate, then process the data and create a Rocket firework with processed valid data
            Rocket_Firework rocketFirework = Rocket_Firework.processLine(Rocket_firework_data);
            // ensuring that Rocket Firework is not null
            assert rocketFirework != null;
            // access the print display function
            rocketFirework.display();
        } else if(Fountain_Firework.validate(Fountain_firework_data) != false && (Fountain_value > 0))
        {
            //  Take in the line that contains data for a Fountain firework and validate, then process the data and create a Rocket firework with processed valid data
            Fountain_Firework fountainFirework = Fountain_Firework.processLine(Fountain_firework_data);
            // ensuring that Fountain Firework is not null
            assert fountainFirework != null;
            // access the print display function
            fountainFirework.display();
        }
        else
        {
            System.out.println("There's no Firework with valid information: Firework (sub class)");
        }
    }
    /**
     * Firework overrides the toString function from the object class
     * @return returns textual representation of Firework object's internal state to the console
     */
    @Override
    public String toString() {
        return STR."Firework ID: \{this.Firework_ID}" +
                STR."\nFirework Name:\{this.Firework_Name}" +
                STR."\nFirework fuse-length: \{this.Firework_Fuse_length} (seconds)" +
                STR."\nFirework Colour: \{this.Colour_container}";
    }
}