package Blog.entry;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;


@Entity
@Table(name = "entries")
public class Entry implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "entry")
    private String entry;

    @Column(name = "date")
    private LocalDateTime date = LocalDateTime.now();

    @JoinColumn(name = "owner_username")
    private String owner_username;

    public Entry(Long id, String title, String entry, String username) {
        this.id = id;
        this.title = title;
        this.entry = entry;
        this.owner_username = username;
    }

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

    public void setOwner_username(String owner_id) {
        this.owner_username = owner_id;
    }

    public Entry() {
    }

    public Entry(Long id, String title, String entry, LocalDateTime date, String ownerId) {
        this.id = id;
        this.title = title;
        this.entry = entry;
        this.date = date;
        this.owner_username = ownerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Entry)) return false;
        Entry entry1 = (Entry) o;
        return getId().equals(entry1.getId()) &&
                getTitle().equals(entry1.getTitle()) &&
                getEntry().equals(entry1.getEntry()) &&
                getDate().equals(entry1.getDate()) &&
                getOwner_username().equals(entry1.getOwner_username());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getEntry(), getDate(), getOwner_username());
    }

    @Override
    public String toString() {
        return "Entry{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", entry='" + entry + '\'' +
                ", date=" + date +
                ", ownerId=" + owner_username +
                '}';
    }

}
