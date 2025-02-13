package com.luizmarcelo.apipessoas.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Map;

@AllArgsConstructor
@Service
public class EmailTemplateService {

    private final TemplateEngine templateEngine;


    public String processarTemplate(String template, Map<String, Object> variaveis) {
        Context context = new Context();
        context.setVariables(variaveis);
        return templateEngine.process(template, context);
    }

}
