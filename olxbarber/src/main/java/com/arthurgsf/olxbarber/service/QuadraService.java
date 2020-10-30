package com.arthurgsf.olxbarber.service;

import com.arthurgsf.olxbarber.model.repositorios.QuadraRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuadraService {
    @Autowired
    QuadraRepository quadraRepository;
    
}
