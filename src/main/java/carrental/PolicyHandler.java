package carrental;

import carrental.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class PolicyHandler{
    @Autowired
    CommentRepository commentRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void onStringEventListener(@Payload String eventString){

    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverPaid_CommentUpdate(@Payload Paid paid){



        System.out.println("######################################### kakfa Comment" );

        if(paid.isMe() && "PAID".equals(paid.getProcStatus())  ){

            System.out.println("##### DesUpdate Logic Start " );

            Comment comment = new Comment();
            comment.setId(paid.getId());
            comment.setCarNo(paid.getCarNo());
            comment.setResrvNo(paid.getResrvNo());

            commentRepository.save(comment);

            System.out.println("##### listener DesUpdate : " + paid.toJson());



            System.out.println("##### listener CommentUpdate : " + paid.toJson());
        }






    }

}
