package com.rankedcircus;

public class Log
{
    public static void CLog(String origin, String input)
    {
        String toLog = String.format("[%s] %s", origin, input);

        if (Config.getConfig().read("circus.dev_mode").equalsIgnoreCase("True"))
            System.out.println(toLog);

        // Log to file
    }
}
