const express = require('express')
const basicAuth = require('express-basic-auth')
const nodemailer = require('nodemailer');
const router = express.Router();
module.exports = router;
const app = express()
const port = 3000

app.set('view engine', 'ejs');
app.set('views', './views');

app.use(express.static('public'));
app.use(express.json())
app.use(express.urlencoded({
  extended: true
}))


const mongoRepository = require('./repository/mongo-repository')

///raiz com lista dos carros
app.get('/', (req, res) => {
  console.log('GET - index')
  mongoRepository.getAllCarros().then((foundCarros) => {
    res.render('index', {
      carros: foundCarros
    })
  })
})

///pagina de criar conta
app.get('/user/signin', function (req, res) {
  message = req.body.message
  res.render('user/signin.ejs');
});

app.post('/user/signin', async (req, res) => {
  try {
    let email = req.body.email
    const isEmailRegistered = await mongoRepository.isEmailAlreadyRegistered(email);
    if (isEmailRegistered) {
      console.log(email)
      res.render('user/signin.ejs', {
        message: 'Esse email já está em uso'
      });
    } else {
      mongoRepository.saveUser(req.body).then((insertedUser) => {
        console.log('Inserted User')
        console.log(insertedUser)
        console.log(email)
        ////enviar email de confirmação
        const transporter = nodemailer.createTransport({
          service: 'gmail',
          auth: {
            /*fala cmg q eu mando a senha*/
          }
        });
        const mailOptions = {
          from: 'testeEstudosFaculdade@gmail.com',
          to: req.body.email,
          subject: 'Confirmação de cadastro',
          text: `Olá ${req.body.name}, seu cadastro foi confirmado. Obrigado!`
        };
        transporter.sendMail(mailOptions, function (error, info) {
          if (error) {
            console.error(error);
          } else {
            console.log('Email enviado: ' + info.response);
          }
        });
        res.redirect('/')
      })
    }
  } catch (err) {
    console.error(err);
    res.sendStatus(500);
  }

});

app.get('/user/signup', function (req, res) {
  res.render('user/signup.ejs');
  console.log(" get user/signup")
});

app.post('/user/signup', function (req, res) {

  res.redirect('/loja/loja.ejs');
  console.log(" post user/signup")

})
app.get('/loja/loja', function (req, res) {
  mongoRepository.getAllCarros().then((foundCarros) => {
    res.render('loja/loja.ejs', {
      carros: foundCarros,
    })
    console.log("get /loja/loja")
  })
});

////adicionar carro
app.get('/admin/add-carro', function (req, res) {
  res.render('admin/add-carro.ejs');
  console.log(" admin/add-carro")
});

app.post('/add-carro', (req, res) => {
  console.log('POST - /admin/add-carro')
  let newCarro = req.body;
  newCarro.createdBy = req.user;
  console.log(newCarro)
  mongoRepository.saveCarros(req.body).then((insertedCarro) => {
    console.log('Inserted Carro')
    console.log(insertedCarro)
    res.redirect('admin/loja')
  })
})

///deletar carro
app.get('/deletar-carro', (req, res) => {
  let deleteCarros = req._id // Obtém o ID do carro a ser excluído dos parâmetros de URL
  mongoRepository.deleteCarros(deleteCarros)
    .then(() => {
      console.log(`Categoria com id ${deleteCarros} excluída com sucesso`)
      res.redirect('admin/loja')
    })
})


///loja admin
app.get('/admin/loja', function (req, res) {
  mongoRepository.getAllCarros().then((foundCarros) => {
    res.render('admin/loja.ejs', {
      carros: foundCarros,
    })
    console.log("get admin/loja")
  })
});

app.use('/user/signup', basicAuth({
  authorizer: myAuthorizerMongo,
  authorizeAsync: true,
  challenge: true
}));

function myAuthorizerMongo(email, password, cb) {
  console.log(database.getUsers(email, password).then(users => {
    return cb(null, users.length > 0);
  }));
}

app.get('/user/signup', (req, res) => {
  basic = Buffer.from(req.headers.authorization.split(' ')[1], 'base64').toString().split(':');
  email = basic[0];
  password = basic[1];
  console.log('email: ' + email);
  console.log('Password: ' + password);
});

///buscar carros
app.post('/busca', (req, res) => {
  const nome = req.body.busca.toLowerCase(); // converte o nome para minúsculo
  const marca = req.body.busca.toLowerCase(); // converte a marca para minúsculo
  
  mongoRepository.getAllCarros()
    .then(carros => {
      const carrosEncontrados = carros.filter(carro => 
        carro.nome.toLowerCase().includes(nome) && 
        carro.marca.toLowerCase().includes(marca)
      );
      
      res.render('loja/loja.ejs', { carros: carrosEncontrados });
    })
    .catch(err => {
      console.error(`Erro ao buscar carros: ${err.message}`);
      res.redirect('loja/loja');
    });
});




/*
    app.get('/list', (req, res) => {
      console.log('GET - list')
      mongoRepository.getAllCarros().then((foundCarros) => {
        res.render('list', {
          carros: foundCarros
        })
      })
    })

    //nova categoria
    app.get('/category/new', (req, res) => {
      res.render('category/new', { user: req.user });

    })

    app.post('/category/new', (req, res) => {
      console.log('POST - /category/new')
      let newCategory = req.body;
      newCategory.createdBy = req.user;
      console.log(newCategory)
      mongoRepository.saveCategory(req.body).then((insertedCategory) => {
        console.log('Inserted Category')
        console.log(insertedCategory)
        res.redirect('/category/list')
      })


    })

    app.use((req, res, next) => {
      console.log('=== Category Middleware');
      mongoRepository.getAllCategorys().then((foundCategorys) => {
        req.category = foundCategorys;
        next();
      })
    })


    app.get('/Produto/Produto-novo', (req, res) => {
      console.log('GET - /Produto/Produto-novo')
      res.render('Produto/Produto-novo', {
        user: req.user,
        category: req.category
      })
    })

    ///novo produto
    app.post('/Produto/Produto-novo', (req, res) => {
      console.log('POST - /Produto/Produto-novo')
      let newProd = req.body;
      newProd.createdBy = req.user;
      newProd.price = parseFloat(newProd.price)
      console.log(newProd)
      mongoRepository.saveProd(req.body).then((insertedProd) => {
        console.log('Inserted Product')
        console.log(insertedProd)
        res.redirect('/list')
      })

    })
    ///os produtos do usuario logado
    app.get('/Produto/Meus-produtos', (req, res) => {
      console.log('GET - list')
      mongoRepository.getProdsByUser(req.user).then((foundProds) => {
        res.render('list', {
          loggedUser: req.user,
          products: foundProds
        })
      })
    })


    ///listando as categorias
    app.get('/category/list', (req, res) => {
      console.log('GET - /category/list')
      mongoRepository.getAllCategorys().then((foundCategorys) => {
        res.render('category/list', {
          loggedUser: req.user,
          category: foundCategorys
        })
      })
    })

    app.get('/categoria-deletar', (req, res) => {
      const deleteCategory = { chave: req.body.chave} // Obtém o ID da categoria a ser excluída do corpo da requisição
      mongoRepository.deleteCategory(deleteCategory)
        .then(() => {
          console.log(`Categoria com Value ${deleteCategory.chave} excluída com sucesso`)
          res.redirect('/category/new')
        })
    })
    */
app.listen(port, () => {
  console.log(`Example app listening on port ${port}`)
})