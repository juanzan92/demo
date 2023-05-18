package model;

import java.util.List;

public interface SearchableGraph extends Graph {
    List<String> getKeywords(int id);
}
