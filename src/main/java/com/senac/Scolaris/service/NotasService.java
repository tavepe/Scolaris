/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.senac.Scolaris.service;

import com.senac.Scolaris.data.NotasEntity;
import com.senac.Scolaris.data.NotasRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author tavep
 */
@Service
public class NotasService {
    @Autowired
    NotasRepository notasRepository;
    
    public NotasEntity getNotaByAlunoAval(Integer alunoId, Integer avalId){
        List<NotasEntity> todosAlunos=notasRepository.findByAlunoId(alunoId);
        NotasEntity notaFinal=new NotasEntity();
        for(NotasEntity aluno:todosAlunos){
            if(aluno.getAvalId()==avalId){
                notaFinal=aluno;
            }
        }
        
        return notaFinal;
    }
    public NotasEntity criarNota(NotasEntity nota) {
        notasRepository.save(nota);
        return nota;
    }
}
