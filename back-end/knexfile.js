const path = require('path');

const BASE_PATH = path.join(__dirname, 'src', 'database', 'knex');

module.exports = {
  development: {
    client: 'mysql',
    connection: {
      host: 'localhost',
      port: 3306,
      user: 'root',
      password: '',
      database: 'hackathon_vacina'
    },
    pool: {
      min: 2,
      max: 10
    },
    migrations: {
      directory: path.join(BASE_PATH, 'migrations')
    },
    useNullAsDefault: true
  },    
  test: {
    client: 'mysql',
    connection: {
      host: 'localhost',
      port: 3306,
      user: 'root',
      password: '',
      database: 'hackathon_vacina_test'
    },
    pool: {
      min: 2,
      max: 10
    },
    migrations: {
      directory: path.join(BASE_PATH, 'migrations')
    },
    useNullAsDefault: true
  }
};
