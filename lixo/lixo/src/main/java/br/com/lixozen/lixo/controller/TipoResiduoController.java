package br.com.lixozen.lixo.controller;

import br.com.lixozen.lixo.model.TipoResiduo;
import br.com.lixozen.lixo.service.TipoResiduoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tipos-residuo")
public class TipoResiduoController {

    @Autowired
    private TipoResiduoService tipoResiduoService;

    // GET /tipos-residuo - Listar todos os tipos de resíduo
    @GetMapping
    public List<TipoResiduo> listarTodos() {
        return tipoResiduoService.listarTodos();
    }

    // GET /tipos-residuo/{id} - Buscar tipo de resíduo por ID
    @GetMapping("/{id}")
    public ResponseEntity<TipoResiduo> buscarPorId(@PathVariable Long id) {
        return tipoResiduoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /tipos-residuo - Cadastrar novo tipo de resíduo
    @PostMapping
    public TipoResiduo criar(@RequestBody TipoResiduo tipoResiduo) {
        return tipoResiduoService.salvar(tipoResiduo);
    }

    // PUT /tipos-residuo/{id} - Atualizar tipo de resíduo
    @PutMapping("/{id}")
    public ResponseEntity<TipoResiduo> atualizar(@PathVariable Long id, @RequestBody TipoResiduo novoTipoResiduo) {
        try {
            TipoResiduo tipoAtualizado = tipoResiduoService.atualizar(id, novoTipoResiduo);
            return ResponseEntity.ok(tipoAtualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE /tipos-residuo/{id} - Deletar um tipo de resíduo
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (tipoResiduoService.existe(id)) {
            tipoResiduoService.deletar(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}