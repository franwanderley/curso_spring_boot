package com.cursospring.aulamc.services;

import java.util.List;
import java.util.Optional;

import com.cursospring.aulamc.domain.Categoria;
import com.cursospring.aulamc.repositories.CategoriaRepository;
import com.cursospring.aulamc.services.exceptions.DataIntegrityException;
import com.cursospring.aulamc.services.exceptions.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {
   @Autowired
   private CategoriaRepository repo;
   
   public Categoria find(Integer id){
      Optional<Categoria> obj = repo.findById(id);
      return obj.orElseThrow(() -> 
         new ObjectNotFoundException("Objeto não encotrado id= " + id + ", Tipo "+ Categoria.class.getName())
      );
   }
   public List<Categoria> list(){
      return repo.findAll();
   }
   public Categoria insert(Categoria cat){
      cat.setId(null);
      return repo.save(cat);
   }
   public Categoria update(Categoria cat){
      find(cat.getId());
      return repo.save(cat);
   }
   public void delete(Integer id){
      find(id);
      try{
         repo.deleteById(id);
      }catch(DataIntegrityViolationException err){
         throw new DataIntegrityException("Não é possivel excluir uma categoria com produtos");
      }
   }
}
