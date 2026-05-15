package br.edu.ifsp.ads.sistema_salgados.controller;

import java.util.Map;
import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifsp.ads.sistema_salgados.model.Cliente;
import br.edu.ifsp.ads.sistema_salgados.model.Movimentacao;
import br.edu.ifsp.ads.sistema_salgados.patterns.command.Command;
import br.edu.ifsp.ads.sistema_salgados.patterns.command.PedidoCommand;
import br.edu.ifsp.ads.sistema_salgados.repository.MovimentacaoDao;
import br.edu.ifsp.ads.sistema_salgados.repository.ClienteDao;
import br.edu.ifsp.ads.sistema_salgados.patterns.factory.MovimentacaoFactory;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {
    
    @Autowired
    private MovimentacaoDao movimentacaoDao;
    @Autowired
    private ClienteDao clienteDao;

    @PostMapping("/vender")
    public ResponseEntity<Movimentacao> vender(@RequestBody Map<String, Object> dados) {
        String sabor = (String) dados.get("sabor");
        Integer qtd = (Integer) dados.get("quantidade");
        
        Cliente cliente = clienteDao.findById(1L).orElse(null);
        Movimentacao mov = MovimentacaoFactory.criar(sabor, qtd, cliente);
        Command comando = new PedidoCommand(movimentacaoDao, mov);
        comando.execute();

        return ResponseEntity.ok(mov);
    }

    @GetMapping("/historico/{clienteId}")
    public ResponseEntity<List<Movimentacao>> listarHistorico(@PathVariable Long clienteId) {
        // Agora o List<Movimentacao> será reconhecido como uma coleção genérica
        List<Movimentacao> historico = movimentacaoDao.findByClienteId(clienteId);
        return ResponseEntity.ok(historico);
    }

    @DeleteMapping("/estornar/{id}")
    public ResponseEntity<String> estornar(@PathVariable Long id) {
        Movimentacao mov = movimentacaoDao.findById(id)
            .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
        
        Command comando = new PedidoCommand(movimentacaoDao, mov);
        comando.undo();
        return ResponseEntity.ok("Pedido estornado com sucesso");
    }
}
