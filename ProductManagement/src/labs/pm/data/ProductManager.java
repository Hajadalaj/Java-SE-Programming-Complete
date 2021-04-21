/*
 * Copyright (C) 2021 hajadalaj
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package labs.pm.data;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

/**
 *
 * @author hajadalaj
 */
public class ProductManager {

    private Locale locale;
    private ResourceBundle resources;
    private DateTimeFormatter dateFormat;
    private NumberFormat moneyFormat;

    private Map<Product, List<Review>> products = new HashMap();

//    private Product product;
//    private Review[] reviews = new Review[5];
    public ProductManager(Locale locale) {
        this.locale = locale;
        resources = ResourceBundle.getBundle("labs.pm.data.resources", locale);
        dateFormat = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT).localizedBy(locale);
        moneyFormat = NumberFormat.getCurrencyInstance(locale);
    }

    public Product createProduct(int id, String name, BigDecimal price, Rating rating, LocalDate bestBefore) {
        Product product = new Food(id, name, price, rating, bestBefore);
        products.putIfAbsent(product, new ArrayList<>());
        return product;
    }

    public Product createProduct(int id, String name, BigDecimal price, Rating rating, boolean alcoholic) {
        Product product = new Drink(id, name, price, rating, alcoholic);
        products.putIfAbsent(product, new ArrayList<>());
        return product;
    }
    
    public Product reviewProduct(int id, Rating rating, String comments) {
        return reviewProduct(findProduct(id), rating, comments);
    }

    public Product reviewProduct(Product product, Rating rating, String comments) {
//        if (reviews[reviews.length - 1] != null) {
//            reviews = Arrays.copyOf(reviews, reviews.length + 5);
//        }
        List<Review> reviews = products.get(product);
        products.remove(product, reviews);
        reviews.add(new Review(rating, comments));
        int sum = 0;
        for (Review review : reviews) {
            sum += review.getRating().ordinal();
        }
//        int sum = 0;
//        int i = 0;
//        boolean reviewed = false;
//        while (i < reviews.length && !reviewed) {
//            if (reviews[i] == null) {
//                reviews[i] = new Review(rating, comments);
//                reviewed = true;
//            }
//            sum += reviews[i].getRating().ordinal();
//            i++;
//        }
//        reviews = new Review(rating, comments);
        product = product.applyRating(Rateable.convert(Math.round((float) sum / reviews.size())));
        products.put(product, reviews);
        return product;

    }

    public void printProductReport(int id) {
        printProductReport(findProduct(id));
    }

    public void printProductReport(Product product) {
        List<Review> reviews = products.get(product);
        StringBuilder txt = new StringBuilder();
        if (product instanceof Food) {
            txt.append(MessageFormat.format(resources.getString("food"),
                    product.getName(),
                    moneyFormat.format(product.getPrice()),
                    product.getRating().getStars(),
                    dateFormat.format(product.getBestBefore())));
            txt.append('\n');
        } else if (product instanceof Drink) {
            txt.append(MessageFormat.format(resources.getString("drink"),
                    product.getName(),
                    moneyFormat.format(product.getPrice()),
                    product.getRating().getStars(),
                    product.isAlcoholic()));
            txt.append('\n');
        }
        if (reviews.isEmpty()) {
            txt.append((resources.getString("no.reviewed")));
            txt.append('\n');
        } else {
            Collections.sort(reviews);
            for (Review review : reviews) {
                txt.append(MessageFormat.format(resources.getString("review"),
                        review.getRating().getStars(),
                        review.getComments()));
                txt.append('\n');
            }
        }
        System.out.println(txt);
    }

    public Product findProduct(int id) {
        for (Product product : products.keySet()) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }
}
