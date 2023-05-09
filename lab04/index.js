const express = require('express')
const basicAuth = require('express-basic-auth')
const app = express();
const port = 3000;

app.set('view engine', 'ejs');
app.set('views', './views');

app.use(express.static("public"));
app.use(express.json());
app.use(express.urlencoded({
  extended: true
}));

const mongoRepository = require('./repository/mongo-repository')

app.use((req, res, next) => {
  console.log('meu middleware')
  next();

})

app.use(basicAuth({
  authorizer: userAuthorizer,
  challenge: true,
  authorizeAsync: true
}));

function userAuthorizer(username, password, callback) {
  console.log('=== UserAuthorizer Middleware');
  console.log('Username: ' + username)
  console.log('Password: ' + password)

  mongoRepository.getUsers(username, password).then(users => {
    console.log(users);
    callback(null, users.length == 0 ? false : true);
  });


}

app.use((req, res, next) => {
  console.log('=== User Session Middleware');
  basic = Buffer.from(req.headers.authorization.split(' ')[1], 'base64').toString().split(':');
  username = basic[0];
  password = basic[1];
  mongoRepository.getUsers(username, password).then(users => {
    loggedUser = users[0];
    console.log(loggedUser);
    req.user = loggedUser;
    next()
  });
})

app.get('/home', (req, res) => {
  console.log('=== GET -/home');
  console.log(req.user);
  res.render('home', {
    user: loggedUser
  })
})

app.get('/prod/new', (req, res) => {
  res.render('prod/new', {
    user: req.user
  })
})

app.post('/prod/new', (req, res) => {
  console.log('POST - /prod/new')
  let newProd = req.body;
  newProd.createdBy = req.user;
  newProd.price = parseFloat(newProd.price)
  console.log(newProd)
  mongoRepository.saveProd(req.body).then((insertedProd) => {
    console.log('Inserted Product')
    console.log(insertedProd)
    res.redirect('/prod/list')
  })


})

app.get('/prod/list', (req, res) => {
  console.log('GET - /prod/list')
  mongoRepository.getProdsByUser(req.user).then((foundProds) => {
    res.render('prod/list', {
      loggedUser: req.user,
      products: foundProds
    })
  })
})



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
  const newCategoria = {
    chave : req.body.chave,
    valor : req.body.valor,
    campos_personalizados : req.body.campos_personalizados
  };
  addCategoria(newCategoria);
  res.redirect('/categorias');
});

  //produtos

app.get('/cadastrarProduto', (req, res) => {
  res.render('cadastrarProduto', {categorias: getCategoria()});
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

app.get('/campos', (req, res) => {
  const categorias = getCategoria();
  res.json(categorias);
});

app.listen(port, () => {
  console.log(`Server listening at http://localhost:${port}`);
});