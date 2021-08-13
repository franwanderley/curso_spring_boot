package com.cursospring.aulamc.services;

import java.util.List;
import java.util.Optional;

import com.cursospring.aulamc.domain.Categoria;
import com.cursospring.aulamc.repositories.CategoriaRepository;
import com.cursospring.aulamc.services.exceptions.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {
   @Autowired
   private CategoriaRepository repo;
   
   public Categoria buscar(Integer id){
      Optional<Categoria> obj = repo.findById(id);
      return obj.orElseThrow(() -> 
         new ObjectNotFoundException("Objeto não encotrado id= " + id + ", Tipo "+ Categoria.class.getName())
      );
   }

   public List<Categoria> listar(){
      return repo.findAll();
   }

   public Categoria criar(Categoria cat){
      return repo.save(cat);
   }

   public Boolean deletar(Integer id){
      try{
         repo.deleteById(id);
      }catch(IllegalArgumentException err){
         return false;
      }
         return true;
   }
}
