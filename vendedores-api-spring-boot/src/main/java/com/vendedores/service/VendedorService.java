package com.vendedores.service;

import com.vendedores.model.Vendedor;
import com.vendedores.repository.VendedorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VendedorService {

    private final VendedorRepository vendedorRepository;

    public Vendedor registrarVendedor(Vendedor vendedor) {
        if (vendedorRepository.existsByRut(vendedor.getRut())) {
            throw new RuntimeException("El RUT ya está registrado");
        }
        return vendedorRepository.save(vendedor);
    }

    public List<Vendedor> obtenerTodos() {
        return vendedorRepository.findAll();
    }

    public Optional<Vendedor> buscarPorId(Long id) {
        return vendedorRepository.findById(id);
    }

    public void eliminarPorId(Long id) {
        if (!vendedorRepository.existsById(id)) {
            throw new RuntimeException("El vendedor no existe");
        }
        vendedorRepository.deleteById(id);
    }
}
