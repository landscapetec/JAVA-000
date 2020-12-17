package io.kimmking.rpcfx.demo.provider;

import io.kimmking.rpcfx.api.RpcfxResponseStatus;
import io.kimmking.rpcfx.demo.api.Order;
import io.kimmking.rpcfx.demo.api.OrderService;
import io.kimmking.rpcfx.exception.RpcfxException;

public class OrderServiceImpl implements OrderService {

    @Override
    public Order findOrderById(int id)  {
        if (id % 2 == 0){
            return new Order(id, "Cuijing" + System.currentTimeMillis(), 9.9f);}
        else{
            throw new RuntimeException("模拟校验校验异常");
        }
    }
}
