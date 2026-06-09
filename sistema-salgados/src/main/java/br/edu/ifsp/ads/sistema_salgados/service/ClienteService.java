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
        
        if (cliente.getSaldo() == null || cliente.getSaldo() < 0) {
            cliente.setSaldo(0.0);
        }
        return clienteDao.save(cliente);
    }

    public Cliente autenticarCliente(String email, String senha) {
        Cliente cliente = clienteDao.findByEmailAndSenha(email, senha);
        if (cliente == null) {
            throw new RuntimeException("Credenciais inválidas");
        }
        return cliente;
    }
}
