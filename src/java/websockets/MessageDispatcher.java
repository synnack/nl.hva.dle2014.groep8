/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package websockets;

import controller.WebSocketEndpoint;
import entity.Course;
import java.util.HashMap;
import java.util.Set;


/**
 *
 * @author wilco
 */
public class MessageDispatcher {
    //private static WebSocketEndpoint[] endpoints = new WebsocketEndpoint[]();
    private final static HashMap<String, Set> subscriptions = new HashMap<>();
    
    public static void add(WebSocketEndpoint ep) {
        //endpoints.add(ep);
        ep.send("Ohai");
    }
    
    public static void remove(WebSocketEndpoint ep) {
        //endpoints.remove(ep);
    }
    
    public static void subscribe(Course course, WebSocketEndpoint ep) {
        
    }
    
    public static void unsubscribe(Course course, WebSocketEndpoint ep) {
        
    }
}
