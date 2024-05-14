package restproyecto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import restproyecto.modelo.dto.SubtipoEspacioDto;
import restproyecto.modelo.dto.TipoSubtipoDto;
import restproyecto.modelo.entities.Subtipo;
import restproyecto.modelo.repository.SubtipoRepository;

@Service
public class SubtipoServiceImplMy8Jpa implements SubtipoService {

	
	@Autowired
	private SubtipoRepository srepo;
	@Override
	public List<Subtipo> buscarSubtipo() {
		// TODO Auto-generated method stub
		return srepo.findAll();
	}
	@Override
	public List<SubtipoEspacioDto> obtenerSubtipoPorEspacio(int idEspacio) {
		// TODO Auto-generated method stub
		return srepo.findSubtipoByIdEspacio(idEspacio);
	}
	@Override
	public Subtipo buscarPorId(int idSubtipo) {
		return srepo.findById(idSubtipo).orElse(null);
	}



}
