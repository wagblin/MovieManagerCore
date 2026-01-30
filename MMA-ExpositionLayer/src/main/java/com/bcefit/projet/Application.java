package com.bcefit.projet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//Si le package dans tous les modules n'ont pas la même racine (org.example, dans l'exemple), on doit ajoute la ligne suivante
//@SpringBootApplication(scanBasePackages = {"com.example","org.example"})

@SpringBootApplication
public class Application {

 public static void main(String[] args){
     SpringApplication.run(Application.class,args);
 }
}
