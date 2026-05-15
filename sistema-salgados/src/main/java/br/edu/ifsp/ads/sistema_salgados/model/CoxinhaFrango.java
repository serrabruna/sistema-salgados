package br.edu.ifsp.ads.sistema_salgados.model;

import org.springframework.stereotype.Component;

@Component
public class CoxinhaFrango implements Coxinha {
    public Double getPreco() { return 7.0; }
}
