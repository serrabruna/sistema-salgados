package br.edu.ifsp.ads.sistema_salgados.patterns.factory;

import java.time.LocalDateTime;

import br.edu.ifsp.ads.sistema_salgados.model.Cliente;
import br.edu.ifsp.ads.sistema_salgados.model.Coxinha;
import br.edu.ifsp.ads.sistema_salgados.model.Movimentacao;
import br.edu.ifsp.ads.sistema_salgados.patterns.template.CalculadoraPrecoTemplate;

public class MovimentacaoFactory {
    public static Movimentacao criar(String sabor, Integer quantidade, Cliente cliente, CalculadoraPrecoTemplate calculadora) {
        Coxinha coxinha = CoxinhaFactory.obterCoxinha(sabor);
        
        Movimentacao mov = new Movimentacao();
        mov.setSabor(sabor);
        mov.setQuantidade(quantidade);
        
        Double valorCalculado = calculadora.calcularPrecoFinal(coxinha.getPreco(), quantidade);
        mov.setValorTotal(valorCalculado);
        
        mov.setDataHora(LocalDateTime.now());
        mov.setCliente(cliente);
        
        return mov;
    }
}