package Blog.entry;


import Blog.auth.User;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class EntryRepository {

    @PersistenceContext
    EntityManager em;


    public Entry findEntryById(Long entryId) {
        var entry = em.find(Entry.class, entryId);
        return entry;
    }


    @Transactional
    public void save(Entry entry){
        if (entry.getId() == null)
        em.persist(entry);
        else
            em.merge(entry);
    }

    public List<Entry> findEntryByUser(String username) {
        User user = new User(username);
        return em.createQuery("from Entry where owner_username=:username order by date desc", Entry.class).setParameter("username", user.getUsername()).getResultList();
    }

    public List<Entry> findAll() {
        return em.createQuery("from Entry order by date desc", Entry.class).getResultList();
    }

}
