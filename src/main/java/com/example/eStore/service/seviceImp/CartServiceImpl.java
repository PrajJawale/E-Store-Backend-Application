package com.example.eStore.service.seviceImp;

import com.example.eStore.Enum.UserNotFoundException;
import com.example.eStore.dto.requestDto.CheckOutCartRequestDto;
import com.example.eStore.dto.requestDto.ItemRequestDto;
import com.example.eStore.dto.responseDto.CartResponseDto;
import com.example.eStore.dto.responseDto.ItemResponseDto;
import com.example.eStore.dto.responseDto.OrderResponseDto;
import com.example.eStore.exception.InvalidCardException;
import com.example.eStore.model.*;
import com.example.eStore.repository.CardRepository;
import com.example.eStore.repository.CartRepository;
import com.example.eStore.repository.CustomerRepository;
import com.example.eStore.repository.OrderRepository;
import com.example.eStore.service.CartService;
import com.example.eStore.service.OrderService;
import com.example.eStore.transeformer.ItemTransFormer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CartRepository cartRepository;

//    @Autowired OrderService orderService;
    @Autowired
    OrderRepository orderedRepository;

    @Autowired
    CardRepository cardRespository;

   @Autowired
    OrderService orderService;
    public CartResponseDto saveCart(Integer customerId, Item item){

        Customer customer = customerRepository.findById(customerId).get();
        Cart cart = customer.getCart();
        if (cart == null) {
            cart = new Cart();
            customer.setCart(cart);
            cart.setCartTotal(0);
        }
        int newTotal = cart.getCartTotal() + item.getRequiredQuantity()*item.getProduct().getPrice();
        cart.setCartTotal(newTotal);
        cart.getItems().add(item);
        cart.setCustomer(customer);
        cart.setNumberOfItems(cart.getItems().size());
        item.setCart(cart);
        Cart savedCart = cartRepository.save(cart);

        CartResponseDto cartResponseDto = CartResponseDto.builder()
                .cartTotal(savedCart.getCartTotal())
                .customerName(customer.getName())
                .numberOfItems(savedCart.getNumberOfItems())
                .build();

        List<ItemResponseDto> itemResponseDtoList = new ArrayList<>();
        for(Item itemEntity: savedCart.getItems()){
            ItemResponseDto itemResponseDto = ItemTransFormer.ItemToItemResponseDto(itemEntity);
            itemResponseDtoList.add(itemResponseDto);
        }

        cartResponseDto.setItems(itemResponseDtoList);
        return cartResponseDto;
    }
    @Override
    public OrderResponseDto checkOutCart(CheckOutCartRequestDto checkOutCartRequestDto) throws Exception {
        Customer customer ;
        try{
            customer=customerRepository.findById(checkOutCartRequestDto.getCustomerID()).get();
        }catch (Exception e){
            throw new UserNotFoundException("User Not Found");
        }


        Card card = cardRespository.findByCardNo(checkOutCartRequestDto.getCardNo());
        if(card == null ||  checkOutCartRequestDto.getCvv() != card.getCvv() || card.getCustomer()!=customer){
          throw new InvalidCardException("Card Is Invalid");
        }

        Cart cart = customer.getCart();
        if(cart.getNumberOfItems()==0){
            throw new Exception("Your Cart is Empty");
        }
        try {

            Orders order = orderService.placeOrder(card, customer);
            customer.getOrderList().add(order);
            resetCart(cart);
            Orders savedOrder = orderedRepository.save(order);

            // prepare response dto
            OrderResponseDto orderResponseDto = new OrderResponseDto();
            orderResponseDto.setOrderDate(savedOrder.getOrderDate());
            orderResponseDto.setCardUsed(savedOrder.getCardUsed());
            orderResponseDto.setCustomerName(customer.getName());
            orderResponseDto.setOrderNo(savedOrder.getOrderNo());
            orderResponseDto.setTotalValue(savedOrder.getTotalValue());

            List<ItemResponseDto> items = new ArrayList<>();
            for(Item itemEntity: savedOrder.getItems()){
                ItemResponseDto itemResponseDto = ItemTransFormer.ItemToItemResponseDto(itemEntity);
                items.add(itemResponseDto);
            }
            orderResponseDto.setItems(items);
            return orderResponseDto;

        }catch (Exception e){
            throw new Exception(e.getMessage());
        }













    }

    public void resetCart(Cart cart){

        cart.setCartTotal(0);
        for(Item item: cart.getItems()){
            item.setCart(null);
        }
        cart.setNumberOfItems(0);
        cart.getItems().clear();

    }

}
