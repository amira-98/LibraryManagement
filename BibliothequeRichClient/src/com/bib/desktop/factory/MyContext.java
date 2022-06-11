package com.bib.desktop.factory;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.jboss.ejb.client.ContextSelector;
import org.jboss.ejb.client.EJBClientConfiguration;
import org.jboss.ejb.client.EJBClientContext;
import org.jboss.ejb.client.PropertiesBasedEJBClientConfiguration;
import org.jboss.ejb.client.remoting.ConfigBasedEJBClientContextSelector;

public class MyContext {

	private static Context context;
	
	private static void init() throws NamingException{
		Properties clientProp = new Properties();
		clientProp.put("remote.connectionprovider.create.options.org.xnio.Options.SSL_ENABLED", "false");
		clientProp.put("remote.connections", "default");
		clientProp.put("remote.connection.default.host", "localhost"); // comes from JVM argument
		clientProp.put("remote.connection.default.port", "8080"); // comes from JVM argument
		clientProp.put("remote.connection.default.connect.options.org.xnio.Options.SASL_POLICY_NOANONYMOUS", "false");
		
		EJBClientConfiguration cc = new PropertiesBasedEJBClientConfiguration(clientProp);
		ContextSelector<EJBClientContext> selector = new ConfigBasedEJBClientContextSelector(cc);
		EJBClientContext.setSelector(selector);

		 

		Properties props = new Properties();
		props.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");

		// props.put("jboss.naming.client.ejb.context"/**/, true); // check if needed...

		context = new InitialContext(props);
	}
	
	public static Context getInstance() throws NamingException{
		if(context==null)
			init();
		return context;
		
	}
	
}
