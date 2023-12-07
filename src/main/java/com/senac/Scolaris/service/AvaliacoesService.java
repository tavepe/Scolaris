/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.senac.Scolaris.service;

import com.senac.Scolaris.data.AvaliacoesEntity;
import com.senac.Scolaris.data.AvaliacoesRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author tavep
 */
@Service
public class AvaliacoesService {

    @Autowired
    AvaliacoesRepository avaliacoesRepository;

    public AvaliacoesEntity criarTurmas(AvaliacoesEntity aval) {
        aval.setId(null);
        avaliacoesRepository.save(aval);
        return aval;
    }

    public AvaliacoesEntity getAvaliacaoById(Integer id) {
        return avaliacoesRepository.findById(id).orElse(null);
    }

    public List<AvaliacoesEntity> listarAvalsTurmas(int turmaId) {
        return avaliacoesRepository.findByTurmaId(turmaId);
    }
}
