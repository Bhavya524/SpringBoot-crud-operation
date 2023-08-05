package com.crud.springbootcrudoperation.controller;


import com.crud.springbootcrudoperation.model.Vehicle;
import com.crud.springbootcrudoperation.repository.VehicleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class VehicleController {

    @Autowired
    private VehicleRepository vehicleRepository;

    @GetMapping("/getAllVehicle")
    public ResponseEntity<List<Vehicle>> getAllVehicle(){
        try{
            List<Vehicle> vehicleList=new ArrayList<>();
            vehicleRepository.findAll().forEach(vehicleList::add);

            if (vehicleList.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(vehicleList,HttpStatus.OK);
        }
        catch (Exception ex ){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getVehicleById/{id}")
    public ResponseEntity<Vehicle> getVehicleById(@PathVariable Long id){
        Optional<Vehicle> vehicleData = vehicleRepository.findById(id);

        if (vehicleData.isPresent()){
            return new ResponseEntity<>(vehicleData.get(),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/addVehicle")
    public ResponseEntity<Vehicle> addVehicle(@RequestBody Vehicle vehicle){
        Vehicle vehicleObj = vehicleRepository.save(vehicle);
        return new ResponseEntity<>(vehicleObj,HttpStatus.OK);
    }

    @PostMapping("/updateVehicleById/{id}")
    public ResponseEntity<Vehicle> updateVehicleById(@PathVariable Long id , @RequestBody Vehicle newVehicleData) {
        Optional<Vehicle> oldVehicleData = vehicleRepository.findById(id);

        if (oldVehicleData.isPresent()) {
            Vehicle updateVehicleData = oldVehicleData.get();

            updateVehicleData.setName(newVehicleData.getName());
            updateVehicleData.setCompany(newVehicleData.getCompany());

            Vehicle vehicleobj = vehicleRepository.save(updateVehicleData);
            return new ResponseEntity<>(vehicleobj, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/deleteVehicleById/{id}")
    public ResponseEntity<HttpStatus> deleteVehicleById(@PathVariable Long id){
        vehicleRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
}

}

