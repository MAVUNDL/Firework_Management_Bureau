package acsse.csc2a.fmb.model;

import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Bembe Skhumbuzo 222074138
 * This class keeps track of the PyroTechnicians
 */
public class PyroTechnician implements IDisplayable
{
    private String Full_Name; // stores the full name of the Technician
    private String  Phone_Number; // Stores the Phone number of the Technician

    /**
     * This is a default constructor
     */

    PyroTechnician()
    {
        this.Full_Name = "Skhumbuzo-Bembe";
        this.Phone_Number = "0790156664";
    }
    /**
     * This is a parameterised constructor
     * @param Full_NAME is the name of the Technician
     * @param Phone_Number is the phone number of the Technician
     */
    PyroTechnician(String Full_NAME, String Phone_Number)
    {
        this.Full_Name = Full_NAME;
        this.Phone_Number = Phone_Number;
    }

    /**
     * This is an Accessor for the full name of the Technician
     * @return this returns the  full name of the Technician
     */
    final String getFull_name()
    {
        return this.Full_Name;
    }
    /**
     * This is an Accessor for the Phone number of the Technician
     * @return this returns the Phone number of the Technician
     */
    final String getPhone_Number()
    {
        return this.Phone_Number;
    }

    /**
     * This function will print the details of the Technician
     */
    public void Technician_Information()
    {
        System.out.println("\n-----------~Information of the Technician in charge of the firework Display~----------");
        System.out.printf("%s%s%s", "Full name of Technician: ", this.Full_Name, "\n");
        System.out.printf("%s%s%s","Contact Number: ", this.Phone_Number, "\n");
    }

    /**
     * this function will validate the line containing the data for a PyroTechnician against a pattern
     * @param line is the line to be matched against the pattern
     * @return returns a status whether the line matched the pattern or not
     */
    public static boolean validate(String line)
    {
        // this contains the pattern to be matched by the line provided base on the given regex
        Pattern pattern = Pattern.compile("^(([\\w\\-\\w])+\\t([1-9])\\d{2}\\-\\d{3}\\-\\d{3}([1-9]))$");
        // this matches the given line against the given pattern
        Matcher matcher = pattern.matcher(line);
        // this boolean will be set to true if line matched the pattern or false if line did not match the pattern
        boolean matched = matcher.find();
        // this boolean will be updated and returned at end of function based on the conditions below
        boolean to_be_returned = false;
        // check if the pattern was matched or not
        if(matched == true)
        {
            // if the pattern matched set the boolean to be returned to true indicating that the line matched the pattern
            to_be_returned = true;
        }
        else
        {
            // if the line was not matched print this statement to the console
            System.out.println("Error:The line does not pattern for PyroTechnician");
        }
        // this will return true if the line matched the pattern or false if line did not match the pattern
        return  to_be_returned;
    }

    /**
     * this function will be used to extract the information from the line when it matched the pattern
     * @param line is the line contain the data to be extracted
     * @return and array containing the extracted data
     */
    public static String[] extract_information(String line)
    {
        // creating a string tokenizer to extract information from the line
        StringTokenizer tokenizer = new StringTokenizer(line, "\t");
        // storing the extracted information
        String full_name = tokenizer.nextToken();
        String phone_number = tokenizer.nextToken();
        // return a string array with the extracted information
        return new String[]{full_name, phone_number};
    }

    /**
     * PyroTechnician implements a static method to process and extract data from a line on a file
     * that contains data for a PyroTechnician
     * @param line the line from the file that contains the data
     * @return returns an Instance of PyroTechnician with the valid data
     */
    public static PyroTechnician processLine(String line)
    {
        if(validate(line) != false)
        {
            // store the valid extracted information from the line into an array of strings
            String[] Pyrotechnician_information = extract_information(line);
            // instantiate and return a PyroTechnician instance with valid information
            return new PyroTechnician(
                    Pyrotechnician_information[0],
                    Pyrotechnician_information[1]
            );
        }
        else
        {
            System.out.println("Cannot instantiate a PyroTechnician will invalid data");
            return null;
        }
    }

    /**
     * PyroTechnician overrides the display function from the IDisplayable interface
     * to output textual representation of it internal state on the console
     */
    @Override
    public void display()
    {
        try
        {
            System.out.println("~~~~~~~~~~~~~~~~~~ PyroTechnician information ~~~~~~~~~~~~~~~~~~~~");
            System.out.println(STR."Full-Name: \{this.Full_Name}");
            System.out.println(STR."Phone-Numbers: \{this.Phone_Number}");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        }
        catch(NullPointerException nullPointerException)
        {
            System.out.println("Error PyroTechnician has no data: Basically this object  is empty");
        }
    }
    /**
     * PyroTechnician overrides the toString method of the object class
     * @return returns a textual representation of a PyroTechnician object
     */
    @Override
    public String toString() {
        return "~~~~~~~~~~~~~~~~~~ PyroTechnician information ~~~~~~~~~~~~~~~~~~~~" +
                STR."\nFull-Name: \{this.Full_Name}" +
                STR."\nPhone-Numbers: \{this.Phone_Number}" +
                "\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
    }
}