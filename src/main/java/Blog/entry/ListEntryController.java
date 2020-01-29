package Blog.entry;


import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.List;






@Named
@RequestScoped
public class ListEntryController implements Serializable {

    @Inject
    EntryRepository entryRepository;

    @Inject
    private HttpServletRequest request;

    public List<Entry> getEntryList() {
        return entryRepository.findAll();
    }
    public List<Entry> getEntryListByUser() {
        var session = request.getSession(false);
        var username = session.getAttribute("username");

        return entryRepository.findEntryByUser((String)username);
    }
}
