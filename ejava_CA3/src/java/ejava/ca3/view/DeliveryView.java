/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejava.ca3.view;

import ejava.ca3.business.DeliveryBean;
import ejava.ca3.business.PodBean;
import ejava.ca3.model.Delivery;
import ejava.ca3.model.Pod;
import java.sql.Date;
import java.util.Calendar;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author agarwal.puja
 */

@RequestScoped
@Named
public class DeliveryView {
    
    @EJB private DeliveryBean deliveryBean;
    @EJB private PodBean podBean;
    
    private String phone;
    private String name;

    private String address;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    public void create(){
        Delivery delivery = new Delivery();
        delivery.setName(name);
        delivery.setAddress(address);
        delivery.setPhone(phone);
        delivery.setCreateDate(new Date(Calendar.getInstance().getTimeInMillis()));
        Delivery packageId = deliveryBean.create(delivery);
        Pod pod = new Pod();
        pod.setPkgId(packageId);
        podBean.create(pod);
        
    }
    
}
