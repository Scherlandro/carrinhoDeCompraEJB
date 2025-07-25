
package com.standard.carrinho.messaging;



import com.standard.carrinho.domain.model.Carrinho;
import jakarta.annotation.Resource;
import jakarta.ejb.Singleton;
import javax.jms.*;

@Singleton
public class CarrinhoEventProducer {

    @Resource(lookup = "java:/ConnectionFactory")
    private ConnectionFactory connectionFactory;

    @Resource(lookup = "java:/jms/queue/carrinho")
    private Queue queue;

    public void notificarCriacao(Carrinho c) {
        try (JMSContext context = connectionFactory.createContext()) {
            ObjectMessage msg = context.createObjectMessage(c.getId());
            context.createProducer().send(queue, msg);
        }
    }
}
