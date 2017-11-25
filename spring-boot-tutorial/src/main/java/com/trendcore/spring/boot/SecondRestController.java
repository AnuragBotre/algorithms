package com.trendcore.spring.boot;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Anurag
 */
@RestController
@RequestMapping("/secondrest")
public class SecondRestController {

    @RequestMapping("/test")
    @ResponseBody
    public String sayHello() {
        return "hello";
    }


    @RequestMapping(method = RequestMethod.POST, path = "/test/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
            )
    public ResponseEntity<SampleBean> produceConsume(@PathVariable Integer id) {


        return new ResponseEntity<SampleBean>(new SampleBean(), HttpStatus.OK);
    }

    @RequestMapping("/exception")
    public String testException() throws Exception{
        throw new Exception();


    }
}
