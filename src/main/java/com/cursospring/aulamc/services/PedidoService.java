package com.cursospring.aulamc.services;

import java.util.Optional;
import java.util.List;

import com.cursospring.aulamc.domain.Pedido;
import com.cursospring.aulamc.repositories.PedidoRepository;
import com.cursospring.aulamc.services.exceptions.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {
   @Autowired
   private PedidoRepository repo;
   
   public Pedido buscar(Integer id){
      Optional<Pedido> obj = repo.findById(id);
      return obj.orElseThrow(() -> 
         new ObjectNotFoundException("Objeto não encotrado id= " + id + ", Tipo "+ Pedido.class.getName())
      );
   }

   public List<Pedido> listar(){
      return repo.findAll();
   }

   public Pedido criar(Pedido cat){
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

