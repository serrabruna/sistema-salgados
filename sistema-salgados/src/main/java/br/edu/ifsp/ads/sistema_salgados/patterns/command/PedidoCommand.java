package br.edu.ifsp.ads.sistema_salgados.patterns.command;

import br.edu.ifsp.ads.sistema_salgados.model.Movimentacao;
import br.edu.ifsp.ads.sistema_salgados.repository.MovimentacaoDao;

public class PedidoCommand implements Command{
    private Movimentacao movimentacao;
    private MovimentacaoDao movimentacaoDao;

    public PedidoCommand(MovimentacaoDao movimentacaoDao, Movimentacao movimentacao) {
        this.movimentacaoDao = movimentacaoDao  ;
        this.movimentacao = movimentacao;
    }

    @Override
    public void execute() {
        movimentacaoDao.save(movimentacao);
    }

    @Override
    public void undo() {
        movimentacaoDao.delete(movimentacao);
    }
    
}
