/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejava.ca3.web;

import ejava.ca3.business.PodBean;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author agarwal.puja
 */
@WebServlet("/upload")
@MultipartConfig
public class UploadServlet extends HttpServlet{
        
    @EJB private PodBean podBean;
    
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException{
        
        
        String note = req.getPart("note").toString();
        System.out.println("note: "+note);
        
//        BufferedReader reader = new BufferedReader(new InputStreamReader(
//            req.getInputStream()));
//            StringBuilder sb = new StringBuilder();
//            for (String line; (line = reader.readLine()) != null;) {
//             System.out.println(line);
//  }
        
//        int podId = Integer.parseInt(req.getParameter("podId"));
//        String note = req.getParameter("note");
//        byte[] image = req.getParameter("image").getBytes();
//        Long time = Long.parseLong(req.getParameter("time"));
//        Pod pod = new Pod();
//        pod.setPodId(podId);
//        pod.setNote(note);
//        pod.setImage(image);
//        Calendar cal = Calendar.getInstance();
//        cal.setTimeInMillis(time);
//        pod.setDeliveryDate(new java.sql.Date(cal.getTimeInMillis()));
//        podBean.update(pod);
        
    }
    
}
