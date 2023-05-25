package com.logic;


import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class RecoverJob implements ServletContextListener{
    
    public void contextDestroyed(ServletContextEvent arg0)
    {
        System.out.println("Stopping Application successfully");
        try{
     	   PublicAudit pa=new PublicAudit();
     	   pa.finalizecheck();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public void contextInitialized(ServletContextEvent arg0) 
    {
       System.out.println("Initializing Application successfully");
       
       try{
    	   
       }
       catch(Exception e)
       {
           e.printStackTrace();
       }
     }          
}