package br.edu.ifsp.ads.sistema_salgados.controller;

import java.util.Map;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        Number clienteIdNum = (Number) dados.get("clienteId");

        if (clienteIdNum == null) {
            return ResponseEntity.badRequest().body("O campo 'clienteId' é obrigatório.");
        }

        Long clienteId = clienteIdNum.longValue();
        Cliente cliente = clienteDao.findById(clienteId).orElse(null);
        if (cliente == null) {
            return ResponseEntity.badRequest().body("Cliente não encontrado.");
        }

        try {
            Movimentacao mov = pedidoService.processarPedido(sabor, qtd, cliente);
            return ResponseEntity.ok(mov);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/historico/{clienteId}")
    public ResponseEntity<List<Movimentacao>> listarHistorico(@PathVariable Long clienteId) {
        List<Movimentacao> historico = movimentacaoDao.findByClienteId(clienteId);
        return ResponseEntity.ok(historico);
    }

    @DeleteMapping("/estornar/{id}")
    public ResponseEntity<String> estornar(@PathVariable Long id) {
        try {
            pedidoService.cancelarPedido(id);
            return ResponseEntity.ok("Pedido estornado com sucesso e saldo devolvido.");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}