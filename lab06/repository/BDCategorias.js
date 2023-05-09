var categorias = []

function addCategoria(categoria){
    categorias.push(categoria);
}

function getCategoria(){
    return categorias;
}

function deleteCategoria(chave){
    categorias = categorias.filter(categoria => categoria.chave != chave);
}




exports.addCategoria = addCategoria;
exports.getCategoria = getCategoria;
exports.deleteCategoria = deleteCategoria;