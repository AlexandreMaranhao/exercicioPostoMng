package com.aluraAPI.aluraAPI.domain.sale.business;

import java.time.LocalDateTime;

import com.aluraAPI.aluraAPI.domain.costumer.Costumer;
import com.aluraAPI.aluraAPI.domain.costumer.CostumerRepository;
import com.aluraAPI.aluraAPI.domain.deal.Deal;
import com.aluraAPI.aluraAPI.domain.paymentMethod.PaymentMethod;
import com.aluraAPI.aluraAPI.domain.paymentMethod.PaymentMethodRepository;
import com.aluraAPI.aluraAPI.domain.deal.DealRepository;
import com.aluraAPI.aluraAPI.domain.sale.dto.SaleCompleteRegisterDto;
import com.aluraAPI.aluraAPI.domain.sale.dto.SaleRegisteredDetails;
import com.aluraAPI.aluraAPI.domain.saleProduct.business.RegisterSaleProductItem;
import com.aluraAPI.aluraAPI.domain.saleProduct.dto.SaleProductRegisterDto;
import com.aluraAPI.aluraAPI.domain.user.User;
import com.aluraAPI.aluraAPI.domain.user.UserRepository;
import com.aluraAPI.aluraAPI.domain.sale.Sale;
import com.aluraAPI.aluraAPI.domain.sale.SaleRepository;
import com.aluraAPI.aluraAPI.domain.sale.dto.SaleRegisterDto;
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
    @Autowired
    private RegisterSaleProductItem registerSaleProductItem;


    public SaleRegisteredDetails newSale(SaleRegisterDto newSaleInput){
        if(!paymentMethodRepository.existsById(newSaleInput.paymentMethodId())){
            throw new GeneralException(("No payment method was found with id: " + newSaleInput.paymentMethodId()));
        }
        Long costumerIdLong =  newSaleInput.costumerId();
        Long dealIdLong = newSaleInput.dealId();


        if (costumerIdLong != null) {
            if (!costumerRepository.existsById(newSaleInput.costumerId())) {
                throw new GeneralException(("No costumer was found with id: " + newSaleInput.costumerId()));
            }
        }
        if(!userRepository.existsById(newSaleInput.userId())){
            throw new GeneralException(("No user was found with id: " + newSaleInput.userId()));
        }
        if (dealIdLong != null) {
            if (!dealRepository.existsById(newSaleInput.dealId())) {
                throw new GeneralException(("No deal was found with id: " + newSaleInput.dealId()));
            }

        }
        LocalDateTime sellDate = LocalDateTime.now();



        PaymentMethod paymentMethod = paymentMethodRepository.findById(newSaleInput.paymentMethodId()).get();
        User user = userRepository.findById(newSaleInput.userId()).get();
        Sale sell = null;


        if((costumerIdLong != null) && (dealIdLong != null)){
            Costumer costumer = costumerRepository.findById(newSaleInput.costumerId()).get();
            Deal deal = dealRepository.findById(newSaleInput.dealId()).get();

            sell = new Sale(sellDate, newSaleInput.amount(), newSaleInput.invoiceNumber(), paymentMethod, costumer, user, deal);
            saleRepository.save(sell);

        } else if ((costumerIdLong != null) && (dealIdLong == null)) {
            Costumer costumer = costumerRepository.findById(newSaleInput.costumerId()).get();

            sell = new Sale(sellDate, newSaleInput.amount(), newSaleInput.invoiceNumber(), paymentMethod, costumer, user);
            saleRepository.save(sell);

        }else if ((costumerIdLong == null) && (dealIdLong != null)) {
            Deal deal = dealRepository.findById(newSaleInput.dealId()).get();

            sell = new Sale(sellDate, newSaleInput.amount(), newSaleInput.invoiceNumber(), paymentMethod, user, deal);
            saleRepository.save(sell);

        }else if ((costumerIdLong == null) && (dealIdLong == null)){
            sell = new Sale(sellDate, newSaleInput.amount(), newSaleInput.invoiceNumber(), paymentMethod, user);
            saleRepository.save(sell);
        }
        return new SaleRegisteredDetails(sell);
    }

    public void findProductsCompleteSale(SaleCompleteRegisterDto newSaleInput){

        for (SaleProductRegisterDto product : newSaleInput.getProducts()){

            var verification = RegisterSaleProductItem.verifyProduct(product);
            if (!verification){
                throw new GeneralException(("No registered product with id: " + product.productId()));
            }
        }

    }
    public void registerCompleteSaleProductItem(SaleCompleteRegisterDto newSaleInput, SaleRegisteredDetails registeredSale){
        for (SaleProductRegisterDto product : newSaleInput.getProducts()){

            registerSaleProductItem.registerSaleProductItem(product, registeredSale);
        }

    }

    public SaleRegisteredDetails realizeCompleteSale(SaleCompleteRegisterDto newSaleInput) {
        emptyProductListOnCompleteSale(newSaleInput);
        findProductsCompleteSale(newSaleInput);
        SaleRegisteredDetails registeredSale = newSale(getSaleInput(newSaleInput));
        registerCompleteSaleProductItem(newSaleInput, registeredSale);

        return new SaleRegisteredDetails(registeredSale);
    }

    public void emptyProductListOnCompleteSale(SaleCompleteRegisterDto newSaleInput){
        if (newSaleInput.getProducts() == null || newSaleInput.getProducts().isEmpty()) {
            throw new GeneralException("Product list can not be null or empty");
        }
    }

    public SaleRegisterDto getSaleInput (SaleCompleteRegisterDto newSaleInput) {
        return new SaleRegisterDto(newSaleInput.date(),
                newSaleInput.amount(),
                newSaleInput.invoiceNumber(),
                newSaleInput.paymentMethodId(),
                newSaleInput.costumerId(),
                newSaleInput.userId(),
                newSaleInput.dealId(),
                newSaleInput.refound());


    }


}
