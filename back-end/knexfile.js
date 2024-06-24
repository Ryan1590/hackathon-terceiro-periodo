const path = require('path');

module.exports = {
  development: {
    client: 'mysql',
    connection: {
      host: 'localhost',
      port: 3306, // Alterado para número
      user: 'root',
      password: '',
      database: 'hackathon_vacina'
    },
    pool: {
      min: 2,
      max: 10
    },
    migrations: {
      directory: path.resolve(__dirname, 'src', 'database', 'knex', 'migrations')
    },
    useNullAsDefault: true
  },
  test: {
    client: 'mysql',
    connection: {
      host: 'localhost',
      port: 3306, // Alterado para número
      user: 'root',
      password: '',
      database: 'hackathon_vacina_test'  // Use um banco de dados separado para testes
    },
    pool: {
      min: 2,
      max: 10
    },
    migrations: {
      
      directory: path.resolve(__dirname, 'src', 'database', 'knex', 'migrations')
    },
    useNullAsDefault: true
  }
};
