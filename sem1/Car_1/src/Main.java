public class Main {
 public static void main(String[] args){
   Car carDefault = new Car();
   Car carFull = new Car("BMW", "C123OC", "Pink", 2021);
   Car carCustom = new Car("Lada", "Brown");

   System.out.println("---Проверка To_String()---");
   carDefault.To_String();
   carCustom.To_String();
   carFull.To_String();

   System.out.println("---Изменение полей сеттором ---");
   carDefault.setModel("Moskuich");
   carDefault.setColor("Green");
   carDefault.setYear(1066);

   System.out.printf("Обнавленные данные: \n  Model: %s\n  Color: %s\n  Age: %d", carDefault.getModel(), carDefault.getColor(), carDefault.getAge());
 } 
}
