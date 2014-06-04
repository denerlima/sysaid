package model.entity;


import java.io.Serializable;
 
import javax.persistence.*;
 
@Entity
@Table(name = "USERS")
@NamedQuery(name = "User.findUserByEmail", query = "select u from User u where u.email = :email")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
 
    public static final String FIND_BY_EMAIL = "User.findUserByEmail";
 
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
 
    @Column(unique = true)
    private String email;
    private String password;
    private String name;
    @Enumerated(EnumType.STRING)
    private Role role;
 
    // get and set
 
    public boolean isAdmin() {
        return Role.ADMIN.equals(role);
    }
 
    public boolean isUser() {
        return Role.USER.equals(role);
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id != other.id)
			return false;
		return true;
	}
 

}