package ch.rssTicker.tests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import ch.common.testing.TestPojo;
import ch.common.utils.HibernateUtil;
import ch.rssTicker.persistence.RssConfigDTO;

public class HibernateTests {
	private final static String TestValue = "Test";
	private static RssConfigDTO rssConfigDTO;

	@BeforeAll
	static void initializeTestData() {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory("Hibernate.cfg.xml",
				"RssTickerConfig.hbm.xml");
		Session session = null;

		try {
			session = sessionFactory.openSession();
			session.beginTransaction();

			rssConfigDTO = new RssConfigDTO();
			rssConfigDTO.setName(TestValue);
			rssConfigDTO.setMailReceivers(TestValue);
			rssConfigDTO.setUrl(TestValue);
			rssConfigDTO.setFrequency(1000);

			session.save(rssConfigDTO);
			session.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
	}

	@Test
	void testGetRssConfig() {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory("Hibernate.cfg.xml",
				"RssTickerConfig.hbm.xml");
		Session session = null;

		try {
			session = sessionFactory.openSession();
			@SuppressWarnings("unchecked")
			List<TestPojo> list = session.createQuery("from RssConfigDTO where Name = :testValue")
					.setParameter("testValue", TestValue).list();
			assertTrue(list.size() == 1);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	

	@AfterAll
	static void cleardown() {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory("Hibernate.cfg.xml", "HibernateTests.hbm.xml");
		Session session = null;

		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			session.delete(rssConfigDTO);
			session.getTransaction().commit();
			assertTrue(session.get(RssConfigDTO.class, rssConfigDTO.getId()) == null);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
			sessionFactory.close();
			HibernateUtil.destroyRegistryBuilder();
		}
	}
}
