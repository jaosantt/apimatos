package com.matosbrasil.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ResponseMessage {
	SUCCESSFUL_REGISTRATION("Cadastro realizado com sucesso!"),
	UNEXPECTED_ERROR_MESSAGE("Ocorreu um erro inesperado.");

    private final String message;
}
