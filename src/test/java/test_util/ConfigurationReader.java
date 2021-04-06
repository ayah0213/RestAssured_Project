package test_util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationReader {

    //create #1 Properties class object
    private static Properties properties = new Properties();

    static {
        //#2 get the path and open teh file
        try {//# and  Open the file in JVM MEmory an dpass the path of the file
            FileInputStream file= new FileInputStream("configuration.properties");

            //#3 Load the opened file into the Properties object
            properties.load(file);

            // closing the file in JVM memory
            file.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    public static String getProperty(String keyword){
        return properties.getProperty(keyword);
    }

    String path="configuration.properties";// for wuindows copy patc->copy path from content root


}


