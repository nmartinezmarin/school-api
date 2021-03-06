package com.apolo.modulos.acudiente.service;

import com.apolo.modulos.acudiente.dao.FiltroAcudienteRequest;
import com.apolo.modulos.acudiente.model.Acudiente;
import com.apolo.modulos.estudiante.model.Estudiante;
import com.apolo.modulos.acudiente.repository.AcudienteRepository;
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
public class AcudienteService implements IAcudienteService {

    private final AcudienteRepository acudienteRepository;

    private final EstudianteRepository estudianteRepository;

    @Autowired
    public AcudienteService(AcudienteRepository acudienteRepository, EstudianteRepository estudianteRepository) {
        this.acudienteRepository = acudienteRepository;
        this.estudianteRepository = estudianteRepository;
    }



    @Override
    public List<Acudiente> obtenerAcudientesPorFiltro(FiltroAcudienteRequest filtro) {

        GenericSpecification<Acudiente> genericSpecification = new GenericSpecification<>();


        if(filtro.getPrimerNombre() != null)
            genericSpecification.add(new SearchCriteria("primerNombre", filtro.getPrimerNombre(),
                    SearchOperation.EQUAL));

        if(filtro.getPrimerApellido() != null)
            genericSpecification.add(new SearchCriteria("primerApellido", filtro.getPrimerApellido(),
                    SearchOperation.EQUAL));

        if(filtro.getTipoDocumento() != null && filtro.getDocumento() != null){
            genericSpecification.add(new SearchCriteria("documento", filtro.getDocumento(),
                    SearchOperation.EQUAL));

        }

        if(filtro.getNumeroCelular() != null)
            genericSpecification.add(new SearchCriteria("numeroCelular", filtro.getNumeroCelular(),
                    SearchOperation.EQUAL));

        return acudienteRepository.findAll(genericSpecification);
    }

    @Override
    public Acudiente asignarEstudiante(Acudiente acudiente, Estudiante estudiante) {
        Estudiante estudianteNuevo = estudianteRepository.save(estudiante);
        acudiente.getEstudiante().add(estudianteNuevo);
        acudiente.setEstudiante(acudiente.getEstudiante());
        estudianteNuevo.setAcudiente(acudiente);
        return acudiente;
    }



    @Override
    public Optional<Acudiente> findById(Long id) {
        Optional<Acudiente> acudiente = acudienteRepository.findById(id);
        if (!acudiente.isPresent())
            throw new ObjetoNoEncontradoException("No existe un acudiente con el usuario id: " + id);

        return acudiente;
    }


}
