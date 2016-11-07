/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejava.ca3.web;

import ejava.ca3.business.PodBean;
import ejava.ca3.model.Pod;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author agarwal.puja
 */
@WebServlet("/callback")
public class CallBackServlet {
    
    @EJB private PodBean podBean;
    
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException{
        
        int podId = Integer.parseInt(req.getParameter("podId"));
        String ackId = req.getParameter("ackId");
        Pod pod = podBean.get(podId).get();
        pod.setAckId(ackId);
        podBean.update(pod);
    
    }
}
