package com.aluraAPI.aluraAPI.controller;

import com.aluraAPI.aluraAPI.domain.paymentMethod.PaymentMethod;
import com.aluraAPI.aluraAPI.domain.paymentMethod.PaymentMethodRepository;

import com.aluraAPI.aluraAPI.domain.paymentMethod.dto.PaymentMethodUpdateDto;
import com.aluraAPI.aluraAPI.domain.paymentMethod.dto.PaymentMethodRegisterDto;
import com.aluraAPI.aluraAPI.domain.paymentMethod.dto.PaymentMethodListDto;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("metodoPagamento")
public class PaymentMethodController {
    @Autowired
    private PaymentMethodRepository paymentMethodRepository;

    @PostMapping
    @Transactional
    public void registerPaymentMethod(@RequestBody @Valid PaymentMethodRegisterDto registerPaymentMethodInput){
        paymentMethodRepository.save(new PaymentMethod(registerPaymentMethodInput));
    }

    @GetMapping
    public List<PaymentMethodListDto> listPaymentMethod(){
        return paymentMethodRepository.findAll().stream().map(PaymentMethodListDto::new).toList();
    }

    @PutMapping
    @Transactional
    public void updatePaymentMethod(@RequestBody @Valid PaymentMethodUpdateDto updatePaymentMethodInput){
        var paymentMethod = paymentMethodRepository.getReferenceById(updatePaymentMethodInput.id());
        paymentMethod.updatePaymentMethod(updatePaymentMethodInput);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void inactivatePaymentMethod(@PathVariable Long id){
        var paymentMethod = paymentMethodRepository.getReferenceById(id);
        paymentMethod.disable();
    }
}
