package br.edu.ifsp.ads.sistema_salgados.model;

import org.springframework.stereotype.Component;

@Component
public class CoxinhaCatupiry implements Coxinha {
    public Double getPreco() {
        return 8.0;
    }
}
