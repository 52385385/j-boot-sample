package jpamysql.model;

import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "user")
@NamedQuery(name = User.FIND_ALL_CUSTOM, query = "select u from User u where u.username is not null")
public class User {
	@Id
	private String id = UUID.randomUUID().toString();
	private String username;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private List<Mail> mails;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Override
	public String toString() {
		return String.format("{id:%s, username:%s, mails:%s}", id, username, mails.toString());
	}
	protected final static String FIND_ALL_CUSTOM = "User.findAllCustom";
}
