package com.cursospring.aulamc.services;

import java.util.List;
import java.util.Optional;

import com.cursospring.aulamc.domain.Cliente;
import com.cursospring.aulamc.repositories.ClienteRepository;
import com.cursospring.aulamc.services.exceptions.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {
   @Autowired
   private ClienteRepository repo;

   public Cliente find(Integer id){
      Optional<Cliente> obj = repo.findById(id);
      return obj.orElseThrow(() -> 
         new ObjectNotFoundException("Objeto não encotrado id= " + id + ", Tipo "+ Cliente.class.getName())
      );
   }
   public List<Cliente> list(){
      return repo.findAll();
   }
   public Cliente insert(Cliente cli){
      cli.setId(null);
      return repo.save(cli);
   }
   public Cliente update(Cliente cli){
      find(cli.getId());
      return repo.save(cli);
   }
   public void delete(Integer id){
      find(id);
      repo.deleteById(id);
   }
}
