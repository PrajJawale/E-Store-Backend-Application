package com.example.eStore.service.seviceImp;

import com.example.eStore.Enum.ProductCategory;
import com.example.eStore.Enum.ProductStatus;
import com.example.eStore.dto.requestDto.ProductRequestDto;
import com.example.eStore.dto.responseDto.ProductResponseDto;
import com.example.eStore.exception.InvalidSelllerException;
import com.example.eStore.model.Item;
import com.example.eStore.model.Product;
import com.example.eStore.model.Seller;
import com.example.eStore.repository.ProductRepository;
import com.example.eStore.repository.SellerRepository;
import com.example.eStore.service.ProductService;
import com.example.eStore.transeformer.ProductTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceimpl implements ProductService {
    @Autowired
    SellerRepository sellerRepository;
    @Autowired
    private ProductRepository productRepository;

    public ProductResponseDto addProduct(ProductRequestDto productRequestDto) throws InvalidSelllerException {

        Seller seller;
        try{
            seller =  sellerRepository.findById(productRequestDto.getSellerId()).get();
        }
        catch (Exception e){
            throw new InvalidSelllerException("Seller doesn't exist");
        }

        Product product = ProductTransformer.ProductRequestDtoToProduct(productRequestDto);
        product.setSeller(seller);

        // add product to current products of seller
        seller.getProducts().add(product);
        sellerRepository.save(seller);  // saves both seller and product

        // prepare Response Dto
        return ProductTransformer.ProductToProductResponseDto(product);
    }

    @Override
    public List<ProductResponseDto> getAllProductsByCategory(ProductCategory productCategory){

        List<Product> products = productRepository.findByProductCategory(productCategory);

        List<ProductResponseDto> productResponseDtos = new ArrayList<>();
        for(Product product: products){
            productResponseDtos.add(ProductTransformer.ProductToProductResponseDto(product));
        }

        return productResponseDtos;
    }

    public void decreaseProductQuantity(Item item) throws Exception{

        Product product = item.getProduct();
        int quantity = item.getRequiredQuantity();
        int currentQuantity = product.getQuantity();
        if(quantity>currentQuantity){
            throw new Exception("Out of stock");
        }
        product.setQuantity(currentQuantity-quantity);
        if(product.getQuantity()==0){
            product.setProductStatus(ProductStatus.OUTOFSTOCK);
        }
    }


}