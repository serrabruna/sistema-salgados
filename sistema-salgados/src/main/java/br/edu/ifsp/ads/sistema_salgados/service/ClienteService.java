package br.edu.ifsp.ads.sistema_salgados.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifsp.ads.sistema_salgados.model.Cliente;
import br.edu.ifsp.ads.sistema_salgados.repository.ClienteDao;

//Atua como Facade para operações relacionadas a clientes, como registro e autenticação
@Service 
public class ClienteService {

    @Autowired
    private ClienteDao clienteDao;

    public Cliente registrarNovoCliente(Cliente cliente) {
        if (cliente.getNome() == null || cliente.getNome().trim().isEmpty()) {
            throw new RuntimeException("O nome é obrigatório!");
        }
        if (cliente.getEmail() == null || cliente.getEmail().trim().isEmpty()) {
            throw new RuntimeException("O e-mail é obrigatório!");
        }
        if (cliente.getSenha() == null || cliente.getSenha().trim().isEmpty()) {
            throw new RuntimeException("A senha é obrigatória!");
        }

        Cliente clienteExistente = clienteDao.findByEmailAndSenha(cliente.getEmail(), cliente.getSenha());
        if (clienteExistente != null) {
            throw new RuntimeException("Este e-mail já está cadastrado no sistema!");
        }
        
        if (cliente.getSaldo() == null || cliente.getSaldo() < 0) {
            cliente.setSaldo(0.0);
        }
        
        return clienteDao.save(cliente);
    }

    public Cliente autenticarCliente(String email, String senha) {
        if (email == null || email.trim().isEmpty()) {
            throw new RuntimeException("Por favor, informe o e-mail de login.");
        }
        if (senha == null || senha.trim().isEmpty()) {
            throw new RuntimeException("Por favor, informe a senha de acesso.");
        }

        Cliente cliente = clienteDao.findByEmailAndSenha(email, senha);
        if (cliente == null) {
            throw new RuntimeException("E-mail ou senha inválidos!");
        }
        
        return cliente;
    }
}
