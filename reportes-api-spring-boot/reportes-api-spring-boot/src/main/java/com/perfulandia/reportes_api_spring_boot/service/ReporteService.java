package com.perfulandia.reportes_api_spring_boot.controller;

import com.perfulandia.reportes_api_spring_boot.model.Reporte;
import com.perfulandia.reportes_api_spring_boot.repository.ReporteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReporteService {

    @Autowired
    private ReporteRepository reporteRepository;

    public List<Reporte> obtenerTodos() {
        return reporteRepository.findAll();
    }

    public Optional<Reporte> obtenerPorId(Long id) {
        return reporteRepository.findById(id);
    }

    public Reporte guardar(Reporte reporte) {
        return reporteRepository.save(reporte);
    }

    public Reporte actualizar(Long id, Reporte reporteActualizado) {
        return reporteRepository.findById(id).map(reporte -> {
            reporte.setTitulo(reporteActualizado.getTitulo());
            reporte.setDescripcion(reporteActualizado.getDescripcion());
            reporte.setFecha(reporteActualizado.getFecha());
            return reporteRepository.save(reporte);
        }).orElse(null);
    }

    public void eliminar(Long id) {
        reporteRepository.deleteById(id);
    }
}
