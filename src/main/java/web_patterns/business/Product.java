package web_patterns.business;

import lombok.*;

/**
 *
 * @author michelle
 */


// Add getter methods
@Getter
// Add a toString method
@ToString
// Add equals and hashcode methods - only include the specifically noted variables
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
// Add the ability to build object with any components in any order
@Builder
// Add an all-args constructor
@AllArgsConstructor
public class Product implements Comparable<Product> {
    // Annotate all fields that cannot be null with NonNull
    // Don't include any auto-generating primary key fields as these may not be known when the object is created
    @EqualsAndHashCode.Include
    @NonNull
    private String productCode;
    @NonNull
    private String productName;
    @NonNull
    private String productLine;
    @NonNull
    private String productScale;
    @NonNull
    private String productVendor;
    private String productDescription;
    @NonNull
    private int quantityInStock;
    @NonNull
    private double buyPrice;
    @NonNull
    private double msrp;

    public String format() {
        String formattedText = productCode + ": " + productName
                + "\n\tProduct Line: " + productLine
                + "\n\tScale: " + productScale
                + "\n\tVendor: " + productVendor
                + "\n\tDescription: " + productDescription
                + "\n\tQuantity in Stock: " + quantityInStock
                + "\n\tBuy Price: $" + buyPrice
                + "\n\tMSRP: $" + msrp;
        return formattedText;
    }

    @Override
    public int compareTo(Product p) {
        return this.productCode.compareTo(p.productCode);
    }
}
