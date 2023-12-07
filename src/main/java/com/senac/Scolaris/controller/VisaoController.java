/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.senac.Scolaris.controller;

import com.senac.Scolaris.data.UsersEntity;
import com.senac.Scolaris.service.Criptografia;
import com.senac.Scolaris.service.TurmasService;
import com.senac.Scolaris.service.UsersService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author tavep
 */
@Controller
@RequestMapping("/")
public class VisaoController {

    @Autowired
    UsersService usersService;
    @Autowired
    TurmasService turmasService;

    @GetMapping("/")
    public String index(HttpServletRequest request, Model model) {
        HttpSession sessao = request.getSession();
        if (sessao.getAttribute("usuario") != null) {
            sessao.removeAttribute("usuario");
            sessao.removeAttribute("senha");
            sessao.removeAttribute("nome");
            sessao.removeAttribute("id");
            sessao.removeAttribute("tipo");
        }
        model.addAttribute("user", new UsersEntity());
        return "index";
    }

    @PostMapping("/")
    public String login(HttpServletRequest request, Model model, @ModelAttribute UsersEntity user) {
        HttpSession sessao = request.getSession();
        UsersEntity usuario = validar(user);
        if (usuario != null) {
            sessao.setAttribute("usuario", usuario.getUsuario());
            sessao.setAttribute("senha", usuario.getSenha());
            sessao.setAttribute("nome", usuario.getNome());
            sessao.setAttribute("id", usuario.getId());
            sessao.setAttribute("tipo", usuario.getTipo());
        }
        if (sessao.getAttribute("tipo") != null) {

            if (sessao.getAttribute("tipo").equals("admin")) {
                return "redirect:/turmasDiretor";
            } else {
                if (sessao.getAttribute("tipo").equals("professor")) {
                    return "redirect:/turmasProfessor/" + sessao.getAttribute("id");
                } else {
                    if (sessao.getAttribute("tipo").equals("aluno")) {
                        return "redirect:/turmaAlunos/"+sessao.getAttribute("id");
                    } else {
                        sessao.removeAttribute("usuario");
                        sessao.removeAttribute("senha");
                        sessao.removeAttribute("nome");
                        sessao.removeAttribute("id");
                        sessao.removeAttribute("tipo");
                        return "redirect:/";
                    }
                }
            }
        } else {
            sessao.removeAttribute("usuario");
            sessao.removeAttribute("senha");
            sessao.removeAttribute("nome");
            sessao.removeAttribute("id");
            sessao.removeAttribute("tipo");
            return "redirect:/";
        }
    }
//TELAS DO DIRETOR

    @GetMapping("/turmasDiretor")
    public String diretorIndex(HttpServletRequest request, Model model) {

        HttpSession sessao = request.getSession();
        if (sessao.getAttribute("tipo") != null) {
            if (sessao.getAttribute("tipo").equals("admin")) {
                return "turmasDiretor";
            } else {
                return "redirect:/";
            }
        } else {
            return "redirect:/";
        }

    }

    @GetMapping("/criarTurmas")
    public String criarTurmas(HttpServletRequest request, Model model) {
        HttpSession sessao = request.getSession();
        if (sessao.getAttribute("tipo") != null) {
            if (sessao.getAttribute("tipo").equals("admin")) {
                return "criarTurma";
            } else {
                return "redirect:/";
            }
        } else {
            return "redirect:/";
        }

    }

    @GetMapping("/editarTurmas/{id}")
    public String editarTurmas(HttpServletRequest request, Model model, @PathVariable Integer id) {
        HttpSession sessao = request.getSession();
        if (sessao.getAttribute("tipo") != null) {
            if (sessao.getAttribute("tipo").equals("admin")) {
                model.addAttribute("turma", turmasService.getTurmaById(id));
                return "editarTurma";
            } else {
                return "redirect:/";
            }
        } else {
            return "redirect:/";
        }

    }

