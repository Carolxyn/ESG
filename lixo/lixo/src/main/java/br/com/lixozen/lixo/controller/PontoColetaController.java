package br.com.lixozen.lixo.controller;

import br.com.lixozen.lixo.model.PontoColeta;
import br.com.lixozen.lixo.service.PontoColetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pontos-coleta")
public class PontoColetaController {

    @Autowired
    private PontoColetaService pontoColetaService;

    // GET /pontos-coleta - Listar todos os pontos de coleta
    @GetMapping
    public List<PontoColeta> listarTodos() {
        return pontoColetaService.listarTodos();
    }

    // GET /pontos-coleta/{id} - Buscar ponto de coleta por ID
    @GetMapping("/{id}")
    public ResponseEntity<PontoColeta> buscarPorId(@PathVariable Long id) {
        return pontoColetaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // GET /pontos-coleta/tipo/{tipoResiduo} - Buscar pontos de coleta por tipo de resíduo
    @GetMapping("/tipo/{tipoResiduo}")
    public List<PontoColeta> buscarPorTipoResiduo(@PathVariable String tipoResiduo) {
        return pontoColetaService.buscarPorTipoResiduo(tipoResiduo);
    }

    // GET /pontos-coleta/cidade/{cidade} - Buscar pontos de coleta por cidade
    @GetMapping("/cidade/{cidade}")
    public List<PontoColeta> buscarPorCidade(@PathVariable String cidade) {
        return pontoColetaService.buscarPorCidade(cidade);
    }

    // POST /pontos-coleta - Cadastrar novo ponto de coleta
    @PostMapping
    public PontoColeta criar(@RequestBody PontoColeta pontoColeta) {
        return pontoColetaService.salvar(pontoColeta);
    }

    // PUT /pontos-coleta/{id} - Atualizar ponto de coleta
    @PutMapping("/{id}")
    public ResponseEntity<PontoColeta> atualizar(@PathVariable Long id, @RequestBody PontoColeta novoPontoColeta) {
        try {
            PontoColeta pontoAtualizado = pontoColetaService.atualizar(id, novoPontoColeta);
            return ResponseEntity.ok(pontoAtualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE /pontos-coleta/{id} - Deletar um ponto de coleta
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (pontoColetaService.existe(id)) {
            pontoColetaService.deletar(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}