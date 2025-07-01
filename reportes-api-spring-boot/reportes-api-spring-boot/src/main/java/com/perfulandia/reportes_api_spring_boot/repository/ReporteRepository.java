package com.perfulandia.reportes_api_spring_boot.controller;

import com.perfulandia.reportes_api_spring_boot.model.Reporte;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReporteRepository extends JpaRepository<Reporte, Long> {
}
