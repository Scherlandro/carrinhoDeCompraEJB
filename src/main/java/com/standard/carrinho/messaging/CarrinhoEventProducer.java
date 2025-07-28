
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
        try {
            Connection connection = connectionFactory.createConnection();
               try {
                Session session =connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
                MessageProducer messageProducer = session.createProducer(queue);
                TextMessage textMessage = session.createTextMessage(queue.toString());
                messageProducer.send(textMessage);

            } finally {
                connection.close();
            }
        } catch (JMSException ex) {
           
        }
    }
}
