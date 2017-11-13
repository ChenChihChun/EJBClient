package jcs.ejb.client;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class ClientUtility {
	
	private static Context initialContext;
	 
	private static String EJBName = "HTTP-EJB";
	
    private static final String PKG_INTERFACES = "org.jboss.ejb.client.naming";
 
    public static Context getInitialContext() throws NamingException {
        if (initialContext == null) {
            Properties properties = new Properties();
            properties.put(Context.URL_PKG_PREFIXES, PKG_INTERFACES);
 
            initialContext = new InitialContext(properties);
        }
        return initialContext;
    }
    

    /**
     *	<B>app Name</B> <br/>
	 *	The app name is the EAR name of the deployed EJB without .ear suffix.
	 *	Since we haven't deployed the application as a .ear,
	 *	the app name for us will be an empty string <br/>
     *	
	 *	<B>module name</B> <br/>
	 *	The module name is the JAR name of the deployed EJB without the .jar suffix. <br/>
     *	
	 *	<B>distinctName</B> <br/>
	 *	AS7 allows each deployment to have an (optional) distinct name.
	 *	This can be an empty string if distinct name is not specified. <br/>
     *	
	 *	<B>bean Name</B> <br/>
	 *	The EJB bean implementation class name <br/>
     *	
	 *	<B>interface Name</B> <br/>
	 *	Fully qualified remote interface name <br/>
     * @param moduleName
     * @param beanName
     * @param interfaceName
     * @return ejb:appName/moduleName/distinctName/beanName!interfaceName
     */
    public static String getLookupName( String moduleName , String beanName, String interfaceName ){
    	String appName = "";
    	String distinctName = "";
    	return String.format("ejb:%s/%s/%s/%s!%s", appName, moduleName, distinctName , beanName, interfaceName );
    }
    
    public static Object lookup( String beanName , String interfaceName ){
    	Context context = null;
    	try{
    		context = ClientUtility.getInitialContext();
    		return context.lookup( getLookupName( EJBName, beanName , interfaceName ) );
    	} catch (NamingException e) {
			e.printStackTrace();
			throw new RuntimeException( e );
		}
    }
    
    public static void setEJBName( String ejbName ){
    	ClientUtility.EJBName = ejbName; 
    }
    
}
