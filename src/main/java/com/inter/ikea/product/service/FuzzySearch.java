package com.inter.ikea.product;

import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FuzzySearch {

    // Function to calculate the Damerau-Levenshtein edit distance between two strings
    public static int calculateEditDistance(String source, String target) {
        int sourceLength = source.length();
        int targetLength = target.length();
        
        // Initialize the distance matrix
        int[][] calculateDistance = new int[sourceLength + 1][targetLength + 1];
    
        // Populate the base cases for deletions and insertions
        for (int i = 0; i <= sourceLength; i++) {
            calculateDistance[i][0] = i; // Deletion cost
        }
        for (int j = 0; j <= targetLength; j++) {
            calculateDistance[0][j] = j; // Insertion cost 
        }
    
    // Fill the distance matrix by computing the minimum cost of edits
int i = 1;
while (i <= sourceLength) {
    int j = 1;
    while (j <= targetLength) {
        // Calculate substitution cost
        int substitutionCost = (source.charAt(i - 1) == target.charAt(j - 1)) ? 0 : 1;

        // Compute minimal cost (deletion, insertion, substitution)
        int deletion = calculateDistance[i - 1][j] + 1;
        int insertion = calculateDistance[i][j - 1] + 1;
        int substitution = calculateDistance[i - 1][j - 1] + substitutionCost;

        calculateDistance[i][j] = Math.min(Math.min(deletion, insertion), substitution);

        // Handle transposition if applicable
        if (i > 1 && j > 1 &&
            source.charAt(i - 1) == target.charAt(j - 2) &&
            source.charAt(i - 2) == target.charAt(j - 1)) {
            int transposition = calculateDistance[i - 2][j - 2] + substitutionCost;
            calculateDistance[i][j] = Math.min(calculateDistance[i][j], transposition);
        }

        j++; // Increment inner loop index
    }
    i++; // Increment outer loop index
}

    
        // Return the computed edit distance between the two strings
        return calculateDistance[sourceLength][targetLength];
    }

    // Function to search products based on a fuzzy match of the search term
    public List<Product> searchProducts(String searchTerm, List<Product> products) {
        // Print all product names for debugging
        products.forEach(product -> System.out.println("Product name: " + product.getName()));

        return products.stream()
            .filter(product -> {
                // Calculate the edit distance between the product name and the search term
                int distance = calculateEditDistance(product.getName().toLowerCase(), searchTerm.toLowerCase());
                
                // Print the calculated distance for debugging
                System.out.println("Distance from '" + searchTerm + "' to '" + product.getName() + "': " + distance);
                
                // Return products where the edit distance is within a reasonable threshold
                return distance <= 5; // You can adjust this threshold value as needed
            })
            .collect(Collectors.toList());
    }
}

/*
 * private int damerauLevenshteinDistance(String s1, String s2) {
        int[][] d = new int[s1.length() + 1][s2.length() + 1];
        for (int i = 0; i <= s1.length(); i++) d[i][0] = i;
        for (int j = 0; j <= s2.length(); j++) d[0][j] = j;
        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                int cost = s1.charAt(i - 1) == s2.charAt(j - 1) ? 0 : 1;
                d[i][j] = Math.min(Math.min(d[i - 1][j] + 1, d[i][j - 1] + 1), d[i - 1][j - 1] + cost);
                if (i > 1 && j > 1 && s1.charAt(i - 1) == s2.charAt(j - 2) && s1.charAt(i - 2) == s2.charAt(j - 1)) {
                    d[i][j] = Math.min(d[i][j], d[i - 2][j - 2] + cost);
                }
            }
        }
        return d[s1.length()][s2.length()];
    }
}
Mock Data Pre-Population:

You can pre-populate some data in memory by adding a method in the controller’s constructor or a separate service.
java
Copy code
@PostConstruct
public void init() {
    products.add(new Product(1L, "Smartphone", "Electronics", "Latest model", 699.99, "image-url-1"));
    products.add(new Product(2L, "Laptop", "Electronics", "Powerful and lightweight", 1199.99, "image-url-2"));
}
Run the Backend Server:

Run your Spring Boot application using your IDE or command line with mvn spring-boot:run or gradle bootRun.
Step 2: Frontend - React Setup
Set Up React with TypeScript:

Initialize a React project with TypeScript:
bash
Copy code
npx create-react-app retail-product-catalog --template typescript
cd retail-product-catalog
Copy code
Set Up a Basic Folder Structure:

Organize your components:
bash
Copy code
src/
├── components/
│   ├── SearchBar.tsx
│   ├── ProductList.tsx
│   └── ProductDetail.tsx
├── services/
│   └── productService.ts
└── App.tsx
Create the Search View (SearchBar + ProductList):

SearchBar.tsx: Handles user input and throttles API requests.

tsx
Copy code
import React, { useState } from 'react';

interface SearchBarProps {
  onSearch: (query: string) => void;
}

const SearchBar: React.FC<SearchBarProps> = ({ onSearch }) => {
  const [query, setQuery] = useState('');

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setQuery(e.target.value);
    onSearch(e.target.value);
  };

  return (
    <input type="text" placeholder="Search for products..." value={query} onChange={handleChange} />
  );
};

export default SearchBar;
ProductList.tsx: Displays the list of products based on search results.

tsx
Copy code
import React from 'react';

interface Product {
  id: number;
  name: string;
  category: string;
  price: number;
  imageUrl: string;
}

interface ProductListProps {
  products: Product[];
}

const ProductList: React.FC<ProductListProps> = ({ products }) => {
  return (
    <ul>
      {products.map(product => (
        <li key={product.id}>
          <h3>{product.name}</h3>
          <p>{product.category} - ${product.price}</p>
        </li>
      ))}
    </ul>
  );
};

export default ProductList;
Product Detail View (ProductDetail.tsx):

tsx
Copy code
import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';

interface Product {
  id: number;
  name: string;
  category: string;
  description: string;
  price: number;
  imageUrl: string;
}

const ProductDetail: React.FC = () => {
  const { id } = useParams();
  const [product, setProduct] = useState<Product | null>(null);

  useEffect(() => {
    // Fetch product by id
    fetch(`/products/${id}`)
      .then(res => res.json())
      .then(data => setProduct(data));
  }, [id]);

  return product ? (
    <div>
      <h1>{product.name}</h1>
      <p>{product.category}</p>
      <p>{product.description}</p>
      <p>${product.price}</p>
      <img src={product.imageUrl} alt={product.name} />
    </div>
  ) : <p>Loading...</p>;
};

export default ProductDetail;
Product Service (productService.ts):

To interact with the backend, create a service to handle API calls:
ts
Copy code
export const searchProducts = async (query: string) => {
  const response = await fetch(`/products/search?query=${query}`);
  return response.json();
};
App Component (App.tsx):

tsx
Copy code
import React, { useState } from 'react';
import SearchBar from './components/SearchBar';
import ProductList from './components/ProductList';
import { searchProducts } from './services/productService';

const App: React.FC = () => {
  const [products, setProducts] = useState([]);

  const handleSearch = (query: string) => {
    searchProducts(query).then(data => setProducts(data));
  };

  return (
    <div>
      <SearchBar onSearch={handleSearch} />
      <ProductList products={products} />
    </div>
  );









 */
