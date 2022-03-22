package com.restaurant;

import java.util.ArrayList;

public class Line {

    public ArrayList<Order> queue;
    public int tail;
    public int totalWaitTime;

    public Line(){
        queue = new ArrayList<>();
        tail = 0;
        totalWaitTime = 0;
    }

    public void addOrder(Customer user){
        if (user == null) return;

        totalWaitTime = 0;
        for (Order order : queue) {
            totalWaitTime += order.waitTime;
        }

        tail++;
        Order newOrder = new Order(user, tail, totalWaitTime);
        newOrder.waitTime += Order.calcWaitTime(newOrder);

        queue.add(newOrder);
    }

    public void removeOrder(){
        if (queue.isEmpty()) return;
        Order finishedOrder = queue.remove(0);
        finishedOrder.completed = true;
        finishedOrder.resetCart();
    }

}
