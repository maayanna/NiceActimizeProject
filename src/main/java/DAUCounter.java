import java.io.*;
import java.util.HashMap;
import java.util.HashSet;

public class DAUCounter
{

    private String fileName;
    private HashMap<String, HashSet<String>> myMap;


    DAUCounter(String fileName){
        this.fileName = fileName;
        this.myMap = new HashMap<>();
    }

    private void parseFile(){
        try{

            FileReader fileReader = new FileReader(fileName);
            BufferedReader reader = new BufferedReader(fileReader);
            String line = reader.readLine();
            String[] values;

            while(line != null){

                values = line.split(",");
                String userId = values[0];
                String date = values[1].split(" ")[0];

                HashSet<String> mySet;
                if (myMap.containsKey(date)){ // Already has a date
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
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {

    }
}
