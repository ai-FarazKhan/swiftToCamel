package com.faraz.camel.swiftToCamel;

import com.prowidesoftware.swift.model.mt.AbstractMT;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;


@Component
public class RouterNew extends RouteBuilder {
    @Override
    public void configure() throws Exception {

//        restConfiguration().component("servlet").host("0.0.0.0").port(8081).bindingMode(RestBindingMode.json).enableCORS(true);
//        rest().path("/helloWorld").get().route()
//        rest("/someRoute")
//                .id("someRoute")
//                .description("Some description")
//                .post("/testing");


        from("file:C:/Users/Dell/Desktop/Folder One")
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        Message input = exchange.getIn();
                        // reading body as a string from input message
                        String data = input.getBody(String.class);

                        AbstractMT msg = AbstractMT.parse(data);
                        String modifiedData = "{sender: "+msg.getSender()+", type: "+msg.getMessageType()+"}";

                        Message output = exchange.getMessage();
                        output.setBody(modifiedData);

                    }

                })
                .to("file:C:/Users/Dell/Desktop/Folder Two?fileName=MT103.json");

    }

}
