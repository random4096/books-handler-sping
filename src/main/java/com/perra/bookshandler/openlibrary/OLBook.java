package com.perra.bookshandler.openlibrary;

import java.lang.reflect.Field;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

class AuthorKey {
    private String key;
    public AuthorKey() {};

    public String getKey() { return this.key; }
    public void setKey(String key) { this.key = key; }
}

@JsonIgnoreProperties(ignoreUnknown = true)
public class OLBook {

    private Object description;
    private String title;
    private String key;
    private String[] subjects;

    private String isbn_10;
    private String isbn_13;
    private String lccn;
    //private String oclc;
    private String olid;

    @JsonProperty("author_key")
    private String authorKey;

    @JsonProperty("author_name")
    private String authorName;

    private String[] publisher;
    @JsonProperty("first_publish_year")
    private String publishDate;

    public OLBook() {}

    public Object getDescription() {
        return this.description;
    }

    public void setDescription(Object description) {
        this.description = description;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String[] getSubjects() {
        return this.subjects;
    }

    public void setSubjects(String[] subjects) {
        this.subjects = subjects;
    }

    public void setIsbn(String[] isbn) {
        for (String i : isbn) {
            if (i.length() == 10) this.isbn_10 = i;
            else if (i.length() == 13) this.isbn_13 = i;
        }
    }

    public String getIsbn_10() {
        return this.isbn_10;
    }

    public void setIsbn_10(String[] isbn_10) {
        if (isbn_10 != null && isbn_10.length > 0) this.isbn_10 = isbn_10[0];
        else this.isbn_10 = "";
    }

    public String getIsbn_13() {
        return this.isbn_13;
    }

    public void setIsbn_13(String[] isbn_13) {
        if (isbn_13 != null && isbn_13.length > 0) this.isbn_13 = isbn_13[0];
        else this.isbn_13 = "";
    }

    public String getLccn() {
        return this.lccn;
    }

    public void setLccn(String[] lccn) {
        if (lccn != null && lccn.length > 0) this.lccn = lccn[0];
        else this.lccn = "";
    }

    public String getAuthorKey() {
        return this.authorKey;
    }

    // don't remember when used
    /*public void setAuthorKey(AuthorKey[] authorKey) {
        if (authorKey != null && authorKey.length > 0) this.authorKey = authorKey[0].getKey();
        else this.authorKey = "";
    }*/
    public void setAuthorKey(String[] authorKey) {
        if (authorKey != null && authorKey.length > 0) this.authorKey = authorKey[0];
        else this.authorKey = "";
    }

    /*public void setAuthorKey(String authorKey) {
        this.authorKey = authorKey;
    }*/

	/*public String getOclc() {
		return this.oclc;
	}
	
	public void setOclc(String oclc) {
		this.oclc = oclc; 
	}*/

	public String getOlid() {
		return this.olid;
	}
	
	public void setOlid(String olid) {
		this.olid = olid; 
	}


	public String getAuthorName() {
		return this.authorName;
	}
	
	public void setAuthorName(String[] authorName) {
		if (authorName != null && authorName.length > 0) this.authorName = authorName[0];
        else this.authorName = "";
	}

	public String[] getPublisher() {
		return this.publisher;
	}
	
	public void setPublisher(String[] publisher) {
		this.publisher = publisher; 
	}

	public String getPublishDate() {
		return this.publishDate;
	}
	
	public void setPublishDate(String publishDate) {
		this.publishDate = String.valueOf(publishDate);
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
 