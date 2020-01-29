package Blog.entry;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;





@Named
@RequestScoped
public class EntryRequest implements Serializable {
    private Long id;
    private String title;
    private String entry;
    private LocalDateTime date = LocalDateTime.now();
    private String owner_username;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEntry() {
        return entry;
    }

    public void setEntry(String entry) {
        this.entry = entry;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getOwner_username() {
        return owner_username;
    }

    public void setOwner_username(String owner_username) {
        this.owner_username = owner_username;
    }

    public EntryRequest() {
    }

    public EntryRequest(Entry entry) {
        this.id = entry.getId();
        this.title = entry.getTitle();
        this.entry = entry.getEntry();
        this.date = entry.getDate();
        this.owner_username = entry.getOwner_username();
    }

    @Override
    public String toString() {
        return "EntryRequest{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", entry='" + entry + '\'' +
                ", date=" + date +
                ", ownerId=" + owner_username +
                '}';
    }

}
