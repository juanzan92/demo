package model;


import exception.IllegalInsertException;

import java.util.Collections;
import java.util.List;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public class CategoryGraph {

    CategoryNode root;

    //initialize the Graph
    public CategoryGraph() {
        this.root = null;
    }

    public CategoryNode insert(int id, List<String> keywords, String name) {
        if (isNull(root)) {
            //check if there is a root or if is the first element of the graph
            root = new CategoryNode(id, name);
            root.setKeywords(keywords);
        } else {
            //Insert the new node with recursive add of keywords
           return insertOrdered(root, id, keywords, name);
        }
        return null;
    }

    private CategoryNode insertOrdered(CategoryNode root, int id, List<String> keyword, String name) {
        //First i check if the node already exist.
        for (CategoryNode child : root.children) {
            if (child.id == id) {
                throw new IllegalInsertException("Duplicated Node");
            }
        }

        //Initilize the new Node with input parameters
        //Recieved all the keywords from parent for serching.
        CategoryNode newNode = new CategoryNode(id, name);
        newNode.getKeywords().addAll(keyword);
        root.getChildren().add(newNode);
        return newNode;
    }


    public List<String> getKeywords(int id) {
        //Search the node by id
        CategoryNode node = getNode(id, root);
        if (nonNull(node)) {
            getLevels(root, id);
            return node.keywords;
        }

        //If node is not found return new empty list
        return Collections.emptyList();
    }

    public CategoryNode getNode(int id, CategoryNode node) {
        //Check if the searched node is root
        if (isNull(root) || root.getId() == id) {
            return root;
        }

        //Search throw all root children node recursively
        for (CategoryNode child : root.children) {
            CategoryNode foundNode = getNode(id, child);
            if (nonNull(foundNode)) {
                return foundNode;
            }
        }
        return null;
    }

    public int getLevels(CategoryNode node, int id) {
        //If searched node is root return 0
        if (root.getId() == id) {
            return 0;
        }
        //Search for the node and with aux variable calculate the levels
        for (CategoryNode child : root.getChildren()) {
            int childrenLevel = getLevels(child, id);
            if (childrenLevel >= 0) {
                return childrenLevel + 1;
            }
        }

        //Graph Not initialize
        return -1;
    }
}
