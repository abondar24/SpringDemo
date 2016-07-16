package org.abondar.experimental.springbase.HelloWorld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Created by abondar on 03.07.16.
 */
@Service("messageRenderer")
public class StandardOutputMessageRenderer implements MessageRenderer {
    private MessageProvider messageProvider;


    @Override
    public void render() {
       if (messageProvider==null){
           throw new RuntimeException(
                   "Set property messageProvider of class" +StandardOutputMessageRenderer.class.getName()
           );
       }
        System.out.println(messageProvider.getMessage());
    }

    //setter injection
    @Override
    @Autowired
    @Qualifier("messageProvider")
    public void setMessageProvider(MessageProvider provider) {
      messageProvider = provider;
    }

    @Override
    public MessageProvider getMessageProvider() {
        return messageProvider;
    }
}
