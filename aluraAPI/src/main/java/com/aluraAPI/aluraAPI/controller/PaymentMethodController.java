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
    public void cadastrarMetodoPagamento(@RequestBody @Valid PaymentMethodRegisterDto inputedData){
        paymentMethodRepository.save(new PaymentMethod(inputedData));
    }

    @GetMapping
    public List<PaymentMethodListDto> listarMetodoPagamento(){
        return paymentMethodRepository.findAll().stream().map(PaymentMethodListDto::new).toList();
    }

    @PutMapping
    @Transactional
    public void atualizarMetodoPagamento(@RequestBody @Valid PaymentMethodUpdateDto inputedData){
        var metodoPagamento = paymentMethodRepository.getReferenceById(inputedData.id());
        metodoPagamento.updatePaymentMethod(inputedData);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void excluirMetodoPagamento(@PathVariable Long id){
        var metodoPagamento = paymentMethodRepository.getReferenceById(id);
        metodoPagamento.disable();
    }
}
