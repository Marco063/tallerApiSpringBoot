package com.edu.uptc.tallerApiSpringBoot.controllers;

import com.edu.uptc.tallerApiSpringBoot.entities.Sectional;
import com.edu.uptc.tallerApiSpringBoot.responses.ResponseHandler;
import com.edu.uptc.tallerApiSpringBoot.services.SectionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sectional")
public class SectionalController {

    @Autowired
    private SectionalService sectionalService;

    @GetMapping()
    public ResponseEntity<Object> findAll(){

        try {

            List<Sectional> result = sectionalService.findAll();

            return ResponseHandler.generateResponse("Success", HttpStatus.OK,result);
        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null );
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable Integer id){
        try {
            Sectional result = sectionalService.findById(id);

            return ResponseHandler.generateResponse("Success", HttpStatus.OK,result);
        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null );
        }
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody Sectional sectional){
        try{
            Sectional result = sectionalService.save(sectional);
            return  ResponseHandler.generateResponse("Success",HttpStatus.OK, result);
        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null );
        }
    }

    @PutMapping
    public ResponseEntity<Object> update(@RequestBody Sectional sectional){
        try{
            Sectional result = sectionalService.update(sectional);
            return ResponseHandler.generateResponse("Success", HttpStatus.OK, result);
        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null );
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Integer id){
        try {
            Sectional sectional = sectionalService.findById(id);
            if(sectional != null){
                sectionalService.delete(sectional);
                return ResponseHandler.generateResponse("Success", HttpStatus.OK, sectional);
            }else{
                return ResponseHandler.generateResponse("Sectional not exist", HttpStatus.NOT_FOUND, null);
            }
        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null );
        }
    }
}
