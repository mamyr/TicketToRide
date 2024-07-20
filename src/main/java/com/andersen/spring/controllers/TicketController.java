package com.andersen.spring.controllers;

import com.andersen.hibernate.Models.City;
import com.andersen.hibernate.Models.Route;
import com.andersen.hibernate.Models.Traveller;
import com.andersen.spring.dtos.FailureOutputForTicket;
import com.andersen.spring.dtos.InputForRoute;
import com.andersen.spring.dtos.InputForTicket;
import com.andersen.spring.dtos.SuccessOutputForTicket;
import com.andersen.spring.services.CityDaoImpl;
import com.andersen.spring.services.RouteDaoImpl;
import com.andersen.spring.services.TicketDaoImpl;
import com.andersen.hibernate.Models.Ticket;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Stream;

/**
 *Implementation of the controller for the {@code Ticket} class from the package {@link com.andersen.hibernate.Models.Ticket}.
 *In Spring’s approach for building RESTful web services, HTTP requests are handled by a controller. The {@code @RestController} is used for annotation of a controller.
 *
 * <p>The {@code @CrossOrigin} annotation from {@code springframework} package is used to enable cross-origin resource sharing.
 * The {@code origins} attribute specifies url to send cross-origin requests.
 * It is allowed for {@code "http://localhost:8080"} to send cross-origin requests.
 *
 * <p>The {@code @RequestMapping} annotaion ensures that HTTP requests to {@code /api} are mapped to the controller {@code TicketController}.
 *
 * <p>The {@code saveTicket}, {@code setCityTemplate}, {@code setRouteTemplate} operations run in O(1) time.  The {@code TicketController} constructor operation runs in constant time.
 *
 * <p>For implementation of the operation in controller, it is used {@code TicketDaoImpl} from the package {@link com.andersen.spring.services.TicketDaoImpl}.
 * The field {@code ticketDao} is connected with the bean from application by annotation {@code @Autowired} and is of type {@code TicketDaoImpl}.
 *
 * <p>For implementation of the operation in controller, it is used {@code RouteDaoImpl} from the package {@link com.andersen.spring.services.RouteDaoImpl}.
 * The field {@code routeDao} is connected with the bean from application by annotation {@code @Autowired} and is of type {@code RouteDaoImpl}.
 *
 * <p>For implementation of the operation in controller, it is used {@code CityDaoImpl} from the package {@link com.andersen.spring.services.CityDaoImpl}.
 * The field {@code cityDao} is connected with the bean from application by annotation {@code @Autowired} and is of type {@code CityDaoImpl}.
 *
 * <p><strong>Note that this implementation is not final.</strong>
 *
 * <p>This class is a member of the
 * <a href="{@docRoot}/com/andersen/spring/controllers/package-info.html">TicketController</a>.
 *
 * @author  Zhanat Kopbayeva
 * @see     Ticket
 * @see     TicketDaoImpl
 * @since   1.0-SNAPSHOT
 */