    @GetMapping("/verUsers")
    public String verUsers(HttpServletRequest request, Model model) {
        HttpSession sessao = request.getSession();
        if (sessao.getAttribute("tipo") != null) {
            if (sessao.getAttribute("tipo").equals("admin")) {
                return "usersDiretor";
            } else {
                return "redirect:/";
            }
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/criarUser")
    public String criarUser(HttpServletRequest request, Model model) {
        HttpSession sessao = request.getSession();
        if (sessao.getAttribute("tipo") != null) {
            if (sessao.getAttribute("tipo").equals("admin")) {
                return "criarUser";
            } else {
                return "redirect:/";
            }
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/editarUser/{id}")
    public String editarUser(HttpServletRequest request, Model model, @PathVariable Integer id) {
        HttpSession sessao = request.getSession();
        if (sessao.getAttribute("tipo") != null) {
            if (sessao.getAttribute("tipo").equals("admin")) {
                model.addAttribute("user", usersService.getUserById(id));
                return "editarUser";
            } else {
                return "redirect:/";
            }
        } else {
            return "redirect:/";
        }
    }

    //TELAS DO PROFESSOR
    @GetMapping("/turmasProfessor/{id}")
    public String turmasProfessor(HttpServletRequest request, Model model, @PathVariable Integer id) {
        HttpSession sessao = request.getSession();
        if (sessao.getAttribute("tipo") != null) {
            if (sessao.getAttribute("tipo").equals("professor")) {
                if (sessao.getAttribute("id").equals(id)) {
                    model.addAttribute("professor", id);
                    return "turmasProfessor";
                } else {
                    return "redirect:/";
                }
            } else {
                return "redirect:/";
            }
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/avalsProfessor/{profId}/{turmaId}")
    public String turmasProfessor(HttpServletRequest request, Model model, @PathVariable Integer profId, @PathVariable Integer turmaId) {
        HttpSession sessao = request.getSession();
        if (sessao.getAttribute("tipo") != null) {
            if (sessao.getAttribute("tipo").equals("professor")) {
                if (sessao.getAttribute("id").equals(profId)) {
                    model.addAttribute("professor", profId);
                    model.addAttribute("turma", turmaId);
                    return "avalsProfessor";
                } else {
                    return "redirect:/";
                }
            } else {
                return "redirect:/";
            }
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/criarAval/{profId}/{turmaId}")
    public String criarAval(HttpServletRequest request, Model model, @PathVariable Integer profId, @PathVariable Integer turmaId) {
        HttpSession sessao = request.getSession();
        if (sessao.getAttribute("tipo") != null) {
            if (sessao.getAttribute("tipo").equals("professor")) {
                if (sessao.getAttribute("id").equals(profId)) {
                    model.addAttribute("professor", profId);
                    model.addAttribute("turma", turmaId);
                    return "criarAval";
                } else {
                    return "redirect:/";
                }
            } else {
                return "redirect:/";
            }
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/darNotas/{profId}/{turmaId}/{avalId}")
    public String darNotas(HttpServletRequest request, Model model, @PathVariable Integer profId, @PathVariable Integer turmaId, @PathVariable Integer avalId) {
        HttpSession sessao = request.getSession();
        if (sessao.getAttribute("tipo") != null) {
            if (sessao.getAttribute("tipo").equals("professor")) {
                if (sessao.getAttribute("id").equals(profId)) {
                    model.addAttribute("professor", profId);
                    model.addAttribute("turma", turmaId);
                    model.addAttribute("avaliacao", avalId);
                    return "darNota";
                } else {
                    return "redirect:/";
                }
            } else {
                return "redirect:/";
            }
        } else {
            return "redirect:/";
        }
    }
//TELAS ALUNOS
    @GetMapping("turmaAlunos/{id}")
    public String turmaAlunos(HttpServletRequest request,Model model,@PathVariable Integer id){
                HttpSession sessao = request.getSession();
        if (sessao.getAttribute("tipo") != null) {
            if (sessao.getAttribute("tipo").equals("aluno")) {
                if (sessao.getAttribute("id").equals(id)) {
                    model.addAttribute("aluno", id);
                    return "turmasAlunos";
                } else {
                    return "redirect:/";
                }
            } else {
                return "redirect:/";
            }
        } else {
            return "redirect:/";
        }
    }
    @GetMapping("avalAlunos/{alunoId}/{turmaId}")
    public String avalAlunos(HttpServletRequest request,Model model,@PathVariable Integer alunoId,@PathVariable Integer turmaId){
                HttpSession sessao = request.getSession();
        if (sessao.getAttribute("tipo") != null) {
            if (sessao.getAttribute("tipo").equals("aluno")) {
                if (sessao.getAttribute("id").equals(alunoId)) {
                    model.addAttribute("aluno", alunoId);
                    model.addAttribute("turma", turmaId);
                    return "avalsAlunos";
                } else {
                    return "redirect:/";
                }
            } else {
                return "redirect:/";
            }
        } else {
            return "redirect:/";
        }
    }
//FUNCAO PARA VALIDADR

    public UsersEntity validar(UsersEntity user) {
        List<UsersEntity> usuarios = usersService.listarTodosUsers();
        UsersEntity usuarioValidado = null;
        for (UsersEntity users : usuarios) {
            String usuario = users.getUsuario();
            String senha = users.getSenha();
            if (user.getUsuario().matches(usuario) && Criptografia.getMD5(user.getSenha()).matches(senha)) {
                usuarioValidado = users;
            }
        }
        return usuarioValidado;
    }

}
