package com.aluraAPI.aluraAPI.domain.persistence.promocao.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.aspectj.weaver.ast.Not;

import java.util.Date;

public record DadosAtualizaPromocao(
        @NotNull
        Long id,
        @NotBlank
        String nome,
        @NotNull
        Date validade
) {
}
