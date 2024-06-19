/**
 * @param { import("knex").Knex } knex
 * @returns { Promise<void> }
 */
exports.up = function(knex) {
    return knex.schema.createTable('usuario', function(table) {
        table.increments('id').primary();
        table.string('usuario').notNullable();
        table.string('senha').notNullable();
        table.string('tipo_user').notNullable();
        table.timestamp('data_hora_criacao').defaultTo(knex.fn.now());
    })
};

/**
 * @param { import("knex").Knex } knex
 * @returns { Promise<void> }
 */
exports.down = function(knex) {

};
