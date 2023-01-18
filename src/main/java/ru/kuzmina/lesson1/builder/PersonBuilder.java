package ru.kuzmina.lesson1.builder;

public class PersonBuilder {
    private String firstName;
    private String lastName;
    private String middleName;
    private String country;
    private String address;
    private String phone;
    private int age;
    private String gender;

    public PersonBuilder() {
    }

    public PersonBuilder withFirstName(String value) {
        firstName = value;
        return this;
    }

    public PersonBuilder withLastName(String value) {
        lastName = value;
        return this;
    }

    public PersonBuilder withMiddleName(String value) {
        middleName = value;
        return this;
    }

    public PersonBuilder withCountry(String value) {
        country = value;
        return this;
    }

    public PersonBuilder withAddress(String value) {
        address = value;
        return this;
    }

    public PersonBuilder withPhone(String value) {
        phone = value;
        return this;
    }

    public PersonBuilder withGender(String value) {
        gender = value;
        return this;
    }

    public PersonBuilder withAge(int value) {
        if (value > 0)
            age = value;
        return this;
    }

    public Person build() {
        Person person = new Person();
        person.setFirstName(firstName);
        person.setLastName(lastName);
        person.setMiddleName(middleName);
        person.setCountry(country);
        person.setAddress(address);
        person.setPhone(phone);
        person.setAge(age);
        person.setGender(gender);
        return person;
    }

    public Person buildFrom(Person person) {
        Person p = new Person();
        p.setFirstName(person.getFirstName());
        p.setLastName(person.getLastName());
        p.setMiddleName(person.getMiddleName());
        p.setCountry(person.getCountry());
        p.setAddress(person.getAddress());
        p.setPhone(person.getPhone());
        p.setAge(person.getAge());
        p.setGender(person.getGender());
        return p;
    }
}
