Firework Management Bureau (FMB) Validation 
Introduction
This repository contains documentation and guidelines for validating Firework Display transmissions, as per the requirements outlined by the Firework Management Bureau (FMB). It includes information about the attributes of Rocket Fireworks and Fountain Fireworks, along with instructions for data formatting, class hierarchy implementation, and testing procedures.

Firework Attributes
Each Firework possesses the following attributes:

Firework ID: Unique alphanumeric identifier.
Firework Name: Official name of the firework.
Firework Fuse Length: Delay before explosion (in seconds).
Firework Colour: Colour emitted by the firework upon detonation.
Additionally, Rocket Fireworks and Fountain Fireworks have specific properties:

Rocket Fireworks:
Rocket Black Powder Colour: Trail color emitted by the Rocket before explosion.
Rocket Star Count: Quantity of Star Spores released upon explosion.
Rocket Star Radius: Dispersion radius of the Star Spores from the explosion’s epicenter.
Fountain Fireworks:
Fountain Duration: Total display time for the Fountain.
Fountain Transition Colours: Sequence of colors exhibited throughout the Fountain’s display duration.
Class Hierarchy Implementation
To ensure efficient data processing and validation, the following steps are mandated:

Implement a Firework class hierarchy to manage Firework objects effectively.
Override the toString method in all classes for enhanced object representation.
Introduce the IDisplayable interface in the acsse.csc2a.fmb.model package with a display method for consistent printing of the class’s textual representation to the console.
File Handling and Validation
The DisplayFileHandler class is enhanced for improved data encapsulation and processing. Key modifications include:

Incorporating a regular expression pattern in each class to verify the completeness of data for object construction.
Implementing a static validate method in each class to assess data validity based on the provided line.
Designing a static processLine method in each class for object instantiation based on the provided line.
Modifying the readDisplay method in DisplayFileHandler to utilize class-specific methods for data validation and processing.
Testing Procedure
To test the application, utilize the provided p04.jar file and accompanying text files, categorized as Dirty, Partial, and Clean files. Call the P04MainTester class’s main method in your Main class for testing purposes.
