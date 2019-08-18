package com.discovery.utils;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

public class Context {
	
    private static Map<String, Object> context = null;
    Logger log = Logger.getLogger("Logger");
    public Map<String, Object> getContext()
    {
    		log.info("Creating the context map");
    		if(context==null)
    		{
    			context = new HashMap<String, Object>();
    		}
    		return context;
    }

    public <Value> Context setValue(final String field, final Value value) 
    {
    		log.info("Setting value to context map " + field + " " + value);
        context.put(field, value);
        return this;
    }

    @SuppressWarnings("unchecked")
    public <Value> Value getValue(final String field)
    {
    		log.info("Getting the value of key " + field);
        return (Value) context.get(field);
    }
}
