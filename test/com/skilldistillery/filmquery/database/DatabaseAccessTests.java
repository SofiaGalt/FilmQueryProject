package com.skilldistillery.filmquery.database;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.skilldistillery.filmquery.entities.Film;

class DatabaseAccessTests {
  private DatabaseAccessor db;

  @BeforeEach
  void setUp() throws Exception {
    
  }

  @AfterEach
  void tearDown() throws Exception {
    
  }

  @Test
  void test_getFilmById_with_invalid_id_returns_null() {
    Film f = DatabaseAccessorObject.access.findFilmById(-42);
    assertNull(f);
  }

}
