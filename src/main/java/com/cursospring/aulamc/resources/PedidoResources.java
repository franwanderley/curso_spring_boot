package com.cursospring.aulamc.resources;
import java.net.URI;
import java.util.List;

import com.cursospring.aulamc.domain.Pedido;
import com.cursospring.aulamc.services.PedidoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(value = "pedidos")
public class PedidoResources {
   
   @Autowired
   private PedidoService service;

   @RequestMapping(value="/{id}",  method = RequestMethod.GET)
   public ResponseEntity<Pedido> find(@PathVariable Integer id){
      Pedido ped1 = service.find(id);
      return ResponseEntity.ok().body(ped1);
   }
   @RequestMapping(method = RequestMethod.GET)
   public ResponseEntity<List<Pedido>> list(){
      List<Pedido> pedidos = service.list();
      return ResponseEntity.ok().body(pedidos);
   }
   @RequestMapping(method = RequestMethod.POST)
   public ResponseEntity<Void> insert(@RequestBody Pedido ped){
      ped = service.insert(ped);
      URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
      .buildAndExpand(ped.getId()).toUri();

      return ResponseEntity.created(uri).build();
   }
   @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
   public ResponseEntity<Void> update(@RequestBody Pedido cli, @PathVariable Integer id ){
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
