package com.primer.micro.servicio.artifact_demo01;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController // Anotación que indica que esta clase es un controlador REST
@RequestMapping (value = "/greeting") // Ruta base para las peticiones a este controlador
// Esta clase es un controlador REST que maneja las peticiones relacionadas con saludos
public class GreetingController {

    private final AtomicLong counter = new AtomicLong();

    private static final String template = "Hello %s!";

    // Metodo que maneja las peticiones GET a la ruta /greeting
    @GetMapping // Anotación que indica que este metodo maneja peticiones GET
    // Este metodo recibe un parámetro opcional "name" y devuelve un objeto Greeting
    // Si no se proporciona el parámetro "name", se utiliza el valor por defecto "World"
    // El parámetro "name" se obtiene de la consulta de la URL (query parameter)
    public Greeting greeting (@RequestParam(value = "name", defaultValue="World") String name) {

        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }
}
