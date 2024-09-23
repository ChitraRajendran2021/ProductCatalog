package com.inter.ikea.product;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FuzzySearch {

    private static final Logger logger = LoggerFactory.getLogger(FuzzySearch.class);
    /*
    * Function to calculate the Damerau-Levenshtein edit distance between two strings
     */ 
    public static int calculateEditDistance(String source, String target) {
        int sourceLength = source.length();
        int targetLength = target.length();
        
        logger.info("Calculating edit distance between '{}' and '{}'", source, target);

        int[][] calculateDistance = new int[sourceLength + 1][targetLength + 1];
    
        // Populate the base cases for deletions and insertions
        for (int i = 0; i <= sourceLength; i++) {
            calculateDistance[i][0] = i; // Deletion cost
        }
        for (int j = 0; j <= targetLength; j++) {
            calculateDistance[0][j] = j; // Insertion cost 
        }
    
        for (int i = 1; i <= sourceLength; i++) {
            for (int j = 1; j <= targetLength; j++) {
                // Calculating substitution cost
                int substitutionCost = (source.charAt(i - 1) == target.charAt(j - 1)) ? 0 : 1;

                // calculate minimal distance (deletion, insertion, substitution)
                int deletion = calculateDistance[i - 1][j] + 1;
                int insertion = calculateDistance[i][j - 1] + 1;
                int substitution = calculateDistance[i - 1][j - 1] + substitutionCost;

                calculateDistance[i][j] = Math.min(Math.min(deletion, insertion), substitution);

                if (i > 1 && j > 1 &&
                    source.charAt(i - 1) == target.charAt(j - 2) &&
                    source.charAt(i - 2) == target.charAt(j - 1)) {
                    int transposition = calculateDistance[i - 2][j - 2] + substitutionCost;
                    calculateDistance[i][j] = Math.min(calculateDistance[i][j], transposition);
                }
            }
        }

        logger.info("Final edit distance between '{}' and '{}' is: {}", source, target, calculateDistance[sourceLength][targetLength]);

        // Return the calculated distance between the two strings
        return calculateDistance[sourceLength][targetLength];
    }

    /*
    * Function to search products based on a fuzzy match of the search term
     */ 
    public List<Product> searchProducts(String searchTerm, List<Product> products) {
        logger.info("Starting fuzzy search for '{}'", searchTerm);

        products.forEach(product -> logger.debug("Product name: " + product.getName()));

        List<Product> result = products.stream()
            .filter(product -> {
                int distance = calculateEditDistance(product.getName().toLowerCase(), searchTerm.toLowerCase());
                logger.debug("Distance from '{}' to '{}': {}", searchTerm, product.getName(), distance);
                return distance <= 5; 
            })
            .collect(Collectors.toList());

        logger.info("Fuzzy search complete. Found {} products matching '{}'", result.size(), searchTerm);
        return result;
    }
}
