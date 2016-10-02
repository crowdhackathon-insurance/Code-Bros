package entities;

import java.io.Serializable;
import javax.persistence.*;
/**
 * The persistent class for the users database table.
 * 
 */
@Entity
@Table(name="users")
@NamedQueries({
@NamedQuery(name="User.findbyusrandpass", query="SELECT u FROM User u WHERE u.username = :username and u.password= :password")})
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String username;
	private boolean checked;
	private String email;
	private String password;
	//bi-directional many-to-one association to Bid
	//@OneToMany(mappedBy="user")
	//private List<Product> products;
	
	public User() {
	}
	
	public User(String username,String password,String email,boolean checked) {
		this.username=username;
		this.email=email;
		this.password=password;
		this.checked=checked;
	}
	
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public boolean getChecked() {
		return this.checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	
}