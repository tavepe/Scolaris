/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.senac.Scolaris.controller;

import com.senac.Scolaris.data.AlunoTurmaEntity;
import com.senac.Scolaris.data.AvaliacoesEntity;
import com.senac.Scolaris.data.NotasEntity;
import com.senac.Scolaris.data.TurmasEntity;
import com.senac.Scolaris.data.UsersEntity;
import com.senac.Scolaris.service.AlunoTurmaService;
import com.senac.Scolaris.service.AvaliacoesService;
import com.senac.Scolaris.service.NotasService;
import com.senac.Scolaris.service.TurmasService;
import com.senac.Scolaris.service.UsersService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author tavep
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/data")
public class DataController {

    @Autowired
    TurmasService turmasService;
    @Autowired
    UsersService usersService;
    @Autowired
    AlunoTurmaService alunoTurmaService;
    @Autowired
    AvaliacoesService avaliacoesService;
    @Autowired
    NotasService notasService;

    @GetMapping("/listarTurmas")
    public ResponseEntity<List> getAllTurmas() {
        List<TurmasEntity> turmas = turmasService.listarTodasTurmas();
        return new ResponseEntity<>(turmas, HttpStatus.OK);
    }
    @GetMapping("/getTurma/{id}")
    public ResponseEntity<TurmasEntity> getTurma(@PathVariable Integer id) {
        TurmasEntity turma = turmasService.getTurmaById(id);
        return new ResponseEntity<>(turma, HttpStatus.OK);
    }


    @GetMapping("/getUser/{id}")
    public ResponseEntity<UsersEntity> getProfessor(@PathVariable Integer id) {
        UsersEntity user = usersService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/getNotaAlunoAval/{alunoId}/{avalId}")
    public ResponseEntity<NotasEntity> getNota(@PathVariable Integer alunoId, @PathVariable Integer avalId) {
        NotasEntity nota = notasService.getNotaByAlunoAval(alunoId, avalId);
        return new ResponseEntity<>(nota, HttpStatus.OK);
    }

    @GetMapping("/getAvaliacao/{id}")
    public ResponseEntity<AvaliacoesEntity> getAvaliacao(@PathVariable Integer id) {
        AvaliacoesEntity aval = avaliacoesService.getAvaliacaoById(id);
        return new ResponseEntity<>(aval, HttpStatus.OK);
    }
    
    @GetMapping("/listarUsers")
    public ResponseEntity<List> getAllUsers() {
        List<UsersEntity> users = usersService.listarTodosUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("listarAvalsTurma/{id}")
    public ResponseEntity<List> getAvalsByTurma(@PathVariable Integer id) {
        List<AvaliacoesEntity> avals = avaliacoesService.listarAvalsTurmas(id);
        return new ResponseEntity<>(avals, HttpStatus.OK);
    }

    @GetMapping("/listarAlunos")
    public ResponseEntity<List> getAllAlunos() {
        List<UsersEntity> users = usersService.listarAlunos();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/listarProfessores")
    public ResponseEntity<List> getAllProfessores() {
        List<UsersEntity> users = usersService.listarProfessores();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/getAlunosTurma/{id}")
    public ResponseEntity<List> getAlunosByTurma(@PathVariable Integer id) {
        List<UsersEntity> users = alunoTurmaService.getAlunoByTurma(id);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/getTurmasProfessor/{id}")
    public ResponseEntity<List> getTurmasByProfessor(@PathVariable Integer id) {
        List<TurmasEntity> turmas = turmasService.litarTurmasProfessor(id);
        return new ResponseEntity<>(turmas, HttpStatus.OK);
    }
    @GetMapping("/getTurmasAluno/{id}")
    public ResponseEntity<List> getTurmasByAluno(@PathVariable Integer id) {
        List<TurmasEntity> turmas = alunoTurmaService.getTurmasByAluno(id);
        return new ResponseEntity<>(turmas, HttpStatus.OK);
    }

    @PostMapping("/adicionarNota")
    public ResponseEntity<NotasEntity> addNota(@RequestBody NotasEntity nota) {
        var novaNota = notasService.criarNota(nota);
        return new ResponseEntity<>(novaNota, HttpStatus.CREATED);
    }

    @PostMapping("/adicionarAval")
    public ResponseEntity<AvaliacoesEntity> addAval(@RequestBody AvaliacoesEntity aval) {
        var novaAval = avaliacoesService.criarTurmas(aval);
        return new ResponseEntity<>(novaAval, HttpStatus.CREATED);
    }

    @PostMapping("/adicionarTurma")
    public ResponseEntity<TurmasEntity> addTurma(@RequestBody TurmasEntity turma) {
        var novaTurma = turmasService.criarTurmas(turma);
        return new ResponseEntity<>(novaTurma, HttpStatus.CREATED);
    }

    @PostMapping("/adicionarAlunosTurma")
    public ResponseEntity<AlunoTurmaEntity> addAlunosTurma(@RequestBody AlunoTurmaEntity alunoTurma) {
        var novoAlunoTurma = alunoTurmaService.criarAlunoTurmas(alunoTurma);
        return new ResponseEntity<>(novoAlunoTurma, HttpStatus.CREATED);
    }

    @PostMapping("/adicionarUser")
    public ResponseEntity<UsersEntity> addFilme(@RequestBody UsersEntity filme) {
        var novoUser = usersService.criarUser(filme);
        return new ResponseEntity<>(novoUser, HttpStatus.CREATED);
    }

    @PutMapping("/atualizarUser/{id}")
    public ResponseEntity<UsersEntity> atualizarUser(@PathVariable Integer id, @RequestBody UsersEntity user) {
        var filmeAtualizado = usersService.atualizarUser(id, user);
        return new ResponseEntity<>(filmeAtualizado, HttpStatus.OK);
    }

    @PutMapping("/atualizarTurma/{id}")
    public ResponseEntity<TurmasEntity> atualizarTurma(@PathVariable Integer id, @RequestBody TurmasEntity turma) {
        var turmaAtualizada = turmasService.atualizarTurma(id, turma);
        return new ResponseEntity<>(turmaAtualizada, HttpStatus.OK);
    }

    @DeleteMapping("/deletarAlunoTurma/{id}")
    public ResponseEntity deletarAlunoTurma(@PathVariable Integer id) {
        alunoTurmaService.deleteAllAlunosTurma(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/deletarUser/{id}")
    public ResponseEntity deletarUser(@PathVariable Integer id) {
        usersService.deletarUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("deletarTurma/{id}")
    public ResponseEntity deletarTurma(@PathVariable Integer id) {
        turmasService.deletarTurma(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
