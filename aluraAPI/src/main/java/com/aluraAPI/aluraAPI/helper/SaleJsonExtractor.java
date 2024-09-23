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


        String cupomFiscal = rootNode.get("cupomfiscal").asText();
        String metodoPagamentoId = rootNode.get("metodopagamento_id").asText();
        String clienteId = rootNode.get("cliente_id").asText();
        String usuariosId = rootNode.get("usuarios_id").asText();
        String promocaoId = rootNode.get("promocao_id").asText();


        String extractedInput = String.format("{ \"cupomfiscal\": \"%s\", \"metodopagamento_id\": \"%s\", \"cliente_id\": \"%s\", \"usuarios_id\": \"%s\", \"promocao_id\": \"%s\" }",
                cupomFiscal, metodoPagamentoId, clienteId, usuariosId, promocaoId);

        RegisterSaleDto newOutputSale = objectMapper.readValue(extractedInput, RegisterSaleDto.class);


        return newOutputSale;
    }



}
