package com.cursospring.aulamc.resources;
import com.cursospring.aulamc.domain.Pedido;
import com.cursospring.aulamc.services.PedidoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "pedidos")
public class PedidoResources {
   
   @Autowired
   private PedidoService service;

   @RequestMapping(value="/{id}",  method = RequestMethod.GET)
   public ResponseEntity<?> buscarPorId(@PathVariable Integer id){
      Pedido ped1 = service.buscar(id);
      return ResponseEntity.ok().body(ped1);
   }

   @RequestMapping(method = RequestMethod.GET)
   public ResponseEntity<?> listar(){
      return ResponseEntity.ok().body("dd");
   }

}
