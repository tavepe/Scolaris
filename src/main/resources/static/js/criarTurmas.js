$(document).ready(function () {
    let alunosTurma = [];
    carregarTabelas();
    carregarProfessores();

    $('#formTurma').submit(function (event) {

        event.preventDefault();

        let professorId = $('#selectProfessor').val();
        let titulo = $('#nomeTurma').val();
        let descricao = $('#descricao').val();
        let turma = {
            professorId: professorId,
            titulo: titulo,
            descricao: descricao
        };
        if (professorId == "") {
            alert("Por favor, selecione um profesor!")
        } else {
            salvarTurma(turma);
        }

    })

    function carregarProfessores() {
        $.ajax({
            url: 'http://localhost:8080/data/listarProfessores',
            method: 'GET',
            success: function (data) {

                $('#divSelectProfessor').empty()
                let select = $('#divSelectProfessor')
                    .attr('class', 'form-select')
                    .attr('id', 'selectProfessor')
                let opcaoNula = $('<option>').text("Selecione um professor")
                opcaoNula.val(null)
                $(select).append(opcaoNula);


                for (let i = 0; i < data.length; i++) {
                    let professor = data[i]
                    let option = $('<option>')
                        .val(professor.id)
                        .text(professor.nome)

                    $(select).append(option);
                }
            },
            error: function () {
                alert('Não foi possível carregar os professores!')
            }
        })
    }

    function carregarTabelas() {
        $.ajax({
            url: 'http://localhost:8080/data/listarAlunos',
            method: 'GET',
            success: function (data) {
                $('#tableTodosAlunos tbody').empty();
                $('#tableAlunosTurma tbody').empty();
                let alunosFora = []

                for (let i = 0; i < data.length; i++) {
                    let aluno = data[i];

                    if (alunosTurma.includes(aluno.id)) {

                    } else {
                        alunosFora.push(aluno)
                    }
                }
                for (let i = 0; i < alunosFora.length; i++) {
                    let alunoFora = alunosFora[i]
                    let nome = $('<td>').text(alunoFora.nome)
                    let botaoEditar = $('<button>').click(function () {
                        addAluno($(this).parent().parent().attr('data-id'))
                    });
                    botaoEditar.attr('class', 'botaoEditar')
                    botaoEditar.append(">")
                    botaoEditar.attr('type', 'button')
                    let colunaBotoes = $('<td>').append(botaoEditar);



                    let tr = $('<tr>')
                        .attr('data-id', alunoFora.id)
                        .append(nome)
                        .append(colunaBotoes)


                    $('#tableTodosAlunos tbody').append(tr);
                }
                for (let i = 0; i < alunosTurma.length; i++) {
                    $.ajax({
                        url: 'http://localhost:8080/data/getUser/' + alunosTurma[i],
                        method: 'GET',
                        success: function (data) {
                            let alunoTurma = data
                            let nome = $('<td>').text(alunoTurma.nome)
                            let botaoEditar = $('<button>').click(function () {
                                removeAluno($(this).parent().parent().attr('data-id'))
                            });
                            botaoEditar.attr('class', 'botaoApagar')
                            botaoEditar.append("X")
                            botaoEditar.attr('type', 'button')
                            let colunaBotoes = $('<td>').append(botaoEditar);



                            let tr = $('<tr>')
                                .attr('data-id', alunoTurma.id)
                                .append(nome)
                                .append(colunaBotoes)


                            $('#tableAlunosTUrma tbody').append(tr);
                        },
                        error: function () {
                            alert('Não foi possível carregar os alunos!')
                        }
                    })
                }
            },
            error: function () {
                alert('Não foi possível carregar os alunos!')
            }
        }

        )
    }
    function addAluno(id) {
        alunosTurma.push(parseInt(id));
        carregarTabelas();
    }
    function removeAluno(id) {
        for (let i = 0; i < alunosTurma.length; i++) {
            if (alunosTurma[i] == id) {
                delete alunosTurma[i];
            }
        }
        alunosTurma = alunosTurma.filter(function (dado) {
            if (dado != undefined) {
                return dado;
            }
        })
        carregarTabelas();
    }
    function salvarTurma(turma) {
        var formValues = JSON.stringify(turma);

        $.ajax({
            url: 'http://localhost:8080/data/adicionarTurma',
            method: 'POST',
            contentType: 'application/json',
            data: formValues,
            success: function (data) {
                salvarAlunos(data);
            },
            error: function () {
                alert("Erro ao cadastrar turma!")
            }
        });
    }
    function salvarAlunos(turma) {
        for (let i = 0; i < alunosTurma.length; i++) {
            alunoId = alunosTurma[i]
            turmaId = turma.id;
            let alunoTurma = {
                alunoId: alunoId,
                turmaId: turmaId
            }
            let turmaAluno = JSON.stringify(alunoTurma);
            $.ajax({
                url: 'http://localhost:8080/data/adicionarAlunosTurma',
                method: 'POST',
                contentType: 'application/json',
                data: turmaAluno,
                error: function () {
                    alert("Falha ao cadastrar alunos na turma!")
                }
            })
        }
        window.location = "/turmasDiretor"
    }
}
)


