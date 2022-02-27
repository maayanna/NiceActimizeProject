import java.io.*;
import java.util.HashMap;
import java.util.HashSet;

public class DAUCounter
{

    /**
     * This function is opening the file and parsing it into a hashmap.
     * The key is the date, and the value is a hashset with the users of that day.
     * Like that we pass only once through the lines of the file.
     */
    private static void parseFile(String fileName, HashMap<String, HashSet<String>> myMap){
        try{

            FileReader fileReader = new FileReader(fileName);
            BufferedReader reader = new BufferedReader(fileReader);
            String line = reader.readLine();
            String[] values;

            while(line != null){  // Line by line

                values = line.split(",");  // Separate the line to find the userId and the date he logged in
                String userId = values[0];
                String date = values[1].split(" ")[0];

                HashSet<String> mySet;
                if (myMap.containsKey(date)){ // Already has a date and has then already a hashset
                    mySet = myMap.get(date);
                }

                else{ // The date is not in the hashmap, so we need to create a hashset
                    mySet = new HashSet<>();
                }
                mySet.add(userId);
                myMap.put(date, mySet);

                line = reader.readLine();
            }
            reader.close();

        }
        catch (IOException e) {
            System.out.println(e.getMessage()); // couldn't open the file or find it either
        }
    }

    /**
     * This function prints the number of DAU day by day, according to the hashmap we filled
     * while parsing the file.
     */
    private static void printDAUByDay(HashMap<String, HashSet<String>> myMap){
        for (String str : myMap.keySet()){
            System.out.println("the DAU for " + str + " is " + myMap.get(str).size());
        }
    }

    /**
     * This function manages the other functions and runs the program from start to end.
     * @param fileName - the file to parse in order to find the DAU users od a day.
     */
    private static void runAll(String fileName){
        HashMap<String, HashSet<String>> myMap = new HashMap<>();
        parseFile(fileName, myMap);
        printDAUByDay(myMap);
    }

    public static void main(String[] args) {

        if (args.length != 1){
            System.err.println("You need to enter only a file path as an argument !");
            return;
        }
        runAll(args[0]); // First arg is supposed to be the fileName
    }
}
