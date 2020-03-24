package shop;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import static shop.InsertData.insertData;
import static shop.RetrieveData.getCategory;
import static shop.RetrieveData.getUser;

public class DemoClass {

    private static final String FILE_NAME = "basketSer.ser";
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        /*Category category1 = new Category("Phones");
        category1.add(new Product("Apple", 500, 5));
        category1.add(new Product("Samsung", 400, 4.8));
        category1.add(new Product("Xiomi", 200, 4.2));

        Category category2 = new Category("TV");
        category2.add(new Product("Apple TV", 1500, 5.1));
        category2.add(new Product("Samsung SmartTV", 1400, 4.7));
        category2.add(new Product("Motorolla", 1200, 4.1));

        Category category3 = new Category("Shoes");
        category3.add(new Product("Nike", 150, 4.9));
        category3.add(new Product("Adidas", 140, 4.4));
        category3.add(new Product("Puma", 130, 4.0));

        List<Category> categories = new ArrayList<>();
        categories.add(category1);
        categories.add(category2);
        categories.add(category3);

        String password;
        System.out.println("Enter login");
        if (scanner.hasNextLine()) {
            String login = scanner.nextLine();

            System.out.println("Enter password");
            if (scanner.hasNextLine()) {
                password = scanner.nextLine();

                User user = new User(login, password);

                boolean startStop = false;
                do {
                    choice(categories, user);
                    startStop = isStartStop(scanner, startStop);
                } while (startStop);

                *//*serialize(user.getBasket());
                System.out.println(deserialize());*//*
            }
        }*/


        User user = new User();
        user = getUser();
        List<Category> categoryList = getCategory();
        boolean startStop = false;
        do {
            choice(categoryList, user);
            startStop = isStartStop(scanner, startStop);
        } while (startStop);

