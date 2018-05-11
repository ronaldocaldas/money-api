package com.example.money.api.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.money.api.model.Categoria;
import com.example.money.api.repository.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Categoria atualizar(Long codigo, Categoria categoria) {
		Categoria categoriaSalva = buscarCategoriaPeloCodigo(codigo);
		BeanUtils.copyProperties(categoria, categoriaSalva, "codigo"); 
		return categoriaRepository.save(categoriaSalva);
	}
	
	
	private Categoria buscarCategoriaPeloCodigo(Long codigo) {
		Categoria categoriaSalva =  categoriaRepository.findOne(codigo); 
		if(categoriaSalva == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return categoriaSalva;
	}

}
