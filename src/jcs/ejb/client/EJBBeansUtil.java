package jcs.ejb.client;

import jcs.ejb.dao.*;
import jcs.ejb.remote.*;

/**
 * 快速取得 EJB Session Bean , 也可以透過 ClientUtility 來取得<P>
 */

public class EJBBeansUtil {
	
	/**
	 * 基本款
	 * @param beanName
	 * @param interfaceName
	 * @return
	 */
	public static Object baseGetter( String beanName , String interfaceName ){
		return ClientUtility.lookup( beanName, interfaceName );
	}
	
	public static HttpauthdataRemote getHttpauthdataRemote() {
		return (HttpauthdataRemote) baseGetter (HttpauthdataDAO.class.getSimpleName(),HttpauthdataRemote.class.getName());
	}
	
	public static HttpuploadrecordRemote getHttpuploadrecordRemote() {
		return (HttpuploadrecordRemote) baseGetter (HttpuploadrecordDAO.class.getSimpleName(),HttpuploadrecordRemote.class.getName());
	}
	
	
}