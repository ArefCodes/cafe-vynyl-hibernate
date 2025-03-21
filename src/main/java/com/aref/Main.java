package com.aref;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration() //Writing Factory class for Session objects.
                .configure("hibernate.cfg.xml") //Configuring hibernate.
                .addAnnotatedClass(Category.class) //Registering Entity Classes.
                .addAnnotatedClass(Menuitems.class)
                .addAnnotatedClass(Vynyl.class)
                .buildSessionFactory(); //Building Session Factory

        try (Session session = sessionFactory.openSession()) { //Adding try catch block to handle exceptions.
            //opening hibernate session in try-catch
            session.beginTransaction(); //starting database transaction

            List<String> categoryNames = Arrays.asList( //I used list because my data be changeable.
                    "Espresso Bar", "Brew Bar", "Ice Coffee", "Tea", "Hot Drink", "Cake and Desserts", "Bar",
                    "Mocktail", "Smoothie", "Milk Shake", "Quick Bread", "Other", "Rock", "Jazz", "Blues", "Electronic"
            ); //inserting the names in category table.
            System.out.println("Categories: " + categoryNames); //Ensuring that our code runs well.

            // Saving each category and its menu items to the database
            for (String categoryName : categoryNames) {
                Category category = new Category();
                category.setName(categoryName);
                session.persist(category);

                List<Menuitems> menuItems = createMenuItemsForCategory(categoryName, category);
                for (Menuitems menuItem : menuItems) {
                    category.addMenuItems(menuItem);
                    session.persist(menuItem);
                }
                List<Vynyl> vynyl = createVynylForCategory(categoryName, category);
                for (Vynyl vynyls : vynyl){
                    category.addVynyl(vynyls);
                    session.persist(vynyls);
                }
            }

            session.getTransaction().commit();
            System.out.println("Categories, menu items, and vynyls are saved in the database.");
        } catch (Exception e) { //handling any type of exception
            e.printStackTrace(); //printing stack trace of exception for type of exception, error message, and the consequence of method calls.
        } finally { //finally for cleanup operations
            sessionFactory.close();
        }
    }

    // Helper method to create menu items for a given category
    private static List<Menuitems> createMenuItemsForCategory(String categoryName, Category category) {
        switch (categoryName) {
            case "Espresso Bar":
                return Arrays.asList(
                        new Menuitems("Commercial Espresso", "40 ml brewed Espresso by machine",
                                BigDecimal.valueOf(80000), category),
                        new Menuitems("Special Espresso", "40 ml brewed Espresso from single origin beans",
                                BigDecimal.valueOf(90000), category),
                        new Menuitems("Commercial Americano", "Espresso with hot water",
                                BigDecimal.valueOf(90000), category),
                        new Menuitems("Special Americano", "brewed single origin Espresso with hot water",
                                BigDecimal.valueOf(90000), category),
                        new Menuitems("Latte", "300 ml brewed Espresso with steamed milk",
                                BigDecimal.valueOf(120000), category),
                        new Menuitems("Flat White", "200 ml brewed Espresso with steamed milk",
                                BigDecimal.valueOf(115000), category),
                        new Menuitems("Cappuccino", "160 ml brewed Espresso with steamed milk",
                                BigDecimal.valueOf(110000), category),
                        new Menuitems("Cortado", "80 ml brewed Espresso with steamed milk",
                                BigDecimal.valueOf(100000), category),
                        new Menuitems("Macchiato", "50 ml brewed Espresso with milk foam",
                                BigDecimal.valueOf(95000), category),
                        new Menuitems("Caramel Macchiato", "300 ml brewed Espresso with steamed milk and Caramel syrup",
                                BigDecimal.valueOf(135000), category),
                        new Menuitems("Mocha", "300 ml brewed Espresso with steamed milk, and Chocolate syrup",
                                BigDecimal.valueOf(135000), category),
                        new Menuitems("Nut Leche", "300 ml brewed Espresso with steamed milk, and roasted nuts",
                                BigDecimal.valueOf(140000), category),
                        new Menuitems("Strawberry Latte", "300 ml brewed Espresso with steamed milk, and fresh strawberries",
                                BigDecimal.valueOf(140000), category),
                        new Menuitems("Miele Fomo", "150 ml brewed Espresso with condensed milk, and honey",
                                BigDecimal.valueOf(150000), category),
                        new Menuitems("Cococcino", "160 ml Cappuccino with coconut cream",
                                BigDecimal.valueOf(125000), category)
                );
            case "Brew Bar":
                return Arrays.asList(
                        new Menuitems("Premium", "300 ml manually brewed coffee with Chemex, V60, French Press,",
                                BigDecimal.valueOf(150000), category),
                        new Menuitems("Limited Edition", "300 ml manually brewed Supreme Origin coffee with V60, Gina, Origami, Aeropress",
                                BigDecimal.valueOf(170000), category),
                        new Menuitems("Extra Cup", "Extra cup for manually brewed premium coffee",
                                BigDecimal.valueOf(100000), category),
                        new Menuitems("Drip Coffee", "300 ml brewed blend coffee with Coffee Dripper",
                                BigDecimal.valueOf(140000), category),
                        new Menuitems("Cold Brew", "300 ml brewed coffee with 8 hours cold brew method",
                                BigDecimal.valueOf(160000), category),
                        new Menuitems("Premium Ice Brew", "300 ml manually brewed coffee on ice",
                                BigDecimal.valueOf(150000), category),
                        new Menuitems("Limited Edition Ice Brew", "300 ml manually brewed coffee on ice",
                                BigDecimal.valueOf(170000), category)
                );
            case "Ice Coffee":
                return Arrays.asList(
                        new Menuitems("Commercial Ice Americano", "Espresso with ice, and water",
                                BigDecimal.valueOf(90000), category),
                        new Menuitems("Special Americano", "Single origin Espresso with ice, and water",
                                BigDecimal.valueOf(90000), category),
                        new Menuitems("Ice Latte", "Espresso with ice, and milk",
                                BigDecimal.valueOf(120000), category),
                        new Menuitems("Ice Caramel Macchiato", "Espresso with ice, milk, and Caramel syrup",
                                BigDecimal.valueOf(135000), category),
                        new Menuitems("Ice Mocha", "Espresso with ice, milk, and Chocolate syrup",
                                BigDecimal.valueOf(135000), category),
                        new Menuitems("Ice Strawberry Latte", "Espresso with ice, milk, and fresh strawberries",
                                BigDecimal.valueOf(140000), category),
                        new Menuitems("Ice Coconut Latte", "Espresso with ice, milk, and coconut cream",
                                BigDecimal.valueOf(125000), category)
                );
            case "Tea":
                return Arrays.asList(
                        new Menuitems("Commercial Tea", "Blend Tea from different regions with Novvot, Cinnamon Stick, and Date",
                                BigDecimal.valueOf(70000), category),
                        new Menuitems("Special Tea", "Single origin manually brewed tea with Novvot, Cinnamon Stick, and Date",
                                BigDecimal.valueOf(80000), category),
                        new Menuitems("Matcha Tea", "Matcha powder with steamed milk, and vanilla",
                                BigDecimal.valueOf(110000), category),
                        new Menuitems("Spirulina Tea", "Blue Spirulina with vanilla, and steamed milk",
                                BigDecimal.valueOf(110000), category),
                        new Menuitems("Green Tea", "Dried Green Tea leaves with, Novvot, Cinnamon Stick, and Date",
                                BigDecimal.valueOf(90000), category),
                        new Menuitems("Red Tea", "Hibiscus Tea, rosemary, clove, and  orange with, Novvot, Cinnamon Stick, and Date",
                                BigDecimal.valueOf(90000), category),
                        new Menuitems("Refresher Tea", "Fresh thyme, ginger, lemon grass, and lemon beebrush with Novvot, Cinnamon Stick, and Date",
                                BigDecimal.valueOf(90000), category),
                        new Menuitems("Serenity", "Echium, Valerian, and Mallow with Novvot, Cinnamon Stick, and Date",
                                BigDecimal.valueOf(90000), category),
                        new Menuitems("Ice Matcha Tea", "Matcha powder with ice, milk, and vanilla",
                                BigDecimal.valueOf(110000), category),
                        new Menuitems("Ice Spirulina Tea", "Blue Spirulina with ice, milk, and vanilla",
                                BigDecimal.valueOf(110000), category),
                        new Menuitems("Ice  Strawberry Matcha Tea", "Matcha powder with ice, milk, vanilla, and fresh strawberries",
                                BigDecimal.valueOf(120000), category),
                        new Menuitems("Ice Strawberry Spirulina Tea", "Blue Spirulina with ice, milk vanilla, and fresh strawberries",
                                BigDecimal.valueOf(120000), category),
                        new Menuitems("Ice Refresher Tea", "Fresh thyme, ginger, lemon grass, and lemon beebrush, and Marrow",
                                BigDecimal.valueOf(90000), category)
                );
            case "Hot Drink":
                return Arrays.asList(
                        new Menuitems("Hot Chocolate", "200 ml blend chocolate powder with milk",
                                BigDecimal.valueOf(100000), category),
                        new Menuitems("White Chocolate", "200 ml white chocolate powder with milk",
                                BigDecimal.valueOf(100000), category),
                        new Menuitems("Special Hot Chocolate", "200 ml chocolate powder, coconut milk, condensed milk",
                                BigDecimal.valueOf(150000), category),
                        new Menuitems("Limited Hot Chocolate", "200 ml chocolate powder, coconut milk, roasted nut, and nutmeg",
                                BigDecimal.valueOf(160000), category),
                        new Menuitems("Hot Biscuit", "200ml biscuit Cream with milk",
                                BigDecimal.valueOf(150000), category),
                        new Menuitems("Masala Tea", "200ml ginger, cinnamon sticks, white pepper, and milk",
                                BigDecimal.valueOf(100000), category),
                        new Menuitems("Crack Tea", "200ml cardamom, saffron, and milk",
                                BigDecimal.valueOf(110000), category),
                        new Menuitems("Orange Ginger", "200ml orange, ginger, and clove",
                                BigDecimal.valueOf(90000), category)
                );
            case "Cake and Desserts":
                return Arrays.asList(
                        new Menuitems("New York Cheesecake", "Rich cheesecake with cream cheese",
                                BigDecimal.valueOf(150000), category),
                        new Menuitems("Japanese Cheesecake", "Cotton cheesecake with less sugar",
                                BigDecimal.valueOf(150000), category),
                        new Menuitems("Nutella Cheesecake", "Nutty and chocolate cheesecake with mascarpone cheese",
                                BigDecimal.valueOf(150000), category),
                        new Menuitems("Cinnamon Roll", "Big, fluffy, and sweet roll",
                                BigDecimal.valueOf(150000), category),
                        new Menuitems("New York Roll", "Croissant dough with vanilla cream",
                                BigDecimal.valueOf(150000), category),
                        new Menuitems("Danish cake", "White, buttery, and sponge cake",
                                BigDecimal.valueOf(150000), category),
                        new Menuitems("Eclair", "A cake which resembles the lightning",
                                BigDecimal.valueOf(100000), category),
                        new Menuitems("Other", "Ask us",
                                BigDecimal.valueOf(150000), category)
                );
            case "Bar":
                return Arrays.asList(
                        new Menuitems("Chocholate Bar", "For those who love chocolate",
                                BigDecimal.valueOf(80000), category),
                        new Menuitems("Protein Bar", "For athletes",
                                BigDecimal.valueOf(120000), category),
                        new Menuitems("Sneakers Bar", "For those who love chocolate and also nut",
                                BigDecimal.valueOf(100000), category)
                );
            case "Mocktail":
                return Arrays.asList(
                        new Menuitems("Peach Cobbler", "Peach, Maple, Mint, Vanilla",
                                BigDecimal.valueOf(130000), category),
                        new Menuitems("Paloma", "Grapefruit, Agave",
                                BigDecimal.valueOf(1250000), category),
                        new Menuitems("Garden", "Elderflower, Cocumber, Mint, Apple",
                                BigDecimal.valueOf(1350000), category),
                        new Menuitems("French Rose", "Watermelon, Simple Syrup, Elder Flower",
                                BigDecimal.valueOf(130000), category),
                        new Menuitems("Honey Bee", "Honey, Caramelised Sugar Cube, Lime",
                                BigDecimal.valueOf(100000), category)
                );
            case "Smoothie":
                return Arrays.asList(
                        new Menuitems("Sangria", "Pineapple, Strawberries, Grape, Mint",
                                BigDecimal.valueOf(130000), category),
                        new Menuitems("Chococado", "Ice Crean, Avocado, Chocolate, Coconut",
                                BigDecimal.valueOf(155000), category),
                        new Menuitems("Peak", "Banana, Chia Seeds, Yogurt",
                                BigDecimal.valueOf(160000), category),
                        new Menuitems("Lady Cherry", "Cherry, Strawberries, Cornelian",
                                BigDecimal.valueOf(130000), category),
                        new Menuitems("Twin Peaks", "Mango, Black Berry, Passion Fruit",
                                BigDecimal.valueOf(150000), category)
                );
            case "Milk Shake":
                return Arrays.asList(
                        new Menuitems("Ferraro Rocher", "Ferraro Rocher chocolate, Irish syrup",
                                BigDecimal.valueOf(170000), category),
                        new Menuitems("Heart Berry", "Raspberry, Milka",
                                BigDecimal.valueOf(175000), category),
                        new Menuitems("Hazlenut", "Roasted nuts, Chocolate sauce",
                                BigDecimal.valueOf(165000), category),
                        new Menuitems("Dream Sip", "Pineapple, Blue Carcao, Coconut cream",
                                BigDecimal.valueOf(175000), category)
                );
            case "Quick Bread":
                return Arrays.asList(
                        new Menuitems("Croissant", "Buttery laminated yeast dough",
                                BigDecimal.valueOf(80000), category),
                        new Menuitems("Chocolate Croissant", "Croissant with Nutella and roasted nuts",
                                BigDecimal.valueOf(100000), category),
                        new Menuitems("Peanut butter Croissant", "Croissant with Jif and peanuts",
                                BigDecimal.valueOf(100000), category),
                        new Menuitems("Cheese Simit", "Simit with cheese, chia seeds, and baselica",
                                BigDecimal.valueOf(100000), category),
                        new Menuitems("Banana Bread", "Bread with banana and nut",
                                BigDecimal.valueOf(100000), category)
                );
            case "Other":
                return Arrays.asList(
                        new Menuitems("Non-Dairy Milk", "Almond, Nut, Oat, and Coconut",
                                BigDecimal.valueOf(60000), category),
                        new Menuitems("Cookies", "Three chocolate and oat cookies",
                                BigDecimal.valueOf(50000), category),
                        new Menuitems("Dairy Milk", "150ml dairy milk warm or cold",
                                BigDecimal.valueOf(40000), category)
                );
            default:
                return Arrays.asList(
                        new Menuitems("Default Item", "Default description", BigDecimal.valueOf(10000),
                                category)
                );
        }
    }
    private static List<Vynyl> createVynylForCategory(String categoryName, Category category){
        switch (categoryName){
            case "Rock":
                return Arrays.asList(
                  new Vynyl("Steven Wilson - The Raven That Refused to Sing",
                  "Album released in 2013 and is about different stories. This album blends with jazz", category),

                  new Vynyl("Red Hot Chili Peppers - Californication",
                          "Album that resembles highest peak of the band members and mixes with funk", category),

                  new Vynyl("Led Zeppelin - Led Zeppelin IV",
                          "One of the best classics every second consists of renovation", category),

                  new Vynyl("Deep Purple - Perfect Strangers",
                          "It's hard to pick best album of Deep purple. We cannot praise this album in one word.", category),

                  new Vynyl("Pink Floyd - Wish You Were Here",
                          "Its about loss, pain, and missing someone thats not here", category)
                        );
            case "Jazz":
                return Arrays.asList(
                        new Vynyl("Jazzbois - Jazzbois Goes Blunt II",
                                "One of the leading live bands in Europe that establish their sound with jazz motifs and hip-hop", category),

                        new Vynyl("Norah Jones - Come Away With Me",
                                "Self described as moody little record", category),

                        new Vynyl("The Dave Brubeck Quartet - Time Out",
                                "The iconic track Take Five is in this album. Time out is groundbreaking Jazz album", category),

                        new Vynyl("Herby Hancock - One Finger Snap",
                                "One of the creative chairs of Jazz.", category),

                        new Vynyl("Chet Baker - Chet Baker Sings",
                                "Icon of West Coast coll Jazz", category)
                );
            case "Blues":
                return Arrays.asList(
                        new Vynyl("B.B. King - Deuces Wild",
                                "One cannot speak about blues and not listen to this album", category),

                        new Vynyl("Stevie Ray Vaughan - Texas Flood",
                                "One of the leading modern Blues albums", category),

                        new Vynyl("Eric Clapton - Pilgrim",
                                "Even those who don't listen to blues, they will fall in love with this album", category),

                        new Vynyl("Gary Moore - Still Got The Blues",
                                "Blues never die because of this album", category),

                        new Vynyl("Chris Bell 100% Blues - Baptized by the Blues",
                                "For those who really love blues", category)
                );
            case "Electronic":
                return Arrays.asList(
                        new Vynyl("Depeche Mode - Violater",
                                "This album expands electro-pop into dark sounds", category),

                        new Vynyl("Thom Yorke - Anima",
                                "If you want to get crazy, get crazy with this", category),

                        new Vynyl("Portishead - Dummy",
                                "Pioneer in Trip-Hop genre", category),

                        new Vynyl("Daft Punk - Discovery",
                                "French house album that is produced by heroes", category),

                        new Vynyl("Kavinsky - Reborn",
                                "Album for 16-bit video game lovers", category)
                );
        }
        return List.of();
    }
}