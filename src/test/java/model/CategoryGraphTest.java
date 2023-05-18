package model;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static java.util.Objects.nonNull;
import static org.junit.Assert.*;

public class CategoryGraphTest {

    private CategoryGraph categories;

    /*
    Lawn & Garden has keywords "Lawn", "Garden", "GardeningTools".
     If a certain category is missing keywords, it needs to inherit it from the parent.
     Assume that there is a Root Category that is at the highest level with a default set of keywords.

Home appliances have sub categories like Major Appliances, Minor Appliances, Lawn & Garden
     */
    @Before
    public void setUp() throws Exception {
        categories = new CategoryGraph();

        var gardenKeywords = List.of("Lawn", "Garden");
        var applianceKeyword = List.of("Appliances");

        categories.insert(1, List.of("Home", "Appliance"), "Home&Appliances");
        categories.insert(101, List.of("Tv", "Freezer"), "Mayor Appliances");
    }

    @Test
    public void insertCategory() {
        // Given
        int nodeId = 103;

        //When
        var insertedNode = categories.insert(nodeId, List.of(""), "Minor Appliances");

        //Then
        assertTrue(nonNull(insertedNode));
        assertEquals(insertedNode.getId(), nodeId);
    }

    @Test
    public void searchKeywords() {
        //Given
        var searchedNodeId = 101;
        //When
        var searchedKeywords = categories.getKeywords(101);
        //Then

        assertTrue(nonNull(searchedKeywords));

    }
}