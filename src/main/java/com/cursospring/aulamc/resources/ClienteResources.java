package com.cursospring.aulamc.resources;

import com.cursospring.aulamc.domain.Cliente;
import com.cursospring.aulamc.services.ClienteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "clientes")
public class ClienteResources {
   
   @Autowired
   private ClienteService service;

   @RequestMapping(value="/{id}",  method = RequestMethod.GET)
   public ResponseEntity<?> buscarPorId(@PathVariable Integer id){
      Cliente cli1 = service.buscar(id);
      return ResponseEntity.ok().body(cli1);
   }

   @RequestMapping(method = RequestMethod.GET)
   public ResponseEntity<?> listar(){
      return ResponseEntity.ok().body("dd");
   }

}
 