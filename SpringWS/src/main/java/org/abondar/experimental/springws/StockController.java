package org.abondar.experimental.springws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Controller;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by abondar on 23.07.16.
 */

@Controller
public class StockController {

    @Autowired
    @Qualifier("messageBrokerTaskScheduler")
    private TaskScheduler taskScheduler;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    private List<Stock> stocks = new ArrayList<>();
    private Random random = new Random(System.currentTimeMillis());

    public StockController(){
        stocks.add(new Stock("VMW",1.00d));
        stocks.add(new Stock("EMC",1.00d));
        stocks.add(new Stock("GOOG",1.00d));
        stocks.add(new Stock("IBM",1.00d));

    }

    @MessageMapping("/addStock")
    public void addStock(Stock stock)throws Exception{
        stocks.add(stock);
        broadcastUpdatedPrices();
    }


    private void broadcastUpdatedPrices(){

        for (Stock stock:stocks){
            stock.setPrice(stock.getPrice() + (gerUpdatedStockPrice() * stock.getPrice()));
            stock.setDate(new Date());
        }
        simpMessagingTemplate.convertAndSend("/topic/price",stocks);
    }


    private double gerUpdatedStockPrice(){
        double priceChange = random.nextDouble() * 5.0;

        if (random.nextInt(2)==1){
            priceChange = -priceChange;
        }

        return priceChange/100.0;
    }

    @PostConstruct
    private void broadcastTimePeriodicallly(){
        taskScheduler.scheduleAtFixedRate(() -> broadcastUpdatedPrices(),1000);
    }

}
