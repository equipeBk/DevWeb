const { MongoClient } = require('mongodb');

// Connection URL
const url = 'mongodb://root:rootpwd@localhost:27017';
const client = new MongoClient(url);

// Database Name
const dbName = 'aluguelCarros';

var user_collection;
var carros_collection;
var admin_collection;
var aluguel_collection;

async function main() {
  // Use connect method to connect to the server
  await client.connect();
  console.log('Connected successfully to Mongo server');
  const db = client.db(dbName);
  user_collection = db.collection('user');
  carros_collection = db.collection('carros');
  admin_collection =db.collection('admin');
  aluguel_collection =db.collection('aluguel');
  // the following code examples can be pasted here...
   
  return 'done.';
}

main()
  .then(console.log)
  .catch(console.error);
//   .finally(() => client.close());


///pegar o user
async function getUsers(email, password) {
    const findResult = await user_collection.find({email: email, password: password}).toArray();
    console.log('Repository - getUsers - Found documents =>', findResult);
    return findResult;
}


///salvar os carros cadastrados
async function saveCarros(carros){
  const result = await carros_collection.insertOne(carros)
  console.log('Repository - saveCarro - Inserted carro')
  console.log(result)
  return result;
}

async function isEmailAlreadyRegistered(email) {
  try {
    const user = await user_collection.findOne({ email });
    return user;
  } catch (err) {
    console.error(err);
    return false;
  }
}


///salvar o usuario
async function saveUser(user){
  const result = await user_collection.insertOne(user)
  console.log('Repository - saveuser - Inserted user')
  console.log(result)
  return result;
}


////deletar usuario apesar de n precisar disso
async function deleteUser(user) {
  const result = await user_collection.deleteOne(user)
  console.log(`Usuario com value ${deleteUser.name} excluída do banco de dados`)
  return result
}

////deletar carro se disponivel
async function deleteCarros(carros) {
  const result = await carros_collection.deleteOne(carros)
  console.log(`Carro com id ${carros} excluído do banco de dados`)
  return result
}



///alugueis do usuario
async function getAluguelByUser(user) {
  console.log('getAluguelByUser - Username param:', user.username)
  
  const query = { "createdBy.username": user.username };
  const findResult = await aluguel_collection.find(query).toArray();
  console.log('Repository - getAluguelByUser - Found documents =>', findResult);
  return findResult;
}


///todos os carros
async function getAllCarros() {
  const findResult = await carros_collection.find({}).toArray();
  console.log('Repository - getAllCarros - Found documents =>', findResult);
  return findResult;
}


/////todos os alugueis
async function getAllAlugueis() {
  const findResult = await aluguel_collection.find({}).toArray();
  console.log('Repository - getAllAlugueis - Found documents =>', findResult);
  return findResult;
}

async function compareEmails(user) {
  const findResult = await user_collection.find({}).toArray();
  console.log('Repository - getAllAlugueis - Found documents =>', findResult);
  return findResult;
}




exports.deleteUser = deleteUser;
exports.deleteCarros = deleteCarros;

exports.getUsers = getUsers;
exports.getAluguelByUser = getAluguelByUser;
exports.getAllCarros = getAllCarros;
exports.compareEmails = compareEmails;
exports.getAllAlugueis = getAllAlugueis;
exports.isEmailAlreadyRegistered = isEmailAlreadyRegistered;

exports.saveCarros = saveCarros;
exports.saveUser = saveUser;