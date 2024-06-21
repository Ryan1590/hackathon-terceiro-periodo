/**
 * @param { import("knex").Knex } knex
 * @returns { Promise<void> }
 */
exports.up = function(knex) {
    return knex.schema.createTable('agendamento', function(table) {
        table.increments('id').primary();
        table.integer('agenteSaude_id').unsigned();
        table.integer('idoso_id').unsigned().notNullable();
        table.integer('vacina_id').unsigned().notNullable();
        table.dateTime('data_hora_visita').notNullable();
        table.string('status').notNullable();

        table.foreign('agenteSaude_id').references('id').inTable('agente_saude');
        table.foreign('idoso_id').references('id').inTable('idoso');
        table.foreign('vacina_id').references('id').inTable('vacina');
    });
};

/**
 * @param { import("knex").Knex } knex
 * @returns { Promise<void> }
 */
exports.down = function(knex) {

};
