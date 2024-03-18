package acsse.csc2a.fmb.model;

import java.util.Objects;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Bembe Skhumbuzo 222074138
 * This class will manage the display of the fireworks
 */
public class FireworkDisplay implements IDisplayable
{
    private String Display_ID; // stores the name of the display ID
    private String Display_Name; // stores the name of the display the firework will produce
    private String Display_Theme; // stores the theme produced by the firework
    private PyroTechnician Technician; // an object of the PyroTechnician class
    private Firework[] fireworks_object; // an array of objects of the Fireworks class

    /**
     * This is a default constructor
     */
    FireworkDisplay()
    {
        this.Display_ID = "Bravo234Zulu";
        this.Display_Name = "Killer Man";
        this.Display_Theme = "Smooth";
        this.Technician = new PyroTechnician();
        this.fireworks_object = new Firework[2];
    }

    /**
     * This is a parameterised constructor
     * @param Display_id stores the ID for the Display
     * @param Display_name stores the name of the Display
     * @param Theme stores the type of theme produced by the display
     * @param tech_object is an object of the PyroTechnicians
     * @param object1 is the first object for the first Firework
     * @param  object2 is the second object for the second Firework
     */
    FireworkDisplay(String Display_id, String Display_name, String Theme, PyroTechnician tech_object, Firework object1, Firework object2)
    {
        this.Display_ID = Display_id;
        this.Display_Name = Display_name;
        this.Display_Theme = Theme;
        this.Technician =  new PyroTechnician(tech_object.getFull_name(), tech_object.getPhone_Number());
        this.fireworks_object = new Firework[2];
        // adding the objects to the array
        this.fireworks_object = new Firework[]{object1, object2};
    }

    /**
     * This function allows FireDisplay to add new Fire works
     * @param fireworks is the new firework to be added to the vector of Fireworks
     */
    public void Add_new_Fireworks(Firework fireworks)
    {
        // create a new Temporary array that bigger that the current array
        Firework[] Temporary_array = new Firework[this.fireworks_object.length + 1];
        // copying all the elements from the current array to the temporary array
        for(int i = 0; i < this.fireworks_object.length; i++)
        {
            Temporary_array[i] = this.fireworks_object[i];
        }
        // add the new object at the last index of the temp array
        Temporary_array[this.fireworks_object.length] = fireworks;

        // assign the reference of the temporary array to the current array ensuring that the changes are performed
        this.fireworks_object = Temporary_array;
    }

    /**
     * This function will delegate the work of Technician_Information function
     */
    public void Technician_details()
    {
        this.Technician.Technician_Information();
    }

    /**
     * This function will print all the details
     */
    public void printDisplay()
    {
        System.out.println("\n----------~The information for the Firework Display~----------");
        System.out.printf("%s%s%s", "ID for the Display: ", this.Display_ID, "\n");
        System.out.printf("%s%s%s", "Name of the Display: ", this.Display_Name, "\n");
        System.out.printf("%s%s%s", "Theme for the Display: ", this.Display_Theme, "\n");
        // displaying the information of the technician in charge
        Technician_details();
        // Displaying the information of th Firework used
        for(int i = 0; i < this.fireworks_object.length; i++)
        {
            this.fireworks_object[i].Firework_information();
        }

    }
    /**
     * this function will be used to extract the information from the line when it matched the pattern
     * @param line is the line contain the data to be extracted
     * @return and array containing the extracted data
     */
    public static String[] extract_information_for_FireworkDisplay(String line)
    {
        // we use a tokenizer to extract the data from the line for FireworkDisplay
        StringTokenizer tokenizer = new StringTokenizer(line, "\t");
        // Store the extracted data
        String FireD_ID = tokenizer.nextToken();
        String FireD_Name = tokenizer.nextToken();
        String FireD_Theme = tokenizer.nextToken();
        // return an array of strings containing the extracted data
        return new String[]{FireD_ID, FireD_Name, FireD_Theme};
    }

