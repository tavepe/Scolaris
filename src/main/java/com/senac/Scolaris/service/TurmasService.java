/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.senac.Scolaris.service;

import com.senac.Scolaris.data.TurmasEntity;
import com.senac.Scolaris.data.TurmasRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author tavep
 */
@Service
public class TurmasService {

    @Autowired
    TurmasRepository turmasRepository;

    @Autowired
    AlunoTurmaService alunoTurmaService;
    
    public TurmasEntity criarTurmas(TurmasEntity turma) {
        turma.setId(null);
        turmasRepository.save(turma);
        return turma;
    }

    public TurmasEntity atualizarTurma(Integer turmaId, TurmasEntity turmaRequest) {

        TurmasEntity turma = getTurmaById(turmaId);
        turma.setTitulo(turmaRequest.getTitulo());
        turma.setDescricao(turmaRequest.getDescricao());
        turma.setProfessorId(turmaRequest.getProfessorId());
        turmasRepository.save(turma);
        return turma;

    }

    public TurmasEntity getTurmaById(Integer turmaId) {
        return turmasRepository.findById(turmaId).orElse(null);
    }

    public List<TurmasEntity> listarTodasTurmas() {
        return turmasRepository.findAll();
    }

    public void deletarTurma(Integer turmaId) {
        TurmasEntity turma = getTurmaById(turmaId);
        turmasRepository.deleteById(turma.getId());
        alunoTurmaService.deleteAllAlunosTurma(turma.getId());
    }
    public List<TurmasEntity> litarTurmasProfessor(Integer id){
        return turmasRepository.findByProfessorId(id);
    }
}
