package com.learnjava.service;

import com.learnjava.domain.checkout.Cart;
import com.learnjava.domain.checkout.CheckoutResponse;
import com.learnjava.domain.checkout.CheckoutStatus;
import com.learnjava.util.DataSet;
import org.junit.jupiter.api.Test;

import static com.learnjava.util.LoggerUtil.log;
import static org.junit.jupiter.api.Assertions.*;

class CheckoutServiceTest {
    PriceValidatorService priceValidatorService =new PriceValidatorService();
    CheckoutService checkoutService= new CheckoutService(priceValidatorService);

    @Test
    void noOfCores() {
        System.out.println("no of cores in your system " +Runtime.getRuntime().availableProcessors());
    }

    @Test
    void checkout() {
        Cart cart = DataSet.createCart(6);
        CheckoutResponse checkoutResponse = checkoutService.checkout(cart);

        assertEquals(CheckoutStatus.SUCCESS,checkoutResponse.getCheckoutStatus());
        log("final rate "+checkoutResponse.getFinalRate());
        assertTrue(checkoutResponse.getFinalRate()>0.0);


    }
}