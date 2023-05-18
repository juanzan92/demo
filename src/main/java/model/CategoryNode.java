package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CategoryNode {

    //I would do a Builder Pattern but for time reasons i won't

    int id;
    String name;

    public String getName() {
        return name;
    }

    List<String> keywords;
    List<CategoryNode> children;

    public CategoryNode(int id, String name) {
        this.id = id;
        this.name = name;
        this.keywords = new ArrayList<>();
        this.children = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }

    public List<CategoryNode> getChildren() {
        return children;
    }

}
