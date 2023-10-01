package com.example.Homework72;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Validated
public class UseraControllr {

@GetMapping("/users")
    public String users(@RequestParam("name") @NnotBlank(message = "名前を入力してください") String name,
                        @RequestParam("age") Integar age,
                        @RequestParam("birthday") String birthday){
    return "入力結果は:" + "名前" + name + "年齢" + age + "生年月日" + birthday + "です。";
}

@PostMapping("/users")
    public ResponseEntity<Map<String,String>> create(@RequestBody @Validated CreateForm createForm){

     URI uri = UriComponentsBuilder.fromUriString("http://localhost:8080")
             .path("/users/id")
             .build()
             .toUri();
     return ResponseEntity.created(uri).body(Map.of("message","name successfully created"));
}

@PatchMapping("/users/{id}")
    public ResponseEntity<Map<String,String>> update(@PathVariable("id") int id, RequestBody @Validated UpdateForm updateForm){

    return ResponseEntity.ok(Map.of("message","name successfully updated"));
}

@DeleteMapping("/users/{id}")
    public  ResponseEntity<Map<String,String>> delete(@PathVariable("id") int id){
      return ResponseEntity.ok(Map.of("messgae", "name syccessfully deleted"));
}
@ResponseStatus(HttpStatus.BAD_REQUEST)
@ExceptionHandler(MethodArgumentNotValidException.class)
    public  Map<String,String> handleValidationExceptions(
            MethodArgumentNotValidException ex){
        Map<String,String> methodErrors = new HashMap<>();
        ex.getBindingResult().gatAllErrors().forEach((methodError) -> {
            String fieldName = ((FieldError)methodErrors).getField();
            String metErrorMessage = methodErrors.getDefaultMessage();
            methodErrors.put(fieldName, metErrorMessage);
});
        return  methodErrors;
}
@ExceptionHandler(ConstraintViolationException.class)
    Public List<Object> handleConstraintViolationExceeption(
            ConstraintViolationException ex){
         List<Object> constraintErrors = new ArrayList<><Object>();
         for (constraintViolation<?> violation : ex.getConstraintViolations()){
              constraintErrors.add(violation.getRootBeanClass().getName() + "" +
                      violation.getPropertyPath() +": " + violation.getMessage());
         }
         return constraintErrors;
    }
}

