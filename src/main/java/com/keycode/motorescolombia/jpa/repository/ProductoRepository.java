package com.keycode.motorescolombia.jpa.repository;

import com.keycode.motorescolombia.jpa.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
}