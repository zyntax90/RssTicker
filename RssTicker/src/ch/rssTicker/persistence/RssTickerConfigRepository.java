package ch.rssTicker.persistence;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import ch.common.utils.HibernateUtil;

public class RssTickerConfigRepository {

	@SuppressWarnings("unchecked")
	public static List<RssConfigDTO> get() {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory("Hibernate.cfg.xml", "RssTickerConfig.hbm.xml");
		Session session = null;
		List<RssConfigDTO> configData = null;

		try {
			session = sessionFactory.openSession();
			configData = session.createQuery("from RssConfigDTO").list();
		} catch (Exception e) {
			System.err.println("Error while loading config data" + e);
		} finally {
			session.close();
			sessionFactory.close();
			HibernateUtil.destroyRegistryBuilder();
		}

		return configData;
	}
}