@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class TicketController {
    /**
     * The {@code Logger} is used for message logging by default on console.
     */
    private static final Logger logger = Logger.getLogger(String.valueOf(TicketController.class));

    @Autowired
    TicketDaoImpl ticketDao;
    @Autowired
    RouteDaoImpl routeDao;
    @Autowired
    CityDaoImpl cityDao;

    /**
     * The constructor is initialised with bean {@code ticketDao} from application context.
     * @param ticketDao
     */
    public TicketController(TicketDaoImpl ticketDao){
        this.ticketDao = ticketDao;
    }

    /**
     * The method {@code setCityTemplate} is used for explicit setting of bean {@code cityDao} from application context.
     * @param cityDao
     */
    public void setCityTemplate(CityDaoImpl cityDao) {
        this.cityDao = cityDao;
    }

    /**
     * The method {@code setRouteTemplate} is used for explicit setting of bean {@code routeDao} from application context.
     * @param routeDao
     */
    public void setRouteTemplate(RouteDaoImpl routeDao) {
        this.routeDao = routeDao;
    }

    /**
     * The {@code @RequestMapping} annotation ensures that HTTP requests to {@code /ticket/**} are mapped to the saveTicket() method.
     * The attribute {@code method} specifies HTTP method to map. The operation {@code saveTicket()} uses HTTP {@code RequestMethod.PUT}.
     * From {@code HttpServletRequest} json object is read into object {@code InputForTicket} by using {@code ObjectMapper} from package {@link com.fasterxml.jackson.databind.ObjectMapper}.
     * The operation returns {@code @ResponseBody} which includes json object associated with data transform object {@code FailureOutputForTicket}, {@code SuccessOutputForTicket} from package {@link com.andersen.spring.dtos}.
     * The {@code Traveller} object is not saved, but used for logical mapping.
     * If Traveller has enough money to pay the ticket price the method returns {@code SuccessOutputForTicket}.
     * If Traveller doesn't have enough money to pay the ticket price the method returns {@code FailureOutputForTicket}.
     * For getting response for ticket correctly the path should contain /username followed by /password.
     * If the path is specified with wrong username or password, the reply would be just one json string to correct the input.
     * If the path is specified with wrong request path then 404 HTTP response is returned, which says this request path NOT FOUND.
     *
     * @param userName
     * @param password
     * @param request
     * @param response
     */
    @RequestMapping(value = "/ticket/{username}/{password}", method = RequestMethod.PUT)
    public @ResponseBody void saveTicket(
            @PathVariable("username") String userName,
            @PathVariable("password") String password,
            HttpServletRequest request,
            HttpServletResponse response) {

         response.setContentType("application/json");
         try {
             StringBuffer sb = new StringBuffer();
             BufferedReader bufferedReader = request.getReader();

             Stream<String> inputTicket = bufferedReader.lines();
             inputTicket.forEach(sb::append);

             ObjectMapper objectMapper = new ObjectMapper();

             String inputTicketObject = sb.toString();
             InputForTicket ticketInput= new ObjectMapper().readValue(inputTicketObject, InputForTicket.class);

             InputForRoute cityNames = new InputForRoute();
             cityNames.setArrival(ticketInput.getArrival());
             cityNames.setDeparture(ticketInput.getDeparture());

             ArrayList<City> cityIds = cityDao.getCityIdListByNameList(cityNames);

             Route routeToSaveInTicket = routeDao.findRouteByCities(cityIds);
             //Not saving traveller, for checking amount and logic
             Traveller traveller = new Traveller();
             traveller.setName(ticketInput.getTraveller());
             traveller.setMoneyAmount(ticketInput.getTravellerAmount());

             boolean allowPayment = ticketInput.getPrice() <= traveller.getMoneyAmount();
             int moneyLeft = traveller.getMoneyAmount() - ticketInput.getPrice();

             String ouputAsString;
             if(allowPayment){
                //ticket must be saved
                 Ticket ticketToSave = new Ticket();
                 ticketToSave.setRoute(routeToSaveInTicket);
                 ticketToSave.setTraveller(traveller);
                 ticketToSave.setPrice(ticketInput.getPrice());
                 ticketToSave = ticketDao.save(ticketToSave);

                 if(ticketToSave.getId() == null){
                     FailureOutputForTicket failure = new FailureOutputForTicket();
                     failure.setCurrency(ticketInput.getCurrency());
                     failure.setResult("Problem saving individual ticket.");
                     failure.setLackOf(0);
                     ouputAsString = objectMapper.writeValueAsString(failure);
                 } else {
                     SuccessOutputForTicket success = new SuccessOutputForTicket();
                     success.setResult("success");
                     success.setChange(moneyLeft);
                     success.setCurrency(ticketInput.getCurrency());
                     ouputAsString = objectMapper.writeValueAsString(success);
                 }
             } else {
                 FailureOutputForTicket failure = new FailureOutputForTicket();
                 failure.setCurrency(ticketInput.getCurrency());
                 failure.setResult("failure");
                 failure.setLackOf(-moneyLeft);
                 ouputAsString = objectMapper.writeValueAsString(failure);
             }
             if(userName.equals("user") && password.equals("password")){
                 response.getOutputStream().write(ouputAsString.getBytes(StandardCharsets.UTF_8));
                 response.getOutputStream().close();
             } else {
                 String jsonString = "{\"error\":\"please provide username and password\"}";
                 response.getOutputStream().write(jsonString.getBytes(StandardCharsets.UTF_8));
                 response.getOutputStream().close();
             }
         } catch (Exception exception){
             logger.severe(exception.getMessage());
         }

    }
}
