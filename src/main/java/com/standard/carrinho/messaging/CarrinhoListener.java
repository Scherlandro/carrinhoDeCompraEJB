
package com.standard.carrinho.messaging;

import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

@MessageDriven(mappedName = "java:/jms/queue/carrinho")
public class CarrinhoListener implements MessageListener {

    @Override
    public void onMessage(Message message) {
        try {
            if (message instanceof ObjectMessage om) {
                String id = (String) om.getObject();
                System.out.println("Novo carrinho criado: " + id);
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
