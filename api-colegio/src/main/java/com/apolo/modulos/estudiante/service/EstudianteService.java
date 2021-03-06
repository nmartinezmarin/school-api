package com.apolo.modulos.estudiante.service;

import com.apolo.modulos.estudiante.dao.FiltroEstudianteRequest;
import com.apolo.modulos.estudiante.model.Estudiante;
import com.apolo.modulos.estudiante.model.Estudiante_;
import com.apolo.modulos.estudiante.repository.EstudianteRepository;
import com.apolo.spring.database.GenericSpecification;
import com.apolo.spring.database.SearchCriteria;
import com.apolo.spring.database.SearchOperation;
import com.apolo.spring.exception.ObjetoNoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EstudianteService implements IEstudianteService {

    private final EstudianteRepository estudianteRepository;

    @Autowired
    public EstudianteService(EstudianteRepository estudianteRepository) {
        this.estudianteRepository = estudianteRepository;
    }


    @Override
    public List<Estudiante> obtenerEstudiantesPorFiltro(FiltroEstudianteRequest filtro) {
        GenericSpecification<Estudiante> genericSpecification = new GenericSpecification<>();

        if(filtro.getPrimerNombre() != null)
            genericSpecification.add(new SearchCriteria(Estudiante_.PRIMER_NOMBRE, filtro.getPrimerNombre(),
                    SearchOperation.EQUAL));

        if(filtro.getPrimerApellido() != null)
            genericSpecification.add(new SearchCriteria(Estudiante_.PRIMER_APELLIDO, filtro.getPrimerApellido(),
                    SearchOperation.EQUAL));

        if(filtro.getTipoDocumento() != null && filtro.getDocumento() != null){
            genericSpecification.add(new SearchCriteria(Estudiante_.DOCUMENTO, filtro.getDocumento(),
                    SearchOperation.EQUAL));

        }
        return estudianteRepository.findAll(genericSpecification);
    }

    @Override
    public Optional<Estudiante> findById(Long id) {

        Optional<Estudiante> estudiante = estudianteRepository.findById(id);
        if (!estudiante.isPresent())
            throw new ObjetoNoEncontradoException("No se existe un estudiante el con id: " + id);

        return estudiante;
    }

    @Override
    public Optional<Estudiante> crearOEditarEstudiante(Estudiante estudiante) {
        return Optional.empty();
    }

    @Override
    public void deleteById(Long id) {
        estudianteRepository.deleteById(id);
    }
}
