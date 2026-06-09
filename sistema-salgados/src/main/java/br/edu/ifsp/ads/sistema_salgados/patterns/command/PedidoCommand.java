package br.edu.ifsp.ads.sistema_salgados.patterns.command;

import br.edu.ifsp.ads.sistema_salgados.model.Cliente;
import br.edu.ifsp.ads.sistema_salgados.model.Movimentacao;
import br.edu.ifsp.ads.sistema_salgados.repository.ClienteDao;
import br.edu.ifsp.ads.sistema_salgados.repository.MovimentacaoDao;

public class PedidoCommand implements Command{
    private Movimentacao movimentacao;
    private MovimentacaoDao movimentacaoDao;
    private ClienteDao clienteDao;


    public PedidoCommand(MovimentacaoDao movimentacaoDao, ClienteDao clienteDao, Movimentacao movimentacao) {
        this.movimentacaoDao = movimentacaoDao  ;
        this.clienteDao = clienteDao;
        this.movimentacao = movimentacao;
    }

    @Override
    public void execute() {
        Cliente cliente = movimentacao.getCliente();
            if (cliente != null) {
                if (cliente.getSaldo() < movimentacao.getValorTotal()) {
                    throw new RuntimeException("Saldo insuficiente para realizar a compra!");
                }
                cliente.setSaldo(cliente.getSaldo() - movimentacao.getValorTotal());
                clienteDao.save(cliente);
            }
            movimentacaoDao.save(movimentacao);
    }

    @Override
    public void undo() {
        Cliente cliente = movimentacao.getCliente();
        if (cliente != null) {
            cliente.setSaldo(cliente.getSaldo() + movimentacao.getValorTotal());
            clienteDao.save(cliente);
        }
        movimentacaoDao.delete(movimentacao);
    }
    
}
