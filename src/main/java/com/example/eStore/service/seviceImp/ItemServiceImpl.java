package com.example.eStore.service.seviceImp;

import com.example.eStore.Enum.ProductStatus;
import com.example.eStore.Enum.UserNotFoundException;
import com.example.eStore.dto.requestDto.ItemRequestDto;
import com.example.eStore.exception.InvalidProductException;
import com.example.eStore.model.Cart;
import com.example.eStore.model.Customer;
import com.example.eStore.model.Item;
import com.example.eStore.model.Product;
import com.example.eStore.repository.CustomerRepository;
import com.example.eStore.repository.ItemRepository;
import com.example.eStore.repository.ProductRepository;
import com.example.eStore.service.ItemService;
import com.example.eStore.transeformer.ItemTransFormer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ItemRepository itemRepository;
    @Override
    public Item addItem(ItemRequestDto itemRequestDto) throws Exception {

        Customer customer;
        try{
            customer = customerRepository.findById(itemRequestDto.getCustomerId()).get();
        }
        catch (Exception e){
            throw new UserNotFoundException("Customer Id is invalid !!");
        }

        Product product;
        try{
            product = productRepository.findById(itemRequestDto.getProductID()).get();
        }
        catch(Exception e){
            throw new InvalidProductException("Product doesn't exist");
        }

        if(itemRequestDto.getRequiredQuantity()>product.getQuantity() || product.getProductStatus()!= ProductStatus.AVAILABLE){
            throw new Exception("Product out of Stock");
        }

        Item item = ItemTransFormer.ItemRequestDtoToItem(itemRequestDto);
        // item.setCart(customer.getCart());
        item.setProduct(product);

        product.getItemList().add(item);
        return itemRepository.save(item);
    }
}
