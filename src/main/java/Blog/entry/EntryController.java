package Blog.entry;


import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;






@Named
@RequestScoped
public class EntryController implements Serializable {

    @Inject
    EntryRepository entryRepository;

    @Inject
    private EntryRequest entryRequest;

    @Inject
    private Retriever retriever;

    @Inject
    private HttpServletRequest request;


    public EntryRequest getEntryRequest() {
        if (entryRequest == null)
            entryRequest = createEntryRequest();
        return entryRequest;
    }

    public EntryRequest createEntryRequest() {
        if (retriever.contains("id")) {
            var id = retriever.getLong("id");
            var entry = entryRepository.findEntryById(id);
            return new EntryRequest(entry);
        }
        return new EntryRequest();
    }


    public String save() {
        var session = request.getSession(false);
        var username = session.getAttribute("username");
        var entry = new Entry(entryRequest.getId(),entryRequest.getTitle(), entryRequest.getEntry(), entryRequest.getDate(), (String) username);
        entryRepository.save(entry);

        return "/index.xhtml?faces-redirect=true";
    }

    public String editSave() {
        Entry entry = null;
        if(retriever.contains("entryId")){
            entry = entryRepository.findEntryById(retriever.getLong("entryId"));
        }

        var session = request.getSession(false);
        var username = session.getAttribute("username");
//        var entry = new Entry(entryRequest.getId(),entryRequest.getTitle(), entryRequest.getEntry(), (String) username);
        entry.setTitle(entryRequest.getTitle());
        entry.setEntry(entryRequest.getEntry());
        entryRepository.save(entry);

        return "/index.xhtml?faces-redirect=true";
    }
}
