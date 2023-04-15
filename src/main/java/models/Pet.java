package models;

import java.util.List;

public class Pet {
    Long id;
    DataModel category;
    String name;
    List<String> photoUrls;
    List<DataModel> tags;
    String status;

    public Pet(String petName) {
        this.name = petName;
    }

    public Pet() {}


    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public DataModel getCategory() {
        return category;
    }

    public void setCategory(DataModel category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getPhotoUrls() {
        return photoUrls;
    }

    public void setPhotoUrls(List<String> photoUrls) {
        this.photoUrls = photoUrls;
    }

    public List<DataModel> getTags() {
        return tags;
    }

    public void setTags(List<DataModel> tags) {
        this.tags = tags;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static class DataModel {
        Long id;
        String name;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
