package model;

import java.util.List;

public interface Graph {
    CategoryNode insert(int id, List<String> keywords, String name);
    CategoryNode getNode(int id);
    int getLevels(int id);
}
