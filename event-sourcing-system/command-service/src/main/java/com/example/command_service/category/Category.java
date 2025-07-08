package com.example.command_service.category;

import com.example.command_service.core.aggregates.AbstractAggregate;
import com.example.common.event.CategoryEvent;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class Category extends AbstractAggregate<CategoryEvent, UUID> {
    private String name;
//    private ProductStatus status;

    public Category() {
    }

    public static Category empty(){
        return new Category();
    }

    public Category(UUID id, String name) {
        this.id = id;
        this.name = name;
        enqueue(
                new CategoryEvent.CategoryEventCreated(
                        id,
                        name
                )
        );
    }

    static Category create(UUID id, String name) {
        return new Category(
                id,
                name
        );
    }

    void update(UUID id,String name) {
        enqueue(
                new CategoryEvent.CategoryEventUpdated(
                        id,
                        name
                )
        );
    }

    void delete(UUID id){
        enqueue(
                new CategoryEvent.CategoryEventDeleted(
                        id
                )
        );
    }

    static String mapToStreamId(UUID productId) {
        return "Category-%s".formatted(productId);
    }

    @Override
    public void when(CategoryEvent categoryEvent) {
        switch (categoryEvent){
            case CategoryEvent.CategoryEventCreated event :{
                id = event.categoryId();
                name = event.name();
                break;
            }
            case CategoryEvent.CategoryEventUpdated event: {
                id = event.categoryId();
                name = event.name();
                break;
            }
            case CategoryEvent.CategoryEventDeleted event: {
                id = event.categoryId();
                break;
            }
            case null:
                throw new IllegalArgumentException("Event cannot be null!");
        }
    }
}
