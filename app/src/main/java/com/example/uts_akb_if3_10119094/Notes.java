package com.example.uts_akb_if3_10119094;

public class Notes {
    private long id;
    private String title, desc, created;

    public Notes(){}
    public Notes(String title, String desc, String created){
        this.title = title;
        this.desc = desc;
        this.created = created;
    }
    public Notes(long id, String title, String desc, String created)
    {
        this.id = id;
        this.title = title;
        this.desc = desc;
        this.created = created;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }
}
