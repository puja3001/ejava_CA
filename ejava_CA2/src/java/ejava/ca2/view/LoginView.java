package ejava.ca2.view;

import ejava.ca2.business.LoginBean;
import ejava.ca2.model.Users;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

@ViewScoped
@Named
public class LoginView implements Serializable {
	private static final long serialVersionUID = 1L;
        
        @EJB private LoginBean loginBean;
        
	private String username;
	private String password;

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String login() {
		HttpServletRequest req = 
				(HttpServletRequest)FacesContext.getCurrentInstance()
						.getExternalContext().getRequest();
		try {
			req.login(username, password);
		} catch (Throwable t) {
			FacesContext.getCurrentInstance()
					.addMessage(null, new FacesMessage("Incorrect login"));
			return (null);
		}

		return ("secure/menu?faces-redirect=true");
	}
        
        public String encrypt(String password){
            try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(password.getBytes("UTF-8"));
            byte[] digest = md.digest();
            BigInteger bigInt = new BigInteger(1, digest);
            String output = bigInt.toString(16);

            System.out.println(output);
            return output;

        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            ex.printStackTrace();
        }
            return null;
        }
        
        public String register(){
            Users user = new Users();
            user.setUserid(username);
            user.setPassword(encrypt(password));
            loginBean.create(user);
            username = "";
            return (null);
            
        }

}

