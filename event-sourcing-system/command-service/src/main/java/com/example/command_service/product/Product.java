package com.example.command_service.product;

import com.example.command_service.core.aggregates.AbstractAggregate;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class Product extends AbstractAggregate<ProductEvent, UUID> {
    private ProductInfo productInfo;
//    private ProductStatus status;

    public Product() {
    }

    public static Product empty(){
        return new Product();
    }

    public Product(UUID id,ProductInfo productInfo) {
        this.id = id;
        this.productInfo = productInfo;
        enqueue(
                new ProductEvent.ProductEventCreated(
                        id,
                        productInfo

                )
        );
    }

    static Product create(UUID id, ProductInfo productInfo) {
        return new Product(
                id,
                productInfo
        );
    }

    void update(ProductInfo productInfo) {
        enqueue(
                new ProductEvent.ProductEventUpdated(productInfo)
        );
    }

    void delete(UUID id){
        enqueue(
                new ProductEvent.ProductEventDeleted(id)
        );
    }

    static String mapToStreamId(UUID productId) {
        return "Product-%s".formatted(productId);
    }

    @Override
    public void when(ProductEvent productEvent) {
        switch (productEvent){
            case ProductEvent.ProductEventCreated event :{
                id = event.productId();
                productInfo = event.productInfo();
                break;
            }
            case ProductEvent.ProductEventUpdated event: {
                productInfo = event.productInfo();
                break;
            }
            case ProductEvent.ProductEventDeleted event: {
                id = event.productId();
                productInfo = null; // Assuming productInfo is cleared on deletion
                break;
            }
            case null:
                throw new IllegalArgumentException("Event cannot be null!");
        }
    }
}
