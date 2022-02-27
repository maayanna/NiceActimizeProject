import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.HashSet;

public class DAUCounterTest {

    @Test
    public void testApp(){
        assert true;
    }

    private void testParseFileSize(String fileName, HashMap<String, HashSet<String>> parsingMap1, int size){

        DAUCounter.parseFile(fileName, parsingMap1);
        Assert.assertEquals(size, parsingMap1.size()); // Check that we have all days

    }

    private void testParseFileSets(HashMap<String, HashSet<String>> myMap, HashMap<String, Integer> sizes){
        for (String str : myMap.keySet()){
            assert sizes.get(str).equals(myMap.get(str).size());
        }
    }


    @Test
    public void ManyTestParseFile(){

        System.out.println("Testing the parse file function\n");

        System.out.println("Testing the parse file function with a regular file with many days");

        // Given File Input
        HashMap<String, HashSet<String>> myMap1 = new HashMap<>();
        testParseFileSize("src/input1.txt", myMap1, 3);
        HashMap<String, Integer> SizeMap1 = new HashMap<>();
        SizeMap1.put("01/01/2020", 3);
        SizeMap1.put("02/01/2020", 2);
        SizeMap1.put("03/01/2020", 2);
        testParseFileSets(myMap1, SizeMap1);

        System.out.println("Testing the parse file function with an empty file");

        // Empty file
        HashMap<String, HashSet<String>> myMap2 = new HashMap<>();
        testParseFileSize("src/input2.txt", myMap2, 0);

        System.out.println("Testing the parse file function with a non ordered file by days");

        // Not in order file
        HashMap<String, HashSet<String>> myMap3 = new HashMap<>();
        testParseFileSize("src/input3.txt", myMap3, 3);
        HashMap<String, Integer> SizeMap3 = new HashMap<>();
        SizeMap3.put("01/01/2020", 3);
        SizeMap3.put("02/01/2020", 2);
        SizeMap3.put("03/01/2020", 2);
        testParseFileSets(myMap3, SizeMap3);

        System.out.println("Testing the parse file function with a regular file with many days and empty lines");

        // With empty lines
        HashMap<String, HashSet<String>> myMap4 = new HashMap<>();
        testParseFileSize("src/input3.txt", myMap4, 3);
        HashMap<String, Integer> SizeMap4 = new HashMap<>();
        SizeMap4.put("01/01/2020", 3);
        SizeMap4.put("02/01/2020", 2);
        SizeMap4.put("03/01/2020", 2);
        testParseFileSets(myMap4, SizeMap4);

    }

    @Test
    public void testMain(){

        System.out.println("Testing the main function\n");

        System.out.println("Testing the main function with no arguments");
        // No arguments
        String[] args1 = {};
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setErr(new PrintStream(outContent));
        DAUCounter.main(args1);
        //check if error message is in the output variable
        Assert.assertEquals("You need to enter only a file path as an argument !", outContent.toString());

        System.out.println("Testing the main function with too many arguments");
        // Too many arguments
        String[] args2 = {"src/input1.txt", "src/input2.txt"};
        outContent = new ByteArrayOutputStream();
        System.setErr(new PrintStream(outContent));
        DAUCounter.main(args2);
        Assert.assertEquals("You need to enter only a file path as an argument !", outContent.toString());

        System.out.println("Testing the main function with one file path argument");
        // One argument
        String[] args3 = {"src/input1.txt"};
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        DAUCounter.main(args3);
        String checkStr = "the DAU for 01/01/2020 is 3\nthe DAU for 03/01/2020 is 2\nthe DAU for 02/01/2020 is 2";
        Assert.assertEquals(checkStr, outContent.toString().trim());


    }

}
