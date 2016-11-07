/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejava.ca3.web;

import ejava.ca3.business.HQBean;
import ejava.ca3.business.PodBean;
import ejava.ca3.model.Pod;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
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
    @EJB private HQBean hqBean;
    
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException{
               
        int podId = Integer.parseInt(req.getParameter("podId"));
        String note = req.getParameter("note");
        
        Part imagePart = req.getPart("image");
        byte[] image = new byte[(int)imagePart.getSize()];
        InputStream is = imagePart.getInputStream();
        is.read(image);
        
        Long time = Long.parseLong(req.getParameter("time"));
        Pod pod =  podBean.get(podId).get();
        pod.setPodId(podId);
        pod.setNote(note);
        pod.setImage(image);
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(time);
        pod.setDeliveryDate(new java.sql.Date(cal.getTimeInMillis()));
        
        writeFile(image,req.getParameter("podId"));
        
        podBean.update(pod);
        hqBean.receiveAcknowledgement(pod);
        
        
    }
    
    public void writeFile(byte[] img, String fileName) throws IOException{
        try(FileOutputStream fs = new FileOutputStream(fileName)){
            fs.write(img);
        }
    }
    
}
