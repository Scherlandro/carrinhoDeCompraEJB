
package com.standard.carrinho.messaging;


import jakarta.ejb.MessageDriven;
import jakarta.resource.ResourceException;
import jakarta.resource.cci.MessageListener;
import jakarta.resource.cci.Record;
import org.apache.logging.log4j.message.ObjectMessage;

@MessageDriven(mappedName = "java:/jms/queue/carrinho")
public class CarrinhoListener implements MessageListener {


    @Override
    public Record onMessage(Record record) throws ResourceException {

        try {
            if (record instanceof ObjectMessage om) {
                String id = (String) om.getParameter();//om.getObject();
                System.out.println("Novo carrinho criado: " + id);
            }
        } catch (Exception e) { //(JMSException e)
            e.printStackTrace();
        }
        return null;
    }
}
