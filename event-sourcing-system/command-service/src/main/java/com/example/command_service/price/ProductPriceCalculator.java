package com.example.command_service.price;

import com.example.command_service.cart.productItems.PricedProductItem;
import com.example.command_service.cart.productItems.ProductItem;

public interface ProductPriceCalculator {
    PricedProductItem calculate(ProductItem productItem);
}
