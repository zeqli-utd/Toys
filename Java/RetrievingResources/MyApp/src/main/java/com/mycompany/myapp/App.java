/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

import java.net.URL;

/**
 * 
 * @author Zeqing Li
 */
public class App {
    public static void main(String[] args){
        App app = new App();
        app.loadFile();
    }
    
    public void loadFile(){
        URL url;
        // False. /com/mycompany/myapp/file3.txt does not exist
        url = getClass().getResource("file3.txt");
        System.out.println("URL1 = " + url);
        
        // True. classes/file3.txt
        url = getClass().getResource("/file3.txt");
        System.out.println("URL2 = " + url);
        
        // True. classes/file3.txt
        url = getClass().getResource("../../../file3.txt");
        System.out.println("URL3 = " + url);
        
        // True
        url = getClass().getClassLoader().getResource("file3.txt");
        System.out.println("URL4 = " + url);
        
        // False. Classloader.getResource('filename') filename cannot start with leading '/'
        url = getClass().getClassLoader().getResource("/file3.txt");
        System.out.println("URL5 = " + url);
        
        // True
        url = getClass().getClassLoader().getResource("./file3.txt");
        System.out.println("URL6 = " + url);        
        
        // True
        url = Thread.currentThread().getContextClassLoader().getResource("file3.txt");
        System.out.println("URL7 = " + url);        
    }
}
