package com.cursospring.aulamc.resources;
import com.cursospring.aulamc.domain.Categoria;
import com.cursospring.aulamc.services.CategoriaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "categorias")
public class CategoriaResources {
   
   @Autowired
   private CategoriaService service;

   @RequestMapping(value="/{id}",  method = RequestMethod.GET)
   public ResponseEntity<?> buscarPorId(@PathVariable Integer id){
      Categoria cat1 = service.buscar(id);
      return ResponseEntity.ok().body(cat1);
   }

   @RequestMapping(method = RequestMethod.GET)
   public ResponseEntity<?> listar(){
      return ResponseEntity.ok().body("dd");
   }

}
