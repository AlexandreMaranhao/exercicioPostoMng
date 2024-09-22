package com.aluraAPI.aluraAPI.domain.sale.business;

import java.time.LocalDateTime;

import com.aluraAPI.aluraAPI.domain.costumer.CostumerRepository;
import com.aluraAPI.aluraAPI.domain.paymentMethod.PaymentMethodRepository;
import com.aluraAPI.aluraAPI.domain.deal.DealRepository;
import com.aluraAPI.aluraAPI.domain.user.UserRepository;
import com.aluraAPI.aluraAPI.domain.sale.Sale;
import com.aluraAPI.aluraAPI.domain.sale.SaleRepository;
import com.aluraAPI.aluraAPI.domain.sale.dto.RegisterSale;
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

    public void novaVenda(RegisterSale newSaleInput){
        if(!paymentMethodRepository.existsById(newSaleInput.paymentMethodId())){
            throw new GeneralException(("Metodo de pagamento informado não existe"));
        }
        if (newSaleInput.costumerId() != 0.0d) {
            if (!costumerRepository.existsById(newSaleInput.costumerId())) {
                throw new GeneralException(("Cliente informado não existe"));
            }
        }
        if(!userRepository.existsById(newSaleInput.userId())){
            throw new GeneralException(("Usuario informada não existe"));
        }
        if (newSaleInput.dealId() != 0.0d) {
            if (!dealRepository.existsById(newSaleInput.dealId())) {
                throw new GeneralException(("Promocao informada não existe"));
            }

        }
        LocalDateTime dataVenda = LocalDateTime.now();


        var metodoPagamento = paymentMethodRepository.findById(newSaleInput.paymentMethodId()).get();
        var usuario = userRepository.findById(newSaleInput.userId()).get();


        if((newSaleInput.costumerId() != 0.0d) && (newSaleInput.dealId() != 0.0d)){
            var cliente = costumerRepository.findById(newSaleInput.costumerId()).get();
            var promocao = dealRepository.findById(newSaleInput.dealId()).get();

            var venda = new Sale(dataVenda, newSaleInput.amount(), newSaleInput.invoiceNumber(), metodoPagamento, cliente, usuario, promocao);
            saleRepository.save(venda);

        } else if ((newSaleInput.costumerId() != 0.0d) && (newSaleInput.dealId() == 0.0d)) {
            var cliente = costumerRepository.findById(newSaleInput.costumerId()).get();

            var venda = new Sale(dataVenda, newSaleInput.amount(), newSaleInput.invoiceNumber(), metodoPagamento, cliente, usuario);
            saleRepository.save(venda);

        }else if ((newSaleInput.costumerId() == 0.0d) && (newSaleInput.dealId() != 0.0d)) {
            var promocao = dealRepository.findById(newSaleInput.dealId()).get();

            var venda = new Sale(dataVenda, newSaleInput.amount(), newSaleInput.invoiceNumber(), metodoPagamento, usuario, promocao);
            saleRepository.save(venda);

        }else if ((newSaleInput.costumerId() == 0.0d) && (newSaleInput.dealId() == 0.0d)){
            var venda = new Sale(dataVenda, newSaleInput.amount(), newSaleInput.invoiceNumber(), metodoPagamento, usuario);
            saleRepository.save(venda);

        }



    }
}
