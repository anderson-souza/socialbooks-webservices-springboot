package com.algaworks.socialbooks.repository;

import com.algaworks.socialbooks.domain.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutoresRepository extends JpaRepository<Autor, Long> {

}
