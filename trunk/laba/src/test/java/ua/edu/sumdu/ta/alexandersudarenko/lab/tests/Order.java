
  package ua.edu.sumdu.ta.alexandersudarenko.lab.tests;
  
  import java.lang.annotation.Retention;
  import java.lang.annotation.RetentionPolicy;
  

  @Retention(RetentionPolicy.RUNTIME)
  public @interface Order {
      int value();
  }