package com.cursospring.aulamc.services;

import java.util.Optional;
import java.util.List;

import com.cursospring.aulamc.domain.Pedido;
import com.cursospring.aulamc.repositories.PedidoRepository;
import com.cursospring.aulamc.services.exceptions.DataIntegrityException;
import com.cursospring.aulamc.services.exceptions.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {
   @Autowired
   private PedidoRepository repo;
   
   public Pedido find(Integer id){
      Optional<Pedido> obj = repo.findById(id);
      return obj.orElseThrow(() -> 
         new ObjectNotFoundException("Objeto não encotrado id= " + id + ", Tipo "+ Pedido.class.getName())
      );
   }
   public List<Pedido> list(){
      return repo.findAll();
   }
   public Pedido insert(Pedido ped){
      ped.setId(null);
      return repo.save(ped);
   }
   public Pedido update(Pedido ped){
      find(ped.getId());
      return repo.save(ped);
   }
   public void delete(Integer id){
      find(id);
      try {
         repo.deleteById(id);
         
      } catch (DataIntegrityViolationException err) {
         throw new DataIntegrityException("Não é possivel excluir uma categoria com produtos");
      }
   }
}

