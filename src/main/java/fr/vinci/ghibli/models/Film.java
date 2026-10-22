
package fr.vinci.ghibli.models;

public class Film {

    private String id;
    private String title;
    private String description;
    private String director;
    private String image;

    public String release_date;

    public Film() {
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getDirector() {
        return director;
    }

    public String getImage() {
        return image;
    }

    public String getReleaseDate() {
        return release_date;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

}
