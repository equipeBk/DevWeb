const { json } = require('express');
const express = require('express');
const { addCategoria, getCategoria} = require('./repository/BDCategorias');
const { getProdutos, addProdutos } = require('./repository/BDProdutos');

const categoriaR = (require('./repository/BDCategorias'));
const produtoR = (require('./repository/BDProdutos'));

const app = express();
const port = 3000;

app.set('view engine', 'ejs');
app.set('views', './views');

app.use(express.static("public"));
app.use(express.json());
app.use(express.urlencoded({
  extended: true
}));

let produtoId = 1;
let camposPersonalizados = [];
app.get('/categorias', (req, res) => {

  res.render('categorias', {categorias: getCategoria()});
});



app.get('/produtos', (req, res) => {
  res.render('produtos', {produtos: getProdutos()});
});

app.get('/categoria-deletar', (req, res) => {
  const chave = req.query.chave;
  categoriaR.deleteCategoria(chave);
  res.redirect('/categorias');
});

app.post('/categoria-salvar', (req, res) => {
  const chave = req.body.chave;
  const valor = req.body.valor;
  const camposCustomizados = camposPersonalizados;
  const categoria = new Categoria({
    chave: chave,
    valor: valor,
    campos_customizados: camposCustomizados
  });
  addCategoria(categoria);
  res.redirect('/categorias');
});


  //produtos

app.get('/cadastrarProduto', (req, res) => {
  res.render('cadastrarProduto', {
      categorias: getCategoria(),
  });
});


app.post('/cadastrar-produto', (req, res) => {
  const newProduto = {
    id : produtoId,
    nome : req.body.nome,
    preco: req.body.preco,
    categoria : req.body.categoria,
    descricao : req.body.descricao
  };
  produtoId++;
  addProdutos(newProduto);
  res.redirect('/produtos');
});

app.get('/produto-editar', (req, res) => {
  const produtoId = parseInt(req.query.id);
  const produto = produtoR.getProdutoId(produtoId);
  res.render('editarProduto', { produto: produto, categorias: categoriaR.getCategoria() });
});

app.post('/produto-editar', (req, res) => {
  const editedProduto = {
    id: parseInt(req.body.id),
    nome: req.body.nome,
    preco: req.body.preco,
    categoria: req.body.categoria,
    descricao: req.body.descricao
  };
  produtoR.editarProduto(editedProduto);
  res.redirect('/produtos');
});

app.listen(port, () => {
  console.log(`Server listening at http://localhost:${port}`);
});
