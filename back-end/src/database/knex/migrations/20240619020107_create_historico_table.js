/**
 * @param { import("knex").Knex } knex
 * @returns { Promise<void> }
 */
exports.up = function(knex) {
    return knex.schema.createTable('historico_medico', function(table) {
        table.increments('id').primary();
        table.integer('idoso_id').unsigned().notNullable();
        table.string('alergias').notNullable();
        table.string('condicoes_medicas').notNullable();
        table.string('outras_observacoes').notNullable();

        table.foreign('idoso_id').references('id').inTable('idoso').onDelete('CASCADE').onUpdate('CASCADE');
    })
};

/**
 * @param { import("knex").Knex } knex
 * @returns { Promise<void> }
 */
exports.down = function(knex) {

};
