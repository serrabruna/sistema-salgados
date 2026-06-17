package br.edu.ifsp.ads.sistema_salgados.controller;

import java.util.Map;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.edu.ifsp.ads.sistema_salgados.model.Cliente;
import br.edu.ifsp.ads.sistema_salgados.model.Movimentacao;
import br.edu.ifsp.ads.sistema_salgados.repository.MovimentacaoDao;
import br.edu.ifsp.ads.sistema_salgados.repository.ClienteDao;
import br.edu.ifsp.ads.sistema_salgados.service.PedidoService;

@RestController
@RequestMapping("/api/pedidos")
@CrossOrigin(origins = "*")
public class PedidoController {

    @Autowired
    private MovimentacaoDao movimentacaoDao;
    
    @Autowired
    private ClienteDao clienteDao;
    
    @Autowired
    private PedidoService pedidoService; 

    @PostMapping("/vender")
    public ResponseEntity<?> vender(@RequestBody Map<String, Object> dados) {
        String sabor = (String) dados.get("sabor");
        Integer qtd = (Integer) dados.get("quantidade");
        Long clienteId = ((Number) dados.get("clienteId")).longValue();

        Cliente cliente = clienteDao.findById(clienteId).orElse(null);
        if (cliente == null) return ResponseEntity.badRequest().body("Cliente não encontrado.");

        try {
            Movimentacao mov = pedidoService.processarPedido(sabor, qtd, cliente);
            return ResponseEntity.ok(mov);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/previa")
    public ResponseEntity<?> obterPrevia(@RequestBody Map<String, Object> dados) {
        String sabor = (String) dados.get("sabor");
        Integer qtd = (Integer) dados.get("quantidade");
        Long clienteId = ((Number) dados.get("clienteId")).longValue();
        
        Cliente cliente = clienteDao.findById(clienteId).orElse(null);
        if (cliente == null) return ResponseEntity.badRequest().body("Cliente não encontrado.");

        try {
            Movimentacao previa = pedidoService.criarPreviaDoPedido(sabor, qtd, cliente);
            return ResponseEntity.ok(previa); 
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/cardapio")
    public ResponseEntity<List<Map<String, Object>>> listarCardapio() {
        return ResponseEntity.ok(List.of(
            Map.of("nome", "Frango", "preco", 7.0),
            Map.of("nome", "Carne", "preco", 7.5),
            Map.of("nome", "Catupiry", "preco", 8.0),
            Map.of("nome", "Queijo", "preco", 6.0)
        ));
    }

    @GetMapping("/log")
    public ResponseEntity<List<Movimentacao>> listarTodosLog() {
        return ResponseEntity.ok(movimentacaoDao.findAll());
    }

    @GetMapping("/historico/{clienteId}")
    public ResponseEntity<List<Movimentacao>> listarHistorico(@PathVariable Long clienteId) {
        return ResponseEntity.ok(movimentacaoDao.findByClienteId(clienteId));
    }

    @DeleteMapping("/estornar/{id}")
    public ResponseEntity<String> estornar(@PathVariable Long id) {
        try {
            pedidoService.cancelarPedido(id);
            return ResponseEntity.ok("Pedido estornado com sucesso.");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}