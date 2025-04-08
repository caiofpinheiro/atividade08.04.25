package com.example.aulapontuada.controller;


import com.example.aulapontuada.model.Funcionario;
import com.example.aulapontuada.service.FuncionarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {
    private final FuncionarioService funcionarioService;

    public FuncionarioController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> listarFuncionarios() {
        return ResponseEntity.ok(Map.of("funcionarios", funcionarioService.getAllFuncionarios()));
    }

    @PostMapping
    public ResponseEntity<Funcionario> salvarFuncionario(@RequestBody Funcionario funcionario) {
        funcionarioService.salvarFuncionario(funcionario);
        return ResponseEntity.ok(funcionario);
    }

    @GetMapping("/buscar")
            public ResponseEntity<Optional<Funcionario>> buscarFuncionario(@RequestParam Long id) {
        Optional<Funcionario> funcionario = funcionarioService.buscarFuncionarioPorId(id);
        return ResponseEntity.ok(funcionario);
        }

    @PutMapping
    public ResponseEntity<Funcionario> atualizarFuncionario(@RequestBody Funcionario funcionario) {
        funcionarioService.atualizarFuncionario(funcionario);
        return ResponseEntity.ok(funcionario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarFuncionario(@PathVariable Long id) {
        funcionarioService.deletarFuncionario(id);
        return ResponseEntity.noContent().build();
    }
}
