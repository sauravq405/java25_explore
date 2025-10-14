package com.java25.features;


import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * This class just has a long list of lines with no logical agenda besides containing too many import statements
 *
 */
public class ModuleImportDeclarationBefore {


    static void main() throws FileNotFoundException, IOException, SQLException, ClassNotFoundException {
        List<String> names = new ArrayList<>();
        names.add("Radhey");
        names.add("Krishna");
        names.add("Durga");
        names.add("Kartik");

        Map<String, Integer> nameLength = new HashMap<>();
        for (String name : names) {
            nameLength.put(name, name.length());
        }
        System.out.println("Name Length Map: " + nameLength);
        Set<Map.Entry<String, Integer>> entries = nameLength.entrySet();
        for(Map.Entry<String, Integer> entry: entries){
            System.out.println("Key: "+entry.getKey()+" Value:"+entry.getValue());
        }


        Set<Integer> numbers = IntStream.range(0, 10).boxed().collect(Collectors.toSet());
        System.out.println("Numbers set: "+numbers);

        Random random = new Random();
        System.out.println("Random: "+random.nextInt());

        File tempFile = new File("src/main/resources/sample.txt");
        BufferedReader bufferedReader = new BufferedReader(new FileReader(tempFile));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            System.out.println(line);
        }
        bufferedReader.close();

        // ensure class is loaded => static block runs and registers the driver
        Class.forName("com.java25.features.DummyDriver");
        Connection connection = DriverManager.getConnection("jdbc:dummy:fake");
        System.out.println("Dummy connection initialized: " + (connection != null));
        System.out.println("Connection established: "+ (connection != null));

        BigInteger bigInt = new BigInteger("123456789");
        BigDecimal bigDec = new BigDecimal("123.456");
    }
}
