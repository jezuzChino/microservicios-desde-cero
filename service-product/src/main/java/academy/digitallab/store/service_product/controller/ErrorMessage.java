package academy.digitallab.store.service_product.controller;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

// Manejo de errores en la API
@Getter
@Setter
@Builder
public class ErrorMessage {
    private String code; //codigo de error

    private List<Map<String, String>> messages; //Lista de mensajes de error

}
