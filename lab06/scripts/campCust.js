const listaCampos = document.getElementById("lista-campos");
const campoNovo = document.getElementById("campo-novo");
const btnAddCampo = document.getElementById("btn-add-campo");

btnAddCampo.addEventListener("click", () => {
  const novoCampo = campoNovo.value.trim();
  if (novoCampo) {
    const li = document.createElement("li");
    li.textContent = novoCampo;
    listaCampos.appendChild(li);
    campoNovo.value = "";
  }
});