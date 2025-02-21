package com.aytronn.example.repository;

import com.aytronn.example.dao.Test;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class TestRepository {

  private final JdbcTemplate jdbcTemplate;

  public TestRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public List<Test> findAll() {
    String query = "SELECT * FROM test";

    return jdbcTemplate.query(query, new TestRowMapper());
  }

  public Optional<Test> findById(UUID id) {
    String query = "SELECT * FROM test WHERE id = ?";

    List<Test> result = jdbcTemplate.query(query, new TestRowMapper(), id);

    if (result.isEmpty()) {
      return Optional.empty();
    }

    return Optional.of(result.getFirst());
  }

  public void update(Test test) {
    String query = "UPDATE test SET name = ?, description = ?, age = ? WHERE id = ?";
    jdbcTemplate.update(query, test.getName(), test.getDescription(), test.getAge(), test.getId());
  }
  
  public void delete(UUID id) {
    String query = "DELETE FROM test WHERE id = ?";
    jdbcTemplate.update(query, id);
  }
  
  public void save(Test test) {
    String query = "INSERT INTO test (id, name, description, age) VALUES (?, ?, ?, ?)";
    jdbcTemplate.update(query, UUID.randomUUID(), test.getName(), test.getDescription(), test.getAge());
  }
  
  private static class TestRowMapper implements RowMapper<Test> {
    @Override
    public Test mapRow(ResultSet rs, int rowNum) throws SQLException {
      return Test.builder()
          .id(UUID.fromString(rs.getString("id")))
          .name(rs.getString("name"))
          .description(rs.getString("description"))
          .age(rs.getInt("age"))
          .build();
    }
  }
}
