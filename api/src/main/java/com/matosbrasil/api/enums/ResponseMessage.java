package com.matosbrasil.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ResponseMessage {
	SUCCESSFUL_REGISTRATION("Cadastro realizado com sucesso!"),
	UNEXPECTED_ERROR_MESSAGE("Ocorreu um erro inesperado."),
	SUCCESSFUL_UPDATED("Registro atualizado com sucesso!");

    private final String message;
}
