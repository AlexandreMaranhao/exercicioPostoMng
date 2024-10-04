package com.aluraAPI.aluraAPI.domain.sale.business;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

import com.aluraAPI.aluraAPI.domain.costumer.Costumer;
import com.aluraAPI.aluraAPI.domain.costumer.CostumerRepository;
import com.aluraAPI.aluraAPI.domain.costumer.dto.CostumerListDto;
import com.aluraAPI.aluraAPI.domain.costumer.dto.CustumerRegistredDto;
import com.aluraAPI.aluraAPI.domain.deal.Deal;
import com.aluraAPI.aluraAPI.domain.loyalty.Loyalty;
import com.aluraAPI.aluraAPI.domain.loyalty.LoyaltyRepository;
import com.aluraAPI.aluraAPI.domain.paymentMethod.PaymentMethod;
import com.aluraAPI.aluraAPI.domain.paymentMethod.PaymentMethodRepository;
import com.aluraAPI.aluraAPI.domain.deal.DealRepository;
import com.aluraAPI.aluraAPI.domain.product.Product;
import com.aluraAPI.aluraAPI.domain.product.ProductRepository;
import com.aluraAPI.aluraAPI.domain.sale.dto.SaleCompleteRegisterDto;
import com.aluraAPI.aluraAPI.domain.sale.dto.SaleReceiptDto;
import com.aluraAPI.aluraAPI.domain.sale.dto.SaleRegisteredDetailsDto;
import com.aluraAPI.aluraAPI.domain.saleProduct.SaleProduct;
import com.aluraAPI.aluraAPI.domain.saleProduct.SaleProductRepository;
import com.aluraAPI.aluraAPI.domain.saleProduct.business.RegisterSaleProductItem;
import com.aluraAPI.aluraAPI.domain.saleProduct.dto.SaleProductRegisterDto;
import com.aluraAPI.aluraAPI.domain.stock.Stock;
import com.aluraAPI.aluraAPI.domain.stock.StockRepository;
import com.aluraAPI.aluraAPI.domain.stockControl.StockControl;
import com.aluraAPI.aluraAPI.domain.stockControl.StockControlRepository;
import com.aluraAPI.aluraAPI.domain.stockControl.Type;
import com.aluraAPI.aluraAPI.domain.user.User;
import com.aluraAPI.aluraAPI.domain.user.UserRepository;
import com.aluraAPI.aluraAPI.domain.sale.Sale;
import com.aluraAPI.aluraAPI.domain.sale.SaleRepository;
import com.aluraAPI.aluraAPI.domain.sale.dto.SaleRegisterDto;
import com.aluraAPI.aluraAPI.exceptions.GeneralException;
import com.aluraAPI.aluraAPI.helper.CreateNew15CharNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NewSale {
    @Autowired
    private SaleRepository saleRepository;
    @Autowired
    private PaymentMethodRepository paymentMethodRepository;
    @Autowired
    private CostumerRepository costumerRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DealRepository dealRepository;
    @Autowired
    private StockControlRepository stockControlRepository;
    @Autowired
    private RegisterSaleProductItem registerSaleProductItem;
    @Autowired
    private SaleProductRepository saleProductRepository;
    @Autowired
    private StockRepository stockRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private LoyaltyRepository loyaltyRepository;


    public SaleRegisteredDetailsDto newSale(SaleRegisterDto newSaleInput, float saleAmount){
        if(!paymentMethodRepository.existsById(newSaleInput.paymentMethodId())){
            throw new GeneralException(("No payment method was found with id: " + newSaleInput.paymentMethodId()));
        }
        Long costumerIdLong =  newSaleInput.costumerId();
        Long dealIdLong = newSaleInput.dealId();
        Integer loyaltyPointsUsed = newSaleInput.loyaltyPoints();

        if (newSaleInput.costumerId() != null) {
            if (!costumerRepository.existsById(newSaleInput.costumerId())) {
                throw new GeneralException(("No costumer was found with id: " + newSaleInput.costumerId()));
            }
        }
        if(!userRepository.existsById(newSaleInput.userId())){
            throw new GeneralException(("No user was found with id: " + newSaleInput.userId()));
        }
        if (newSaleInput.dealId() != null) {
            saleAmount = calculatingInvoicValueAfterDeal(saleAmount);
            if (!dealRepository.existsById(newSaleInput.dealId())) {
                throw new GeneralException(("No deal was found with id: " + newSaleInput.dealId()));
            }
        }
        if (newSaleInput.costumerId() != null){
            Costumer costumer = costumerRepository.findById(newSaleInput.costumerId()).get();
            Loyalty loyalty = loyaltyRepository.findById(costumer.getLoyaltyId().getId()).get();
            Integer loyaltyPointsInBank = loyalty.getPoints();

            if (loyaltyPointsUsed != null){
                if (loyaltyPointsUsed > loyaltyPointsInBank){
                    throw new GeneralException("This costumer has no enough points, the actual loyalty points is: " + loyaltyPointsInBank);
                }else{
                    loyaltyPointsInBank -= loyaltyPointsUsed;
                    loyalty.setPoints(loyaltyPointsInBank);
                    loyaltyRepository.save(loyalty);
                    saleAmount = getSaleAmountAfterPoints(loyaltyPointsUsed, saleAmount);
                }
            }
            int loyaltyPointsAfterSale = loyaltyPointsInBank + generateLoyaltyPoints(saleAmount);
            loyalty.setPoints(loyaltyPointsAfterSale);
            loyaltyRepository.save(loyalty);
        }


        LocalDateTime sellDate = LocalDateTime.now();
        String invoiceNumber = CreateNew15CharNumber.generateInvoiceNumber();

        PaymentMethod paymentMethod = paymentMethodRepository.findById(newSaleInput.paymentMethodId()).get();
        User user = userRepository.findById(newSaleInput.userId()).get();
        Sale sell = null;


        if((newSaleInput.costumerId() != null) && (newSaleInput.dealId() != null)){
            Costumer costumer = costumerRepository.findById(newSaleInput.costumerId()).get();
            Deal deal = dealRepository.findById(newSaleInput.dealId()).get();

            sell = new Sale(sellDate, saleAmount, invoiceNumber, paymentMethod, costumer, user, deal);
            saleRepository.save(sell);

        } else if ((newSaleInput.costumerId() != null) && (newSaleInput.dealId() == null)) {
            Costumer costumer = costumerRepository.findById(newSaleInput.costumerId()).get();

            sell = new Sale(sellDate, saleAmount, invoiceNumber, paymentMethod, costumer, user);
            saleRepository.save(sell);

        }else if ((newSaleInput.costumerId() == null) && (newSaleInput.dealId() != null)) {
            Deal deal = dealRepository.findById(newSaleInput.dealId()).get();

            sell = new Sale(sellDate, saleAmount, invoiceNumber, paymentMethod, user, deal);
            saleRepository.save(sell);

        }else if ((newSaleInput.costumerId() == null) && (newSaleInput.dealId() == null)){
            sell = new Sale(sellDate, saleAmount, invoiceNumber, paymentMethod, user);
            saleRepository.save(sell);
        }
        //return new SaleRegisteredDetailsDto(sell);
        return sell.changeToSaleRegisteredDetailsDto();
    }

    public void findProductsCompleteSale(SaleCompleteRegisterDto newSaleInput){

        for (SaleProductRegisterDto product : newSaleInput.getProducts()){

            var verification = RegisterSaleProductItem.verifyProduct(product);
            if (!verification){
                throw new GeneralException(("No registered product with id: " + product.productId()));
            }
        }

    }
    public void registerCompleteSaleProductItem(SaleCompleteRegisterDto newSaleInput, SaleRegisteredDetailsDto registeredSale){
        for (SaleProductRegisterDto product : newSaleInput.getProducts()){
            stockVerification(product);
            SaleProduct registeredSaleProduct = registerSaleProductItem.registerSaleProductItem(product, registeredSale);
            registerSaleStockControl(registeredSaleProduct, registeredSale, product);
        }

    }

    public void stockUpdateOnSale(SaleProductRegisterDto product){

    }

    public void stockVerification(SaleProductRegisterDto product){
        Product productId = productRepository.findById(product.productId()).get();
        List<Stock> productStockList = getStockByProductId(productId);
        productStockList.sort(Comparator.comparing(Stock::getValidity));

        float totalAmoutItensInStock = 0;
        for (Stock item : productStockList){
            if (item.getValidity().isBefore(LocalDateTime.now())){
                continue;
            }else{
                totalAmoutItensInStock += item.getQuantity();
            }
        }

        if(totalAmoutItensInStock < product.quantity()){
            throw new GeneralException("There is no stock for this sell on product: " + product.productId() + "\nThe actual stock for this product is: " + totalAmoutItensInStock );
        }
    }

    public float stockQuantityUpdateSale(Stock stockEntrance, float saleQuantity){
        float updatedStock = stockEntrance.getQuantity() - saleQuantity;
        Stock newStock = stockRepository.findById(stockEntrance.getId()).get();
        newStock.updateStock(updatedStock);
        return updatedStock;
    }

    public void registerSaleStockControl(SaleProduct registeredSaleProduct, SaleRegisteredDetailsDto registeredSale, SaleProductRegisterDto product) {
        LocalDateTime date = registeredSale.date();
        Float quantity;
        Type type = Type.valueOf("SELL");
        SaleProduct saleProduct = saleProductRepository.findById(registeredSaleProduct.getId()).get();
        User user = userRepository.getReferenceById(registeredSale.userId().getId());
        //Stock stock = stockRepository.findById(saleProduct.getId()).get();

        Product productId = productRepository.findById(product.productId()).get();
        List<Stock> productStockList = getStockByProductId(productId);
        productStockList.sort(Comparator.comparing(Stock::getValidity));
        Float productSaleQuantity = product.quantity();
        for (Stock item : productStockList){

            if (item.getValidity().isBefore(LocalDateTime.now()) || item.getQuantity() == 0) {
                continue;
            }
            else{
                if (item.getQuantity() > productSaleQuantity){
                    stockQuantityUpdateSale(item, productSaleQuantity);

                    StockControl registeredStockControl = new StockControl(date, productSaleQuantity, type, saleProduct, user, item);
                    stockControlRepository.save(registeredStockControl);

                    break;
                }
                else{
                    productSaleQuantity -= item.getQuantity();
                    stockQuantityUpdateSale(item, item.getQuantity());

                    StockControl registeredStockControl = new StockControl(date, item.getQuantity(), type, saleProduct, user, item);
                    stockControlRepository.save(registeredStockControl);

                    if (productSaleQuantity == 0){
                        break;
                    }
                }
            }
        }
        //stockUpdateOnSale(product);
        //StockControl registeredStockControl = new StockControl(date, quantity, type, saleProduct, user, stock);
        //stockControlRepository.save(registeredStockControl);

    }

    public SaleRegisteredDetailsDto realizeCompleteSale(SaleCompleteRegisterDto newSaleInput) {
        emptyProductListOnCompleteSale(newSaleInput);
        findProductsCompleteSale(newSaleInput);
        float saleAmount = getSaleAmount(newSaleInput);
        SaleRegisteredDetailsDto registeredSale = newSale(getSaleInput(newSaleInput), saleAmount);
        registerCompleteSaleProductItem(newSaleInput, registeredSale);

        return new SaleRegisteredDetailsDto(registeredSale);
    }

/*
    public SaleCompleteReciptDTO generateReceipt(SaleRegisteredDetailsDto newCompleteSale, SaleCompleteRegisterDto newSaleInput){
        List<SaleProductRegisterDto> products = newSaleInput.products();
        int usedPoints = 0;
        float finalAmount = newCompleteSale.amount();
        int pointsGenerated = 0;


        if (newSaleInput.loyalty_points() != null){
            usedPoints = newSaleInput.loyalty_points();
            if (usedPoints == 250){
                finalAmount -= 5;
            } else if (usedPoints == 500) {
                finalAmount -= 15;
            } else if (usedPoints == 1000) {
                finalAmount -= 40;
            }
        }
        if (newSaleInput.dealId() != null){
            finalAmount = (float) (finalAmount *0.9);
        }
        if (newSaleInput.costumerId() != null){
            pointsGenerated = (int) finalAmount *5;
        }

        SaleCompleteReciptDTO receipt = new SaleCompleteReciptDTO
                (
                newCompleteSale.id(),
                newCompleteSale.date(),
                newCompleteSale.amount(),
                newCompleteSale.invoiceNumber(),
                newCompleteSale.paymentMethodId(),
                newCompleteSale.costumerId(),
                newCompleteSale.userId(),
                newCompleteSale.dealId(),
                newCompleteSale.refound(),
                products,
                usedPoints,
                finalAmount,
                pointsGenerated
                );

        return receipt;
    }
*/

    public SaleReceiptDto generateReceipt(SaleRegisteredDetailsDto newCompleteSale, SaleCompleteRegisterDto newSaleInput){
        int usedPoints = newSaleInput.loyaltyPoints();
        int pointsGenerated = generateLoyaltyPoints(newCompleteSale.amount());
        List<SaleProductRegisterDto> products = newSaleInput.products();

        PaymentMethod paymentMethod = paymentMethodRepository.findById(newCompleteSale.paymentMethodId().getId()).get();
        User user = userRepository.findById(newCompleteSale.userId().getId()).get();


        Deal deal = null;
        if (newCompleteSale.dealId() != null){
            deal = dealRepository.findById(newCompleteSale.dealId().getId()).get();
        }
        Costumer costumer = null;
        if (newCompleteSale.costumerId() != null){
            costumer = costumerRepository.findById(newCompleteSale.costumerId().getId()).get();
        }

        SaleReceiptDto receipt = new SaleReceiptDto(
                newCompleteSale.id(),
                newCompleteSale.date(),
                newCompleteSale.amount(),
                newCompleteSale.invoiceNumber(),
                paymentMethod,
                costumer,
                user,
                deal,
                newCompleteSale.refound(),
                usedPoints,
                pointsGenerated,
                products);//TODO: finalizar recibo

        return receipt;
    }

    public void emptyProductListOnCompleteSale(SaleCompleteRegisterDto newSaleInput){
        if (newSaleInput.getProducts() == null || newSaleInput.getProducts().isEmpty()) {
            throw new GeneralException("Product list can not be null or empty");
        }
    }

    public SaleRegisterDto getSaleInput (SaleCompleteRegisterDto newSaleInput) {
        return new SaleRegisterDto(newSaleInput.date(),
                newSaleInput.paymentMethodId(),
                newSaleInput.costumerId(),
                newSaleInput.userId(),
                newSaleInput.dealId(),
                newSaleInput.refound(),
                newSaleInput.loyaltyPoints());


    }

    public List<Stock> getStockByProductId(Product productId) {
        return stockRepository.findByProductId(productId);
    }

    public float getSaleAmount(SaleCompleteRegisterDto newSaleInput){
        float totalAmount = 0;

        for (SaleProductRegisterDto product : newSaleInput.products()){
            Float price = productRepository.findById(product.productId()).get().getPrice();
            totalAmount += price* product.quantity();
        }
        return totalAmount;
    }
    
    public float getSaleAmountAfterPoints(int points, float amount){
        if (points == 250){
            amount -= 5;
        } else if (points == 500) {
            amount -= 15;
        } else if (points == 1000) {
            amount -= 40;
        }
        return amount;
    }

    public int generateLoyaltyPoints(float saleAmount){
        int generatedPoints = (int) saleAmount*5;

        return generatedPoints;
    }

    public float calculatingInvoicValueAfterDeal (float amount){
        amount = (float) (amount*0.9);

        return amount;
    }

}
