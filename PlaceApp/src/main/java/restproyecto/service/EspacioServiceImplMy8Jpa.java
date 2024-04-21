package restproyecto.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import restproyecto.modelo.dto.EspacioDto;
import restproyecto.modelo.dto.EspacioUsuarioDto;
import restproyecto.modelo.dto.ExtraDto;
import restproyecto.modelo.entities.Espacio;
import restproyecto.modelo.entities.Extra;
import restproyecto.modelo.entities.Usuario;
import restproyecto.modelo.repository.EspacioRepository;
import restproyecto.modelo.repository.ExtraRepository;
import restproyecto.modelo.repository.ProvinciaRepository;
import restproyecto.modelo.repository.UsuarioRepository;



@Service
public class EspacioServiceImplMy8Jpa implements EspacioService{
	@Autowired
	private EspacioRepository erepo;
	@Autowired
	private UsuarioRepository urepo;

	@Autowired
	private ExtraRepository exrepo;
	
	
	@Override
	public List<Espacio> buscarTodosEspacios() {
		// TODO Auto-generated method stub
		return erepo.findAll();
	}

	  @Override
	    public Espacio darAltaEspacio(EspacioDto espacioDto) {
	        // Verificar que el usuario tenga el perfil de PROPIETARIO
	        Usuario usuario = urepo.findUsuarioByusername(espacioDto.getUsername());
	        if (usuario == null || usuario.getPerfil().getIdPerfil() != 2) {
	            throw new IllegalArgumentException("El usuario no tiene el perfil de propietario para dar de alta un espacio.");
	        }
        // Crear instancia de Espacio
        Espacio espacio = new Espacio();
        espacio.setNombre(espacioDto.getNombreEspacio());
        espacio.setDescripcion(espacioDto.getDescripcion());
        espacio.setCp(espacioDto.getCp());
        espacio.setEnabled(1);

        // Asignar el usuario al espacio
        espacio.setUsuario(usuario);

        // Guardar el espacio en el repositorio
        Espacio espacioGuardado = erepo.save(espacio);

        // Obtener el extra existente de la base de datos
      //  Extra extraExistente = exrepo.findById(0)

        // Asociar el extra al espacio si existe
       // if (extraExistente != null) {
            List<Extra> extras = new ArrayList<>();
         //   extras.add(extraExistente);
          //  espacio.setExtras(extras);
       // }
        // Guardar el espacio actualizado con los extras asociados
        return erepo.save(espacioGuardado);
    }

	
	

	@Override
	public List<Espacio> obtenerEspaciosDeUsuario(String username) {
		// TODO Auto-generated method stub
		return erepo.buscarPorUsuario(username);
	}
}


	

		
	
	


