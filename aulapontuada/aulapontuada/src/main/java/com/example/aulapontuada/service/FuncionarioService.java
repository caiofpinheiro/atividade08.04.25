package com.example.aulapontuada.service;

import com.example.aulapontuada.exception.EmailJaEncontrado;
import com.example.aulapontuada.model.Funcionario;
import com.example.aulapontuada.repository.FuncionarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService {
    @Autowired
    private final FuncionarioRepository funcionarioRepository;

    public FuncionarioService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public List<Funcionario> getAllFuncionarios() {
        return funcionarioRepository.findAll();
    }

    public Funcionario salvarFuncionario(@Valid Funcionario funcionario) {
        if (funcionarioRepository.findByEmail(funcionario.getEmail()).isPresent()) {
            throw new EmailJaEncontrado("Email já cadastrado");
        }
            return funcionarioRepository.save(funcionario);
    }

    public Optional<Funcionario> buscarFuncionarioPorId(Long id) {
        return funcionarioRepository.findById(id);
    }

    public Funcionario atualizarFuncionario(Funcionario funcionario) {
        return funcionarioRepository.save(funcionario);
    }

    public void deletarFuncionario(Long id) {
        Funcionario funcionario = buscarFuncionarioPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Funcionário não encontrado"));
        funcionarioRepository.delete(funcionario);
    }

    public Funcionario atualizar(@Valid Funcionario funcionario) {
        Funcionario funcionarioExistente = buscarFuncionarioPorId(funcionario.getId())
                .orElseThrow(() -> new IllegalArgumentException("Funcionário não encontrado"));
        funcionarioExistente.setNome(funcionario.getNome());
        funcionarioExistente.setCpf(funcionario.getCpf());
        funcionarioExistente.setDataNascimento(funcionario.getDataNascimento());
        funcionarioExistente.setSexo(funcionario.getSexo());
        funcionarioExistente.setSetor(funcionario.getSetor());
        funcionarioExistente.setEstadoCivil(funcionario.getEstadoCivil());
        funcionarioExistente.setSalario(funcionario.getSalario());
        funcionarioExistente.setEmail(funcionario.getEmail());
        funcionarioExistente.setEndereco(funcionario.getEndereco());
        return funcionarioRepository.save(funcionarioExistente);
    }
}
