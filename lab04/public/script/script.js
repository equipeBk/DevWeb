const camposPersonalizados = [];

function adicionarCampo() {
    const campoPersonalizado = document.getElementById("campos_customizados").value;
    camposPersonalizados.push(campoPersonalizado);
    const listaCampos = document.getElementById("campos_personalizados");
    const itemLista = document.createElement("li");
    itemLista.innerText = campoPersonalizado;
    listaCampos.appendChild(itemLista);
}

function enviarFormulario() {
  
    // Adiciona os campos personalizados à array
    const camposPersonalizadosNodes = document.querySelectorAll("#campos_personalizados li");
    camposPersonalizadosNodes.forEach(node => {
      camposPersonalizados.push(node.textContent);
    });
  
    // Faz a requisição POST com a array no corpo da requisição
    fetch("/categoria-salvar", {
      method: "POST",
      body: JSON.stringify({ campos_personalizados: camposPersonalizados }),
      headers: {
        "Content-Type": "application/json"
      }
    }).then(response => {
      // Lida com a resposta da requisição
      if (response.ok) {
        console.log("Requisição enviada com sucesso!");
      } else {
        console.error("Erro na requisição:", response.status, response.statusText);
      }
    }).catch(error => {
      console.error("Erro na requisição:", error);
    });
  }
  