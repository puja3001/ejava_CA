/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejava.ca2.websocket;

import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedScheduledExecutorService;
import javax.enterprise.context.RequestScoped;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author Vrinda
 */
@RequestScoped
@ServerEndpoint("/notice")
public class NoticeSocket {
    
    @Resource(lookup = "concurrent/noticeThreadPool")
	private ManagedScheduledExecutorService executor;

	@OnOpen
	public void open(Session session) {
		System.out.println(">>> new session: " + session.getId());
	}
}
