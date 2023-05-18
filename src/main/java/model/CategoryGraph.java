package model;


import exception.IllegalInsertException;
import exception.NotFoundException;

import java.util.*;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public class CategoryGraph implements SearchableGraph {

    CategoryNode root;

    //initialize the Graph
    public CategoryGraph() {
        this.root = null;
    }


    @Override
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

    private CategoryNode insertOrdered(CategoryNode parent, int id, List<String> keyword, String name) {
        //First i check if the node already exist.
        for (CategoryNode child : root.children) {
            if (child.id == id) {
                throw new IllegalInsertException("Duplicated Node");
            }
        }

        //Initilize the new Node with input parameters
        //Recieved all the keywords from parent for searching.
        CategoryNode newNode = new CategoryNode(id, name);
        newNode.getKeywords().addAll(keyword);
        newNode.getKeywords().addAll(parent.getKeywords());
        parent.getChildren().add(newNode);
        return newNode;
    }

    @Override
    public List<String> getKeywords(int id) {
        //Search the node by id
        CategoryNode node = getNode(id);
        if (nonNull(node)) {
            getLevels(id);
            return node.keywords;
        }

        //If node is not found return new empty list
        return Collections.emptyList();
    }

    @Override
    public CategoryNode getNode(int id) {
        //Check if the searched node is root
        if (isNull(root) || root.getId() == id) {
            return root;
        }

        //Search throw all root children node recursively
        for (CategoryNode child : root.children) {
            CategoryNode foundNode = child.getId() == id ? child : getNode(id);
            if (nonNull(foundNode)) {
                return foundNode;
            }
        }
        return null;
    }

    @Override
    public int getLevels(int id) {
        //If searched node is root return 0
        if (root.getId() == id) {
            return 0;
        }
        //Search for the node and with aux variable calculate the levels
        Queue<CategoryNode> queue = new LinkedList<>();
        int level = 0;

        queue.offer(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();

            for (int i = 0; i < levelSize; i++) {
                CategoryNode node = queue.poll();
                if (node.getId() == id) {
                    return level;
                }
                queue.addAll(node.getChildren());
            }
            level++;
        }

        //Graph Not initialize
        throw new NotFoundException("Node with id " + id + " not found");
    }
}