    /**
     * this function will validate the line containing the data for a FireworkDisplay against a pattern
     * @param line is the line to be matched against the pattern
     * @return returns a status whether the line matched the pattern or not
     */
    public static boolean validate(String line)
    {
        // this pattern will validate a line that should contain information about a FireworkDisplay
        Pattern pattern = Pattern.compile("^((FD)\\d{4}\\t\\[\\w+\\s\\w+\\s\\w+\\]\\t\\\"\\w+\\s\\w+\\s\\w+\\\")$");
        // this will check if the pattern matches or not
        Matcher matcher = pattern.matcher(line);
        // this return a status about the validity of the line
        boolean matched = matcher.find();
        // this will contain the status
        boolean returning_status = false;
        // updating the boolean
        if(matched != false)
        {
            // set this boolean true indicating that the match was successful
            returning_status = matched;
        }
        else
        {
            System.out.println("The line did not match the pattern for A FireworkDisplay");
        }
        // return true if=d the pattern matched, false if the pattern did not match
        return returning_status;
    }
    /**
     * FireworkDisplay implements a static method to process and extract data from a line on a file
     * that contains data for a FireworkDisplay
     * @param line is a vector containing the  lines from the file that contains the data
     * @return returns an Instance of FireworkDisplay with the valid data
     */
    public static FireworkDisplay processLine(Vector<String> line)
    {
        // check first if the pattern was matched before processing and instantiating
        if(validate(line.elementAt(0)) != false)
        {
            // extracting the data from the line and store the data on this string array
            String[] fireworkDisplay = extract_information_for_FireworkDisplay(line.elementAt(0));
            // instantiate and return a FireworkDisplay with the valid data
            return new FireworkDisplay(
                    fireworkDisplay[0],
                    fireworkDisplay[1],
                    fireworkDisplay[2],
                    Objects.requireNonNull(PyroTechnician.processLine(line.elementAt(1))),
                    Fountain_Firework.processLine(line.elementAt(2)),
                    Rocket_Firework.processLine(line.elementAt(3))
            );
        }
        else
        {
            System.out.println("FireworkDisplay cannot be instantiated will null data");
            return null;
        }
    }
    /**
     * FireworkDisplay overrides the display function from the IDisplayable interface
     * to output textual representation of it internal state on the console
     */
    @Override
    public void display()
    {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~ FireworkDisplay ~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println(STR."FireworkDisplay ID: \{this.Display_ID}");
        System.out.println(STR."FireworkDisplay Name: \{this.Display_Name}");
        System.out.println(STR."FireworkDisplay Theme: \{this.Display_Theme}");
        System.out.println();
        this.Technician.display();
        for(Firework firework : fireworks_object)
        {
            if(firework != null)
            {
                firework.display();
            }
            else
            {
                System.out.println("Cannot Display this Firework because it is assigned to null due to it's data found corrupt");
            }
        }
    }

    /**
     * This function will be used to output textual format of a Firework object's internal state
     * @return return a string representation of the Firework object
     */
    public String fireworks_internal_states()
    {
        // create a variable to store the textual data
        StringBuilder internal_state = new StringBuilder();

        // loop though the array of Firework objects
        for(Firework objects: this.fireworks_object)
        {
            // ensure the object is not null
           if(objects != null)
           {
               // store the textual data of each object
               internal_state.append(objects.toString()).append("\n");
           }
           else
           {
               System.out.println("Cannot use toString function on a null Firework object");
           }
        }
        return  internal_state.toString();
    }
    /**
     * Firework overrides the toString function from the object class
     * @return returns textual representation of Firework object's internal state to the console
     */
    @Override
    public String toString()
    {
        return "~~~~~~~~~~~~~~~~~~~~~~~~~ FireworkDisplay ~~~~~~~~~~~~~~~~~~~~~~~~~" +
                STR."\nFireworkDisplay ID: \{this.Display_ID}" +
                STR."\nFireworkDisplay Name: \{this.Display_Name}" +
                STR."\nFireworkDisplay Theme: \{this.Display_Theme}\n" +
                "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" +
                this.Technician.toString() +
                STR."\n\{fireworks_internal_states()}";
    }
}