public class Car {
 private String model;
 private String license;
 private String color;
 private int year;

 private static final int CURRENT_YEAR = 2026;

 public Car() {
   this.model = "Не указано";
   this.license = "Транзит";
   this.color = "Не указано";
   this.year = CURRENT_YEAR;
 }

 public Car(String model, String license, String color, int year) {
  this.model = model;
  this.license = license;
  this.color = color;
  this.year = year;
 }

 public Car(String model, String color) {
   this(model, "Транзит", color, CURRENT_YEAR);
 }

   public String getModel() {
      return this.model;
  }

  public void setModel(String model) {
      this.model = model;
  }

  public String getLicense() {
      return this.license;
  }

  public void setLicense(String license) {
      this.license = license;
  }

  public String getColor() {
      return this.color;
  }

  public void setColor(String color) {
      this.color = color;
  }

  public int getYear() {
      return this.year;
  }

  public void setYear(int year) {
      this.year = year;
  }
  public int getAge() {
    return CURRENT_YEAR - this.year;
  }

  public void To_String() {
    System.out.printf("Model: %s| License: %s| Color: %s| Year: $d| Age: %d\n", model, license, color, year, getAge()); 
  }
}
