package com.aluraAPI.aluraAPI.helper;

import com.aluraAPI.aluraAPI.domain.sale.dto.RegisterCompleteSaleDto;
import com.aluraAPI.aluraAPI.domain.sale.dto.RegisterSaleDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SaleJsonExtractor {


    public static RegisterSaleDto extractSaleInput(RegisterCompleteSaleDto jsonInput) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(String.valueOf(jsonInput));


        String invoiceNumber = rootNode.get("invoice_number").asText();
        String paymentMethodId = rootNode.get("payment_method_id").asText();
        String costumerId = rootNode.get("costumer_id").asText();
        String userId = rootNode.get("user_id").asText();
        String dealId = rootNode.get("deal_id").asText();


        String extractedInput = String.format("{ \"invoice_number\": \"%s\", \"payment_method_id\": \"%s\", \"costumer_id\": \"%s\", \"user_id\": \"%s\", \"deal_id\": \"%s\" }",
                invoiceNumber, paymentMethodId, costumerId, userId, dealId);

        RegisterSaleDto newOutputSale = objectMapper.readValue(extractedInput, RegisterSaleDto.class);


        return newOutputSale;
    }



}
