/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.senac.Scolaris.service;

import com.senac.Scolaris.data.AlunoTurmaEntity;
import com.senac.Scolaris.data.AlunoTurmaRepository;
import com.senac.Scolaris.data.TurmasEntity;
import com.senac.Scolaris.data.UsersEntity;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author tavep
 */
@Service
public class AlunoTurmaService {

    @Autowired
    AlunoTurmaRepository alunoTurmaRepository;
    @Autowired
    UsersService usersService;
    @Autowired
    TurmasService turmasService;

    public AlunoTurmaEntity criarAlunoTurmas(AlunoTurmaEntity alunoTurma) {
        alunoTurma.setId(null);
        alunoTurmaRepository.save(alunoTurma);
        return alunoTurma;
    }
    public List<UsersEntity> getAlunoByTurma(int idTurma){
        List<UsersEntity> usuarios=new ArrayList();
        List<AlunoTurmaEntity> alunos=alunoTurmaRepository.findByTurmaId(idTurma);
        for(AlunoTurmaEntity aluno:alunos){
            usuarios.add(usersService.getUserById(aluno.getAlunoId()));
        }
        return usuarios;
    }
        public List<TurmasEntity> getTurmasByAluno(int idAluno){
        List<TurmasEntity> turmas=new ArrayList();
        List<AlunoTurmaEntity> alunos=alunoTurmaRepository.findByAlunoId(idAluno);
        for(AlunoTurmaEntity aluno:alunos){
            turmas.add(turmasService.getTurmaById(aluno.getTurmaId()));
        }
        return turmas;
    }
    public void deleteAllAlunosTurma(Integer turmaId){
        List<AlunoTurmaEntity> alunos=alunoTurmaRepository.findByTurmaId(turmaId);
        for(AlunoTurmaEntity aluno:alunos){
            alunoTurmaRepository.deleteById(aluno.getId());
        }
    }

}
