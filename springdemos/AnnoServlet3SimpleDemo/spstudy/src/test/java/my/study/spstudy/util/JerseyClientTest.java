package my.study.spstudy.util;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;

import org.junit.Before;
import org.junit.Test;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.client.urlconnection.HttpURLConnectionFactory;
import com.sun.jersey.client.urlconnection.URLConnectionClientHandler;

public class JerseyClientTest {
	
	@Before
	public void before(){
		System.setProperty("proxySet", "true"); 
		System.setProperty("http.proxyHost", "127.0.0.1"); 
		System.setProperty("http.proxyPort", "8888");
	}
	
	@Test
	public void testUseProxy(){
		ClientConfig config = new DefaultClientConfig();
		Client client = new Client(new URLConnectionClientHandler(
		        new HttpURLConnectionFactory() {
		    Proxy p = null;
		    @Override
		    public HttpURLConnection getHttpURLConnection(URL url)
		            throws IOException {
		        if (p == null) {
		            if (System.getProperties().containsKey("http.proxyHost")) {
		                p = new Proxy(Proxy.Type.HTTP,
		                        new InetSocketAddress(
		                        System.getProperty("http.proxyHost"),
		                        Integer.getInteger("http.proxyPort", 80)));
		            } else {
		                p = Proxy.NO_PROXY;
		            }
		        }
		        return (HttpURLConnection) url.openConnection(p);
		    }
		}), config);
		
		String rs = client.resource("http://www.vip.com/").get(String.class);
		System.out.println("result:" + rs);
	}
	
	
}

