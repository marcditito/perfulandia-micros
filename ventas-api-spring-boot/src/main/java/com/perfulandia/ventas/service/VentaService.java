package com.perfulandia.ventas.service;

import com.perfulandia.ventas.model.Venta;
import com.perfulandia.ventas.repository.VentaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VentaService {

    private final VentaRepository ventaRepository;

    public Venta guardarVenta(Venta venta) {
        return ventaRepository.save(venta);
    }

    public List<Venta> obtenerTodas() {
        return ventaRepository.findAll();
    }

    public Optional<Venta> buscarPorId(Long id) {
        return ventaRepository.findById(id);
    }

    public void eliminarPorId(Long id) {
        ventaRepository.deleteById(id);
    }
}
