package com.sonyairlines.tickets.Config;

import com.sonyairlines.tickets.Model.VueloDto;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VueloDtoConfig {
    @Bean
    public VueloDto vueloDto() {
        return new VueloDto();
    }
}
