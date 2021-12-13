package com.rankedcircus;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

// Singleton
public class Config
{
    private static Config config;
    private static Properties properties = new Properties();

    static
    {
        try
        {
            config = new Config();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    public static Config getConfig()
    {
        return config;
    }

    public Config() throws FileNotFoundException
    {
        //--------------------------------------------------
        // Load-or-create configuration file.
        try
        {
            File file = new File("circus.config");

            if ( file.createNewFile() )
                System.out.println("[Config] Configuration file did not exist, writing default to path circus.config");
            else
                System.out.println("[Config] Configuration file exists at path circus.config");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

       try
       {
           FileInputStream is =  new FileInputStream("circus.config");
           properties.load(is);
       }
       catch (IOException ex)
       {
           ex.printStackTrace();
       }
    }

    public String read(String key)
    {
        return properties.getProperty(key);
    }
}
