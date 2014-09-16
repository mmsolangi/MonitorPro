package com.rc.service;



import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class ServiceLocator {
	
    static volatile ServiceLocator serviceLocator = null; 
    public static ServiceLocator getInstance() {
        if (serviceLocator == null) {
            synchronized (ServiceLocator.class){
                if (null == serviceLocator)
                    serviceLocator = new ServiceLocator();
            }
        }
        return serviceLocator;
    }
   
    public Object getEJB(String jndiName) {

        System.out.println("WEBSPHERE EJB Lookup : " + jndiName);
        String modifiedJndiName = "";
        Hashtable properties = new Hashtable();
        properties.put(Context.INITIAL_CONTEXT_FACTORY, "com.ibm.websphere.naming.WsnInitialContextFactory");
        //properties.put(Context.PROVIDER_URL, PropertyUtil.getProperty("PROVIDER_URL"));
        properties.put(Context.PROVIDER_URL, "iiop://localhost:2809");
        try {
            Context context = new InitialContext(properties);
            if(null != jndiName && jndiName.indexOf(".") != -1)
                modifiedJndiName = jndiName.substring(jndiName.lastIndexOf(".")+1);
            else
                modifiedJndiName = jndiName;
            System.out.println("ServiceLocator. getEJB WEBSPHERE EJB Lookup Modified JNDI Name: " + modifiedJndiName);
            return context.lookup("ejblocal:"+modifiedJndiName);
        }catch (NamingException ne) {
            System.out.println("Naming Exception occurred :"+jndiName +">>>"+ne.getMessage());
            System.out.println("ServiceLocator. getEJB jndiName: "+jndiName +""+ne.getMessage());
        }

        return null;
    }

    
  

    
    public Object getResources(final String jndiName) {
        Context context = null;
        Hashtable properties = new Hashtable();
        properties.put(Context.INITIAL_CONTEXT_FACTORY, "com.ibm.websphere.naming.WsnInitialContextFactory");
        properties.put(Context.PROVIDER_URL, "iiop://localhost:2809");
        try {
            context = new InitialContext();
            return context.lookup(jndiName);

        } catch (NamingException e) {
            System.out.println("ServiceLocator. getResources jndiName: "+jndiName +" -Exception : "+e.getMessage());
        }
        return null;
    }


}
    
