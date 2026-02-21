package com.example.demo_helloworld;

import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    // Clase interna para representar un cliente
    static class Cliente {
        private Long id;
        private String nombre;
        private String email;

        // Constructores
        public Cliente() {}
        public Cliente(Long id, String nombre, String email) {
            this.id = id;
            this.nombre = nombre;
            this.email = email;
        }

        // Getters y Setters
        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }

        public String getNombre() { return nombre; }
        public void setNombre(String nombre) { this.nombre = nombre; }

        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
    }

    // Almacenamiento en memoria
    private Map<Long, Cliente> clientes = new HashMap<>();
    private Long nextId = 1L;

    // CREATE
    @PostMapping
    public Cliente crearCliente(@RequestBody Cliente cliente) {
        cliente.setId(nextId++);
        clientes.put(cliente.getId(), cliente);
        return cliente;
    }

    // READ - todos
    @GetMapping
    public Collection<Cliente> listarClientes() {
        return clientes.values();
    }

    // READ - uno
    @GetMapping("/{id}")
    public Cliente obtenerCliente(@PathVariable Long id) {
        return clientes.get(id);
    }

    // UPDATE
    @PutMapping("/{id}")
    public Cliente actualizarCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
        if (clientes.containsKey(id)) {
            cliente.setId(id);
            clientes.put(id, cliente);
            return cliente;
        }
        return null; // o lanzar excepción
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String eliminarCliente(@PathVariable Long id) {
        clientes.remove(id);
        return "Cliente con id " + id + " eliminado";
    }
}
