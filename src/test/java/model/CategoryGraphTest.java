package model;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static java.util.Objects.nonNull;
import static org.junit.Assert.*;

public class CategoryGraphTest {

    private CategoryGraph categories;

    @Before
    public void setUp() throws Exception {
        categories = new CategoryGraph();

        categories.insert(1, List.of("Home", "Appliance"), "Home&Appliances");
        categories.insert(2, List.of("Tv", "Freezer"), "Mayor Appliances");
        categories.insert(4, List.of("Lawn", "Garden"), "Lawn&Garden");
    }

    @Test
    public void insertCategory() {
        // Given
        int nodeId = 3;

        //When
        var insertedNode = categories.insert(nodeId, List.of(""), "Minor Appliances");

        //Then
        assertTrue(nonNull(insertedNode));
        assertEquals(insertedNode.getId(), nodeId);
    }

    @Test
    public void searchKeywords() {
        //Given
        var searchedNodeId = 2;
        //When
        var searchedKeywords = categories.getKeywords(2);

        //Then
        assertTrue(nonNull(searchedKeywords));
        assertTrue(searchedKeywords.contains("Home"));
    }
    @Test
    public void searchKeywordsNotFound() {
        //Given
        var searchedNodeId = 2;
        //When
        var searchedKeywords = categories.getKeywords(2);

        //Then
        assertTrue(nonNull(searchedKeywords));
        assertFalse(searchedKeywords.contains("Garden"));
    }
}