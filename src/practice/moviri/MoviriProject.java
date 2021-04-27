package practice.moviri;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * @author Lingxiao Du
 * calculate date from 2 cvs files
 */
public class MoviriProject {
    private static String[] contentOfBitRate = new String[30];
    private static String[] contentOfBandwidth = new String[30];
    private static HashMap<String, String> bandwidthMap = new HashMap<>();   //store the value from bandwidthFile

    /**
     * method to calculate the total number of rows of the file
     * @param f
     * @return
     * @throws Exception
     */
    public static int countRow(File f) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(f));
        int count = 0;
        while(bufferedReader.readLine() != null)
            count++;
        return count;
    }

    public static void storeBandwidthValue(HashMap map, Path path) throws IOException {
        map = bandwidthMap;
        //path: /Users/lingxiaodudu/Downloads/bandwidth.csv
        path = Paths.get("src/practice/moviri/bandwidth.csv");
        List<String> list = Files.readAllLines(path);
        list.remove(0); //remove the title of the file
        for (int i = 0; i < list.size(); i++) {
            String values[] = list.get(i).split(",");
            map.put(values[0] + values[1], values[2]);  //key{server+interface}, value{bandwidth}
        }
    }


    //Output:
    //Timestamp | Server | Network Interface | Network bit rate / Bandwidth (Network Bandwidth Utilization)
    //The Network Bandwidth Utilization of an interface is calculated as the Network Bit Rate divided by its Bandwidth.


    public static void main(String[] args) throws Exception {
        //path2: /Users/lingxiaodudu/Downloads/bandwidth.csv
        String path1 = "src/practice/moviri/netbitrate.csv";  //TIMESTAMP-SERVER-INTERFACE-NetBitRate
        String path2 = "src/practice/moviri/bandwidth.csv";   //SERVER-INTERFACE-BANDWIDTH

        File f = new File(path1);


        try {
            Path pathOne = Paths.get(path1);
            List<String> list = Files.readAllLines(pathOne);

            Path pathTwo = Paths.get(path2);
            List<String> list2 = Files.readAllLines(pathTwo);

            storeBandwidthValue(bandwidthMap, pathTwo); //store all values from bandwidthFile to hashmap first

            String title = list.get(0);
            System.out.println(title);
            list.remove(0); //get ride of the title

            String titleOfBandwidth = list2.get(0);
            list2.remove(0);    //get ride of the title

            for (int i = 0; i < list.size(); i++) {
                contentOfBitRate = list.get(i).split(",");
                System.out.print(contentOfBitRate[0] + "\t" + contentOfBitRate[1] + "\t" + contentOfBitRate[2] + "\t");
                float result = 0;

                result = Float.parseFloat(contentOfBitRate[3]) / Float.parseFloat(bandwidthMap.get(contentOfBitRate[1] + contentOfBitRate[2]));
                System.out.println(result);

            }
        }

        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }


        //unit test for Method storeBandwidthValue()
//        Path path = Paths.get(path2);
//        storeBandwidthValue(bandwidthMap, path);
//        Set<String> keys = bandwidthMap.keySet();
//        for(String k: keys)
//            System.out.println(k + ": " + bandwidthMap.get(k));
    }


}
