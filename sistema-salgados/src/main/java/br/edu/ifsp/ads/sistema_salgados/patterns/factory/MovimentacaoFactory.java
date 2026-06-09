package br.edu.ifsp.ads.sistema_salgados.patterns.factory;

import java.time.LocalDateTime;

import br.edu.ifsp.ads.sistema_salgados.model.Cliente;
import br.edu.ifsp.ads.sistema_salgados.model.Coxinha;
import br.edu.ifsp.ads.sistema_salgados.model.Movimentacao;
import br.edu.ifsp.ads.sistema_salgados.patterns.strategy.CalculoPrecoStrategy;

public class MovimentacaoFactory {
    public static Movimentacao criar(String sabor, Integer quantidade, Cliente cliente, CalculoPrecoStrategy strategy) {
        Coxinha coxinha = CoxinhaFactory.obterCoxinha(sabor);
        Movimentacao mov = new Movimentacao();

        mov.setSabor(sabor);
        mov.setQuantidade(quantidade);

        Double valorTotal = strategy.calcular(coxinha.getPreco(), quantidade);
        
        mov.setValorTotal(valorTotal);
        mov.setDataHora(LocalDateTime.now());
        mov.setCliente(cliente);
        return mov;
    }
}