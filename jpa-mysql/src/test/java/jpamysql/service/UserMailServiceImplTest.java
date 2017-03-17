package jpamysql.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import jpamysql.JpaMysqlApplication;
import jpamysql.model.Mail;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JpaMysqlApplication.class)
public class UserMailServiceImplTest {
	@Autowired
	UserMailService ums;

//	EntityManagerFactory emf = null;
//	
//	@Before
//	public void setUp() throws Exception {
//		emf = Persistence.createEntityManagerFactory(persistenceUnitName);
//	}

	@Test
	public void testGetMail() {
		List<Mail> mails = ums.getMail("01");
		try {
			for (Mail mail : mails) {
				System.out.println(mail.getContent());
//				System.out.println(mail.getUser());
			}
//			System.out.println(mails.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
