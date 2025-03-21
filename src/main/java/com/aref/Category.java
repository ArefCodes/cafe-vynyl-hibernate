package com.aref;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity //representing a table in relational database.
public class Category {
    @Id //marking a field as primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //eliminating need for manually assigning primary key values.
    //generatedValue has different strategies. GenerationType.Identity database automatically generates the primary key value.
    private int id;

    @Column(name = "name", nullable = false, unique = true) //making columns of the table
    //declaring nullable = false for better exception handling.
    //unique = true for creating unique constraints.
    private String name;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL) //mapping other tables to Category.
    private List<Menuitems> menuItems = new ArrayList<>();

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL) //
    private List<Vynyl> vynyl = new ArrayList<>();

    public Category() {} //building no arg-constructor, in this way we can call superclass constructor.

    public Category(int id, String name, List<Menuitems> menuItems, List<Vynyl> vynyl) { //using constructor to make instances of class
        this.id = id;
        this.name = name;
        this.menuItems = menuItems;
        this.vynyl = vynyl;
    }

    public List<Menuitems> getMenuItems() {
        return menuItems;
    }
    public List<Vynyl> getVynyl() {
        return vynyl;
    }

    public void setVynyl(List<Vynyl> vynyl) {
        this.vynyl = vynyl;
    }

    public void setMenuItems(List<Menuitems> menuItems) {
        this.menuItems = menuItems;
    }

    public String getName() { //getter and setter methods to
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    //helper method
    public void addMenuItems(Menuitems menuitems) {
        menuItems.add(menuitems);
        menuitems.setCategory(this);
    }
    public void addVynyl(Vynyl vynyls) {
        vynyl.add(vynyls);
        vynyls.setCategory(this);
    }

    @Override
    public String toString() { //toString for object handling
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", menuItems=" + menuItems +
                ", vynyl=" + vynyl +
                '}';
    }
}
