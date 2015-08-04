package to.jump.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import to.jump.models.Click;
import to.jump.models.Link;

@Repository
@Transactional
public class ClickDAO {
	@Autowired
	private SessionFactory sessionFactory;

	public void insertClick(Click click) {
		sessionFactory.getCurrentSession().persist(click);
	}
	
	public void removeAllClicks(Link link) {
		sessionFactory.getCurrentSession()
			.createQuery("delete from Click c where c.linkId = :linkId")
			.setParameter("linkId", link.getId())
			.executeUpdate();
	}
	
	public long countClicks(Link link) {
		return (long) sessionFactory.getCurrentSession()
				.createQuery("select count(*) from Click c where c.linkId = :linkId")
				.setParameter("linkId", link.getId())
				.uniqueResult();
	}
}