package io.kimmking.rpcfx.demo.consumer;

import io.kimmking.rpcfx.client.RpcfxClient;
import io.kimmking.rpcfx.client.RpcfxClientByteBuddy;
import io.kimmking.rpcfx.demo.api.Order;
import io.kimmking.rpcfx.demo.api.OrderService;
import io.kimmking.rpcfx.demo.api.User;
import io.kimmking.rpcfx.demo.api.UserService;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.lang.reflect.InvocationTargetException;

@Aspect
@SpringBootApplication
public class RpcfxClientApplication {

    // 二方库
    // 三方库 lib
    // nexus, userserivce -> userdao -> user
    //

    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {

        // UserService service = new xxx();
        // service.findById

        UserService userService = RpcfxClientByteBuddy.create(UserService.class, "http://localhost:9200/");
        User user = userService.findById(1);
        System.out.println("find user id=1 from server: " + user.getName());

        OrderService orderService = RpcfxClient.create(OrderService.class, "http://localhost:9200/");

        try {
            Order order2 = orderService.findOrderById(1992120);
            System.out.println(String.format("find order name=%s, amount=%f", order2.getName(), order2.getAmount()));

            Order order1 = orderService.findOrderById(1992129);
            if (order1 != null) {
                System.out.println(String.format("find order name=%s, amount=%f", order1.getName(), order1.getAmount()));
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }


        // 新加一个OrderService

//		SpringApplication.run(RpcfxClientApplication.class, args);
    }

}
