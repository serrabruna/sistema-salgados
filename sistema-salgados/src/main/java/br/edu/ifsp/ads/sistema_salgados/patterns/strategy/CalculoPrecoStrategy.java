package br.edu.ifsp.ads.sistema_salgados.patterns.strategy;

public interface CalculoPrecoStrategy {
    Double calcular(Double precoBase, Integer quantidade);
}
