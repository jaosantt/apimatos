package com.matosbrasil.api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    // Configura o CORS globalmente
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // Aplica a todas as rotas da API
                .allowedOrigins("https://hefesto-erp.flutterflow.app/") // Substitua pelos domínios necessários
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  // Métodos HTTP permitidos
                .allowedHeaders("*")  // Permite todos os cabeçalhos
                .allowCredentials(true)  // Permite cookies/autenticação, se necessário
                .maxAge(3600);  // Tempo de cache da requisição preflight
    }
}