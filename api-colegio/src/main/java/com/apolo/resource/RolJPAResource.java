package com.apolo.resource;

import com.apolo.model.DeleteResponse;
import com.apolo.model.Rol;
import com.apolo.model.Usuario;
import com.apolo.repository.RolRepository;
import com.apolo.spring.exception.ErrorGeneralExcepcion;
import com.apolo.spring.exception.ObjetoNoEncontradoException;
import com.apolo.spring.model.AuthenticationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE})
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
    public ResponseEntity<?> deleteRol(@PathVariable int id) {

        EntityModel<?> resource = EntityModel.of(new DeleteResponse(id));

        rolRepository.deleteById(id);

        return ResponseEntity.ok(resource);
    }

    @PostMapping("/roles")
    public EntityModel<Rol> creaOEditarRol(@Valid @RequestBody Rol rol) {
        Integer idRol = rol.getId();

        if (idRol != null) {
            Optional<Rol> rolExistente = rolRepository.findById(idRol);
            if (!rolExistente.isPresent())
                throw new ObjetoNoEncontradoException("No existe un rol con el id " + idRol);
        }else{

            String credencial = rol.getCredencial();

            Optional<Rol>  rolExistente  = rolRepository.findByCredencial(credencial);

            if (rolExistente.isPresent())
                throw new ErrorGeneralExcepcion("No puedes crear otro rol con la credencial " + credencial);
        }

        Rol nuevoRol = rolRepository.save(rol);

        return EntityModel.of(nuevoRol);

    }
}
