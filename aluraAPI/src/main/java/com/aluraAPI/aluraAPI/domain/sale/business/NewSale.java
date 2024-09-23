package com.aluraAPI.aluraAPI.domain.sale.business;

import java.time.LocalDateTime;

import com.aluraAPI.aluraAPI.domain.costumer.CostumerRepository;
import com.aluraAPI.aluraAPI.domain.paymentMethod.PaymentMethodRepository;
import com.aluraAPI.aluraAPI.domain.deal.DealRepository;
import com.aluraAPI.aluraAPI.domain.user.UserRepository;
import com.aluraAPI.aluraAPI.domain.sale.Sale;
import com.aluraAPI.aluraAPI.domain.sale.SaleRepository;
import com.aluraAPI.aluraAPI.domain.sale.dto.RegisterSaleDto;
import com.aluraAPI.aluraAPI.exceptions.GeneralException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NewSale {
    @Autowired
    SaleRepository saleRepository;
    @Autowired
    PaymentMethodRepository paymentMethodRepository;
    @Autowired
    CostumerRepository costumerRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    DealRepository dealRepository;

    public void newSell(RegisterSaleDto newSaleInput){
        if(!paymentMethodRepository.existsById(newSaleInput.paymentMethodId())){
            throw new GeneralException(("No payment method was found with id: " + newSaleInput.paymentMethodId()));
        }
        if (newSaleInput.costumerId() != 0.0d) {
            if (!costumerRepository.existsById(newSaleInput.costumerId())) {
                throw new GeneralException(("No costumer was found with id: " + newSaleInput.costumerId()));
            }
        }
        if(!userRepository.existsById(newSaleInput.userId())){
            throw new GeneralException(("No user was found with id: " + newSaleInput.userId()));
        }
        if (newSaleInput.dealId() != 0.0d) {
            if (!dealRepository.existsById(newSaleInput.dealId())) {
                throw new GeneralException(("No deal was found with id: " + newSaleInput.dealId()));
            }

        }
        LocalDateTime sellDate = LocalDateTime.now();


        var paymentMethod = paymentMethodRepository.findById(newSaleInput.paymentMethodId()).get();
        var user = userRepository.findById(newSaleInput.userId()).get();


        if((newSaleInput.costumerId() != 0.0d) && (newSaleInput.dealId() != 0.0d)){
            var costumer = costumerRepository.findById(newSaleInput.costumerId()).get();
            var deal = dealRepository.findById(newSaleInput.dealId()).get();

            var sell = new Sale(sellDate, newSaleInput.amount(), newSaleInput.invoiceNumber(), paymentMethod, costumer, user, deal);
            saleRepository.save(sell);

        } else if ((newSaleInput.costumerId() != 0.0d) && (newSaleInput.dealId() == 0.0d)) {
            var costumer = costumerRepository.findById(newSaleInput.costumerId()).get();

            var sell = new Sale(sellDate, newSaleInput.amount(), newSaleInput.invoiceNumber(), paymentMethod, costumer, user);
            saleRepository.save(sell);

        }else if ((newSaleInput.costumerId() == 0.0d) && (newSaleInput.dealId() != 0.0d)) {
            var deal = dealRepository.findById(newSaleInput.dealId()).get();

            var sell = new Sale(sellDate, newSaleInput.amount(), newSaleInput.invoiceNumber(), paymentMethod, user, deal);
            saleRepository.save(sell);

        }else if ((newSaleInput.costumerId() == 0.0d) && (newSaleInput.dealId() == 0.0d)){
            var sell = new Sale(sellDate, newSaleInput.amount(), newSaleInput.invoiceNumber(), paymentMethod, user);
            saleRepository.save(sell);

        }



    }
}
