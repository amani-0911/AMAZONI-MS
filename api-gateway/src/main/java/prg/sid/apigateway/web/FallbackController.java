package prg.sid.apigateway.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class FallbackController {
    @RequestMapping("/orderFallback")
    public Mono<String> orderServiceFallback(){
        return Mono.just("order service is taking too long respond or is down. Please try again later");
    }
    @RequestMapping("/paymentFallback")
    public Mono<String> paymentServiceFallback(){
        return Mono.just("payment service is taking too long respond or is down. Please try again later");
    }
}
