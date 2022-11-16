package com.learnjava.service;

import com.learnjava.domain.checkout.Cart;
import com.learnjava.domain.checkout.CartItem;
import com.learnjava.domain.checkout.CheckoutResponse;
import com.learnjava.domain.checkout.CheckoutStatus;

import java.util.List;
import java.util.stream.Collectors;

import static com.learnjava.util.CommonUtil.startTimer;
import static com.learnjava.util.CommonUtil.timeTaken;
import static com.learnjava.util.LoggerUtil.log;

public class CheckoutService {

    private PriceValidatorService priceValidatorService;

    public CheckoutService(PriceValidatorService priceValidatorService){
        this.priceValidatorService=priceValidatorService;
    }

    public CheckoutResponse checkout(Cart cart){
        startTimer();
        log("price validation list"+cart.getCartItemList());
        List<CartItem> priceValidationList = cart.getCartItemList()
                .parallelStream()
                .map(cartItem -> {
                    boolean isPriceInvalid = priceValidatorService.isCartItemInvalid(cartItem);
                    cartItem.setExpired(isPriceInvalid);
                    return cartItem;
                })
                .filter(CartItem::isExpired)
                .collect(Collectors.toList());

        if(priceValidationList.size()>0){
            log("price validationlist size"+priceValidationList.size());
            return new CheckoutResponse(CheckoutStatus.FAILURE,priceValidationList);
        }
        timeTaken();
        return new CheckoutResponse(CheckoutStatus.SUCCESS);
    }
}
