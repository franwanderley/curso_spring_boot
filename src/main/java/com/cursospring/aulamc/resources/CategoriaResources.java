package com.cursospring.aulamc.resources;
import java.net.URI;
import java.util.List;

import com.cursospring.aulamc.domain.Categoria;
import com.cursospring.aulamc.services.CategoriaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(value = "categorias")
public class CategoriaResources {
   
   @Autowired
   private CategoriaService service;

   @RequestMapping(value="/{id}",  method = RequestMethod.GET)
   public ResponseEntity<Categoria> find(@PathVariable Integer id){
      Categoria cat1 = service.find(id);
      return ResponseEntity.ok().body(cat1);
   }

   @RequestMapping(method = RequestMethod.GET)
   public ResponseEntity<List<Categoria>> list(){
      List<Categoria> categorias = service.list();
      return ResponseEntity.ok().body(categorias);
   }
   @RequestMapping(method = RequestMethod.POST)
   public ResponseEntity<Void> insert(@RequestBody Categoria cat){
      cat = service.insert(cat);
      URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
      .buildAndExpand(cat.getId()).toUri();

      return ResponseEntity.created(uri).build();
   }
   @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
   public ResponseEntity<Void> update(@RequestBody Categoria cat, @PathVariable Integer id ){
      cat.setId(id);
      cat = service.update(cat);
      return ResponseEntity.noContent().build();
   }
   @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
   public ResponseEntity<Void> delete(@PathVariable Integer id){
      service.delete(id);
      return ResponseEntity.noContent().build();
   }
}
