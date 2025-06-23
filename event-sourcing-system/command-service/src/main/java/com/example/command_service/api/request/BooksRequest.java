package com.example.command_service.api.request;

import lombok.NonNull;
import org.springframework.validation.annotation.Validated;

import java.util.UUID;

public class BooksRequest {

    @Validated
    public record BookItem(
            String title,
            String author,
            Long price
    ){
    }

    public record CreateBook(
            @NonNull BookItem bookItem
    ) { }

    public record UpdateBook(
            @NonNull UUID bookId,
            @NonNull BookItem bookItem
    ) { }

    public record DeleteBook(
            @NonNull UUID bookId
    ) { }
}
