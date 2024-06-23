/**
 * @param { import("knex").Knex } knex
 * @returns { Promise<void> }
 */
exports.up = function(knex) {
    return knex.schema.createTable('alertas', function(table) {
        table.increments('id').primary();
        table.integer('agendamento_id').unsigned();
        table.string('mensagem').notNullable();
        table.string('tipo').notNullable();

        table.foreign('agendamento_id').references('id').inTable('agendamento').onDelete('CASCADE').onUpdate('CASCADE');
    });
};

/**
 * @param { import("knex").Knex } knex
 * @returns { Promise<void> }
 */
exports.down = function(knex) {

};
