package br.edu.ifsp.ads.sistema_salgados.patterns.template;

import org.springframework.stereotype.Component;

@Component
public class CalculadoraPedidoProprio extends CalculadoraPrecoTemplate {
    @Override
    protected boolean deveAplicarDesconto(Integer quantidade) {
        return quantidade >= 3;
    }

    @Override
    protected Double aplicarDescontoEspecial(Double precoBruto) {  
        return precoBruto * 0.10;
    }
}