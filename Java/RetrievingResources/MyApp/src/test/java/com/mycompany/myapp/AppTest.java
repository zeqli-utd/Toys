package com.mycompany.myapp;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLDecoder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class AppTest {
    
    private static final Logger logger = LogManager.getRootLogger();

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {

        logger.info("---------- classpath ----------");
        ClassLoader classloader = ClassLoader.getSystemClassLoader();
        URL[] urls = ((URLClassLoader)classloader).getURLs();
        for(URL url: urls){
            logger.info(url.getPath().substring(1));
        }
        
        logger.info("---------- current classpath ----------");
        logger.info(AppTest.class.getResource(""));
        logger.info(AppTest.class.getClassLoader().getResource(""));
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testResourcesRetrieval() {
        URL url1 = getClass().getClassLoader().getResource("file3.txt");    // Absolute path
        URL url2 = getClass().getClassLoader().getResource("./file3.txt");  // Absolute path
        URL url3 = Thread.currentThread().getContextClassLoader().getResource("file3.txt");
        URL url4 = getClass().getClassLoader().getResource("/file3.txt"); 
        
        assertNotNull(url1);
        assertNotNull(url2);    
        assertNotNull(url3);
        assertNull(url4); // Classloader.getResource('filename') filename cannot start with leading '/'
    }
    
    @Test
    public void testClassGetResource(){
        // Classpath: target/classes/
        URL url1 = getClass().getResource("/file3.txt");            // Absolute path
        URL url2 = getClass().getResource("../../../file3.txt");    // Relative path
        URL url3 = getClass().getResource("file3.txt");             // Relative path
        
        
        assertNotNull(url1);
        assertNotNull(url2);  
        assertNull(url3);       // /com/mycompany/myapp/file3.txt does not exist
    }
    
    @Test
    public void testGetResourcePath(){
        logger.info("---------- URI and URL ----------");
        ClassLoader classLoader = getClass().getClassLoader();
        
        URI uri = null;
        URL url = null;
        try {
            uri = classLoader.getResource("file3.txt").toURI();
            url = classLoader.getResource("file3.txt");
            logger.info(uri.getScheme());
            
            logger.info("---------- Encoded Paths ----------");
            logger.info(url.getFile());   // encoded path /C:/User/...
            logger.info(url.getPath());   // same as above
            logger.info(uri.getRawSchemeSpecificPart());  // URL without file:
            
            logger.info("---------- Dncoded Paths ----------");
            logger.info(uri.getPath());    // decoded /C:/Users/zeqing/...

            logger.info("---------- URI Paths ----------");
            logger.info(url.toExternalForm());
            logger.info(url.toString());
            logger.info(uri.toURL().toString());                     // file:/C:/Users/zeqing/....
            logger.info(uri.toString());        // same as toURL

            logger.info("---------- URLDecoder.decode(<encoded-string>, \"UTF-8\") ----------");
            String s = URLDecoder.decode(url.getPath().substring(1), "UTF-8");
            logger.info(s);
            
        } catch (URISyntaxException | MalformedURLException | UnsupportedEncodingException e) { }
        
    }

}
