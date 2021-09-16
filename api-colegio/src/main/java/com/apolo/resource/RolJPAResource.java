package com.apolo.resource;

import com.apolo.model.Rol;
import com.apolo.model.Usuario;
import com.apolo.repository.RolRepository;
import com.apolo.spring.exception.ObjetoNoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class RolJPAResource {


    private final RolRepository rolRepository;

    @Autowired
    public RolJPAResource(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

    //todos los roles
    @GetMapping("/roles")
    public List<Rol> retrieveAllRoles() {
        return rolRepository.findAll();
    }

    //obtener rol
    //roles/id
    @GetMapping("/roles/{id}")
    public EntityModel<Rol> getRol(@PathVariable int id) {
        Optional<Rol> rol = rolRepository.findById(id);

        if (!rol.isPresent())
            throw new ObjetoNoEncontradoException("id-" + id);

        return EntityModel.of(rol.get());
    }

    //eliminar rol
    //roles/id
    @DeleteMapping("/roles/{id}")
    public void deleteRol(@PathVariable int id) {
        rolRepository.deleteById(id);
    }

    @PostMapping("/roles/")
    public ResponseEntity<Rol> creaOEditarRol(@Valid @RequestBody Rol rol) {
        Integer idRol = rol.getId();

        if (idRol != null) {
            Optional<Rol> rolExistente = rolRepository.findById(idRol);
            if (!rolExistente.isPresent())
                throw new ObjetoNoEncontradoException("id-" + idRol);
        }

        Rol nuevoRol = rolRepository.save(rol);

        /* Responde en el header el lugar donde se puede encontrar la información
         * del rol creado
         */
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(
                        nuevoRol.getId()).toUri();

        return ResponseEntity.created(location).build();

    }
}
