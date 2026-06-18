package br.edu.ifsp.ads.sistema_salgados.patterns.template;

import br.edu.ifsp.ads.sistema_salgados.patterns.strategy.CalculoPrecoStrategy;
import br.edu.ifsp.ads.sistema_salgados.patterns.strategy.PrecoVendaPadrao;

public abstract class CalculadoraPrecoTemplate {
    private CalculoPrecoStrategy estrategia = new PrecoVendaPadrao();
    public final Double calcularPrecoFinal(Double precoBase, Integer quantidade) {
        Double precoBruto = estrategia.calcular(precoBase, quantidade);
        Double desconto = 0.0;
        
        if (deveAplicarDesconto(quantidade)) {
            desconto = aplicarDescontoEspecial(precoBruto);
        }
        
        return precoBruto - desconto;
    }

    protected abstract boolean deveAplicarDesconto(Integer quantidade);
    protected abstract Double aplicarDescontoEspecial(Double precoBruto);
}
