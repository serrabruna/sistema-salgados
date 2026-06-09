package br.edu.ifsp.ads.sistema_salgados.patterns.strategy;

public class PrecoPadrao implements CalculoPrecoStrategy {
    @Override
    public Double calcular(Double precoBase, Integer quantidade) {
        return precoBase * quantidade; 
    }
}
