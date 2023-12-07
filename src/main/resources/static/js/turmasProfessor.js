$(document).ready(function () {
    carregarTurmas();
    function carregarTurmas() {
        let ide=$('#professor').val()
        $.ajax({
            url: 'http://localhost:8080/data/getTurmasProfessor/'+ide,
            method: 'GET',
            success: function (data) {
                $('#tabelaTurmas tbody').empty();

                for (let i = 0; i < data.length; i++) {
                    let turma = data[i];

                    let id = $('<td>')
                        .text(turma.id);

                    let titulo = $('<td>')
                        .text(turma.titulo);
                    let descricao = $('<td>')
                        .text(turma.descricao);

                    let imgEdita = $('<img>').attr('src', '../images/edit.png')
                    
                    let botaoEditar = $('<a>').attr('href', '/avalsProfessor/' +ide+'/'+(turma.id));
                    botaoEditar.attr('class', 'botaoEditar')
                    botaoEditar.append(imgEdita)
                    let colunaBotoes = $('<td>').append(botaoEditar);



                    let tr = $('<tr>')
                        .attr('data-id', turma.id)
                        .append(id)
                        .append(titulo)
                        .append(descricao)
                        .append(colunaBotoes)


                    $('#tabelaTurmas tbody').append(tr);
                }
            },
            error: function () {
                alert('Não foi possível carregar as turmas!')
            }
        }

        )
    }

}
)


