package io.github.gfumagali.domain.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import io.github.gfumagali.domain.entity.Cliente;

@Repository
public class Clientes {

    private static String INSERT = "insert into cliente (nome) values (?)";
    private static String SELECT_ALL = "SELECT * FROM CLIENTE";
    private static String UPDATE = "UPDATE cliente SET nome = ? WHERE id = ?;";
    private static String DELETE = "DELETE FROM cliente WHERE id = ?;";


    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Cliente save(Cliente cliente) {
        jdbcTemplate.update(INSERT, new Object[]{cliente.getNome()});
        return cliente;
    }

    public Cliente update(Cliente cliente){
        jdbcTemplate.update(UPDATE, new Object[]{cliente.getNome(), cliente.getId()});
        return cliente;
    }

    public void delete(Cliente cliente){
        this.delete(cliente.getId());
    }

    public void delete(Integer id){
        jdbcTemplate.update(DELETE, new Object[]{id});
    }

    public List<Cliente> getByName(String nome){
        return jdbcTemplate.query(SELECT_ALL.concat(" where nome like ?"), getClienteRowMapper(), "%" + nome + "%");
    }

    public List<Cliente> getAll(){
        return jdbcTemplate.query(SELECT_ALL, getClienteRowMapper());
    }

    private RowMapper<Cliente> getClienteRowMapper(){
        return new RowMapper<Cliente>() {
            @Override
            public Cliente mapRow(ResultSet resultSet, int i) throws SQLException {
                Integer id = resultSet.getInt("id");
                String nome = resultSet.getString("nome");

                return new Cliente(id, nome);
            }
        };
    }
}
