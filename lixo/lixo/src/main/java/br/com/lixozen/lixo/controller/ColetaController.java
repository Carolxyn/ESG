package br.com.lixozen.lixo.controller;

import br.com.lixozen.lixo.model.Coleta;
import br.com.lixozen.lixo.repository.ColetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/coletas")
public class ColetaController {

    @Autowired
    private ColetaRepository coletaRepository;

    // GET /coletas - Listar todas as coletas
    @GetMapping
    public List<Coleta> listarTodas() {
        return coletaRepository.findAll();
    }

    // GET /coletas/{id} - Buscar coleta por ID
    @GetMapping("/{id}")
    public ResponseEntity<Coleta> buscarPorId(@PathVariable Long id) {
        Optional<Coleta> coleta = coletaRepository.findById(id);
        return coleta.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /coletas - Cadastrar nova coleta
    @PostMapping
    public Coleta criar(@RequestBody Coleta coleta) {
        return coletaRepository.save(coleta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Coleta> atualizar(@PathVariable Long id, @RequestBody Coleta novaColeta) {
        return coletaRepository.findById(id)
                .map(coletaExistente -> {
                    coletaExistente.setTipoResiduo(novaColeta.getTipoResiduo());
                    coletaExistente.setQuantidadeKg(novaColeta.getQuantidadeKg());
                    coletaExistente.setDataColeta(novaColeta.getDataColeta());
                    coletaExistente.setLocalizacao(novaColeta.getLocalizacao());
                    coletaExistente.setResponsavel(novaColeta.getResponsavel());
                    return ResponseEntity.ok(coletaRepository.save(coletaExistente));
                }).orElse(ResponseEntity.notFound().build());
    }


    // DELETE /coletas/{id} - Deletar uma coleta
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (coletaRepository.existsById(id)) {
            coletaRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
