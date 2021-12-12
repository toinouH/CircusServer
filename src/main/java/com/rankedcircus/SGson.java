package com.rankedcircus;

import com.google.gson.Gson;

// Singleton
public class SGson
{
    private static Gson gson;

    static  {   gson = new Gson();  }

    // Reckon this is a wonky way of implementing this, I want to remove json support in favor of yaml anyway.
    public static Gson getGson()    {   return gson;    }
}
