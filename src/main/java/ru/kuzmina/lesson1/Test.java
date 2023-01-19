package ru.kuzmina.lesson1;

import ru.kuzmina.lesson1.builder.Person;
import ru.kuzmina.lesson1.builder.PersonBuilder;
import ru.kuzmina.lesson1.oop.Circle;
import ru.kuzmina.lesson1.oop.GeometryShape;

public class Test {
    public static void main(String[] args) {
        GeometryShape circle = new Circle();
        ((Circle) circle).setRadius(15.3);
        Circle circle1 = new Circle();
        circle1.setRadius(10.0);
        System.out.println("circle1.area = " + circle1.area());
        System.out.println("circle.perimeter =" + circle.perimeter());

        PersonBuilder builder = new PersonBuilder();
        Person person = builder
                .withFirstName("Ivan")
                .withLastName("Ivanov")
                .withGender("male")
                .withAge(33)
                .withCountry("Russia")
                .build();
        System.out.println(person.toString());
    }
}
