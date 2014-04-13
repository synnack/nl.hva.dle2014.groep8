/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import java.io.IOException;
import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonStructure;
import javax.servlet.http.HttpSession;
import javax.websocket.EndpointConfig;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import websockets.GetHttpSessionConfigurator;
import websockets.MessageDispatcher;

/**
 *
 * @author wilco
 */
@ServerEndpoint(value = "/websocket", 
                configurator = GetHttpSessionConfigurator.class)
public class WebSocketEndpoint {
    private HttpSession httpSession;
    private Session wsSession;

    @OnOpen
    public void onOpen(Session session, EndpointConfig config) {
        this.wsSession = session;
        this.httpSession = (HttpSession) config.getUserProperties()
                                               .get(HttpSession.class.getName());
        
        MessageDispatcher.add(this);
    }

    public void send(String message) {
        try {
            this.wsSession.getBasicRemote().sendText(message);
        } catch (IOException ex) {
            Logger.getLogger(WebSocketEndpoint.class.getName()).log(Level.SEVERE, "IO Exception", ex);
        }
    }
    
    @OnMessage
    public String onMessage(String message) {
        try {
            JsonReader reader = Json.createReader(new StringReader(message));
            JsonStructure jsonst = reader.read();
            JsonObject object = (JsonObject) jsonst;
            String type = object.getString("message_type");
            switch (type) {
                case "OFFER_SDP":
                    System.out.println("SDP Offer received.");
                    break;
                case "SDP_OK":
                    System.out.println("SDP Offer was accepted.");
                    break;
                case "LIST_PARTICIPANTS":
                    System.out.println("Participant list requested.");
                    break;
                case "NOTIFY_ERROR":
                    System.out.println("Received error notification.");
                    break;
                case "NOTIFY_STATUS":
                    System.out.println("Received status notification.");
                    break;
                case "CHAT_MESSAGE":
                    System.out.println("Received chat message.");
                    break;
                default:
                    break;
            }
        } catch (Exception e) { // Intentional catch all. Parse errors should be reported, but no further action is required.
            System.out.println("Exception " + e.toString());
            e.printStackTrace(System.out);
        }
        return null;
    }

    

}
