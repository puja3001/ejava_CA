/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejava.ca3.web;

import ejava.ca3.business.PodBean;
import ejava.ca3.model.Pod;
import java.io.IOException;
import java.util.Calendar;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author agarwal.puja
 */
@WebServlet("/upload")
public class UploadServlet extends HttpServlet{
        
        @EJB private PodBean podBean;
    
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException{
        
        Integer podId = Integer.parseInt(req.getParameter("podId"));
        String note = req.getParameter("note");
        byte[] image = req.getParameter("image").getBytes();
        Long time = Long.parseLong(req.getParameter("time"));
        Pod pod = new Pod();
        pod.setPodId(podId);
        pod.setNote(note);
        pod.setImage(image);
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(time);
        pod.setDeliveryDate(new java.sql.Date(cal.getTimeInMillis()));
        podBean.update(pod);
        
    }
    
}
