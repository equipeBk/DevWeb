const { MongoClient } = require('mongodb');

// Connection URL
const url = 'mongodb://root:rootpwd@localhost:27017';
const client = new MongoClient(url);

// Database Name
const dbName = 'lab06';

var user_collection;
var product_collection;
var category_collection;

async function main() {
  // Use connect method to connect to the server
  await client.connect();
  console.log('Connected successfully to Mongo server');
  const db = client.db(dbName);
  user_collection = db.collection('user');
  product_collection = db.collection('product');
  category_collection =db.collection('category');
  // the following code examples can be pasted here...
   
  return 'done.';
}

main()
  .then(console.log)
  .catch(console.error);
//   .finally(() => client.close());

async function getUsers(username, password) {
    const findResult = await user_collection.find({username: username, password: password}).toArray();
    console.log('Repository - getUsers - Found documents =>', findResult);
    return findResult;
}

async function saveProd(product){
  const result = await product_collection.insertOne(product)
  console.log('Repository - saveProd - Inserted prod')
  console.log(result)
  return result;
}

async function saveCategory(category){
  const result = await category_collection.insertOne(category)
  console.log('Repository - saveCategory - Inserted category')
  console.log(result)
  return result;
}

async function deleteCategory(category) {
  const result = await category_collection.deleteOne(category)
  console.log(`Categoria com value ${deleteCategory.value} excluída do banco de dados`)
  return result
}

async function getProdsByUser(user) {
  console.log('getProdsByUser - Username param:', user.username)
  
  const query = { "createdBy.username": user.username };
  const findResult = await product_collection.find(query).toArray();
  console.log('Repository - getProdsByUser - Found documents =>', findResult);
  return findResult;
}

async function getCategorysByUser(user) {
  console.log('getCategorysByUser - Username param:', user.username)
  
  const query = { "createdBy.username": user.username };
  const findResult = await category_collection.find(query).toArray();
  console.log('Repository - getCategorysByUser - Found documents =>', findResult);
  return findResult;
}

async function getAllProds() {
  const findResult = await product_collection.find({}).toArray();
  console.log('Repository - getAllProds - Found documents =>', findResult);
  return findResult;
}

async function getAllCategorys() {
  const findResult = await category_collection.find({}).toArray();
  console.log('Repository - getAllcategorys - Found documents =>', findResult);
  return findResult;
}


exports.getUsers = getUsers;
exports.saveProd = saveProd;
exports.getProdsByUser = getProdsByUser;
exports.saveCategory = saveCategory;
exports.deleteCategory = deleteCategory;
exports.getCategorysByUser = getCategorysByUser;
exports.getAllProds = getAllProds;
exports.getAllCategorys = getAllCategorys;