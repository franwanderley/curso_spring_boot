package com.cursospring.aulamc.resources;

import java.net.URI;
import java.util.List;

import com.cursospring.aulamc.domain.Cliente;
import com.cursospring.aulamc.services.ClienteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(value = "clientes")
public class ClienteResources {
   
   @Autowired
   private ClienteService service;

   @RequestMapping(value="/{id}",  method = RequestMethod.GET)
   public ResponseEntity<Cliente> find(@PathVariable Integer id){
      Cliente cli1 = service.find(id);
      return ResponseEntity.ok().body(cli1);
   }

   @RequestMapping(method = RequestMethod.GET)
   public ResponseEntity<List<Cliente>> list(){
      List<Cliente> clientes = service.list();
      return ResponseEntity.ok().body(clientes);
   }

   @RequestMapping(method = RequestMethod.POST)
   public ResponseEntity<Void> insert(@RequestBody Cliente cli){
      cli = service.insert(cli);
      URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
      .buildAndExpand(cli.getId()).toUri();

      return ResponseEntity.created(uri).build();
   }
   @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
   public ResponseEntity<Void> update(@RequestBody Cliente cli, @PathVariable Integer id ){
      cli.setId(id);
      cli = service.update(cli);
      return ResponseEntity.noContent().build();
   }
   @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
   public ResponseEntity<Void> delete(@PathVariable Integer id){
      service.delete(id);
      return ResponseEntity.noContent().build();
   }
}
 