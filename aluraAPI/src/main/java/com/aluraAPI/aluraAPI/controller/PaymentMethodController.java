package com.aluraAPI.aluraAPI.controller;

import com.aluraAPI.aluraAPI.domain.paymentMethod.PaymentMethod;
import com.aluraAPI.aluraAPI.domain.paymentMethod.PaymentMethodRepository;

import com.aluraAPI.aluraAPI.domain.paymentMethod.dto.UpdatePaymentMethodDto;
import com.aluraAPI.aluraAPI.domain.paymentMethod.dto.RegisterPaymentMethodDto;
import com.aluraAPI.aluraAPI.domain.paymentMethod.dto.ListPaymentMethodDto;
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
    public void cadastrarMetodoPagamento(@RequestBody @Valid RegisterPaymentMethodDto inputedData){
        paymentMethodRepository.save(new PaymentMethod(inputedData));
    }

    @GetMapping
    public List<ListPaymentMethodDto> listarMetodoPagamento(){
        return paymentMethodRepository.findAll().stream().map(ListPaymentMethodDto::new).toList();
    }

    @PutMapping
    @Transactional
    public void atualizarMetodoPagamento(@RequestBody @Valid UpdatePaymentMethodDto inputedData){
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
