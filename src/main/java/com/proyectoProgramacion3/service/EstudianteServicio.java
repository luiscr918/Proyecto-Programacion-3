package com.proyectoProgramacion3.service;

import com.proyectoProgramacion3.entity.Curso;
import com.proyectoProgramacion3.entity.Docente;
import com.proyectoProgramacion3.entity.Estudiante;
import com.proyectoProgramacion3.repository.EstudianteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstudianteServicio {
    @Autowired
    private EstudianteRepository estudianteRepository; //instancio mi repositorio
    @Autowired
    private PasswordEncoder passwordEncoder;


    //Listar los estudiantes
    public List<Estudiante> mostrarEstudiantes(){
        return estudianteRepository.findAll();
    }
    //Mostrar por cedula
    public List<Estudiante> buscarPorCedula(String buscarEstudiante){
        if (buscarEstudiante==null||buscarEstudiante.isEmpty()){
            return estudianteRepository.findAll();
        }else{
            return estudianteRepository.findByCedulaContainingIgnoreCase(buscarEstudiante);
        }
    }
    //Buscar por id
    public Optional<Estudiante> buscarEstudianteId(Long id){
        return estudianteRepository.findById(id);
    }
    //Guardar estudiante
    public Estudiante guardarEstudiante(Estudiante estudiante){
        //encripto
        String passwordEncriptado=passwordEncoder.encode(estudiante.getPassword());
        //añado el encriptado al objeto
        estudiante.setPassword(passwordEncriptado);
        return estudianteRepository.save(estudiante);
    }
    //Eliminar Libro
    public void eliminarEstudiante(Long id){
         estudianteRepository.deleteById(id);
    }
    //Listar estudiantes sin curso
    public List<Estudiante> mostrarEstudiantesSinCurso(){
        return estudianteRepository.findByCursoIsNull();
    }
    //Obtener numero total de estudiantes
    public int obtenerNumeroEstudiantes(){
        return estudianteRepository.findAll().size();
    }
    //Obtener estudiante con sus materias
    @Transactional
    public Estudiante ObtenerEstudianteConMaterias(Long id){
        Estudiante estudiante=estudianteRepository.findById(id).orElseThrow();
        return estudiante;
    }
    //Obtener estudiante con sus registro de entrega
    @Transactional
    public Estudiante ObtenerEstudianteConRegistro(Long id){
        Estudiante estudiante=estudianteRepository.findById(id).orElseThrow();
        return estudiante;
    }
    //OBTNER ID DEL EMIAL
    public Optional<Estudiante> obtenerEstudaintePorEmail(String email) {
        return estudianteRepository.findByEmail(email);
    }
    public void actualizarCursoEstudiante(Estudiante estudiante, Curso curso) {
        // Asegurar que la contraseña y otros datos importantes no se toquen
        estudiante.setCurso(curso);
        estudianteRepository.save(estudiante);
}


}
