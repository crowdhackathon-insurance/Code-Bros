package beans;


import java.io.IOException;

import database.Userdb;
import entities.User;


import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name="user")
@SessionScoped
public class UserBean  {
	private String username;
	private String password;
	private String email;
	private String confpass;
	private User u;

	

	public String login() {
		String ret=null;
		if(username.isEmpty() || password.isEmpty()) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			facesContext.addMessage(null, new FacesMessage("Empty username or password!"));
			username=null;
			password=null;
			return null;
		}
		u=new User();
		Userdb dbuser= new Userdb();
		u=dbuser.searchuser(getUsername(),getPassword());
		if(u!=null) {
				ret="home.xhtml";
			
		} 
		else {
			username=null;
			password=null;
			FacesContext facesContext = FacesContext.getCurrentInstance();
			facesContext.addMessage(null, new FacesMessage("Incorrect username or password!"));
		}
	
		return ret;
	}
	
	
	public Integer Bidder() {
		int company1_start = 280;   //arixikh timh
		int company2_start= 240;
		int company1_min=160,company2_min=190;
		int customer_aprox = 150;
		int i =0;
	//	while(i++<4){
			
	//	}
		return 180;

	}
	
	public String take(){
		String ret=null;
		System.out.println("ds000000000da");
		Userdb dbuser=new Userdb();
		System.out.println("dsadsadASDASdsada");
		dbuser.validateuser("CodeBros");
		System.out.println("ds222222222ada");
		ret="home.xhtml";
		return ret;
	}
	
	public boolean seecheck(){
		Userdb dbuser= new Userdb();
		u=dbuser.searchuser("CodeBros","12345678");
		return u.getChecked();
	}
	
	public String logout() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/index?faces-redirect=false";
	}
	
	public String register() {
		String ret=null;
		Userdb dbuser=new Userdb();
		if(!getPassword().equals(getConfpass())) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			facesContext.addMessage(null, new FacesMessage("Different password!"));
			return ret;
		}
		int ok=dbuser.insertuser(getUsername(),getPassword(),getConfpass(),getEmail());
		if(ok==1) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			facesContext.addMessage(null, new FacesMessage("Your information has been successfully submitted, please wait for the account confirmation!"));
		}
		else {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			facesContext.addMessage(null, new FacesMessage("This username is already in use!"));
		} 
		u=null;
		username=null;
		password=null;
		email=null;
		confpass=null;
		return ret;
	}



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
	

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getConfpass() {
		return confpass;
	}

	public void setConfpass(String confpass) {
		this.confpass = confpass;
	}

	public User getU() {
		return u;
	}

	public void setU(User u) {
		this.u = u;
	}

	
}
