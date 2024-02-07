package com.example.eStore.service.seviceImp;

import com.example.eStore.Enum.ProductStatus;
import com.example.eStore.Enum.UserNotFoundException;
import com.example.eStore.dto.requestDto.OrderRequestDto;
import com.example.eStore.dto.responseDto.OrderResponseDto;
import com.example.eStore.exception.InvalidProductException;
import com.example.eStore.model.*;
import com.example.eStore.repository.CardRepository;
import com.example.eStore.repository.CustomerRepository;
import com.example.eStore.repository.OrderRepository;
import com.example.eStore.repository.ProductRepository;
import com.example.eStore.service.OrderService;
import com.example.eStore.service.ProductService;
import jakarta.persistence.criteria.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    ProductService productService;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CardRepository cardRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    private JavaMailSender emailSender;

    @Override
    public Orders placeOrder(Card card, Customer customer) throws Exception {

        Cart cart = customer.getCart();

        Orders order = new Orders();
        order.setOrderNo(String.valueOf(UUID.randomUUID()));

        String maskedCardNo = generateMaskedCard(card.getCardNo());
        order.setCardUsed(maskedCardNo);
        order.setCustomer(customer);


        List<Item> orderedItems = new ArrayList<>();
        for (Item item : cart.getItems()) {
            try {
                productService.decreaseProductQuantity(item);
                orderedItems.add(item);
            } catch (Exception e) {
                throw new Exception("Product Out of stock");
            }
        }
        order.setItems(orderedItems);
        for (Item item : orderedItems)
            item.setOrder(order);
        order.setTotalValue(cart.getCartTotal());
        return order;


    }

    public String generateMaskedCard(String cardNo) {
        String maskedCardNo = "";
        for (int i = 0; i < cardNo.length() - 4; i++)
            maskedCardNo += 'X';
        maskedCardNo += cardNo.substring(cardNo.length() - 4);
        return maskedCardNo;

    }


    public OrderResponseDto placeorder(OrderRequestDto orderRequestDto) throws Exception {
        Customer customer;
        try {
            customer = customerRepository.findById(orderRequestDto.getCustomerId()).get();
        } catch (Exception e) {
            throw new UserNotFoundException("User Not Resistered");
        }

        Product product;
        try {
            product = productRepository.findById(orderRequestDto.getProductId()).get();
        } catch (Exception e) {
            throw new InvalidProductException("Product Not Present");
        }

        Card card = cardRepository.findByCardNo(orderRequestDto.getCardNo());
        if (card == null || orderRequestDto.getCvv() != card.getCvv() || card.getCustomer() != customer) {
            throw new UserNotFoundException("Wrong Credential");
        }

        Orders order = new Orders();

        order.setCardUsed(orderRequestDto.getCardNo());
        order.setCustomer(customer);
        order.setTotalValue(product.getPrice() * orderRequestDto.getRequiredQuantity());
        order.setOrderNo(String.valueOf(UUID.randomUUID()));
        Orders savedOrder = orderRepository.save(order);

        int avilable = product.getQuantity();
        int currQuantity = orderRequestDto.getRequiredQuantity();
        product.setQuantity(avilable - currQuantity);
        if (product.getQuantity() == 0) {
            product.setProductStatus(ProductStatus.OUTOFSTOCK);
            throw new Exception("Product Out Of Stock");
        }

        productRepository.save(product);
        String text = "Congratulation "+ customer.getName() + " Your Product " + product.getName()+"Ordered Successfully";
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply@baeldung.com");
        message.setTo(customer.getEmailId());
        message.setSubject("EStore");
        message.setText(text);
        emailSender.send(message);
        return OrderResponseDto.builder()
                .customerName(customer.getName())
                .orderNo(card.getCardNo())
                .totalValue(product.getPrice() * orderRequestDto.getRequiredQuantity())
                .build();


    }
}
