/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.senac.Scolaris.service;

import com.senac.Scolaris.data.UsersEntity;
import com.senac.Scolaris.data.UsersRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author tavep
 */
@Service
public class UsersService {

    @Autowired
    UsersRepository usersRepository;

    public UsersEntity criarUser(UsersEntity user) {
        user.setId(null);
        user.setSenha(Criptografia.getMD5(user.getSenha()));
        usersRepository.save(user);
        return user;
    }

    public UsersEntity atualizarUser(Integer filmeId, UsersEntity userRequest) {

        UsersEntity user = getUserById(filmeId);
        user.setNome(userRequest.getNome());
        user.setSenha(Criptografia.getMD5(userRequest.getSenha()));
        user.setTipo(userRequest.getTipo());
        user.setUsuario(userRequest.getUsuario());
        usersRepository.save(user);
        return user;

    }

    public List<UsersEntity> listarTodosUsers() {
        return usersRepository.findAll();
    }

    public List<UsersEntity> listarAlunos() {
        List<UsersEntity> users = listarTodosUsers();
        List<UsersEntity> alunos = new ArrayList();
        for (UsersEntity user : users) {
            if (user.getTipo().equals("aluno")) {
                alunos.add(user);
            }
        }
        return alunos;
    }

    public List<UsersEntity> listarProfessores() {
        List<UsersEntity> users = listarTodosUsers();
        List<UsersEntity> professores = new ArrayList();
        for (UsersEntity user : users) {
            if (user.getTipo().equals("professor")) {
                professores.add(user);
            }
        }
        return professores;
    }

    public UsersEntity getUserById(Integer filmeId) {
        return usersRepository.findById(filmeId).orElse(null);
    }

    public void deletarUser(Integer turmaId) {
        UsersEntity turma = getUserById(turmaId);
        usersRepository.deleteById(turma.getId());
    }

}
