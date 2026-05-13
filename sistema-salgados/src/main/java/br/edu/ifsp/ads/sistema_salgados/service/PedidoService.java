package br.edu.ifsp.ads.sistema_salgados.service;

import br.edu.ifsp.ads.sistema_salgados.model.Movimentacao;
import br.edu.ifsp.ads.sistema_salgados.patterns.command.Command;
import br.edu.ifsp.ads.sistema_salgados.patterns.command.PedidoCommand;
import br.edu.ifsp.ads.sistema_salgados.repository.MovimentacaoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public class PedidoService {
    @Autowired
    private MovimentacaoDao movimentacaoDao;

    public Movimentacao processarPedido(Movimentacao mov) {
        // usar um Factory para criar a Movimentacao
        Command comando = new PedidoCommand(movimentacaoDao, mov);
        comando.execute();
        return mov;
    }

    public void cancelarPedido(Long id) {
        Movimentacao mov = movimentacaoDao.findById(id)
            .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
        
        Command comando = new PedidoCommand(movimentacaoDao, mov);
        comando.undo();
    }
}
