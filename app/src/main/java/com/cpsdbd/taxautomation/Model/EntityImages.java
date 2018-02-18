package com.cpsdbd.taxautomation.Model;

/**
 * Created by Sohel on 2/18/2018.
 */

public class EntityImages {

    private String id;
    private String entity_id;
    private String url;

    public EntityImages() {
    }

    public EntityImages(String id, String entity_id, String url) {
        this.id = id;
        this.entity_id = entity_id;
        this.url = url;
    }


    public EntityImages(String entity_id, String url) {
        this("",entity_id,url);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEntity_id() {
        return entity_id;
    }

    public void setEntity_id(String entity_id) {
        this.entity_id = entity_id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
