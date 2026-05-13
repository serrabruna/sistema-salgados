package br.edu.ifsp.ads.sistema_salgados.repository;

import br.edu.ifsp.ads.sistema_salgados.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Cliente findByEmailAndSenha(String email, String senha);
}
