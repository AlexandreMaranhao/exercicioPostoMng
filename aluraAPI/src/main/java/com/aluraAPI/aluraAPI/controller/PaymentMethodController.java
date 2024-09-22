package com.aluraAPI.aluraAPI.controller;

import com.aluraAPI.aluraAPI.domain.paymentMethod.PaymentMethod;
import com.aluraAPI.aluraAPI.domain.paymentMethod.PaymentMethodRepository;

import com.aluraAPI.aluraAPI.domain.paymentMethod.dto.UpdatePaymentMethod;
import com.aluraAPI.aluraAPI.domain.paymentMethod.dto.RegisterPaymentMethod;
import com.aluraAPI.aluraAPI.domain.paymentMethod.dto.ListPaymentMethod;
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
    public void cadastrarMetodoPagamento(@RequestBody @Valid RegisterPaymentMethod inputedData){
        paymentMethodRepository.save(new PaymentMethod(inputedData));
    }

    @GetMapping
    public List<ListPaymentMethod> listarMetodoPagamento(){
        return paymentMethodRepository.findAll().stream().map(ListPaymentMethod::new).toList();
    }

    @PutMapping
    @Transactional
    public void atualizarMetodoPagamento(@RequestBody @Valid UpdatePaymentMethod inputedData){
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