        insertData(user);
        /*serialize(user.getBasket());
        System.out.println(deserialize());*/
    }

    public static boolean isStartStop(Scanner scanner, boolean startStop) {
        System.out.println("If you want to continue type in 'Yes', else 'No'");
        if (scanner.hasNext()) {
            String answer = scanner.nextLine();
            if (answer.equals("yes") || answer.equals("Yes") || answer.equals("1")) {
                startStop = true;
            } else {
                startStop = false;
            }
        }
        return startStop;
    }

    private static Basket deserialize() {
        Basket basket = null;
        try (FileInputStream fis = new FileInputStream(FILE_NAME);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            basket = (Basket) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return basket;
    }

    private static void serialize(Basket basket) {
        try (FileOutputStream fs = new FileOutputStream(FILE_NAME);
             ObjectOutputStream os = new ObjectOutputStream(fs)) {
            os.writeObject(basket);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void choice(List<Category> categories, User user) {

        instructions();
        Variant choice1 = input();
        switch (choice1) {
            case CATALOGS:
                printingCatalogs(categories);
                break;
            case CATALOG:
                printCatalog(categories);
                break;
            case TO_BASKET:
                choosingFromList(categories, user);
                break;
            case BUY_PRODUCTS:
                user.boughtProducts();
                break;
            default:
                System.out.println("You made mistake in writing some option, recheck instructions again");
                instructions();
        }
    }

    public static Variant input() {
        Variant choice = Variant.BEGIN;
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNext()) {
            String scannerChoice = scanner.nextLine();
            if (scannerChoice.equals("catalogs") || scannerChoice.equals("Catalogs") || scannerChoice.equals("1")) {
                choice = Variant.CATALOGS;
            }
            if (scannerChoice.equals("Catalog") || scannerChoice.equals("catalog") || scannerChoice.equals("2")) {
                choice = Variant.CATALOG;
            }
            if (scannerChoice.equals("to basket") || scannerChoice.equals("To Basket") ||
                    scannerChoice.equals("To basket") || scannerChoice.equals("3")) {
                choice = Variant.TO_BASKET;
            }
            if (scannerChoice.equals("Buy Products") || scannerChoice.equals("Buy products") ||
                    scannerChoice.equals("buy products") || scannerChoice.equals("4")) {
                choice = Variant.BUY_PRODUCTS;
            }
        }
        return choice;
    }

    public static void printingCatalogs(List<Category> categories) {
        Scanner scanner = new Scanner(System.in);
        instructionsForSorting();
        List<Product> productsSort = new ArrayList<>();
        for (Category category : categories) {
            category.getProducts().forEach((k, v) -> {
                productsSort.add(v);
            });
        }
        if (scanner.hasNext()) {
            int choice = scanner.nextInt();
            System.out.printf("%-20s %-20s %-20s", "Имя", "Цена", "Рейтинг");
            System.out.println();
            switch (choice) {
                case 1:
                    productsSort.sort(Comparator.comparing(Product::getName));
                    for (Product product : productsSort) {
                        System.out.printf("%-20s %-20.1f %-20.1f",
                                product.getName(), product.getPrice(), product.getRating());
                        System.out.println();
                    }
                    break;
                case 2:
                    productsSort.sort(Comparator.comparing(Product::getPrice));
                    for (Product product : productsSort) {
                        System.out.printf("%-20s %-20.1f %-20.1f",
                                product.getName(), product.getPrice(), product.getRating());
                        System.out.println();
                    }
                    break;
                case 3:
                    productsSort.sort(Comparator.comparing(Product::getRating));
                    for (Product product : productsSort) {
                        System.out.printf("%-20s %-20.1f %-20.1f",
                                product.getName(), product.getPrice(), product.getRating());
                        System.out.println();
                    }
                    break;
                case 4:
                    for (Product product : productsSort) {
                        System.out.printf("%-20s %-20.1f %-20.1f",
                                product.getName(), product.getPrice(), product.getRating());
                        System.out.println();
                    }
                    break;
                default:
                    System.out.println("Read instructions carefully");
            }
        }
    }

    public static void instructionsForSorting() {
        System.out.println("For sorting products by name type in '1'");
        System.out.println("For sorting products by price type in '2'");
        System.out.println("For sorting products by rate type in '3'");
        System.out.println("Without sorting products type in '4'");
    }

    public static void instructions() {
        System.out.println("1. If you want to see all catalogs you should type in 'Catalogs' or '1'");
        System.out.println("2. If you want to see a certain catalog you should type in 'Catalog' or '2'");
        System.out.println("3. If you want to put some products in basket you should type in 'To basket' or '3'");
        System.out.println("4. If you want to buy products from the basket you should type in 'Buy products' or '4'");
    }

    public static void printCatalog(List<Category> categories) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("You have these categories: ");
        for (Category category : categories) {
            System.out.println("Type in '" + category.getId() + "' to see list of products from this category: " + category.getName());
        }
        if (scanner.hasNextInt()) {
            int id = scanner.nextInt();
            for (Category category : categories) {
                if (category.getId() == id) {
                    System.out.println(category);
                }
            }
        }
    }

    public static void choosingFromList(List<Category> categories, User user) {
        System.out.println("You have these categories: ");
        for (Category category : categories) {
            System.out.println("Type in '" + category.getId() + "' to choose" +
                    " a product from this category: " + category.getName());
        }
        puttingProduct(categories, user);
    }

    private static void puttingProduct(List<Category> categories, User user) {
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNextInt()) {
            int choiceOfCategory = scanner.nextInt();
            for (Category category : categories) {
                choiceOfCategory(choiceOfCategory, category, user);
            }
        }
    }

    private static void choiceOfCategory(int choiceOfCategory, Category category, User user) {
        Scanner scanner = new Scanner(System.in);
        if (category.getId() == choiceOfCategory) {
            category.getProducts().forEach((k, v) -> System.out.println("Type in '"
                    + k + "' to choose this product: " + v));
            if (scanner.hasNextInt()) {
                int productToBasket = scanner.nextInt();
                if (category.getProduct(productToBasket) != null) {
                    user.getBasket().add(category.getProduct(productToBasket));
                }
            }
        }
    }
}
