package br.com.scotti.krustykrab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.scotti.krustykrab.entities.Menu;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {}
