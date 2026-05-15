package br.edu.ifsp.ads.sistema_salgados.model;

import org.springframework.stereotype.Component;

public interface Coxinha {
    Double getPreco();
}

@Component
class CoxinhaQueijo implements Coxinha {
    public Double getPreco() { return 8.0; }
}

@Component
class CoxinhaFrango implements Coxinha {
    public Double getPreco() { return 7.0; }
}