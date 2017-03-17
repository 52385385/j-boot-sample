package jpamysql.model;

import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
@Entity
@Table(name = "mail")
@NamedQuery(name = Mail.FIND_MAIL_BY_USER_ID, query = "select m from Mail m join fetch m.user u where u.id=?1")
public class Mail {
	@Id
	private String id = UUID.randomUUID().toString();
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "userid")
	private User user;
	private String content;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Override
	public String toString() {
		return String.format("{id:%s, user:%s, content:%s}", id, user.toString(), content);
	}
	protected final static String FIND_MAIL_BY_USER_ID = "Mail.findMailByUserId";
	
}
