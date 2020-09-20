package com.cooldatasoft.camel;

import com.cooldatasoft.data.Book;
import com.cooldatasoft.entity.BookEntity;
import lombok.Data;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.apache.camel.model.rest.RestParamType.body;
import static org.apache.camel.model.rest.RestParamType.path;

@Component
public class CamelRouteBuilder extends RouteBuilder {

    @Autowired
    private Environment env;

    @Value("${camel.component.servlet.mapping.context-path}")
    private String contextPath;

    @Override
    public void configure() {

        restConfiguration()
                .component("servlet")
                .bindingMode(RestBindingMode.json)
                .dataFormatProperty("prettyPrint", "true")
                .enableCORS(true)
                .apiContextPath("/api-doc")
                .contextPath(contextPath.substring(0, contextPath.length() - 2))
                .port(env.getProperty("server.port", "8080"))
                .apiProperty("api.title", "Camel Rest API")
                .apiProperty("api.version", "1.0.0");


        // General error handler
        onException(RuntimeException.class, Exception.class, NullPointerException.class)
                .handled(true) // NOT send the exception back to the client (clients need a meaningful response)
                // use HTTP status 500 when we had a server side error
                .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(500))
                .setBody(simple(" { \"messageId\": \"${id}\", \"timestamp\": \"${date:now:dd/MMM/yyyy:HH:mm:ss Z}\", \"description\": \"Report this messageId for escalation\" }"))
                .log("messageId: ${id}, timestamp: \"${date:now:dd/MMM/yyyy:HH:mm:ss Z}\", message: \"${exception.message}\"\n ${exception.stacktrace}")
                .end();


        //consume rest endpoint and make it available as rest endpoint

        @Data
        class Deneme {
            private int code;
            private List<Country> result;
            private List extra;

            @Data
            class Country {
                private String name;
                private String code;
                private String states;
            }
        }

        from("rest://get:countries")
//                .to("json-validator:be/country-schema.json")
                .to("https://api.printful.com/countries?bridgeEndpoint=true");



        rest("/books").consumes("application/json").produces("application/json")
                .get()
                .type(BookEntity[].class)
                .outType(Book[].class)
                .responseMessage().code(200).endResponseMessage()
                .to("bean:bookService?method=findBooks")

                .get("/{id}")
                .outType(Book.class)
                .param().name("id").type(path).dataType("string").endParam()
                .responseMessage().code(200).message("Book successfully returned").endResponseMessage()
                .to("bean:bookService?method=findBook(${header.id})")


                .put("/{id}")
                .type(Book.class)
                .param().name("id").type(path).dataType("string").endParam()
                .param().name("body").type(body).description("The book to update").endParam()
                .responseMessage().code(204).message("Book successfully updated").endResponseMessage()
                .to("direct:update-book")

                .post()
                .outType(Book.class)
                .type(Book.class)
                .param().name("body").type(body).endParam()
                .responseMessage().code(201).message("Book successfully created").endResponseMessage()
                .to("direct:book-create");


        from("direct:update-book")
                .to("bean:bookService?method=updateBook")
                .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(204))
                .setBody(constant(""));


        from("direct:book-create")
                .log("********************************1****** ${body}")
                .process(exchange -> {
                    Book book = exchange.getIn().getBody(Book.class);
                    exchange.getIn().setBody(new BookEntity(book));
                })
//                .marshal().json()
//                .log("************************** ${body}")
//                .to("json-validator:be/book-schema.json")
                .to("bean:bookService?method=createBook")
                .convertBodyTo(Book.class);


    }

}
