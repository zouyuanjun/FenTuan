package com.lejiaokeji.fentuan.databean;

public class PDD_Shop_Bean {
    int catId;
    int categoryId;
    int pageStart ;
    String pddPid ="";

    public PDD_Shop_Bean() {
    }

    public PDD_Shop_Bean(String pddPid) {
        this.pddPid = pddPid;
    }

    public PDD_Shop_Bean(int pageStart, String pddPid) {
        this.pageStart = pageStart;
        this.pddPid = pddPid;
    }

    public int getCatId() {
        return catId;
    }

    public void setCatId(int catId) {
        this.catId = catId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getPageStart() {
        return pageStart;
    }

    public void setPageStart(int pageStart) {
        this.pageStart = pageStart;
    }

    public String getPddPid() {
        return pddPid;
    }

    public void setPddPid(String pddPid) {
        this.pddPid = pddPid;
    }
}
