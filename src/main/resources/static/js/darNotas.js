$(document).ready(function () {
    carregarTabela();
    carregarAlunos();
    carregarDados();

    function carregarDados(){
        let idAval=$('#avalId').val();
        $.ajax({
            url:'http://localhost:8080/data/getAvaliacao/'+idAval,
            method:'GET',
            success:function(data){
                $('#tituloAval').empty();
                $('#tituloAval').text(data.titulo)
                $('#valorAval').empty();
                $('#valorAval').text(data.valorTotal)
                $('#descricaoAval').empty();
                $('#descricaoAval').text(data.descricao)
            }
        })
    }
    function carregarAlunos() {
        let ide = $('#turmaId').val()
        $.ajax({
            url: 'http://localhost:8080/data/getAlunosTurma/' + ide,
            method: 'GET',
            success: function (data) { 
                $('#selectAluno').empty()
                let select = $('#selectAluno')
                    .attr('class', 'form-select')
                let opcaoNula = $('<option>').text("Selecione um aluno")
                opcaoNula.val(null)
                $(select).append(opcaoNula);


                for (let i = 0; i < data.length; i++) {
                    let aluno = data[i]
                    let option = $('<option>')
                        .val(aluno.id)
                        .text(aluno.nome)

                    $(select).append(option);
                }
            }
        })
    }
    function carregarTabela() {
        let ide = $('#turmaId').val()
        $.ajax({
            url: 'http://localhost:8080/data/getAlunosTurma/' + ide,
            method: 'GET',
            success: function (data) {
                $('#tableNotas tbody').empty();

                for (let i = 0; i < data.length; i++) {
                    let aluno = data[i];
                    let nota = $('<td>').text('X')
                    let nome = $('<td>')
                        .text(aluno.nome);
                    $.ajax({
                        url: 'http://localhost:8080/data/getNotaAlunoAval/' + aluno.id + '/' + $('#avalId').val(),
                        method: 'GET',
                        success: function (data) {
                            nota.text(data.nota)
                        }
                    })



                    let tr = $('<tr>')
                        .append(nome)
                        .append(nota)


                    $('#tableNotas tbody').append(tr);
                }
            },
            error: function () {
                alert('Não foi possível carregar as turmas!')
            }
        }

        )
    }
    $("#formNota").submit(function (event) {

        event.preventDefault();

        let alunoId = $('#selectAluno').val();
        let avalId = $('#avalId').val();
        let nota = $('#nota').val();
        let recado = $('#recado').val();

        let notaObj = {
            alunoId: alunoId,
            avalId: avalId,
            nota: nota,
            recado: recado
        };
        if (alunoId == "") {
            alert("Você precisa selecionar um aluno!")
        } else {
            adicionarUser(notaObj);
        }


    })
    function adicionarUser(user) {
        var formValues = JSON.stringify(user);

        $.ajax({
            url: 'http://localhost:8080/data/adicionarNota',
            method: 'POST',
            contentType: 'application/json',
            data: formValues
        })
            .done(function () {
                alert("User registrado com sucesso!")
                $("#nota").val("");
                $("#recado").val("");

                carregarTabela();
                carregarAlunos();
            })
            .fail(function () {

                alert("Falha ao enviar os dados!")
            });
    }
})