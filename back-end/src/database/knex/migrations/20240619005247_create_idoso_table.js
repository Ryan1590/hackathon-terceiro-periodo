/**
 * @param { import("knex").Knex } knex
 * @returns { Promise<void> }
 */
exports.up = function(knex) {
    return knex.schema.createTable('idoso', function(table) {
        table.increments('id').primary();
        table.string('nome').notNullable();
        table.date('data_nascimento').notNullable();
        table.string('cpf', 11).notNullable();
        table.string('logradouro').notNullable();
        table.integer('numero_logradouro').notNullable();
        table.string('bairro').notNullable();
        table.string('cep').notNullable();
        table.string('cidade').notNullable();
        table.string('estado').notNullable();
    })
};

/**
 * @param { import("knex").Knex } knex
 * @returns { Promise<void> }
 */
exports.down = function(knex) {

};
