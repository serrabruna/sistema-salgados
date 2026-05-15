package br.edu.ifsp.ads.sistema_salgados.patterns.factory;

import java.time.LocalDateTime;

import br.edu.ifsp.ads.sistema_salgados.model.Cliente;
import br.edu.ifsp.ads.sistema_salgados.model.Coxinha;
import br.edu.ifsp.ads.sistema_salgados.model.Movimentacao;

public class MovimentacaoFactory {
    public static Movimentacao criar(String sabor, Integer quantidade, Cliente cliente) {
        Coxinha coxinha = CoxinhaFactory.obterCoxinha(sabor);
        
        Movimentacao mov = new Movimentacao();
        mov.setSabor(sabor);
        mov.setQuantidade(quantidade);
        mov.setValorTotal(coxinha.getPreco() * quantidade);
        mov.setDataHora(LocalDateTime.now());
        mov.setCliente(cliente);
        
        return mov;
    }
}