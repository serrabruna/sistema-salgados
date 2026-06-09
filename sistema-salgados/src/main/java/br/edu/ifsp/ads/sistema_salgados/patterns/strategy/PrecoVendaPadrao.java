package br.edu.ifsp.ads.sistema_salgados.patterns.strategy;

public class PrecoVendaPadrao implements CalculoPrecoStrategy {
    @Override
    public Double calcular(Double precoBase, Integer quantidade) {
        return precoBase * quantidade; 
    }
}
