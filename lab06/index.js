const { json } = require('express');
const express = require('express');
const { addCategoria, getCategoria } = require('./repository/BDCategorias');
const { getProdutos, addProdutos } = require('./repository/BDProdutos');
const categoriaR = (require('./repository/BDCategorias'));
const produtoR = (require('./repository/BDProdutos'));
const mongoDb = (require('./repository/mongoClient'));

const app = express();
const port = 3000;

app.set('view engine', 'ejs');
app.set('views', './views');

app.use(express.static("public"));
app.use(express.json());
app.use(express.urlencoded({
  extended: true
}));

const basicAuth = require('express-basic-auth')
app.use(basicAuth({
    users: { 'admin': '12345' },
    challenge: true
}));

function myAsyncAuthorizer(username, password, cb) {
  if (username.startsWith('A') & password.startsWith('secret'))
      return cb(null, true)
  else
      return cb(null, false)
}



app.get('/categorias', (req, res) => {
  res.render('categorias', {categorias: getCategoria()});
});



app.listen(port, () => {
  console.log(`Server listening at http://localhost:${port}`);
});