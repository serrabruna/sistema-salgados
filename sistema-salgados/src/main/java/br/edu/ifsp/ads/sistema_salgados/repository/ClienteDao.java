package br.edu.ifsp.ads.sistema_salgados.repository;

import br.edu.ifsp.ads.sistema_salgados.model.Cliente;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteDao extends JpaRepository<Cliente, Long> {
    Cliente findByEmailAndSenha(String email, String senha);
    List<Cliente> findByEmail(String email);
}
