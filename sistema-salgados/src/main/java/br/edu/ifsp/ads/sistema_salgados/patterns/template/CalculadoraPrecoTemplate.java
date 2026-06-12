package br.edu.ifsp.ads.sistema_salgados.patterns.template;

public abstract class CalculadoraPrecoTemplate {
    public final Double calcularPrecoFinal(Double precoBase, Integer quantidade) {
        Double precoBruto = precoBase * quantidade;
        Double desconto = 0.0;
        
        if (deveAplicarDesconto(quantidade)) {
            desconto = aplicarDescontoEspecial(precoBruto);
        }
        
        return precoBruto - desconto;
    }

    protected abstract boolean deveAplicarDesconto(Integer quantidade);
    protected abstract Double aplicarDescontoEspecial(Double precoBruto);
}
