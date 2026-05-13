package br.edu.ifsp.ads.sistema_salgados.repository;

import br.edu.ifsp.ads.sistema_salgados.model.Cliente;
import br.edu.ifsp.ads.sistema_salgados.model.Movimentacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

public interface MovimentacaoDao extends JpaRepository<Movimentacao, Long> {
    List<Movimentacao> findByCliente(Cliente cliente);
    List<Movimentacao> findByClienteId(Long clienteId);
}
