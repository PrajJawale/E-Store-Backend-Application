package com.example.eStore.service.seviceImp;

import com.example.eStore.dto.requestDto.CustomerRequestDto;
import com.example.eStore.dto.responseDto.CustomerResponseDto;
import com.example.eStore.exception.EmailAlreadyResisterdException;
import com.example.eStore.model.Cart;
import com.example.eStore.model.Customer;
import com.example.eStore.repository.CustomerRepository;
import com.example.eStore.service.Customerservice;
import com.example.eStore.transeformer.CustomerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements Customerservice {
    @Autowired
    CustomerRepository customerRepository;
    @Override
    public CustomerResponseDto addnewcustomer(CustomerRequestDto customerRequestDto) throws EmailAlreadyResisterdException {

        if(customerRepository.findByEmailId(customerRequestDto.getEmailId()) != null){
            throw new EmailAlreadyResisterdException("This Email Already In Use");
        }

        Customer customer = CustomerTransformer.customerRequestDtoToCustomer(customerRequestDto);
        customerRepository.save(customer);

        Cart cart = Cart.builder()
                   .cartTotal(0)
                   .numberOfItems(0).
                    build();

        customer.setCart(cart);



        Customer savedCustomer = customerRepository.save(customer);  // customer and cart

        // prepare response dto
        return CustomerTransformer.customerResponseDtoToCustomer(savedCustomer);

    }
}
