package acsse.csc2a.fmb.file;

/**
 * Name & Surname: Skhumbuzo Bembe
 * Student number: 222074138
 */


// imports to be used in this class
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

// importing the fireworkdisplayer class
import acsse.csc2a.fmb.model.FireworkDisplay;
import acsse.csc2a.fmb.model.PyroTechnician;
import acsse.csc2a.fmb.model.Firework;
import acsse.csc2a.fmb.model.E_COLOUR;

/**
 * This class will handle the work to open a file and read all the lines on the file and test for valid information on the file
 * @author Skhumbuzo Bembe
 */
public class DisplayFileHandler
{
    // class variables
    private FireworkDisplay displayer;
    private PyroTechnician Technician;
    private Vector<String> input_lines;

    private Vector<Firework> fireworkVector;

    /**
     * This function will be used to read files and check if the information inside each file is valid
     * @param Filename is the name of the file we want to read
     * @return returns a FireworkDisplay object with valid information
     */
    public FireworkDisplay readDisplay(String Filename)
    {
        try(BufferedReader  reader = new BufferedReader(new FileReader(Filename)))
        {
            input_lines = new Vector<>();
            // read the first line on the file
            String text_from_line = reader.readLine();
            // check if the first line is not empty
            if(text_from_line != null)
            {
                //store the first line in the file
                input_lines.add(text_from_line);
            }
            // continue to read lines from the file while the file is ready to be read
            while(reader.ready())
            {
                // read the next line
                text_from_line = reader.readLine();
                // ensure line read is not empty
                if(text_from_line != null && !text_from_line.isEmpty())
                {
                    // store that line
                    input_lines.add(text_from_line);
                }
            }

            this.displayer = FireworkDisplay.processLine(input_lines);

        }
        catch(IOException exception)
        {
            exception.printStackTrace();
        }
        // returning the correct FireworkDisplay
        return this.displayer;
    }


}
