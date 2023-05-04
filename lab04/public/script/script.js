let camposPersonalizados = [];

function adicionarCampo() {
    const campoPersonalizado = document.getElementById("campos_customizados").value;
    camposPersonalizados.push(campoPersonalizado);
    const listaCampos = document.getElementById("campos_personalizados");
    const itemLista = document.createElement("li");
    itemLista.innerText = campoPersonalizado;
    listaCampos.appendChild(itemLista);
}