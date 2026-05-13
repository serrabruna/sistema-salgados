package br.edu.ifsp.ads.sistema_salgados.controller;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifsp.ads.sistema_salgados.model.Movimentacao;
import br.edu.ifsp.ads.sistema_salgados.patterns.command.Command;
import br.edu.ifsp.ads.sistema_salgados.patterns.command.PedidoCommand;
import br.edu.ifsp.ads.sistema_salgados.repository.MovimentacaoDao;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {
    
    @Autowired
    private MovimentacaoDao movimentacaoDao;

    @PostMapping("/vender")
    public ResponseEntity<Movimentacao> vender(@RequestBody Movimentacao mov) {
        Command comando = new PedidoCommand(movimentacaoDao, mov);
        comando.execute();
        return ResponseEntity.ok(mov);
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
