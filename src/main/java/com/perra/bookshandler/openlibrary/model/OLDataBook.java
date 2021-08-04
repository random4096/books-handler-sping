package com.perra.bookshandler.openlibrary.model;

import java.lang.reflect.Field;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
class Key {
    private String url;
    private String name;

    public Key() {
    };

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

@JsonIgnoreProperties(ignoreUnknown = true)
public class OLDataBook {

    private String url;
    private String title;
    private String subtitle;
    private Key[] authors;
    @JsonProperty("publish_date")
    private String publishDate;
    @JsonProperty("number_of_pages")
    private int numberOfPages;

    private Key[] subjects;
    @JsonProperty("subject_places")
    private Key[] subjectPlaces;
    @JsonProperty("subject_people")
    private Key[] subjectPeople;
    @JsonProperty("subject_times")
    private Key[] subjectTimes;

    private OLCover cover;

    public OLDataBook() {
    }

    public OLDataBook(OLDataBook b) {
        this.url = b.url.toString();
        this.title = b.title.toString();
        this.subtitle = b.subtitle.toString();
        this.numberOfPages = b.numberOfPages;
       /* for (int k = 0; k < b.authors.length; k++) this.authors[k] = b.authors[k];
        for (int k = 0; k < b.subjects.length; k++) this.subjects[k] = b.subjects[k];
        for (int k = 0; k < b.subjectPlaces.length; k++) this.subjectPlaces[k] = b.subjectPlaces[k];
        for (int k = 0; k < b.subjectPeople.length; k++) this.subjectPeople[k] = b.subjectPeople[k];
        for (int k = 0; k < b.subjectTimes.length; k++) this.subjectTimes[k] = b.subjectTimes[k];*/
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return this.subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public Key[] getAuthors() {
        return this.authors;
    }

    public void setAuthors(Key[] authors) {
        this.authors = authors;
    }

    public String getPublishDate() {
        return this.publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public int getNumberOfPages() {
        return this.numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public Key[] getSubjects() {
        return this.subjects;
    }

    public void setSubjects(Key[] subjects) {
        this.subjects = subjects;
    }

    public Key[] getSubjectPlaces() {
        return this.subjectPlaces;
    }

    public void setSubjectPlaces(Key[] subjectPlaces) {
        this.subjectPlaces = subjectPlaces;
    }

    public Key[] getSubjectPeople() {
        return this.subjectPeople;
    }

    public void setSubjectPeople(Key[] subjectPeople) {
        this.subjectPeople = subjectPeople;
    }

    public Key[] getSubjectTimes() {
        return this.subjectTimes;
    }

    public void setSubjectTimes(Key[] subjectTimes) {
        this.subjectTimes = subjectTimes;
    }

	public OLCover getCover() {
		return this.cover;
	}
	
	public void setCover(OLCover cover) {
		this.cover = cover; 
	}


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getName());
        sb.append(": ");
        for (Field f : getClass().getDeclaredFields()) {
            sb.append(f.getName());
            sb.append("=");
            try {
                sb.append(f.get(this));
            } catch (IllegalArgumentException | IllegalAccessException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            sb.append(", ");
        }
        return sb.toString();
    }

}
