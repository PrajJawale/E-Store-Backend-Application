package com.example.eStore.service.seviceImp;

import com.example.eStore.dto.requestDto.SellerRequestDto;
import com.example.eStore.dto.responseDto.SellerResponseDto;
import com.example.eStore.exception.SellerAlreadyPresentException;
import com.example.eStore.model.Seller;
import com.example.eStore.repository.SellerRepository;
import com.example.eStore.service.sellerService;
import com.example.eStore.transeformer.SellertTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerServiceImpl implements sellerService {

    @Autowired
    SellerRepository sellerRepository;

    @Override
    public SellerResponseDto addSeller(SellerRequestDto sellerRequestDto) throws SellerAlreadyPresentException {
//        Seller seller = new Seller();
//
//        seller.setName(sellerRequestDto.getName());
//        seller.setAge(sellerRequestDto.getAge());
//        seller.setEmailId(sellerRequestDto.getEmailId());
//        seller.setMonNO(sellerRequestDto.getMonNO());
//
//        // Save the seller to the repository
//        sellerRepository.save(seller);
//
//        // Retrieve the saved seller from the repository to get the actual data
//        Seller savedSeller = sellerRepository.findById(seller.getId()).orElse(null);
//
//        // Create a response DTO and set its values based on the saved seller
//        SellerResponseDto sellerResponseDto = new SellerResponseDto();
//        if (savedSeller != null) {
//            sellerResponseDto.setName(savedSeller.getName());
//            sellerResponseDto.setMessege("Seller added successfully");
//        } else {
//            // Handle the case where the seller was not saved properly
//            sellerResponseDto.setMessege("Failed to add seller");
//        }

         if(sellerRepository.findByEmailId(sellerRequestDto.getEmailId())!=null){
             throw new SellerAlreadyPresentException("Email Already Resister");
         }

         Seller seller = SellertTransformer.sellerRequestDtotoSeller(sellerRequestDto);
         Seller saved = sellerRepository.save(seller);

         SellerResponseDto sellerresponseDto = SellertTransformer.sellerResponseDtotoSeller(saved);


//         SellerResponseDto sellerResponseDto = new SellerResponseDto();
//         sellerResponseDto.setName(seller.getName());
//         sellerResponseDto.setMessege(" seller added succefully");
//         sellerRepository.save(seller);
         return sellerresponseDto;
    }
}
