var produtos = []

function addProdutos(produto){
    produtos.push(produto);
}

function getProdutos(){
    return produtos;
}

function getProdutoId(id) {
    return produtos.find(produto => produto.id === id);
}

function editarProduto(produtoEditado) {
    const produtoIndex = produtos.findIndex(produto => produto.id === produtoEditado.id);
    if (produtoIndex === -1) {
      return null; 
    }
    produtos[produtoIndex] = produtoEditado; 
    return produtoEditado;
  }
  
  
exports.editarProduto = editarProduto;
exports.getProdutoId = getProdutoId;
exports.addProdutos = addProdutos;
exports.getProdutos = getProdutos;