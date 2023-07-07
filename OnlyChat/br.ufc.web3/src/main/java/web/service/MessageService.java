package web.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.model.Message;
import web.model.User;
import web.repository.MessageRepository;
import web.repository.UserRepository;

@Service
public class MessageService {
    @Autowired
    private final MessageRepository messageRepository;
    @Autowired
    private final UserRepository userRepository;

    public MessageService(MessageRepository messageRepository, UserRepository userRepository) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
    }

    public List<Message> getMessages(Long senderId, Long receiverId) {
        Optional<User> sender = userRepository.findById(senderId);
        Optional<User> receiver = userRepository.findById(receiverId);

        if (sender.isPresent() && receiver.isPresent()) {
            return messageRepository.findBySenderAndReceiverOrderByCreatedAtDesc(sender.get(), receiver.get());
        } else {
            throw new IllegalArgumentException("Remetente ou destinatário inválido");
        }
    }

    public Message createMessage(Long senderId, Long receiverId, Message message) {
        Optional<User> sender = userRepository.findById(senderId);
        Optional<User> receiver = userRepository.findById(receiverId);

        if (sender.isPresent() && receiver.isPresent()) {
            message.setSender(sender.get());
            message.setReceiver(receiver.get());

            return messageRepository.save(message);
        } else {
            throw new IllegalArgumentException("Remetente ou destinatário inválido");
        }
    }
}
