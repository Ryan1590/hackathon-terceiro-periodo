/**
 * @param { import("knex").Knex } knex
 * @returns { Promise<void> }
 */
exports.up = function(knex) {
    return knex.schema.createTable('agente_saude', function(table) {
        table.increments('id').primary();
        table.string('nome').notNullable();
        table.string('cpf', 11).notNullable();
        table.date('data_nascimento').notNullable();
    })
};

/**
 * @param { import("knex").Knex } knex
 * @returns { Promise<void> }
 */
exports.down = function(knex) {

};
