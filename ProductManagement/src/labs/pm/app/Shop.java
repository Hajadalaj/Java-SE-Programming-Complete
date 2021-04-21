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
package labs.pm.app;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Locale;
import labs.pm.data.Product;
import labs.pm.data.ProductManager;
import labs.pm.data.Rating;

/**
 *
 * {@code Shop}Class represents an application that manages Products
 *
 * @version 0.1
 * @author hajadalaj
 */
public class Shop {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ProductManager pm = new ProductManager(Locale.US);
        
        Product p1 = pm.createProduct(101, "Chopp", BigDecimal.valueOf(3.99), Rating.NOT_RATED, true);
        pm.printProductReport();
        
        p1 = pm.reviewProduct(p1, Rating.FIVE_STAR, "The best chopp of the world");
        p1 = pm.reviewProduct(p1, Rating.FIVE_STAR, "So cold");
        p1 = pm.reviewProduct(p1, Rating.FIVE_STAR, "The better of the country");
        p1 = pm.reviewProduct(p1, Rating.ONE_STAR, "Very strong");
        p1 = pm.reviewProduct(p1, Rating.ONE_STAR, "I don't like that");
        pm.printProductReport();
        
//        Product p3 = pm.createProduct(103, "Cake", BigDecimal.valueOf(3.99), Rating.NOT_RATED, LocalDate.now().plusDays(2));
//        pm.printProductReport();
//        p3 = pm.reviewProduct(p3, Rating.ONE_STAR, "No taste of 'grandma' cake");
//        pm.printProductReport();
        
//        Product p2 = pm.createProduct(102, "Coffee", BigDecimal.valueOf(1.99), Rating.FOUR_STAR, false);
//        Product p3 = pm.createProduct(103, "Cake", BigDecimal.valueOf(3.99), Rating.FIVE_STAR, LocalDate.now().plusDays(2));
//        Product p4 = pm.createProduct(105, "Vodka", BigDecimal.valueOf(10.00), Rating.FIVE_STAR, true);
//        Product p5 = p3.applyRating(Rating.THREE_STAR);
//        Product p6 = pm.createProduct(104, "Chocolate", BigDecimal.valueOf(2.99), Rating.FIVE_STAR, false);
//        Product p7 = pm.createProduct(104, "Chocolate", BigDecimal.valueOf(2.99), Rating.FIVE_STAR, LocalDate.now().plusDays(2));
//        Product p8 = p6.applyRating(Rating.TWO_STAR);
//        Product p9 = p7.applyRating(Rating.THREE_STAR);
//        System.out.println(p6.equals(p7));
//        p1.setId(101);
//        p1.setName("Tea");
//        p1.setPrice(BigDecimal.valueOf(1.99));
//        
//        System.out.println(p1);
//        System.out.println(p2);
//        System.out.println(p3);
//        System.out.println(p4);
//        System.out.println(p5);
//        System.out.println(p6);
//        System.out.println(p7);
//        System.out.println(p8);
//        System.out.println(p9);
//        
    }

}
