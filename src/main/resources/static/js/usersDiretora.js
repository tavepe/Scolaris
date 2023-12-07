$(document).ready(function () {


    carregarUsers();
    function carregarUsers() {
        $.ajax({
            url: 'http://localhost:8080/data/listarUsers',
            method: 'GET',
            success: function (data) {
                $('#tabelaUsers tbody').empty();

                for (let i = 0; i < data.length; i++) {
                    let user = data[i];

                    let id = $('<td>')
                        .text(user.id);

                    let nome = $('<td>')
                        .text(user.nome);
                    let usuario = $('<td>')
                        .text(user.usuario);

                    let tipo = $('<td>')
                        .text(user.tipo);

                    let imgDeleta = $('<img>').attr('src', '../images/trash.png')
                    let imgEdita = $('<img>').attr('src', '../images/edit.png')

                    let botaoApagar = $('<a>');
                    botaoApagar.attr('class', 'botaoApagar')
                    botaoApagar.append(imgDeleta);
                    botaoApagar.click(function () {
                        modalApagar($(this).parent().parent().attr('data-id'))
                    })
                    let botaoEditar = $('<a>').attr('href', '/editarUser/' + (user.id));
                    botaoEditar.attr('class', 'botaoEditar')
                    botaoEditar.append(imgEdita)
                    let colunaBotoes = $('<td>').append(botaoApagar).append(botaoEditar);



                    let tr = $('<tr>')
                        .attr('data-id', user.id)
                        .append(id)
                        .append(nome)
                        .append(tipo)
                        .append(usuario)
                        .append(colunaBotoes)


                    $('#tabelaUsers tbody').append(tr);
                }
            },
            error: function () {
                alert('Não foi possível carregar os usuários!')
            }
        }

        )
    }

    function modalApagar(id) {
        if (id == 1) {
            alert("Atenção, você não pode excluir o diretor!")
        } else {

            $('#modalBody').text('Tem certeza que deseja deletar a o usuário de id ' + id + '?')
            $("#botaoDeletarModal").click(function () {
                apagarUser(id);
            })
            $('#myModal').modal('show')

        }
    }
    function apagarUser(id) {
        $.ajax({
            url: 'http://localhost:8080/data/deletarUser/' + id,
            method: 'DELETE',
            success: function () {
                carregarUsers()
                $('#myModal').modal('hide')
            },
            error: function () {

            }

        })
    }

})