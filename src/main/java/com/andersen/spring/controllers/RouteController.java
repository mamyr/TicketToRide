package com.andersen.spring.controllers;

import com.andersen.hibernate.Models.City;
import com.andersen.hibernate.Models.Route;
import com.andersen.spring.dtos.InputForRoute;
import com.andersen.spring.dtos.OutputForRoute;
import com.andersen.spring.services.CityDaoImpl;
import com.andersen.spring.services.RouteDaoImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.logging.Logger;
import java.util.stream.Stream;

/**
 *Implementation of the controller for the {@code Route} class from the package {@link com.andersen.hibernate.Models.Route}.
 *In Spring’s approach for building RESTful web services, HTTP requests are handled by a controller. The {@code @RestController} is used for annotation of a controller.
 *
 * <p>The {@code @CrossOrigin} annotation from {@code springframework} package is used to enable cross-origin resource sharing.
 * The {@code origins} attribute specifies url to send cross-origin requests.
 * It is allowed for {@code "http://localhost:8080"} to send cross-origin requests.
 *
 * <p>The {@code @RequestMapping} annotaion ensures that HTTP requests to {@code /api} are mapped to the controller {@code RouteController}.
 *
 * <p>The {@code generateReport}, {@code setCityTemplate} operations run in O(1) time.  The {@code RouteController} constructor operation runs in constant time.
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
 * <a href="{@docRoot}/com/andersen/spring/controllers/package-info.html">RouteController</a>.
 *
 * @author  Zhanat Kopbayeva
 * @see     Route
 * @see     RouteDaoImpl
 * @since   1.0-SNAPSHOT
 */
@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class RouteController {
    /**
     * The {@code Logger} is used for message logging by default on console.
     */
    private static final Logger logger = Logger.getLogger(String.valueOf(RouteController.class));

    @Autowired
    RouteDaoImpl routeDao;
    @Autowired
    CityDaoImpl cityDao;

    /**
     * The constructor is initialised with bean {@code routeDao} from application context.
     * @param routeDao
     */
    public RouteController(RouteDaoImpl routeDao){
        this.routeDao = routeDao;
    }

    /**
     * The method {@code setCityTemplate} is used for explicit setting of bean {@code cityDao} from application context.
     * @param cityDao
     */
    public void setCityTemplate(CityDaoImpl cityDao) {
        this.cityDao = cityDao;
    }

    /**
     * The {@code @RequestMapping} annotation ensures that HTTP requests to {@code /route} are mapped to the generateReport() method.
     * The attribute {@code method} specifies HTTP method to map. The operation {@code generateReport()} uses HTTP {@code RequestMethod.GET}.
     * From {@code HttpServletRequest} json object is read into object {@code InputForRoute} by using {@code ObjectMapper} from package {@link com.fasterxml.jackson.databind.ObjectMapper}.
     * The operation returns {@code @ResponseBody} which includes json object associated with data transform object {@code OutputForRoute} from package {@link com.andersen.spring.dtos.OutputForRoute}.
     * @param request
     * @param response
     */
    @RequestMapping(value = "/route", method = RequestMethod.GET)
    public @ResponseBody void generateReport(
            HttpServletRequest request,
            HttpServletResponse response) {

        response.setContentType("application/json");
        try {
            StringBuffer sb = new StringBuffer();
            BufferedReader bufferedReader = request.getReader();

            Stream<String> inputCities = bufferedReader.lines();
            inputCities.forEach(sb::append);

            ObjectMapper objectMapper = new ObjectMapper();

            InputForRoute cities= new ObjectMapper().readValue(sb.toString(), InputForRoute.class);

            ArrayList<City> cityIds = cityDao.getCityIdListByNameList(cities);
            Route foundRoute = routeDao.findRouteByCities(cityIds);

            OutputForRoute output = new OutputForRoute();
            output.setPrice(foundRoute.getPrice());
            output.setCurrency(foundRoute.getCurrency());
            output.setSegments(foundRoute.getBoundryAmount());

            String routeAsString = objectMapper.writeValueAsString(output);

            response.getOutputStream().write(routeAsString.getBytes(StandardCharsets.UTF_8));
            response.getOutputStream().close();
        } catch (Exception exception){
            logger.severe(exception.getMessage());
        }
    }

}
