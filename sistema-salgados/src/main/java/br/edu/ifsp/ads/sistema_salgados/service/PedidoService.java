package br.edu.ifsp.ads.sistema_salgados.service;

import br.edu.ifsp.ads.sistema_salgados.model.Cliente;
import br.edu.ifsp.ads.sistema_salgados.model.Movimentacao;
import br.edu.ifsp.ads.sistema_salgados.patterns.command.Command;
import br.edu.ifsp.ads.sistema_salgados.patterns.command.PedidoCommand;
import br.edu.ifsp.ads.sistema_salgados.patterns.factory.MovimentacaoFactory;
import br.edu.ifsp.ads.sistema_salgados.repository.ClienteDao;
import br.edu.ifsp.ads.sistema_salgados.repository.MovimentacaoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service 
public class PedidoService {

    @Autowired
    private MovimentacaoDao movimentacaoDao;

    @Autowired
    private ClienteDao clienteDao; 
    public Movimentacao processarPedido(String sabor, Integer quantidade, Cliente cliente) {
        Movimentacao mov = MovimentacaoFactory.criar(sabor, quantidade, cliente);
        Command comando = new PedidoCommand(movimentacaoDao, clienteDao, mov);
        comando.execute();
        return mov;
    }

    public void cancelarPedido(Long id) {
        Movimentacao mov = movimentacaoDao.findById(id)
            .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
        Command comando = new PedidoCommand(movimentacaoDao, clienteDao, mov);
        comando.undo();
    }
}
