package br.com.lixozen.lixo.controller;

import br.com.lixozen.lixo.model.Alerta;
import br.com.lixozen.lixo.service.AlertaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alertas")
public class AlertaController {

    @Autowired
    private AlertaService alertaService;

    // GET /alertas - Listar todos os alertas
    @GetMapping
    public List<Alerta> listarTodos() {
        return alertaService.listarTodos();
    }

    // GET /alertas/{id} - Buscar alerta por ID
    @GetMapping("/{id}")
    public ResponseEntity<Alerta> buscarPorId(@PathVariable Long id) {
        return alertaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // GET /alertas/ativos - Listar alertas ativos
    @GetMapping("/ativos")
    public List<Alerta> listarAlertasAtivos() {
        return alertaService.listarAlertasAtivos();
    }

    // GET /alertas/tipo/{tipo} - Listar alertas por tipo
    @GetMapping("/tipo/{tipo}")
    public List<Alerta> listarPorTipo(@PathVariable String tipo) {
        return alertaService.listarPorTipo(tipo);
    }

    // POST /alertas - Criar novo alerta
    @PostMapping
    public Alerta criar(@RequestBody Alerta alerta) {
        return alertaService.salvar(alerta);
    }

    // PUT /alertas/{id} - Atualizar alerta
    @PutMapping("/{id}")
    public ResponseEntity<Alerta> atualizar(@PathVariable Long id, @RequestBody Alerta novoAlerta) {
        try {
            Alerta alertaAtualizado = alertaService.atualizar(id, novoAlerta);
            return ResponseEntity.ok(alertaAtualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE /alertas/{id} - Deletar um alerta
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (alertaService.existe(id)) {
            alertaService.deletar(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    // POST /alertas/{id}/resolver - Marcar alerta como resolvido
    @PostMapping("/{id}/resolver")
    public ResponseEntity<Alerta> resolverAlerta(@PathVariable Long id) {
        try {
            Alerta alertaResolvido = alertaService.resolverAlerta(id);
            return ResponseEntity.ok(alertaResolvido);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}